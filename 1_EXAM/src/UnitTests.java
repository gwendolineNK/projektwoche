import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Runenkampf". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /**
     * Testbrett 4x4, alle Felder neutral:
     *     A A B B
     *     A C C B
     *     D D C B
     *     D A A A
     */
    private Spielfeld testBrett() {
        Spielfeld f = new Spielfeld();
        char[][] r = {
            { 'A', 'A', 'B', 'B' },
            { 'A', 'C', 'C', 'B' },
            { 'D', 'D', 'C', 'B' },
            { 'D', 'A', 'A', 'A' }
        };
        f.setFelderFuerTest(r, new int[4][4]);
        return f;
    }

    private int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++) if (text.charAt(i) == c) n++;
        return n;
    }

    // ---------- Spieler / Computer ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex", 1);
        assertEquals("Alex", s.getName());
        assertEquals(1, s.getNummer());
        assertTrue(s.hatRotation());
    }

    @Test
    void testRotationVerbrauchen() {
        Spieler s = new Spieler("Alex", 1);
        s.rotationVerbrauchen();
        assertFalse(s.hatRotation());
    }

    @Test
    void testComputerNeu() {
        Computer c = new Computer(2);
        assertEquals("Computer", c.getName());
        assertEquals(2, c.getNummer());
    }

    // ---------- Grundlagen ----------

    @Test
    void testRunenWert() {
        Spielfeld f = new Spielfeld();
        assertEquals(1, f.runenWert('A'));
        assertEquals(4, f.runenWert('D'));
        assertEquals(8, f.runenWert('H'));
    }

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
    void testRunenVollstaendig() {
        // Jede Rune A-H muss genau 8 Mal vorkommen (auch nach dem Mischen).
        Spielfeld f = new Spielfeld();
        int[] anzahl = new int[128];
        for (int z = 0; z < Spielfeld.GROESSE; z++) {
            for (int s = 0; s < Spielfeld.GROESSE; s++) {
                anzahl[f.getRunen()[z][s]]++;
            }
        }
        for (char c = 'A'; c <= 'H'; c++) {
            assertEquals(8, anzahl[c], "Rune " + c + " kommt nicht 8 Mal vor.");
        }
    }

    @Test
    void testGetRuneUndBesitzer() {
        Spielfeld f = testBrett();
        assertEquals('B', f.getRune(0, 2));
        assertEquals(0, f.getBesitzer(0, 2));
        f.getBesitzerFeld()[0][2] = 1;
        assertEquals(1, f.getBesitzer(0, 2));
    }

    @Test
    void testIstNeutral() {
        Spielfeld f = testBrett();
        assertTrue(f.istNeutral(0, 0));
        assertFalse(f.istNeutral(-1, 0));
        assertFalse(f.istNeutral(4, 0));
        f.getBesitzerFeld()[0][0] = 2;
        assertFalse(f.istNeutral(0, 0));
    }

    @Test
    void testZaehleNeutraleUndSpielEnde() {
        Spielfeld f = testBrett();
        assertEquals(16, f.zaehleNeutrale());
        assertFalse(f.istSpielEnde());
        for (int z = 0; z < 4; z++) {
            for (int s = 0; s < 4; s++) f.getBesitzerFeld()[z][s] = 1;
        }
        assertEquals(0, f.zaehleNeutrale());
        assertTrue(f.istSpielEnde());
    }

    // ---------- Gebiete ----------

    @Test
    void testGebietGroesse() {
        Spielfeld f = testBrett();
        assertEquals(3, f.gebietGroesse(0, 0));   // A: (0,0),(0,1),(1,0)
        assertEquals(4, f.gebietGroesse(0, 2));   // B: (0,2),(0,3),(1,3),(2,3)
        assertEquals(3, f.gebietGroesse(1, 1));   // C: (1,1),(1,2),(2,2)
        assertEquals(3, f.gebietGroesse(2, 0));   // D: (2,0),(2,1),(3,0)
        assertEquals(3, f.gebietGroesse(3, 1));   // A: (3,1),(3,2),(3,3)
    }

    @Test
    void testGebietGroesseNichtNeutral() {
        Spielfeld f = testBrett();
        f.getBesitzerFeld()[0][0] = 1;
        assertEquals(0, f.gebietGroesse(0, 0));
        assertEquals(0, f.gebietGroesse(9, 9));
    }

    @Test
    void testBeansprucheGebiet() {
        Spielfeld f = testBrett();
        assertEquals(4, f.beansprucheGebiet(0, 2, 1));
        assertEquals(1, f.getBesitzer(0, 2));
        assertEquals(1, f.getBesitzer(0, 3));
        assertEquals(1, f.getBesitzer(1, 3));
        assertEquals(1, f.getBesitzer(2, 3));
        assertEquals(0, f.getBesitzer(0, 0));     // fremdes Gebiet unberuehrt
        assertEquals(12, f.zaehleNeutrale());
    }

    @Test
    void testBeansprucheGebietUngueltig() {
        Spielfeld f = testBrett();
        f.beansprucheGebiet(0, 2, 1);
        assertEquals(0, f.beansprucheGebiet(0, 2, 2));   // nicht mehr neutral
        assertEquals(0, f.beansprucheGebiet(9, 9, 1));   // ausserhalb
        assertEquals(1, f.getBesitzer(0, 2));
    }

    @Test
    void testPunkte() {
        Spielfeld f = testBrett();
        f.beansprucheGebiet(0, 2, 1);      // 4 Felder Rune B -> 4 * 2
        f.beansprucheGebiet(2, 0, 2);      // 3 Felder Rune D -> 3 * 4
        assertEquals(8, f.punkte(1));
        assertEquals(12, f.punkte(2));
    }

    // ---------- Rotation ----------

    @Test
    void testRotiereZeile() {
        Spielfeld f = testBrett();
        f.getBesitzerFeld()[0][0] = 1;
        f.rotiereZeile(0);
        assertEquals('B', f.getRune(0, 0));
        assertEquals('A', f.getRune(0, 1));
        assertEquals('A', f.getRune(0, 2));
        assertEquals('B', f.getRune(0, 3));
        assertEquals(1, f.getBesitzer(0, 1));   // Besitzer wandert mit
        assertEquals(0, f.getBesitzer(0, 0));
    }

    @Test
    void testRotiereZeileUngueltig() {
        Spielfeld f = testBrett();
        f.rotiereZeile(9);
        assertEquals('A', f.getRune(0, 0));     // nichts geaendert
    }

    // ---------- Anzeige ----------

    @Test
    void testToString() {
        Spielfeld f = testBrett();
        assertEquals(16, zaehle(f.toString(), '-'));
        f.beansprucheGebiet(0, 2, 1);
        assertEquals(12, zaehle(f.toString(), '-'));
    }

    // ---------- Computer ----------

    @Test
    void testComputerWaehleZug() {
        // Gewinne: A=3*1=3, B=4*2=8, C=3*3=9, D=3*4=12 -> D, erstes Feld (2,0)
        Spielfeld f = testBrett();
        Computer c = new Computer(2);
        int[] zug = c.waehleZug(f);
        assertEquals(2, zug[0]);
        assertEquals(0, zug[1]);
    }

    @Test
    void testComputerWaehleZugKeinFeld() {
        Spielfeld f = testBrett();
        for (int z = 0; z < 4; z++) {
            for (int s = 0; s < 4; s++) f.getBesitzerFeld()[z][s] = 1;
        }
        int[] zug = new Computer(2).waehleZug(f);
        assertEquals(-1, zug[0]);
        assertEquals(-1, zug[1]);
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
    void testAuswertung() {
        Spielfeld f = testBrett();
        Spieler s1 = new Spieler("Alex", 1);
        Spieler s2 = new Spieler("Mika", 2);
        f.beansprucheGebiet(2, 0, 2);            // Mika 12 Punkte
        String e = f == null ? "" : Spiel.auswertung(s1, s2, f);
        assertTrue(e.indexOf("Mika") >= 0 && e.indexOf("gewonnen") >= 0);

        Spielfeld g = testBrett();
        g.beansprucheGebiet(0, 0, 1);            // Alex 3 Punkte (A)
        g.beansprucheGebiet(3, 1, 2);            // Mika 3 Punkte (A)
        assertTrue(Spiel.auswertung(s1, s2, g).indexOf("Unentschieden") >= 0);
    }
}
