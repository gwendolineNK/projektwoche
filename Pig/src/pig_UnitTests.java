import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * UnitTests fuer "Pig". NICHT veraendern!
 * Falls JUnit 5 nicht startet: nutze TestRunner.java (Run As -> Java Application).
 */
class UnitTests {

    // ---------- Spieler ----------

    @Test
    void testSpielerNeu() {
        Spieler s = new Spieler("Alex");
        assertEquals("Alex", s.getName());
        assertEquals(0, s.getPunkte());
    }

    @Test
    void testPunkteDazu() {
        Spieler s = new Spieler("Alex");
        s.punkteDazu(5);
        s.punkteDazu(3);
        assertEquals(8, s.getPunkte());
    }

    // ---------- Spiel: reine Logik ----------

    @Test
    void testCheckEingabeMenu() {
        assertTrue(Spiel.checkEingabeMenu(1));
        assertTrue(Spiel.checkEingabeMenu(2));
        assertFalse(Spiel.checkEingabeMenu(0));
        assertFalse(Spiel.checkEingabeMenu(3));
    }

    @Test
    void testWuerfelnImBereich() {
        for (int i = 0; i < 200; i++) {
            int w = Spiel.wuerfeln();
            assertTrue(w >= 1 && w <= 6, "Wuerfel ausserhalb 1-6: " + w);
        }
    }

    @Test
    void testIstPech() {
        assertTrue(Spiel.istPech(1));
        assertFalse(Spiel.istPech(2));
        assertFalse(Spiel.istPech(6));
    }

    @Test
    void testNeuerRundenScore() {
        assertEquals(14, Spiel.neuerRundenScore(10, 4));   // 10 + 4
        assertEquals(6, Spiel.neuerRundenScore(0, 6));     // 0 + 6
        assertEquals(0, Spiel.neuerRundenScore(10, 1));    // Pech -> alles weg
    }

    @Test
    void testHatGewonnen() {
        Spieler s = new Spieler("Alex");
        s.punkteDazu(99);
        assertFalse(Spiel.hatGewonnen(s, 100));
        s.punkteDazu(1);                                   // jetzt 100
        assertTrue(Spiel.hatGewonnen(s, 100));
        s.punkteDazu(10);                                  // 110 >= 100
        assertTrue(Spiel.hatGewonnen(s, 100));
    }
}
