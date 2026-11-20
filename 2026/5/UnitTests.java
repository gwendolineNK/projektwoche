import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Blinker (5x5): drei lebende Zellen nebeneinander in Zeile 2.
	 *       0   1   2   3   4
	 *   0 | .   .   .   .   .
	 *   1 | .   .   .   .   .
	 *   2 | .   X   X   X   .
	 *   3 | .   .   .   .   .
	 *   4 | .   .   .   .   .
	 */
	private boolean[][] blinker() {
		boolean[][] f = new boolean[5][5];
		f[2][1] = true;
		f[2][2] = true;
		f[2][3] = true;
		return f;
	}

	/*
	 * Block (4x4): vier lebende Zellen im Quadrat - eine stabile Figur.
	 */
	private boolean[][] block() {
		boolean[][] f = new boolean[4][4];
		f[0][0] = true;
		f[0][1] = true;
		f[1][0] = true;
		f[1][1] = true;
		return f;
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Generation
	 *************************************************************/

	@Test
	void testZaehleLebendeNachbarn() {
		boolean[][] f = blinker();

		assertEquals(2, Generation.zaehleLebendeNachbarn(f, 2, 2));
		assertEquals(3, Generation.zaehleLebendeNachbarn(f, 1, 2));
		assertEquals(3, Generation.zaehleLebendeNachbarn(f, 3, 2));
		assertEquals(1, Generation.zaehleLebendeNachbarn(f, 2, 1));
		assertEquals(2, Generation.zaehleLebendeNachbarn(f, 1, 1));

		// Randzellen duerfen nicht ueber das Feld hinaus zaehlen
		assertEquals(0, Generation.zaehleLebendeNachbarn(f, 0, 0));
		assertEquals(1, Generation.zaehleLebendeNachbarn(f, 2, 0));
		assertEquals(0, Generation.zaehleLebendeNachbarn(f, 4, 4));

		f = block();
		assertEquals(3, Generation.zaehleLebendeNachbarn(f, 0, 0));
		assertEquals(1, Generation.zaehleLebendeNachbarn(f, 2, 2));
		assertEquals(2, Generation.zaehleLebendeNachbarn(f, 1, 2));
	}

	@Test
	void testNaechsterZustand() {
		// lebende Zelle
		assertFalse(Generation.naechsterZustand(true, 0));
		assertFalse(Generation.naechsterZustand(true, 1));
		assertTrue(Generation.naechsterZustand(true, 2));
		assertTrue(Generation.naechsterZustand(true, 3));
		assertFalse(Generation.naechsterZustand(true, 4));
		assertFalse(Generation.naechsterZustand(true, 8));

		// tote Zelle
		assertFalse(Generation.naechsterZustand(false, 0));
		assertFalse(Generation.naechsterZustand(false, 2));
		assertTrue(Generation.naechsterZustand(false, 3));
		assertFalse(Generation.naechsterZustand(false, 4));
		assertFalse(Generation.naechsterZustand(false, 8));
	}

	@Test
	void testGleich() {
		assertTrue(Generation.gleich(blinker(), blinker()));
		assertTrue(Generation.gleich(new boolean[3][3], new boolean[3][3]));

		boolean[][] a = blinker();
		boolean[][] b = blinker();
		b[0][0] = true;
		assertFalse(Generation.gleich(a, b));

		assertFalse(Generation.gleich(new boolean[3][3], new boolean[4][4]));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/

	@Test
	void testKonstruktor() {
		Spielfeld sf = new Spielfeld(5);
		assertEquals(5, sf.getN());
		for (int z = 0; z < 5; z++) {
			for (int s = 0; s < 5; s++) {
				assertFalse(sf.istLebendig(z, s));
			}
		}

		sf = new Spielfeld(10);
		assertEquals(10, sf.getN());
		assertFalse(sf.istLebendig(9, 9));
	}

	@Test
	void testSetzeZelleUndIstLebendig() {
		Spielfeld sf = new Spielfeld(5);

		sf.setzeZelle(2, 3, true);
		assertTrue(sf.istLebendig(2, 3));
		assertFalse(sf.istLebendig(2, 2));

		sf.setzeZelle(2, 3, false);
		assertFalse(sf.istLebendig(2, 3));
	}

	@Test
	void testImFeld() {
		Spielfeld sf = new Spielfeld(5);

		assertTrue(sf.imFeld(0, 0));
		assertTrue(sf.imFeld(4, 4));
		assertFalse(sf.imFeld(-1, 0));
		assertFalse(sf.imFeld(0, -1));
		assertFalse(sf.imFeld(5, 0));
		assertFalse(sf.imFeld(0, 5));
	}

	@Test
	void testZaehleLebende() {
		assertEquals(0, new Spielfeld(5).zaehleLebende());
		assertEquals(3, new Spielfeld(blinker()).zaehleLebende());
		assertEquals(4, new Spielfeld(block()).zaehleLebende());
	}

	@Test
	void testNaechsteGeneration() {
		Spielfeld sf = new Spielfeld(blinker());

		// aus der waagerechten Reihe wird eine senkrechte
		assertTrue(sf.naechsteGeneration());

		assertTrue(sf.istLebendig(1, 2));
		assertTrue(sf.istLebendig(2, 2));
		assertTrue(sf.istLebendig(3, 2));

		assertFalse(sf.istLebendig(2, 1));
		assertFalse(sf.istLebendig(2, 3));
		assertFalse(sf.istLebendig(1, 1));
		assertFalse(sf.istLebendig(0, 2));
		assertFalse(sf.istLebendig(4, 2));

		assertEquals(3, sf.zaehleLebende());

		// und wieder zurueck
		assertTrue(sf.naechsteGeneration());
		assertTrue(sf.istLebendig(2, 1));
		assertTrue(sf.istLebendig(2, 2));
		assertTrue(sf.istLebendig(2, 3));
		assertFalse(sf.istLebendig(1, 2));
		assertEquals(3, sf.zaehleLebende());
	}

	@Test
	void testStabileGeneration() {
		Spielfeld sf = new Spielfeld(block());

		// der Block veraendert sich nicht
		assertFalse(sf.naechsteGeneration());

		assertTrue(sf.istLebendig(0, 0));
		assertTrue(sf.istLebendig(0, 1));
		assertTrue(sf.istLebendig(1, 0));
		assertTrue(sf.istLebendig(1, 1));
		assertEquals(4, sf.zaehleLebende());

		// ein leeres Feld bleibt leer
		sf = new Spielfeld(5);
		assertFalse(sf.naechsteGeneration());
		assertEquals(0, sf.zaehleLebende());
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spieler
	 *************************************************************/

	@Test
	void testSpielerNeu() {
		Spieler s = new Spieler("Alex", 5);
		assertEquals("Alex", s.getName());

		s = new Spieler("Toni", 7);
		assertEquals("Toni", s.getName());
	}

	@Test
	void testGetSpielfeld() {
		Spieler s = new Spieler("Alex", 5);
		assertNotNull(s.getSpielfeld());
		assertEquals(5, s.getSpielfeld().getN());
		assertEquals(0, s.getSpielfeld().zaehleLebende());

		s = new Spieler("Toni", 7);
		assertEquals(7, s.getSpielfeld().getN());
		assertEquals(0, s.getSpielfeld().zaehleLebende());
	}

	/*
	 * *************************************************************
	 * Test fuer Klasse Spiel
	 *************************************************************/

	@Test
	void testCheckEingabeMenu() {
		assertTrue(Spiel.checkEingabeMenu(1));
		assertTrue(Spiel.checkEingabeMenu(2));

		assertFalse(Spiel.checkEingabeMenu(0));
		assertFalse(Spiel.checkEingabeMenu(3));
		assertFalse(Spiel.checkEingabeMenu(-1));
		assertFalse(Spiel.checkEingabeMenu(10));
	}
}
