import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/* S O S O S */
	private char[] sosos() {
		return new char[] { 'S', 'O', 'S', 'O', 'S' };
	}

	/* . S O S . */
	private char[] mitteSOS() {
		return new char[] { '.', 'S', 'O', 'S', '.' };
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Muster
	 *************************************************************/

	@Test
	void testIstSOS() {
		char[] feld = sosos();

		assertTrue(Muster.istSOS(feld, 0));
		assertFalse(Muster.istSOS(feld, 1));
		assertTrue(Muster.istSOS(feld, 2));

		// passt nicht mehr ins Feld
		assertFalse(Muster.istSOS(feld, 3));
		assertFalse(Muster.istSOS(feld, 4));
		assertFalse(Muster.istSOS(feld, 5));

		// ausserhalb des Feldes
		assertFalse(Muster.istSOS(feld, -1));
		assertFalse(Muster.istSOS(feld, -3));

		feld = new char[] { 'S', 'O', 'O', 'S', '.' };
		assertFalse(Muster.istSOS(feld, 0));
		assertFalse(Muster.istSOS(feld, 1));
	}

	@Test
	void testZaehleSOS() {
		assertEquals(2, Muster.zaehleSOS(sosos()));
		assertEquals(1, Muster.zaehleSOS(mitteSOS()));
		assertEquals(0, Muster.zaehleSOS(new char[] { 'S', 'O', 'O', 'S', '.' }));
		assertEquals(0, Muster.zaehleSOS(new char[] { '.', '.', '.' }));
		assertEquals(3, Muster.zaehleSOS(new char[] { 'S', 'O', 'S', 'O', 'S', 'O', 'S' }));
	}

	@Test
	void testZaehleSOSMit() {
		char[] feld = sosos();
		assertEquals(1, Muster.zaehleSOSMit(feld, 0));
		assertEquals(1, Muster.zaehleSOSMit(feld, 1));
		assertEquals(2, Muster.zaehleSOSMit(feld, 2));   // an zwei SOS beteiligt
		assertEquals(1, Muster.zaehleSOSMit(feld, 3));
		assertEquals(1, Muster.zaehleSOSMit(feld, 4));

		feld = mitteSOS();
		assertEquals(0, Muster.zaehleSOSMit(feld, 0));
		assertEquals(1, Muster.zaehleSOSMit(feld, 1));
		assertEquals(1, Muster.zaehleSOSMit(feld, 2));
		assertEquals(1, Muster.zaehleSOSMit(feld, 3));
		assertEquals(0, Muster.zaehleSOSMit(feld, 4));

		feld = new char[] { 'S', 'O', 'S', 'O', 'S', 'O', 'S' };
		assertEquals(2, Muster.zaehleSOSMit(feld, 4));
		assertEquals(1, Muster.zaehleSOSMit(feld, 3));
		assertEquals(1, Muster.zaehleSOSMit(feld, 6));

		assertEquals(0, Muster.zaehleSOSMit(new char[] { '.', '.', '.' }, 1));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/

	@Test
	void testKonstruktor() {
		Spielfeld sf = new Spielfeld(5);
		assertEquals(5, sf.getN());
		for (int i = 0; i < 5; i++) {
			assertEquals(Muster.LEER, sf.getStelle(i));
		}

		sf = new Spielfeld(12);
		assertEquals(12, sf.getN());
		assertEquals(Muster.LEER, sf.getStelle(11));
	}

	@Test
	void testSetStelle() {
		Spielfeld sf = new Spielfeld(5);

		sf.setStelle(0, Muster.S);
		assertEquals('S', sf.getStelle(0));

		sf.setStelle(3, Muster.O);
		assertEquals('O', sf.getStelle(3));

		assertEquals(Muster.LEER, sf.getStelle(1));
	}

	@Test
	void testGueltigeEingabe() {
		Spielfeld sf = new Spielfeld(5);

		assertTrue(sf.gueltigeEingabe(0));
		assertTrue(sf.gueltigeEingabe(4));

		assertFalse(sf.gueltigeEingabe(-1));
		assertFalse(sf.gueltigeEingabe(5));
		assertFalse(sf.gueltigeEingabe(100));

		sf.setStelle(2, Muster.S);
		assertFalse(sf.gueltigeEingabe(2));
		assertTrue(sf.gueltigeEingabe(3));
	}

	@Test
	void testZaehleFreie() {
		Spielfeld sf = new Spielfeld(5);
		assertEquals(5, sf.zaehleFreie());

		sf.setStelle(1, Muster.S);
		assertEquals(4, sf.zaehleFreie());

		sf = new Spielfeld(new char[] { 'S', 'O', '.', '.', 'S' });
		assertEquals(2, sf.zaehleFreie());

		sf = new Spielfeld(new char[] { 'S', 'O', 'S' });
		assertEquals(0, sf.zaehleFreie());
	}

	@Test
	void testIstVoll() {
		assertFalse(new Spielfeld(5).istVoll());
		assertFalse(new Spielfeld(new char[] { 'S', '.', 'S' }).istVoll());
		assertTrue(new Spielfeld(new char[] { 'S', 'O', 'S' }).istVoll());
		assertTrue(new Spielfeld(new char[] { 'O', 'O' }).istVoll());
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spieler
	 *************************************************************/

	@Test
	void testSpielerNeu() {
		Spieler s = new Spieler("Alex");
		assertEquals("Alex", s.getName());
		assertEquals(0, s.getPunkte());

		s = new Spieler("Toni");
		assertEquals("Toni", s.getName());
		assertEquals(0, s.getPunkte());
	}

	@Test
	void testPunkteDazu() {
		Spieler s = new Spieler("Alex");
		s.punkteDazu(2);
		assertEquals(2, s.getPunkte());
		s.punkteDazu(1);
		assertEquals(3, s.getPunkte());
		s.punkteDazu(0);
		assertEquals(3, s.getPunkte());
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spiel
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

	@Test
	void testCheckBuchstabeUndBuchstabeAus() {
		assertTrue(Spiel.checkBuchstabe("S"));
		assertTrue(Spiel.checkBuchstabe("s"));
		assertTrue(Spiel.checkBuchstabe("O"));
		assertTrue(Spiel.checkBuchstabe("o"));

		assertFalse(Spiel.checkBuchstabe("x"));
		assertFalse(Spiel.checkBuchstabe("SO"));
		assertFalse(Spiel.checkBuchstabe(""));
		assertFalse(Spiel.checkBuchstabe("0"));

		assertEquals(Muster.S, Spiel.buchstabeAus("S"));
		assertEquals(Muster.S, Spiel.buchstabeAus("s"));
		assertEquals(Muster.O, Spiel.buchstabeAus("O"));
		assertEquals(Muster.O, Spiel.buchstabeAus("o"));
		assertEquals(Muster.LEER, Spiel.buchstabeAus("x"));
	}
}
