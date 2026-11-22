import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Testbrett (6x6):
	 *        0   1   2   3   4   5
	 *   0 |   -   -   -   -   -   -
	 *   1 |   -   -  15   -   -   -      kleiner Kreis von Spieler 1
	 *   2 |   -   -  12   -   -   -      grosses Quadrat von Spieler 1
	 *   3 |   -   -  23   -   -   -      kleines Dreieck von Spieler 2
	 *   4 |  11   -   -  14   -   -      kleines Quadrat / grosses Dreieck von Spieler 1
	 *   5 |   -   -   -   -   -   -
	 */
	private Spielbrett testBrett(Spieler s1, Spieler s2) {
		Spielbrett b = new Spielbrett();
		b.setzeFigur(1, 2, s1.getFigur(4));
		b.setzeFigur(2, 2, s1.getFigur(1));
		b.setzeFigur(3, 2, s2.getFigur(2));
		b.setzeFigur(4, 0, s1.getFigur(0));
		b.setzeFigur(4, 3, s1.getFigur(3));
		return b;
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Figur
	 *************************************************************/

	@Test
	void testFigurNeu() {
		Spieler s = new Spieler("Alex", 1);
		Figur f = s.getFigur(3);

		assertNotNull(f);
		assertEquals(4, f.getTyp());
		assertSame(s, f.getBesitzer());
		assertTrue(f.istImSpiel());
		assertEquals(-1, f.getZeile());
		assertEquals(-1, f.getSpalte());
	}

	@Test
	void testFigurEigenschaften() {
		Spieler s = new Spieler("Alex", 1);

		// Typ 1 - kleines Quadrat
		assertFalse(s.getFigur(0).istGross());
		assertTrue(s.getFigur(0).ziehtGerade());
		assertFalse(s.getFigur(0).ziehtDiagonal());

		// Typ 2 - grosses Quadrat
		assertTrue(s.getFigur(1).istGross());
		assertTrue(s.getFigur(1).ziehtGerade());
		assertFalse(s.getFigur(1).ziehtDiagonal());

		// Typ 3 - kleines Dreieck
		assertFalse(s.getFigur(2).istGross());
		assertFalse(s.getFigur(2).ziehtGerade());
		assertTrue(s.getFigur(2).ziehtDiagonal());

		// Typ 4 - grosses Dreieck
		assertTrue(s.getFigur(3).istGross());
		assertFalse(s.getFigur(3).ziehtGerade());
		assertTrue(s.getFigur(3).ziehtDiagonal());

		// Typ 5 - kleiner Kreis
		assertFalse(s.getFigur(4).istGross());
		assertTrue(s.getFigur(4).ziehtGerade());
		assertTrue(s.getFigur(4).ziehtDiagonal());

		// Typ 6 - grosser Kreis
		assertTrue(s.getFigur(5).istGross());
		assertTrue(s.getFigur(5).ziehtGerade());
		assertTrue(s.getFigur(5).ziehtDiagonal());
	}

	@Test
	void testSetzePositionUndSchlagen() {
		Spieler s = new Spieler("Alex", 1);
		Figur f = s.getFigur(0);

		f.setzePosition(2, 3);
		assertEquals(2, f.getZeile());
		assertEquals(3, f.getSpalte());
		assertTrue(f.istImSpiel());

		f.schlagen();
		assertFalse(f.istImSpiel());
		assertEquals(-1, f.getZeile());
		assertEquals(-1, f.getSpalte());
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

		// sechs Figuren mit den Typen 1 bis 6, alle gehoeren diesem Spieler
		for (int i = 0; i < Spieler.ANZAHL_FIGUREN; i++) {
			assertNotNull(s.getFigur(i));
			assertEquals(i + 1, s.getFigur(i).getTyp());
			assertSame(s, s.getFigur(i).getBesitzer());
		}

		Spieler t = new Spieler("Toni", 2);
		assertEquals("Toni", t.getName());
		assertEquals(2, t.getNummer());
		assertSame(t, t.getFigur(0).getBesitzer());
	}

	@Test
	void testZaehleFigurenImSpiel() {
		Spieler s = new Spieler("Alex", 1);
		assertEquals(6, s.zaehleFigurenImSpiel());

		s.getFigur(0).schlagen();
		assertEquals(5, s.zaehleFigurenImSpiel());

		s.getFigur(3).schlagen();
		assertEquals(4, s.zaehleFigurenImSpiel());
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielbrett
	 *************************************************************/

	@Test
	void testKonstruktorUndImBrett() {
		Spielbrett b = new Spielbrett();
		assertEquals(6, b.getN());

		for (int z = 0; z < 6; z++) {
			for (int s = 0; s < 6; s++) {
				assertNull(b.getFigur(z, s));
			}
		}

		assertTrue(b.imBrett(0, 0));
		assertTrue(b.imBrett(5, 5));
		assertFalse(b.imBrett(-1, 0));
		assertFalse(b.imBrett(0, -1));
		assertFalse(b.imBrett(6, 0));
		assertFalse(b.imBrett(0, 6));
	}

	@Test
	void testSetzeFigurUndIstFrei() {
		Spieler s = new Spieler("Alex", 1);
		Spielbrett b = new Spielbrett();
		Figur f = s.getFigur(2);

		assertTrue(b.istFrei(2, 3));

		b.setzeFigur(2, 3, f);
		assertSame(f, b.getFigur(2, 3));
		assertFalse(b.istFrei(2, 3));

		// die Figur kennt ihre neue Position
		assertEquals(2, f.getZeile());
		assertEquals(3, f.getSpalte());

		b.setzeFigur(2, 3, null);
		assertTrue(b.istFrei(2, 3));
		assertNull(b.getFigur(2, 3));
	}

	@Test
	void testGrundaufstellung() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = new Spielbrett();

		b.grundaufstellung(s1, s2);

		for (int s = 0; s < 6; s++) {
			assertSame(s1.getFigur(s), b.getFigur(5, s));
			assertSame(s2.getFigur(s), b.getFigur(0, s));
			assertEquals(s + 1, b.getFigur(5, s).getTyp());
		}

		assertNull(b.getFigur(2, 2));
		assertNull(b.getFigur(3, 0));

		// die Figuren kennen ihre Position
		assertEquals(5, s1.getFigur(0).getZeile());
		assertEquals(0, s1.getFigur(0).getSpalte());
		assertEquals(0, s2.getFigur(3).getZeile());
		assertEquals(3, s2.getFigur(3).getSpalte());
	}

	@Test
	void testIstZugGueltig() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		// Koordinaten ausserhalb des Brettes
		assertFalse(b.istZugGueltig(-1, 0, 0, 0, s1));
		assertFalse(b.istZugGueltig(0, -1, 0, 0, s1));
		assertFalse(b.istZugGueltig(2, 2, 6, 2, s1));
		assertFalse(b.istZugGueltig(2, 2, 2, 6, s1));

		// Start und Ziel gleich
		assertFalse(b.istZugGueltig(2, 2, 2, 2, s1));

		// Startfeld leer
		assertFalse(b.istZugGueltig(0, 0, 1, 1, s1));

		// Startfeld gehoert dem Gegner
		assertFalse(b.istZugGueltig(3, 2, 2, 2, s1));

		// Zielfeld mit eigener Figur besetzt
		assertFalse(b.istZugGueltig(2, 2, 1, 2, s1));

		// Grosses Quadrat (2,2)
		assertTrue(b.istZugGueltig(2, 2, 2, 5, s1));    // waagerecht, Weg frei
		assertTrue(b.istZugGueltig(2, 2, 2, 0, s1));    // waagerecht nach links
		assertTrue(b.istZugGueltig(2, 2, 3, 2, s1));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(2, 2, 4, 2, s1));   // Weg durch (3,2) blockiert
		assertFalse(b.istZugGueltig(2, 2, 4, 4, s1));   // Quadrat zieht nicht diagonal

		// Kleiner Kreis (1,2)
		assertTrue(b.istZugGueltig(1, 2, 0, 1, s1));    // diagonal 1 Feld
		assertTrue(b.istZugGueltig(1, 2, 1, 3, s1));    // gerade 1 Feld
		assertFalse(b.istZugGueltig(1, 2, 1, 4, s1));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(1, 2, 3, 4, s1));   // kleine Figur, 2 Felder diagonal

		// Kleines Quadrat (4,0)
		assertTrue(b.istZugGueltig(4, 0, 3, 0, s1));    // gerade 1 Feld
		assertFalse(b.istZugGueltig(4, 0, 2, 0, s1));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(4, 0, 3, 1, s1));   // Quadrat zieht nicht diagonal

		// Grosses Dreieck (4,3)
		assertTrue(b.istZugGueltig(4, 3, 2, 5, s1));    // diagonal, Weg frei
		assertTrue(b.istZugGueltig(4, 3, 3, 2, s1));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(4, 3, 2, 1, s1));   // Weg durch (3,2) blockiert
		assertFalse(b.istZugGueltig(4, 3, 4, 5, s1));   // Dreieck zieht nicht gerade
		assertFalse(b.istZugGueltig(4, 3, 2, 4, s1));   // weder gerade noch diagonal

		// Zuege von Spieler 2 mit dem kleinen Dreieck (3,2)
		assertTrue(b.istZugGueltig(3, 2, 4, 1, s2));    // diagonal 1 Feld
		assertTrue(b.istZugGueltig(3, 2, 4, 3, s2));    // schlaegt die gegnerische Figur
		assertFalse(b.istZugGueltig(3, 2, 2, 2, s2));   // Dreieck zieht nicht gerade
		assertFalse(b.istZugGueltig(3, 2, 5, 0, s2));   // kleine Figur, 2 Felder
		assertFalse(b.istZugGueltig(3, 2, 4, 1, s1));   // falscher Spieler
	}

	@Test
	void testFuehreZugAus() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		// Zug ohne Schlagen
		Figur kleinesQuadrat = s1.getFigur(0);
		assertNull(b.fuehreZugAus(4, 0, 3, 0));
		assertSame(kleinesQuadrat, b.getFigur(3, 0));
		assertNull(b.getFigur(4, 0));
		assertEquals(3, kleinesQuadrat.getZeile());
		assertEquals(0, kleinesQuadrat.getSpalte());

		// Zug mit Schlagen
		s1 = new Spieler("Alex", 1);
		s2 = new Spieler("Toni", 2);
		b = testBrett(s1, s2);

		Figur grossesQuadrat = s1.getFigur(1);
		Figur gegner = s2.getFigur(2);

		assertSame(gegner, b.fuehreZugAus(2, 2, 3, 2));
		assertSame(grossesQuadrat, b.getFigur(3, 2));
		assertNull(b.getFigur(2, 2));
		assertFalse(gegner.istImSpiel());
		assertEquals(5, s2.zaehleFigurenImSpiel());
	}

	@Test
	void testHatGrundlinieErreicht() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		assertFalse(b.hatGrundlinieErreicht(s1));
		assertFalse(b.hatGrundlinieErreicht(s2));

		// Spieler 1 erreicht die Zeile 0
		b.setzeFigur(0, 3, s1.getFigur(5));
		assertTrue(b.hatGrundlinieErreicht(s1));
		assertFalse(b.hatGrundlinieErreicht(s2));

		// Spieler 2 erreicht die Zeile 5
		b.setzeFigur(5, 1, s2.getFigur(5));
		assertTrue(b.hatGrundlinieErreicht(s2));
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
