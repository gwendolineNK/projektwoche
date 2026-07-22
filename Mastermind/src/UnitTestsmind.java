import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Mastermind / Bulls & Cows". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    /** Code mit bekanntem Inhalt {1,2,3,4} fuer die Tests. */
    private Code codeBoard() {
        Code c = new Code();
        c.setCodeFuerTest(new int[] { 1, 2, 3, 4 });
        return c;
    }

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex");
        assertEquals("Alex", s.getName());
        assertEquals(0, s.getVersuche());
    }

    @Test
    void testVersuchDazu() {
        Spieler s = new Spieler("Alex");
        s.versuchDazu(); s.versuchDazu();
        assertEquals(2, s.getVersuche());
    }

    // ---------- Code: Grundlagen ----------

    @Test
    void testGetLaenge() {
        assertEquals(4, new Code().getLaenge());
    }

    @Test
    void testCodeIstZufaelligUndVerschieden() {
        for (int versuch = 0; versuch < 20; versuch++) {
            int[] c = new Code().getCode();
            assertEquals(4, c.length);
            boolean[] gesehen = new boolean[10];
            for (int x : c) {
                assertTrue(x >= 0 && x <= 9, "Ziffer ausserhalb 0-9: " + x);
                assertFalse(gesehen[x], "Ziffer doppelt: " + x);
                gesehen[x] = true;
            }
        }
    }

    @Test
    void testEnthaelt() {
        Code c = codeBoard();
        assertTrue(c.enthaelt(3));
        assertFalse(c.enthaelt(5));
    }

    // ---------- Code: Auswertung ----------

    @Test
    void testZaehleBulls() {
        Code c = codeBoard();                       // Code {1,2,3,4}
        assertEquals(2, c.zaehleBulls(new int[] { 1, 0, 3, 0 }));
        assertEquals(0, c.zaehleBulls(new int[] { 0, 0, 0, 0 }));
        assertEquals(4, c.zaehleBulls(new int[] { 1, 2, 3, 4 }));
    }

    @Test
    void testIstGeloest() {
        Code c = codeBoard();
        assertTrue(c.istGeloest(new int[] { 1, 2, 3, 4 }));
        assertFalse(c.istGeloest(new int[] { 1, 2, 3, 0 }));
    }

    @Test
    void testZaehleKuehe() {
        Code c = codeBoard();                       // Code {1,2,3,4}
        assertEquals(4, c.zaehleKuehe(new int[] { 4, 3, 2, 1 }));  // alle vorhanden, falsche Stelle
        assertEquals(0, c.zaehleKuehe(new int[] { 1, 2, 3, 4 }));  // alles Bulls -> keine Kuehe
        assertEquals(0, c.zaehleKuehe(new int[] { 5, 6, 7, 8 }));  // nichts vorhanden
        assertEquals(2, c.zaehleKuehe(new int[] { 2, 1, 3, 4 }));  // 2 und 1 als Kuehe
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
