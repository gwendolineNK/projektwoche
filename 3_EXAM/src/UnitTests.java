import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Sternwanderung". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /**
     * Testfeld 4x4:
     *      s0 s1 s2 s3
     * z0:   A  A  B  C
     * z1:   B  C  A  D
     * z2:   C  D  B  A
     * z3:   D  A  C  B
     */
    private Sternenhimmel testHimmel() {
        Sternenhimmel h = new Sternenhimmel();
        char[][] s = {
            { 'A', 'A', 'B', 'C' },
            { 'B', 'C', 'A', 'D' },
            { 'C', 'D', 'B', 'A' },
            { 'D', 'A', 'C', 'B' }
        };
        h.setSterneFuerTest(s);
        return h;
    }

    private int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == c) n++;
        return n;
    }

    // ---------- Spieler / Computer ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex", 1, 0, 0);
        assertEquals("Alex", s.getName());
        assertEquals(1, s.getNummer());
        assertEquals(0, s.getZeile());
        assertEquals(0, s.getSpalte());
        assertEquals(0, s.getPunkte());
    }

    @Test
    void testSetzePositionUndPunkte() {
        Spieler s = new Spieler("Alex", 1, 0, 0);
        s.setzePosition(3, 5);
        assertEquals(3, s.getZeile());
        assertEquals(5, s.getSpalte());
        s.punkteDazu(7);
        s.punkteDazu(3);
        assertEquals(10, s.getPunkte());
    }

    @Test
    void testComputerNeu() {
        Computer c = new Computer(2, 7, 7);
        assertEquals("Computer", c.getName());
        assertEquals(2, c.getNummer());
        assertEquals(7, c.getZeile());
        assertEquals(7, c.getSpalte());
    }

    // ---------- Grundlagen ----------

    @Test
    void testWert() {
        Sternenhimmel h = new Sternenhimmel();
        assertEquals(1, h.wert('A'));
        assertEquals(4, h.wert('D'));
        assertEquals(8, h.wert('H'));
        assertEquals(0, h.wert(Sternenhimmel.LEER));
    }

    @Test
    void testImFeld() {
        Sternenhimmel h = new Sternenhimmel();
        assertTrue(h.imFeld(0, 0));
        assertTrue(h.imFeld(7, 7));
        assertFalse(h.imFeld(-1, 0));
        assertFalse(h.imFeld(8, 0));
        assertFalse(h.imFeld(0, 8));
    }

    @Test
    void testSterneVollstaendig() {
        Sternenhimmel h = new Sternenhimmel();
        int[] anzahl = new int[128];
        for (int z = 0; z < Sternenhimmel.GROESSE; z++) {
            for (int s = 0; s < Sternenhimmel.GROESSE; s++) {
                anzahl[h.getSterne()[z][s]]++;
            }
        }
        for (char c = 'A'; c <= 'H'; c++) {
            assertEquals(8, anzahl[c], "Stern " + c + " kommt nicht 8 Mal vor.");
        }
    }

    @Test
    void testZaehleSterneUndSpielEnde() {
        Sternenhimmel h = new Sternenhimmel();
        assertEquals(64, h.zaehleSterne());
        assertFalse(h.istSpielEnde());

        Sternenhimmel t = testHimmel();
        assertEquals(16, t.zaehleSterne());
        for (int z = 0; z < 4; z++) {
            for (int s = 0; s < 4; s++) t.getSterne()[z][s] = Sternenhimmel.LEER;
        }
        assertEquals(0, t.zaehleSterne());
        assertTrue(t.istSpielEnde());
    }

    @Test
    void testGetSternUndIstLeer() {
        Sternenhimmel h = testHimmel();
        assertEquals('D', h.getStern(1, 3));
        assertFalse(h.istLeer(1, 3));
        h.getSterne()[1][3] = Sternenhimmel.LEER;
        assertTrue(h.istLeer(1, 3));
        assertFalse(h.istLeer(9, 9));
    }

    // ---------- Modulare Arithmetik ----------

    @Test
    void testNormiere() {
        Sternenhimmel h = new Sternenhimmel();
        assertEquals(3, h.normiere(3, 8));
        assertEquals(0, h.normiere(8, 8));
        assertEquals(7, h.normiere(-1, 8));
        assertEquals(7, h.normiere(-9, 8));
        assertEquals(1, h.normiere(9, 8));
        assertEquals(0, h.normiere(0, 4));
    }

    @Test
    void testZielZeileUndSpalte() {
        Sternenhimmel h = testHimmel();                 // 4x4
        assertEquals(3, h.zielZeile(0, 0, 1));          // oben links ueber den Rand
        assertEquals(3, h.zielSpalte(0, 0, 1));
        assertEquals(1, h.zielZeile(3, 7, 2));          // unten rechts, 2 Schritte
        assertEquals(1, h.zielSpalte(3, 7, 2));
        assertEquals(2, h.zielZeile(2, 4, 3));          // Richtung 4 aendert die Zeile nicht
        assertEquals(1, h.zielSpalte(2, 4, 3));
    }

    @Test
    void testSchrittweite() {
        Sternenhimmel h = testHimmel();
        assertEquals(3, h.schrittweite(1, 1));          // 'C'
        assertEquals(1, h.schrittweite(0, 0));          // 'A'
        h.getSterne()[0][0] = Sternenhimmel.LEER;
        assertEquals(1, h.schrittweite(0, 0));          // leer -> 1
    }

    // ---------- Nachbarn / Einsammeln ----------

    @Test
    void testZaehleGleicheNachbarn() {
        Sternenhimmel h = testHimmel();
        assertEquals(1, h.zaehleGleicheNachbarn(0, 0));   // nur (0,1) ist auch 'A'
        assertEquals(2, h.zaehleGleicheNachbarn(1, 2));   // (0,1) und (2,3) sind 'A'
        assertEquals(1, h.zaehleGleicheNachbarn(3, 0));   // (2,1) ist auch 'D'
    }

    @Test
    void testZaehleGleicheNachbarnLeerUndAussen() {
        Sternenhimmel h = testHimmel();
        h.getSterne()[0][0] = Sternenhimmel.LEER;
        assertEquals(0, h.zaehleGleicheNachbarn(0, 0));
        assertEquals(0, h.zaehleGleicheNachbarn(9, 9));
    }

    @Test
    void testSammle() {
        Sternenhimmel h = testHimmel();
        assertEquals(2, h.sammle(0, 0));                 // 'A' = 1 + 1 Nachbar
        assertTrue(h.istLeer(0, 0));
        assertEquals(15, h.zaehleSterne());
    }

    @Test
    void testSammleMitBonus() {
        Sternenhimmel h = testHimmel();
        assertEquals(3, h.sammle(1, 2));                 // 'A' = 1 + 2 Nachbarn
        assertTrue(h.istLeer(1, 2));
    }

    @Test
    void testSammleLeerUndAussen() {
        Sternenhimmel h = testHimmel();
        h.sammle(0, 0);
        assertEquals(0, h.sammle(0, 0));                 // schon leer
        assertEquals(0, h.sammle(9, 9));                 // ausserhalb
    }

    // ---------- Anzeige ----------

    @Test
    void testToString() {
        Sternenhimmel h = testHimmel();
        Spieler s1 = new Spieler("Alex", 1, 0, 0);
        Spieler s2 = new Spieler("Mika", 2, 2, 2);
        String t = h.toString(s1, s2);
        assertEquals(0, zaehle(t, '.'));                 // noch kein leeres Feld
        assertEquals(4, zaehle(t, 'A'));                 // 5 mal 'A', eines vom Schiff verdeckt

        h.sammle(1, 2);
        String t2 = h.toString(s1, s2);
        assertEquals(1, zaehle(t2, '.'));
        assertEquals(3, zaehle(t2, 'A'));
    }

    // ---------- Computer ----------

    @Test
    void testComputerWaehleRichtung() {
        // Schiff auf (0,0), Schrittweite 1. Bester Gewinn: Richtung 1 -> (3,0) = 'D' (4) + 1 Nachbar
        Sternenhimmel h = testHimmel();
        Computer c = new Computer(2, 0, 0);
        Spieler gegner = new Spieler("Alex", 1, 2, 2);
        assertEquals(1, c.waehleRichtung(h, gegner));
    }

    @Test
    void testComputerWaehleRichtungBlockiert() {
        // Gegner steht auf (3,0) -> Richtung 1 faellt weg, dann gewinnt Richtung 5
        Sternenhimmel h = testHimmel();
        Computer c = new Computer(2, 0, 0);
        Spieler gegner = new Spieler("Alex", 1, 3, 0);
        assertEquals(5, c.waehleRichtung(h, gegner));
    }

    // ---------- Spiel ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Spiel.checkEingabeMenu(1));
        assertTrue(Spiel.checkEingabeMenu(2));
        assertTrue(Spiel.checkEingabeMenu(3));
        assertFalse(Spiel.checkEingabeMenu(0));
        assertFalse(Spiel.checkEingabeMenu(4));
    }

    @Test
    void testCheckRichtung() {
        assertTrue(Spiel.checkRichtung(0));
        assertTrue(Spiel.checkRichtung(7));
        assertFalse(Spiel.checkRichtung(-1));
        assertFalse(Spiel.checkRichtung(8));
    }

    @Test
    void testAuswertung() {
        Spieler s1 = new Spieler("Alex", 1, 0, 0);
        Spieler s2 = new Spieler("Mika", 2, 7, 7);
        s1.punkteDazu(10);
        s2.punkteDazu(4);
        String e = Spiel.auswertung(s1, s2);
        assertTrue(e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0);

        s2.punkteDazu(6);   // 10 : 10
        assertTrue(Spiel.auswertung(s1, s2).indexOf("Unentschieden") >= 0);
    }
}
