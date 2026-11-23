import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitTests {

	/*
	 * Testbrett (7x7, Mauern als ##):
	 *        0   1   2   3   4   5   6
	 *   0 |   -   -   -   -   -   -   -
	 *   1 |   -  ##   -   -   -  ##   -
	 *   2 |   -   -  1Q   -   -  2K   -
	 *   3 |   -   -  2Q  ##   -   -   -
	 *   4 |   -   -   -   -  1K   -   -
	 *   5 |   -  ##   -   -   -  ##   -
	 *   6 |   -   -   -   -   -   -   -
	 */
	private Spielbrett testBrett(Spieler s1, Spieler s2) {
		Spielbrett b = new Spielbrett();
		b.setzeFigur(2, 2, s1.getFigur(0));   // Quadrat von Spieler 1
		b.setzeFigur(2, 5, s2.getFigur(4));   // Kreis von Spieler 2
		b.setzeFigur(4, 4, s1.getFigur(4));   // Kreis von Spieler 1
		b.setzeFigur(3, 2, s2.getFigur(0));   // Quadrat von Spieler 2
		return b;
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Figur
	 *************************************************************/

	@Test
	void testFigurNeu() {
		Spieler s = new Spieler("Alex", 1);
		Figur f = s.getFigur(0);

		assertNotNull(f);
		assertEquals(Figur.QUADRAT, f.getTyp());
		assertSame(s, f.getBesitzer());
		assertTrue(f.istImSpiel());
		assertEquals(-1, f.getZeile());
		assertEquals(-1, f.getSpalte());
	}

	@Test
	void testVerwandle() {
		Spieler s = new Spieler("Alex", 1);
		Figur f = s.getFigur(0);

		// Quadrat zieht gerade
		assertEquals(Figur.QUADRAT, f.getTyp());
		assertTrue(f.ziehtGerade());
		assertFalse(f.ziehtDiagonal());

		// nach der Verwandlung ist es ein Kreis und zieht diagonal
		f.verwandle();
		assertEquals(Figur.KREIS, f.getTyp());
		assertFalse(f.ziehtGerade());
		assertTrue(f.ziehtDiagonal());

		// und wieder zurueck
		f.verwandle();
		assertEquals(Figur.QUADRAT, f.getTyp());
		assertTrue(f.ziehtGerade());

		// auch ein Kreis verwandelt sich richtig
		Figur k = s.getFigur(4);
		assertEquals(Figur.KREIS, k.getTyp());
		k.verwandle();
		assertEquals(Figur.QUADRAT, k.getTyp());
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

		// vier Quadrate, danach drei Kreise - alle gehoeren diesem Spieler
		for (int i = 0; i < Spieler.ANZAHL_FIGUREN; i++) {
			assertNotNull(s.getFigur(i));
			assertSame(s, s.getFigur(i).getBesitzer());
			if (i < Spieler.ANZAHL_QUADRATE) {
				assertEquals(Figur.QUADRAT, s.getFigur(i).getTyp());
			} else {
				assertEquals(Figur.KREIS, s.getFigur(i).getTyp());
			}
		}

		Spieler t = new Spieler("Toni", 2);
		assertEquals(2, t.getNummer());
		assertSame(t, t.getFigur(6).getBesitzer());
	}

	@Test
	void testZaehleFigurenImSpiel() {
		Spieler s = new Spieler("Alex", 1);
		assertEquals(7, s.zaehleFigurenImSpiel());

		s.getFigur(0).schlagen();
		assertEquals(6, s.zaehleFigurenImSpiel());

		s.getFigur(5).schlagen();
		assertEquals(5, s.zaehleFigurenImSpiel());
	}

	/*
	 * *************************************************************
	 * Tests fuer Klasse Spielbrett
	 *************************************************************/

	@Test
	void testKonstruktorUndMauern() {
		Spielbrett b = new Spielbrett();
		assertEquals(7, b.getN());

		assertTrue(b.istMauer(1, 1));
		assertTrue(b.istMauer(1, 5));
		assertTrue(b.istMauer(3, 3));
		assertTrue(b.istMauer(5, 1));
		assertTrue(b.istMauer(5, 5));

		assertFalse(b.istMauer(0, 0));
		assertFalse(b.istMauer(2, 2));
		assertFalse(b.istMauer(6, 6));

		for (int z = 0; z < 7; z++) {
			for (int s = 0; s < 7; s++) {
				assertNull(b.getFigur(z, s));
			}
		}

		// ein Mauerfeld ist nicht frei
		assertFalse(b.istFrei(1, 1));
		assertTrue(b.istFrei(0, 0));

		assertTrue(b.imBrett(0, 0));
		assertTrue(b.imBrett(6, 6));
		assertFalse(b.imBrett(-1, 0));
		assertFalse(b.imBrett(0, -1));
		assertFalse(b.imBrett(7, 0));
		assertFalse(b.imBrett(0, 7));
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

		for (int s = 0; s < 7; s++) {
			assertSame(s1.getFigur(s), b.getFigur(6, s));
			assertSame(s2.getFigur(s), b.getFigur(0, s));
		}

		assertEquals(Figur.QUADRAT, b.getFigur(6, 0).getTyp());
		assertEquals(Figur.QUADRAT, b.getFigur(6, 3).getTyp());
		assertEquals(Figur.KREIS, b.getFigur(6, 4).getTyp());
		assertEquals(Figur.KREIS, b.getFigur(6, 6).getTyp());

		assertNull(b.getFigur(2, 2));

		assertEquals(6, s1.getFigur(0).getZeile());
		assertEquals(0, s1.getFigur(0).getSpalte());
		assertEquals(0, s2.getFigur(4).getZeile());
		assertEquals(4, s2.getFigur(4).getSpalte());
	}

	@Test
	void testIstZugGueltig() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		// Koordinaten ausserhalb des Brettes
		assertFalse(b.istZugGueltig(-1, 0, 0, 0, s1));
		assertFalse(b.istZugGueltig(2, 2, 7, 2, s1));
		assertFalse(b.istZugGueltig(2, 2, 2, 7, s1));

		// Start und Ziel gleich
		assertFalse(b.istZugGueltig(2, 2, 2, 2, s1));

		// Startfeld leer
		assertFalse(b.istZugGueltig(0, 0, 1, 0, s1));

		// Startfeld gehoert dem Gegner
		assertFalse(b.istZugGueltig(3, 2, 2, 2, s1));

		// ---------- Quadrat von Spieler 1 auf (2,2) ----------
		assertTrue(b.istZugGueltig(2, 2, 2, 0, s1));    // waagerecht, Weg frei
		assertTrue(b.istZugGueltig(2, 2, 2, 5, s1));    // schlaegt den gegnerischen Kreis
		assertTrue(b.istZugGueltig(2, 2, 0, 2, s1));    // senkrecht nach oben
		assertTrue(b.istZugGueltig(2, 2, 3, 2, s1));    // schlaegt das gegnerische Quadrat
		assertFalse(b.istZugGueltig(2, 2, 2, 6, s1));   // Weg durch (2,5) blockiert
		assertFalse(b.istZugGueltig(2, 2, 4, 2, s1));   // Weg durch (3,2) blockiert
		assertFalse(b.istZugGueltig(2, 2, 3, 3, s1));   // Quadrat zieht nicht diagonal

		// ---------- Kreis von Spieler 1 auf (4,4) ----------
		assertTrue(b.istZugGueltig(4, 4, 3, 5, s1));    // diagonal, Weg frei
		assertTrue(b.istZugGueltig(4, 4, 2, 6, s1));    // diagonal ueber zwei Felder
		assertTrue(b.istZugGueltig(4, 4, 5, 3, s1));
		assertTrue(b.istZugGueltig(4, 4, 6, 2, s1));
		assertFalse(b.istZugGueltig(4, 4, 3, 3, s1));   // Zielfeld ist eine Mauer
		assertFalse(b.istZugGueltig(4, 4, 2, 2, s1));   // Weg durch die Mauer (3,3)
		assertFalse(b.istZugGueltig(4, 4, 5, 5, s1));   // Zielfeld ist eine Mauer
		assertFalse(b.istZugGueltig(4, 4, 6, 6, s1));   // Weg durch die Mauer (5,5)
		assertFalse(b.istZugGueltig(4, 4, 4, 6, s1));   // Kreis zieht nicht gerade

		// ---------- Figuren von Spieler 2 ----------
		assertTrue(b.istZugGueltig(3, 2, 3, 0, s2));    // Quadrat waagerecht
		assertTrue(b.istZugGueltig(3, 2, 2, 2, s2));    // schlaegt das gegnerische Quadrat
		assertTrue(b.istZugGueltig(3, 2, 4, 2, s2));
		assertFalse(b.istZugGueltig(3, 2, 3, 4, s2));   // Weg durch die Mauer (3,3)
		assertFalse(b.istZugGueltig(3, 2, 2, 1, s2));   // Quadrat zieht nicht diagonal
		assertFalse(b.istZugGueltig(3, 2, 3, 0, s1));   // falscher Spieler

		assertTrue(b.istZugGueltig(2, 5, 1, 4, s2));    // Kreis diagonal
		assertTrue(b.istZugGueltig(2, 5, 4, 3, s2));    // Kreis diagonal ueber zwei Felder
		assertFalse(b.istZugGueltig(2, 5, 2, 2, s2));   // Kreis zieht nicht gerade
	}

	@Test
	void testFuehreZugAusVerwandelt() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		// Zug ohne Schlagen: das Quadrat wird zum Kreis
		Figur quadrat = s1.getFigur(0);
		assertNull(b.fuehreZugAus(2, 2, 2, 0));
		assertSame(quadrat, b.getFigur(2, 0));
		assertNull(b.getFigur(2, 2));
		assertEquals(2, quadrat.getZeile());
		assertEquals(0, quadrat.getSpalte());
		assertEquals(Figur.KREIS, quadrat.getTyp());
		assertTrue(quadrat.ziehtDiagonal());

		// Zug mit Schlagen
		s1 = new Spieler("Alex", 1);
		s2 = new Spieler("Toni", 2);
		b = testBrett(s1, s2);

		Figur zieher = s1.getFigur(0);
		Figur gegner = s2.getFigur(0);

		assertSame(gegner, b.fuehreZugAus(2, 2, 3, 2));
		assertSame(zieher, b.getFigur(3, 2));
		assertNull(b.getFigur(2, 2));
		assertFalse(gegner.istImSpiel());
		assertEquals(6, s2.zaehleFigurenImSpiel());
		assertEquals(Figur.KREIS, zieher.getTyp());
	}

	@Test
	void testHatZugMoeglichkeit() {
		Spieler s1 = new Spieler("Alex", 1);
		Spieler s2 = new Spieler("Toni", 2);
		Spielbrett b = testBrett(s1, s2);

		assertTrue(b.hatZugMoeglichkeit(s1));
		assertTrue(b.hatZugMoeglichkeit(s2));

		// ein Kreis in der Ecke (0,0) ist durch die Mauer (1,1) eingesperrt
		Spieler allein = new Spieler("Solo", 1);
		Spielbrett leer = new Spielbrett();
		leer.setzeFigur(0, 0, allein.getFigur(4));
		assertFalse(leer.hatZugMoeglichkeit(allein));

		// ein Quadrat in derselben Ecke kann dagegen ziehen
		Spieler zweiter = new Spieler("Solo2", 1);
		Spielbrett leer2 = new Spielbrett();
		leer2.setzeFigur(0, 0, zweiter.getFigur(0));
		assertTrue(leer2.hatZugMoeglichkeit(zweiter));
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