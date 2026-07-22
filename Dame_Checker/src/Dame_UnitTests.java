import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Dame (vereinfacht)". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /** Leeres 8x8-Feld fuer die Tests. */
    private Spielfeld leer() {
        Spielfeld f = new Spielfeld();
        f.setFeldFuerTest(new int[8][8]);
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
    }

    // ---------- Grundlagen ----------

    @Test
    void testGegner() {
        Spielfeld f = new Spielfeld();
        assertEquals(2, f.gegner(1));
        assertEquals(1, f.gegner(2));
    }

    @Test
    void testImFeld() {
        Spielfeld f = new Spielfeld();
        assertTrue(f.imFeld(0, 0));
        assertTrue(f.imFeld(7, 7));
        assertFalse(f.imFeld(-1, 0));
        assertFalse(f.imFeld(8, 0));
        assertFalse(f.imFeld(0, 8));
    }

    @Test
    void testRichtung() {
        Spielfeld f = new Spielfeld();
        assertEquals(1, f.richtung(1));
        assertEquals(-1, f.richtung(2));
    }

    @Test
    void testStartaufstellung() {
        Spielfeld f = new Spielfeld();
        assertEquals(12, f.zaehleSteine(1));
        assertEquals(12, f.zaehleSteine(2));
    }

    @Test
    void testGetZelle() {
        Spielfeld f = leer();
        assertEquals(0, f.getZelle(0, 0));
        f.getFeld()[0][0] = 1;
        assertEquals(1, f.getZelle(0, 0));
    }

    // ---------- Zug-Regeln ----------

    @Test
    void testEinfacherZug() {
        Spielfeld f = leer();
        f.getFeld()[2][2] = 1;
        assertTrue(f.istEinfacherZugGueltig(2, 2, 3, 3, 1));   // diagonal vorwaerts
        assertFalse(f.istEinfacherZugGueltig(2, 2, 3, 2, 1));  // gerade -> ungueltig
        assertFalse(f.istEinfacherZugGueltig(2, 2, 1, 1, 1));  // rueckwaerts -> ungueltig
        f.getFeld()[3][3] = 2;
        assertFalse(f.istEinfacherZugGueltig(2, 2, 3, 3, 1));  // Ziel belegt
    }

    @Test
    void testEinfacherZugSpieler2() {
        Spielfeld f = leer();
        f.getFeld()[5][5] = 2;
        assertTrue(f.istEinfacherZugGueltig(5, 5, 4, 4, 2));    // Spieler 2 zieht nach oben
        assertFalse(f.istEinfacherZugGueltig(5, 5, 6, 6, 2));   // nach unten -> ungueltig
    }

    @Test
    void testSchlagzug() {
        Spielfeld f = leer();
        f.getFeld()[2][2] = 1;
        f.getFeld()[3][3] = 2;                                  // Gegner dazwischen
        assertTrue(f.istSchlagzugGueltig(2, 2, 4, 4, 1));
        f.getFeld()[3][3] = 0;                                  // niemand dazwischen
        assertFalse(f.istSchlagzugGueltig(2, 2, 4, 4, 1));
        f.getFeld()[3][3] = 1;                                  // eigener Stein dazwischen
        assertFalse(f.istSchlagzugGueltig(2, 2, 4, 4, 1));
    }

    // ---------- Zug ausfuehren ----------

    @Test
    void testFuehreZugEinfach() {
        Spielfeld f = leer();
        f.getFeld()[2][2] = 1;
        f.fuehreZug(2, 2, 3, 3, 1);
        assertEquals(1, f.getZelle(3, 3));
        assertEquals(0, f.getZelle(2, 2));
    }

    @Test
    void testFuehreZugSchlag() {
        Spielfeld f = leer();
        f.getFeld()[2][2] = 1;
        f.getFeld()[3][3] = 2;
        f.fuehreZug(2, 2, 4, 4, 1);
        assertEquals(1, f.getZelle(4, 4));
        assertEquals(0, f.getZelle(2, 2));
        assertEquals(0, f.getZelle(3, 3));   // geschlagener Stein entfernt
    }

    @Test
    void testHatGewonnen() {
        Spielfeld f = leer();
        f.getFeld()[0][0] = 1;               // nur Spieler 1 hat einen Stein
        assertTrue(f.hatGewonnen(1));
        assertFalse(f.hatGewonnen(2));
    }

    // ---------- toString ----------

    @Test
    void testToString() {
        Spielfeld f = new Spielfeld();
        String t = f.toString();
        assertEquals(40, zaehle(t, '.'));    // 64 - 24 Steine
        assertTrue(t.indexOf("X") >= 0);
        assertTrue(t.indexOf("O") >= 0);
    }

    // ---------- Spiel ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Spiel.checkEingabeMenu(1));
        assertTrue(Spiel.checkEingabeMenu(2));
        assertFalse(Spiel.checkEingabeMenu(0));
        assertFalse(Spiel.checkEingabeMenu(3));
    }
}
