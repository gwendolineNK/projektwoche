import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Schatzsuche". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
public class UnitTests {

    /** Hilfsboard 3x3 mit genau einem Schatz in der Mitte (1,1). */
    private Spielfeld mitteBoard() {
        Spielfeld f = new Spielfeld(3);
        boolean[][] karte = new boolean[3][3];
        karte[1][1] = true;
        f.setSchatzkarteFuerTest(karte);
        return f;
    }

    private int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == c) n++;
        return n;
    }

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex", 1);
        assertEquals("Alex", s.getName());
        assertEquals(1, s.getSpielernummer());
        assertEquals(0, s.getPunkte());
    }

    @Test
    void testPunktDazu() {
        Spieler s = new Spieler("Alex", 1);
        s.punktDazu();
        s.punktDazu();
        assertEquals(2, s.getPunkte());
    }

    // ---------- Spielfeld: Grundlagen ----------

    @Test
    void testGetN() {
        assertEquals(5, new Spielfeld(5).getN());
    }

    @Test
    void testAnzahlSchaetze() {
        // Genau n Schaetze bei einem n x n Feld.
        for (int n : new int[] { 3, 4, 6 }) {
            Spielfeld f = new Spielfeld(n);
            int count = 0;
            for (int z = 0; z < n; z++)
                for (int s = 0; s < n; s++)
                    if (f.getSchatzkarte()[z][s]) count++;
            assertEquals(n, count, "Es muessen genau n Schaetze sein (n=" + n + ").");
        }
    }

    @Test
    void testTreffer() {
        Spielfeld f = mitteBoard();
        assertTrue(f.treffer(1, 1));
        assertFalse(f.treffer(0, 0));
    }

    @Test
    void testGueltigeEingabe() {
        Spielfeld f = mitteBoard();
        assertTrue(f.gueltigeEingabe(0, 0));
        assertFalse(f.gueltigeEingabe(-1, 0));
        assertFalse(f.gueltigeEingabe(3, 0));
        assertFalse(f.gueltigeEingabe(0, 3));
        f.deckeAuf(0, 0, new Spieler("A", 1));
        assertFalse(f.gueltigeEingabe(0, 0), "Aufgedeckte Stelle ist nicht mehr gueltig.");
    }

    @Test
    void testAngrenzend() {
        Spielfeld f = mitteBoard();
        assertTrue(f.angrenzend(0, 1));   // ueber (1,1)
        assertTrue(f.angrenzend(1, 0));   // links von (1,1)
        assertFalse(f.angrenzend(0, 0));  // nur diagonal -> zaehlt nicht
        assertFalse(f.angrenzend(1, 1));  // die Stelle selbst zaehlt nicht
    }

    // ---------- Spielfeld: deckeAuf ----------

    @Test
    void testDeckeAufTreffer() {
        Spielfeld f = mitteBoard();
        Spieler s1 = new Spieler("Alex", 1);
        assertEquals(Spielfeld.TREFFER, f.deckeAuf(1, 1, s1));
        assertEquals(1, f.getOffenesSpielfeld()[1][1]);
    }

    @Test
    void testDeckeAufNah() {
        Spielfeld f = mitteBoard();
        assertEquals(Spielfeld.NAH, f.deckeAuf(0, 1, new Spieler("A", 1)));
        assertEquals(-2, f.getOffenesSpielfeld()[0][1]);
    }

    @Test
    void testDeckeAufFern() {
        Spielfeld f = mitteBoard();
        assertEquals(Spielfeld.FERN, f.deckeAuf(0, 0, new Spieler("A", 1)));
        assertEquals(-1, f.getOffenesSpielfeld()[0][0]);
    }

    // ---------- Spielfeld: toString ----------

    @Test
    void testToStringVerdeckt() {
        Spielfeld f = new Spielfeld(4);
        assertEquals(16, zaehle(f.toString(), '_'));
    }

    @Test
    void testToStringMitSchatz() {
        Spielfeld f = mitteBoard();
        f.deckeAuf(1, 1, new Spieler("Alex", 1));
        assertTrue(f.toString().indexOf("S1") >= 0);
    }

    // ---------- Spiel ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Spiel.checkEingabeMenu(1));
        assertTrue(Spiel.checkEingabeMenu(2));
        assertFalse(Spiel.checkEingabeMenu(0));
        assertFalse(Spiel.checkEingabeMenu(3));
    }

    @Test
    void testWeiterspielen() {
        Spieler s1 = new Spieler("A", 1);
        Spieler s2 = new Spieler("B", 2);
        s1.punktDazu();               // zusammen 1 gefunden
        assertTrue(Spiel.weiterspielen(s1, s2, 4));
        s2.punktDazu(); s2.punktDazu(); s2.punktDazu();   // zusammen 4
        assertFalse(Spiel.weiterspielen(s1, s2, 4));
    }

    @Test
    void testAuswertung() {
        Spieler s1 = new Spieler("Alex", 1);
        Spieler s2 = new Spieler("Mika", 2);
        s1.punktDazu(); s1.punktDazu();
        s2.punktDazu();
        String e = Spiel.auswertung(s1, s2);
        assertTrue(e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0);

        Spieler g1 = new Spieler("A", 1);
        Spieler g2 = new Spieler("B", 2);
        g1.punktDazu(); g2.punktDazu();
        assertTrue(Spiel.auswertung(g1, g2).indexOf("Unentschieden") >= 0);
    }
}