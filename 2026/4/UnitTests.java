import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Testbrett (n = 6):
	 *   Mulde:   0   1   2   3   4   5
	 *   Samen:   3   1   0   2   4   0
	 */
	private Spielfeld testBrett() {
		return new Spielfeld(new int[] { 3, 1, 0, 2, 4, 0 });
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Zug
	 *************************************************************/

	@Test
	void testRichtungVon() {
		assertEquals(1, Zug.richtungVon(1));
		assertEquals(-1, Zug.richtungVon(2));
	}

	@Test
	void testLetzteMulde() {
		assertEquals(5, Zug.letzteMulde(2, 1, 3));
		assertEquals(0, Zug.letzteMulde(4, -1, 4));
		assertEquals(1, Zug.letzteMulde(0, 1, 1));
		assertEquals(3, Zug.letzteMulde(5, -1, 2));
		assertEquals(8, Zug.letzteMulde(4, 1, 4));
		assertEquals(-3, Zug.letzteMulde(0, -1, 3));
	}

	@Test
	void testPasstInsFeld() {
		assertTrue(Zug.passtInsFeld(6, 0, 1, 3));     // letzte Mulde 3
		assertTrue(Zug.passtInsFeld(6, 4, -1, 4));    // letzte Mulde 0
		assertTrue(Zug.passtInsFeld(6, 2, 1, 3));     // letzte Mulde 5
		assertTrue(Zug.passtInsFeld(6, 2, -1, 2));    // letzte Mulde 0

		assertFalse(Zug.passtInsFeld(6, 4, 1, 4));    // letzte Mulde 8
		assertFalse(Zug.passtInsFeld(6, 0, -1, 1));   // letzte Mulde -1
		assertFalse(Zug.passtInsFeld(6, 5, 1, 1));    // letzte Mulde 6
		assertFalse(Zug.passtInsFeld(6, 2, -1, 3));   // letzte Mulde -1
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/

	@Test
	void testKonstruktor() {
		Spielfeld b = new Spielfeld(6, 2);
		assertEquals(6, b.getN());
		for (int i = 0; i < 6; i++) {
			assertEquals(2, b.getMulde(i));
		}

		b = new Spielfeld(12, 3);
		assertEquals(12, b.getN());
		assertEquals(3, b.getMulde(0));
		assertEquals(3, b.getMulde(11));
	}

	@Test
	void testSetMulde() {
		Spielfeld b = new Spielfeld(6, 2);
		b.setMulde(2, 7);
		assertEquals(7, b.getMulde(2));
		b.setMulde(5, 0);
		assertEquals(0, b.getMulde(5));
		assertEquals(2, b.getMulde(0));
	}

	@Test
	void testZaehleSamen() {
		assertEquals(10, testBrett().zaehleSamen());
		assertEquals(12, new Spielfeld(6, 2).zaehleSamen());
		assertEquals(36, new Spielfeld(12, 3).zaehleSamen());
		assertEquals(0, new Spielfeld(new int[] { 0, 0, 0 }).zaehleSamen());
	}

	@Test
	void testIstZugGueltig() {
		Spielfeld b = testBrett();   // { 3, 1, 0, 2, 4, 0 }

		// ---------- Spieler 1, Richtung +1 ----------
		assertTrue(b.istZugGueltig(0, 1));    // 3 Samen -> 1,2,3 ; letzte Mulde 3 ist nicht leer

		assertFalse(b.istZugGueltig(1, 1));   // letzter Samen faellt in die leere Mulde 2
		assertFalse(b.istZugGueltig(2, 1));   // Mulde ist leer
		assertFalse(b.istZugGueltig(3, 1));   // letzter Samen faellt in die leere Mulde 5
		assertFalse(b.istZugGueltig(4, 1));   // 4 Samen passen nicht mehr aufs Brett
		assertFalse(b.istZugGueltig(5, 1));   // Mulde ist leer

		// ---------- Spieler 2, Richtung -1 ----------
		assertTrue(b.istZugGueltig(1, 2));    // 1 Samen -> Mulde 0, nicht leer
		assertTrue(b.istZugGueltig(3, 2));    // 2 Samen -> 2,1 ; letzte Mulde 1 nicht leer
		assertTrue(b.istZugGueltig(4, 2));    // 4 Samen -> 3,2,1,0 ; letzte Mulde 0 nicht leer

		assertFalse(b.istZugGueltig(0, 2));   // 3 Samen passen nicht nach links
		assertFalse(b.istZugGueltig(2, 2));   // Mulde ist leer
		assertFalse(b.istZugGueltig(5, 2));   // Mulde ist leer

		// ---------- Muldennummern ausserhalb des Brettes ----------
		assertFalse(b.istZugGueltig(-1, 1));
		assertFalse(b.istZugGueltig(6, 1));
		assertFalse(b.istZugGueltig(-1, 2));
		assertFalse(b.istZugGueltig(6, 2));
	}

	@Test
	void testFuehreZugAus() {
		// Spieler 1 verteilt die Mulde 0 nach rechts
		Spielfeld b = testBrett();
		assertEquals(3, b.fuehreZugAus(0, 1));
		assertEquals(0, b.getMulde(0));
		assertEquals(2, b.getMulde(1));
		assertEquals(1, b.getMulde(2));
		assertEquals(3, b.getMulde(3));
		assertEquals(4, b.getMulde(4));
		assertEquals(0, b.getMulde(5));
		assertEquals(10, b.zaehleSamen());    // kein Samen geht verloren

		// Spieler 2 verteilt die Mulde 4 nach links
		b = testBrett();
		assertEquals(4, b.fuehreZugAus(4, 2));
		assertEquals(4, b.getMulde(0));
		assertEquals(2, b.getMulde(1));
		assertEquals(1, b.getMulde(2));
		assertEquals(3, b.getMulde(3));
		assertEquals(0, b.getMulde(4));
		assertEquals(0, b.getMulde(5));
		assertEquals(10, b.zaehleSamen());
	}

	@Test
	void testHatZugMoeglichkeit() {
		Spielfeld b = testBrett();
		assertTrue(b.hatZugMoeglichkeit(1));
		assertTrue(b.hatZugMoeglichkeit(2));

		// nur Spieler 1 kann noch ziehen
		b = new Spielfeld(new int[] { 1, 3, 0, 0, 0, 0 });
		assertTrue(b.hatZugMoeglichkeit(1));
		assertFalse(b.hatZugMoeglichkeit(2));

		// niemand kann mehr ziehen
		b = new Spielfeld(new int[] { 0, 0, 0, 0, 0, 2 });
		assertFalse(b.hatZugMoeglichkeit(1));
		assertFalse(b.hatZugMoeglichkeit(2));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spieler
	 *************************************************************/

	@Test
	void testSpielerNeu() {
		Spieler s = new Spieler("Alex", 1);
		assertEquals("Alex", s.getName());
		assertEquals(1, s.getNummer());

		s = new Spieler("Toni", 2);
		assertEquals("Toni", s.getName());
		assertEquals(2, s.getNummer());
	}

	@Test
	void testGetRichtung() {
		assertEquals(1, new Spieler("Alex", 1).getRichtung());
		assertEquals(-1, new Spieler("Toni", 2).getRichtung());
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
