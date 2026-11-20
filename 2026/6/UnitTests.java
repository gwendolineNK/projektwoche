import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/* 3x3-Stellung mit genau einem Stein auf (z, s) */
	private boolean[][] einStein(int z, int s) {
		boolean[][] f = new boolean[3][3];
		f[z][s] = true;
		return f;
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Stellung
	 *************************************************************/

	@Test
	void testDrehe90() {
		// obere linke Ecke wandert in die obere rechte Ecke
		boolean[][] gedreht = Stellung.drehe90(einStein(0, 0));
		assertTrue(gedreht[0][2]);
		assertFalse(gedreht[0][0]);
		assertFalse(gedreht[2][0]);
		assertFalse(gedreht[2][2]);

		// obere rechte Ecke wandert in die untere rechte Ecke
		gedreht = Stellung.drehe90(einStein(0, 2));
		assertTrue(gedreht[2][2]);

		// zwei Steine in der obersten Zeile werden zur rechten Spalte
		boolean[][] oben = new boolean[3][3];
		oben[0][0] = true;
		oben[0][1] = true;
		gedreht = Stellung.drehe90(oben);
		assertTrue(gedreht[0][2]);
		assertTrue(gedreht[1][2]);
		assertFalse(gedreht[2][2]);

		// das Original darf nicht veraendert werden
		assertTrue(oben[0][0]);
		assertTrue(oben[0][1]);
		assertFalse(oben[0][2]);

		// viermal drehen ergibt wieder das Original
		boolean[][] start = einStein(0, 1);
		boolean[][] rund = Stellung.drehe90(
				Stellung.drehe90(Stellung.drehe90(Stellung.drehe90(start))));
		assertTrue(Stellung.gleich(start, rund));
	}

	@Test
	void testGleich() {
		assertTrue(Stellung.gleich(einStein(0, 0), einStein(0, 0)));
		assertTrue(Stellung.gleich(new boolean[3][3], new boolean[3][3]));

		assertFalse(Stellung.gleich(einStein(0, 0), einStein(0, 2)));
		assertFalse(Stellung.gleich(einStein(0, 0), new boolean[3][3]));
		assertFalse(Stellung.gleich(new boolean[3][3], new boolean[4][4]));
	}

	@Test
	void testPuanGleich() {
		boolean[][] ecke = einStein(0, 0);

		// dieselbe Stellung
		assertTrue(Stellung.puanGleich(ecke, einStein(0, 0)));

		// die vier Ecken sind alle puan-gleich
		assertTrue(Stellung.puanGleich(ecke, einStein(0, 2)));
		assertTrue(Stellung.puanGleich(ecke, einStein(2, 2)));
		assertTrue(Stellung.puanGleich(ecke, einStein(2, 0)));

		// Kantenmitte ist zu keiner Ecke puan-gleich
		assertFalse(Stellung.puanGleich(ecke, einStein(0, 1)));
		assertFalse(Stellung.puanGleich(ecke, einStein(1, 1)));

		// die vier Kantenmitten sind untereinander puan-gleich
		assertTrue(Stellung.puanGleich(einStein(0, 1), einStein(1, 2)));
		assertTrue(Stellung.puanGleich(einStein(0, 1), einStein(2, 1)));

		// leere Stellungen
		assertTrue(Stellung.puanGleich(new boolean[3][3], new boolean[3][3]));
		assertFalse(Stellung.puanGleich(new boolean[3][3], ecke));
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielfeld
	 *************************************************************/

	@Test
	void testKonstruktor() {
		Spielfeld b = new Spielfeld(3, 100);
		assertEquals(3, b.getN());
		assertEquals(0, b.zaehleSteine());
		assertEquals(0, b.getAnzahlVerlauf());
		for (int z = 0; z < 3; z++) {
			for (int s = 0; s < 3; s++) {
				assertFalse(b.istBesetzt(z, s));
			}
		}

		b = new Spielfeld(5, 50);
		assertEquals(5, b.getN());
		assertFalse(b.istBesetzt(4, 4));
	}

	@Test
	void testZugAusfuehren() {
		Spielfeld b = new Spielfeld(3, 100);

		// Stein setzen
		assertTrue(b.zugAusfuehren(0, 0));
		assertTrue(b.istBesetzt(0, 0));
		assertEquals(1, b.zaehleSteine());

		// Stein wegnehmen
		assertFalse(b.zugAusfuehren(0, 0));
		assertFalse(b.istBesetzt(0, 0));
		assertEquals(0, b.zaehleSteine());

		b.zugAusfuehren(1, 1);
		b.zugAusfuehren(2, 2);
		assertEquals(2, b.zaehleSteine());
	}

	@Test
	void testImFeld() {
		Spielfeld b = new Spielfeld(3, 100);

		assertTrue(b.imFeld(0, 0));
		assertTrue(b.imFeld(2, 2));
		assertFalse(b.imFeld(-1, 0));
		assertFalse(b.imFeld(0, -1));
		assertFalse(b.imFeld(3, 0));
		assertFalse(b.imFeld(0, 3));
	}

	@Test
	void testZaehleSteine() {
		assertEquals(0, new Spielfeld(3, 100).zaehleSteine());
		assertEquals(1, new Spielfeld(einStein(1, 1)).zaehleSteine());

		boolean[][] drei = new boolean[3][3];
		drei[0][0] = true;
		drei[1][1] = true;
		drei[2][2] = true;
		assertEquals(3, new Spielfeld(drei).zaehleSteine());
	}

	@Test
	void testMerkeStellung() {
		Spielfeld b = new Spielfeld(3, 100);
		assertEquals(0, b.getAnzahlVerlauf());

		b.merkeStellung();
		assertEquals(1, b.getAnzahlVerlauf());

		b.zugAusfuehren(0, 0);
		b.merkeStellung();
		assertEquals(2, b.getAnzahlVerlauf());
	}

	@Test
	void testStellungKamSchonVor() {
		// ---------- noch nichts gemerkt ----------
		Spielfeld b = new Spielfeld(3, 100);
		assertFalse(b.stellungKamSchonVor());

		// ---------- zurueck zur leeren Anfangsstellung ----------
		b.merkeStellung();                 // leere Stellung merken
		b.zugAusfuehren(0, 0);
		assertFalse(b.stellungKamSchonVor());

		b.merkeStellung();                 // Stellung mit einer Ecke merken
		b.zugAusfuehren(0, 0);             // wieder leer
		assertTrue(b.stellungKamSchonVor());

		// ---------- gedrehte Stellung zaehlt auch ----------
		b = new Spielfeld(3, 100);
		b.zugAusfuehren(0, 0);
		b.merkeStellung();                 // Stein in der oberen linken Ecke
		b.zugAusfuehren(0, 0);             // wieder leer
		b.zugAusfuehren(0, 2);             // Stein in der oberen rechten Ecke
		assertTrue(b.stellungKamSchonVor());

		// ---------- keine Uebereinstimmung ----------
		b = new Spielfeld(3, 100);
		b.zugAusfuehren(0, 0);
		b.merkeStellung();
		b.zugAusfuehren(0, 0);             // wieder leer
		b.zugAusfuehren(1, 1);             // Stein in der Mitte
		assertFalse(b.stellungKamSchonVor());
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
	void testGetGegner() {
		assertEquals(2, new Spieler("Alex", 1).getGegner());
		assertEquals(1, new Spieler("Toni", 2).getGegner());
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
