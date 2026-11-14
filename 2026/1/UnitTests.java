import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Testbrett:
	 *        0   1   2   3   4   5
	 *  0 |   -   -   -   -   -   -
	 *  1 |   -   -   5   -   -   -      kleiner Kreis (Spieler 1)
	 *  2 |   -   -   2   -   -   -      grosses Quadrat (Spieler 1)
	 *  3 |   -   -  -3   -   -   -      kleines Dreieck (Spieler 2)
	 *  4 |   1   -   -   4   -   -      kleines Quadrat / grosses Dreieck (Spieler 1)
	 *  5 |   -   -   -   -   -   -
	 */
	private Spielbrett testBrett() {
		int[][] feld = {
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 5, 0, 0, 0 },
				{ 0, 0, 2, 0, 0, 0 },
				{ 0, 0, -3, 0, 0, 0 },
				{ 1, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }
		};
		return new Spielbrett(feld);
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Figur
	 *************************************************************/

	@Test
	void testTypUndBesitzer() {
		assertEquals(0, Figur.typ(0));
		assertEquals(1, Figur.typ(1));
		assertEquals(4, Figur.typ(4));
		assertEquals(4, Figur.typ(-4));
		assertEquals(6, Figur.typ(-6));

		assertEquals(0, Figur.besitzer(0));
		assertEquals(1, Figur.besitzer(3));
		assertEquals(1, Figur.besitzer(6));
		assertEquals(2, Figur.besitzer(-3));
		assertEquals(2, Figur.besitzer(-1));
	}

	@Test
	void testIstGross() {
		assertTrue(Figur.istGross(2));
		assertTrue(Figur.istGross(4));
		assertTrue(Figur.istGross(6));
		assertTrue(Figur.istGross(-2));
		assertTrue(Figur.istGross(-6));

		assertFalse(Figur.istGross(1));
		assertFalse(Figur.istGross(3));
		assertFalse(Figur.istGross(5));
		assertFalse(Figur.istGross(-1));
		assertFalse(Figur.istGross(-5));
		assertFalse(Figur.istGross(0));
	}

	@Test
	void testZugarten() {
		// Quadrate und Kreise ziehen gerade
		assertTrue(Figur.ziehtGerade(1));
		assertTrue(Figur.ziehtGerade(2));
		assertTrue(Figur.ziehtGerade(5));
		assertTrue(Figur.ziehtGerade(6));
		assertTrue(Figur.ziehtGerade(-1));
		assertTrue(Figur.ziehtGerade(-6));
		assertFalse(Figur.ziehtGerade(3));
		assertFalse(Figur.ziehtGerade(4));
		assertFalse(Figur.ziehtGerade(-4));
		assertFalse(Figur.ziehtGerade(0));

		// Dreiecke und Kreise ziehen diagonal
		assertTrue(Figur.ziehtDiagonal(3));
		assertTrue(Figur.ziehtDiagonal(4));
		assertTrue(Figur.ziehtDiagonal(5));
		assertTrue(Figur.ziehtDiagonal(6));
		assertTrue(Figur.ziehtDiagonal(-3));
		assertTrue(Figur.ziehtDiagonal(-5));
		assertFalse(Figur.ziehtDiagonal(1));
		assertFalse(Figur.ziehtDiagonal(2));
		assertFalse(Figur.ziehtDiagonal(-2));
		assertFalse(Figur.ziehtDiagonal(0));
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
	 * Tests fuer Klasse Spielbrett
	 *************************************************************/

	@Test
	void testGrundaufstellung() {
		Spielbrett b = new Spielbrett();
		assertEquals(6, b.getN());

		for (int spalte = 0; spalte < 6; spalte++) {
			assertEquals(spalte + 1, b.getStelle(5, spalte));
			assertEquals(-(spalte + 1), b.getStelle(0, spalte));
		}

		for (int zeile = 1; zeile <= 4; zeile++) {
			for (int spalte = 0; spalte < 6; spalte++) {
				assertEquals(0, b.getStelle(zeile, spalte));
			}
		}
	}

	@Test
	void testSetStelleUndImBrett() {
		Spielbrett b = new Spielbrett();

		b.setStelle(2, 3, 6);
		assertEquals(6, b.getStelle(2, 3));
		b.setStelle(0, 0, -4);
		assertEquals(-4, b.getStelle(0, 0));

		assertTrue(b.imBrett(0, 0));
		assertTrue(b.imBrett(5, 5));
		assertFalse(b.imBrett(-1, 0));
		assertFalse(b.imBrett(0, -1));
		assertFalse(b.imBrett(6, 0));
		assertFalse(b.imBrett(0, 6));
	}

	@Test
	void testIstZugGueltig() {
		Spielbrett b = testBrett();

		// Koordinaten ausserhalb des Brettes
		assertFalse(b.istZugGueltig(-1, 0, 0, 0, 1));
		assertFalse(b.istZugGueltig(0, -1, 0, 0, 1));
		assertFalse(b.istZugGueltig(2, 2, 6, 2, 1));
		assertFalse(b.istZugGueltig(2, 2, 2, 6, 1));

		// Start und Ziel gleich
		assertFalse(b.istZugGueltig(2, 2, 2, 2, 1));

		// Startfeld leer
		assertFalse(b.istZugGueltig(0, 0, 1, 1, 1));

		// Startfeld gehoert dem Gegner
		assertFalse(b.istZugGueltig(3, 2, 2, 2, 1));

		// Zielfeld mit eigener Figur besetzt
		assertFalse(b.istZugGueltig(2, 2, 1, 2, 1));

		// Grosses Quadrat (2,2)
		assertTrue(b.istZugGueltig(2, 2, 2, 5, 1));    // waagerecht, Weg frei
		assertTrue(b.istZugGueltig(2, 2, 2, 0, 1));    // waagerecht nach links
		assertTrue(b.istZugGueltig(2, 2, 3, 2, 1));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(2, 2, 4, 2, 1));   // Weg durch (3,2) blockiert
		assertFalse(b.istZugGueltig(2, 2, 4, 4, 1));   // Quadrat zieht nicht diagonal

		// Kleiner Kreis (1,2)
		assertTrue(b.istZugGueltig(1, 2, 0, 1, 1));    // diagonal 1 Feld
		assertTrue(b.istZugGueltig(1, 2, 1, 3, 1));    // gerade 1 Feld
		assertFalse(b.istZugGueltig(1, 2, 1, 4, 1));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(1, 2, 3, 4, 1));   // kleine Figur, 2 Felder diagonal

		// Kleines Quadrat (4,0)
		assertTrue(b.istZugGueltig(4, 0, 3, 0, 1));    // gerade 1 Feld
		assertFalse(b.istZugGueltig(4, 0, 2, 0, 1));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(4, 0, 3, 1, 1));   // Quadrat zieht nicht diagonal

		// Grosses Dreieck (4,3)
		assertTrue(b.istZugGueltig(4, 3, 2, 5, 1));    // diagonal, Weg frei
		assertTrue(b.istZugGueltig(4, 3, 3, 2, 1));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(4, 3, 2, 1, 1));   // Weg durch (3,2) blockiert
		assertFalse(b.istZugGueltig(4, 3, 4, 5, 1));   // Dreieck zieht nicht gerade
		assertFalse(b.istZugGueltig(4, 3, 2, 4, 1));   // weder gerade noch diagonal

		// Zuege von Spieler 2 mit dem kleinen Dreieck (3,2)
		assertTrue(b.istZugGueltig(3, 2, 4, 1, 2));    // diagonal 1 Feld
		assertTrue(b.istZugGueltig(3, 2, 4, 3, 2));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(3, 2, 2, 2, 2));   // Dreieck zieht nicht gerade
		assertFalse(b.istZugGueltig(3, 2, 5, 0, 2));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(3, 2, 4, 1, 1));   // falscher Spieler
	}

	@Test
	void testFuehreZugAus() {
		Spielbrett b = testBrett();

		// Zug ohne Schlagen
		assertEquals(0, b.fuehreZugAus(4, 0, 3, 0));
		assertEquals(1, b.getStelle(3, 0));
		assertEquals(0, b.getStelle(4, 0));

		// Zug mit Schlagen
		b = testBrett();
		assertEquals(-3, b.fuehreZugAus(2, 2, 3, 2));
		assertEquals(2, b.getStelle(3, 2));
		assertEquals(0, b.getStelle(2, 2));
	}

	@Test
	void testZaehleFiguren() {
		Spielbrett b = testBrett();
		assertEquals(4, b.zaehleFiguren(1));
		assertEquals(1, b.zaehleFiguren(2));

		b = new Spielbrett();
		assertEquals(6, b.zaehleFiguren(1));
		assertEquals(6, b.zaehleFiguren(2));
	}

	@Test
	void testHatGrundlinieErreicht() {
		Spielbrett b = testBrett();
		assertFalse(b.hatGrundlinieErreicht(1));
		assertFalse(b.hatGrundlinieErreicht(2));

		b = new Spielbrett();
		assertFalse(b.hatGrundlinieErreicht(1));
		assertFalse(b.hatGrundlinieErreicht(2));

		// Spieler 1 erreicht Zeile 0
		int[][] feld1 = {
				{ 0, 0, 0, 0, 3, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 }
		};
		b = new Spielbrett(feld1);
		assertTrue(b.hatGrundlinieErreicht(1));
		assertFalse(b.hatGrundlinieErreicht(2));

		// Spieler 2 erreicht Zeile 5
		int[][] feld2 = {
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0 },
				{ 0, -2, 0, 0, 0, 0 }
		};
		b = new Spielbrett(feld2);
		assertFalse(b.hatGrundlinieErreicht(1));
		assertTrue(b.hatGrundlinieErreicht(2));
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
