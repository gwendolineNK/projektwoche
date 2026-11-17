import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Testfeld:
	 *      2 2 0 4
	 *      0 0 0 0
	 *      4 4 4 4
	 *      2 0 2 0
	 */
	private Spielfeld testFeld() {
		int[][] feld = {
				{ 2, 2, 0, 4 },
				{ 0, 0, 0, 0 },
				{ 4, 4, 4, 4 },
				{ 2, 0, 2, 0 }
		};
		return new Spielfeld(feld);
	}

	private void pruefeZeile(Spielfeld sf, int zeile, int a, int b, int c, int d) {
		assertEquals(a, sf.getStelle(zeile, 0));
		assertEquals(b, sf.getStelle(zeile, 1));
		assertEquals(c, sf.getStelle(zeile, 2));
		assertEquals(d, sf.getStelle(zeile, 3));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Reihe
	 *************************************************************/

	@Test
	void testUmdrehen() {
		int[] original = { 2, 4, 0, 8 };
		int[] gedreht = Reihe.umdrehen(original);

		assertEquals(8, gedreht[0]);
		assertEquals(0, gedreht[1]);
		assertEquals(4, gedreht[2]);
		assertEquals(2, gedreht[3]);

		// die uebergebene Reihe darf nicht veraendert werden
		assertEquals(2, original[0]);
		assertEquals(8, original[3]);
	}

	@Test
	void testZusammenschieben() {
		int[] r = Reihe.zusammenschieben(new int[] { 0, 2, 0, 4 });
		assertEquals(2, r[0]);
		assertEquals(4, r[1]);
		assertEquals(0, r[2]);
		assertEquals(0, r[3]);

		r = Reihe.zusammenschieben(new int[] { 2, 4, 0, 0 });
		assertEquals(2, r[0]);
		assertEquals(4, r[1]);
		assertEquals(0, r[2]);

		r = Reihe.zusammenschieben(new int[] { 0, 0, 0, 0 });
		assertEquals(0, r[0]);
		assertEquals(0, r[3]);

		r = Reihe.zusammenschieben(new int[] { 0, 0, 0, 8 });
		assertEquals(8, r[0]);
		assertEquals(0, r[1]);

		// die uebergebene Reihe darf nicht veraendert werden
		int[] original = { 0, 2, 0, 4 };
		Reihe.zusammenschieben(original);
		assertEquals(0, original[0]);
		assertEquals(2, original[1]);
	}

	@Test
	void testVerschmelzen() {
		int[] r = { 2, 2, 4, 0 };
		assertEquals(4, Reihe.verschmelzen(r));
		assertEquals(4, r[0]);
		assertEquals(0, r[1]);
		assertEquals(4, r[2]);
		assertEquals(0, r[3]);

		// jede Kachel darf nur einmal verschmelzen
		r = new int[] { 4, 4, 4, 4 };
		assertEquals(16, Reihe.verschmelzen(r));
		assertEquals(8, r[0]);
		assertEquals(0, r[1]);
		assertEquals(8, r[2]);
		assertEquals(0, r[3]);

		r = new int[] { 2, 2, 2, 0 };
		assertEquals(4, Reihe.verschmelzen(r));
		assertEquals(4, r[0]);
		assertEquals(0, r[1]);
		assertEquals(2, r[2]);

		// nichts zu verschmelzen
		r = new int[] { 2, 4, 8, 0 };
		assertEquals(0, Reihe.verschmelzen(r));
		assertEquals(2, r[0]);
		assertEquals(4, r[1]);
		assertEquals(8, r[2]);
	}

	@Test
	void testGleich() {
		assertTrue(Reihe.gleich(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 }));
		assertTrue(Reihe.gleich(new int[] { 0, 0 }, new int[] { 0, 0 }));
		assertFalse(Reihe.gleich(new int[] { 1, 2, 3 }, new int[] { 1, 2, 4 }));
		assertFalse(Reihe.gleich(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/

	@Test
	void testKonstruktorUndZufaelligeKachel() {
		for (int versuch = 0; versuch < 5; versuch++) {
			Spielfeld sf = new Spielfeld(4);

			assertEquals(4, sf.getN());
			assertEquals(0, sf.getPunkte());
			assertEquals(14, sf.zaehleLeere());

			int anzahlZweien = 0;
			for (int z = 0; z < 4; z++) {
				for (int s = 0; s < 4; s++) {
					int wert = sf.getStelle(z, s);
					assertTrue(wert == 0 || wert == 2, "Nur Kacheln mit 2 erlaubt: " + wert);
					if (wert == 2) {
						anzahlZweien++;
					}
				}
			}
			assertEquals(2, anzahlZweien);
		}

		// nur noch ein freies Feld: dort muss die neue Kachel landen
		int[][] feld = {
				{ 2, 4, 8, 16 },
				{ 2, 4, 8, 16 },
				{ 2, 4, 8, 0 },
				{ 2, 4, 8, 16 }
		};
		Spielfeld sf = new Spielfeld(feld);
		assertEquals(1, sf.zaehleLeere());
		sf.zufaelligeKachel();
		assertEquals(2, sf.getStelle(2, 3));
		assertEquals(0, sf.zaehleLeere());
	}

	@Test
	void testSetStelleUndZaehleLeere() {
		int[][] feld = {
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 }
		};
		Spielfeld sf = new Spielfeld(feld);
		assertEquals(16, sf.zaehleLeere());

		sf.setStelle(1, 2, 8);
		assertEquals(8, sf.getStelle(1, 2));
		assertEquals(15, sf.zaehleLeere());

		sf.setStelle(3, 3, 2048);
		assertEquals(2048, sf.getStelle(3, 3));
		assertEquals(14, sf.zaehleLeere());
	}

	@Test
	void testVerschiebe() {
		// ---------- LINKS ----------
		Spielfeld sf = testFeld();
		assertTrue(sf.verschiebe(Spielfeld.LINKS));
		pruefeZeile(sf, 0, 4, 4, 0, 0);
		pruefeZeile(sf, 1, 0, 0, 0, 0);
		pruefeZeile(sf, 2, 8, 8, 0, 0);
		pruefeZeile(sf, 3, 4, 0, 0, 0);
		assertEquals(24, sf.getPunkte());

		// ---------- RECHTS ----------
		sf = testFeld();
		assertTrue(sf.verschiebe(Spielfeld.RECHTS));
		pruefeZeile(sf, 0, 0, 0, 4, 4);
		pruefeZeile(sf, 1, 0, 0, 0, 0);
		pruefeZeile(sf, 2, 0, 0, 8, 8);
		pruefeZeile(sf, 3, 0, 0, 0, 4);
		assertEquals(24, sf.getPunkte());

		// ---------- OBEN ----------
		sf = testFeld();
		assertTrue(sf.verschiebe(Spielfeld.OBEN));
		pruefeZeile(sf, 0, 2, 2, 4, 8);
		pruefeZeile(sf, 1, 4, 4, 2, 0);
		pruefeZeile(sf, 2, 2, 0, 0, 0);
		pruefeZeile(sf, 3, 0, 0, 0, 0);
		assertEquals(8, sf.getPunkte());

		// ---------- UNTEN ----------
		sf = testFeld();
		assertTrue(sf.verschiebe(Spielfeld.UNTEN));
		pruefeZeile(sf, 0, 0, 0, 0, 0);
		pruefeZeile(sf, 1, 2, 0, 0, 0);
		pruefeZeile(sf, 2, 4, 2, 4, 0);
		pruefeZeile(sf, 3, 2, 4, 2, 8);
		assertEquals(8, sf.getPunkte());

		// ---------- keine Veraenderung ----------
		int[][] feld = {
				{ 2, 4, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 }
		};
		sf = new Spielfeld(feld);
		assertFalse(sf.verschiebe(Spielfeld.LINKS));
		pruefeZeile(sf, 0, 2, 4, 0, 0);
		assertEquals(0, sf.getPunkte());
	}

	@Test
	void testHatGewonnen() {
		assertFalse(testFeld().hatGewonnen());

		int[][] feld = {
				{ 2, 4, 8, 16 },
				{ 32, 64, 128, 256 },
				{ 512, 1024, 2048, 2 },
				{ 4, 8, 16, 32 }
		};
		assertTrue(new Spielfeld(feld).hatGewonnen());
	}

	@Test
	void testZugMoeglich() {
		// es gibt leere Felder
		assertTrue(testFeld().zugMoeglich());

		// voll, aber zwei gleiche Nachbarn
		int[][] mitPaar = {
				{ 2, 2, 4, 8 },
				{ 4, 8, 16, 32 },
				{ 2, 4, 8, 16 },
				{ 4, 8, 16, 32 }
		};
		assertTrue(new Spielfeld(mitPaar).zugMoeglich());

		// voll und keine zwei gleichen Nachbarn
		int[][] ohnePaar = {
				{ 2, 4, 2, 4 },
				{ 4, 2, 4, 2 },
				{ 2, 4, 2, 4 },
				{ 4, 2, 4, 2 }
		};
		assertFalse(new Spielfeld(ohnePaar).zugMoeglich());
	}

	/*
	 * *************************************************************
	 * Test fuer Klasse Spieler
	 *************************************************************/

	@Test
	void testSpieler() {
		Spieler s = new Spieler("Alex", 4);
		assertEquals("Alex", s.getName());
		assertNotNull(s.getSpielfeld());
		assertEquals(4, s.getSpielfeld().getN());
		assertEquals(14, s.getSpielfeld().zaehleLeere());

		s = new Spieler("Toni", 5);
		assertEquals("Toni", s.getName());
		assertEquals(5, s.getSpielfeld().getN());
		assertEquals(23, s.getSpielfeld().zaehleLeere());
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
	void testCheckRichtungUndRichtungAus() {
		assertTrue(Spiel.checkRichtung("l"));
		assertTrue(Spiel.checkRichtung("r"));
		assertTrue(Spiel.checkRichtung("o"));
		assertTrue(Spiel.checkRichtung("u"));

		assertFalse(Spiel.checkRichtung("x"));
		assertFalse(Spiel.checkRichtung("links"));
		assertFalse(Spiel.checkRichtung(""));

		assertEquals(Spielfeld.LINKS, Spiel.richtungAus("l"));
		assertEquals(Spielfeld.RECHTS, Spiel.richtungAus("r"));
		assertEquals(Spielfeld.OBEN, Spiel.richtungAus("o"));
		assertEquals(Spielfeld.UNTEN, Spiel.richtungAus("u"));
		assertEquals(-1, Spiel.richtungAus("x"));
	}
}
