import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/* *****************************************************************************************************
 * NICHT AENDERN!
 * Diese Klasse dient Ihnen zur Ueberpruefung Ihres Codes.
 ****************************************************************************************************** */

class UnitTests {

	/*
	 * Hilfsmethode: erzeugt einen Spieler mit Spielfeldgroesse 6 und
	 * platziert seine drei Schiffe:
	 * Schiff 0 (Laenge 3) waagerecht ab (0,0) -> (0,0) (0,1) (0,2)
	 * Schiff 1 (Laenge 2) senkrecht ab (2,3) -> (2,3) (3,3)
	 * Schiff 2 (Laenge 2) waagerecht ab (5,4) -> (5,4) (5,5)
	 */
	private Spieler vorbereiteterSpieler() {
		Spieler spieler = new Spieler("Alex", 6);
		spieler.getSchiff(0).platziere(0, 0, true);
		spieler.getSchiff(1).platziere(2, 3, false);
		spieler.getSchiff(2).platziere(5, 4, true);
		return spieler;
	}

	@Test
	void testSpielfeldKonstruktor() {
		Spielfeld feld = new Spielfeld(6);
		assertEquals(6, feld.getN());
		for (int z = 0; z < 6; z++) {
			for (int s = 0; s < 6; s++) {
				assertEquals(Spielfeld.LEER, feld.getStelle(z, s));
			}
		}
	}

	@Test
	void testImFeld() {
		Spielfeld feld = new Spielfeld(6);
		assertTrue(feld.imFeld(0, 0));
		assertTrue(feld.imFeld(5, 5));
		assertTrue(feld.imFeld(3, 2));
		assertFalse(feld.imFeld(-1, 0));
		assertFalse(feld.imFeld(0, -1));
		assertFalse(feld.imFeld(6, 0));
		assertFalse(feld.imFeld(0, 6));
	}

	@Test
	void testMarkiereUndIstBeschossen() {
		Spielfeld feld = new Spielfeld(6);
		assertFalse(feld.istBeschossen(2, 3));
		feld.markiere(2, 3, Spielfeld.WASSER);
		assertEquals(Spielfeld.WASSER, feld.getStelle(2, 3));
		assertTrue(feld.istBeschossen(2, 3));
		feld.markiere(4, 1, Spielfeld.TREFFER);
		assertEquals(Spielfeld.TREFFER, feld.getStelle(4, 1));
		assertTrue(feld.istBeschossen(4, 1));
		assertFalse(feld.istBeschossen(0, 0));
	}

	@Test
	void testSchiffKonstruktor() {
		Schiff schiff = new Schiff(3);
		assertEquals(3, schiff.getLaenge());
		assertEquals(-1, schiff.getZeile());
		assertEquals(-1, schiff.getSpalte());
		assertEquals(0, schiff.getTreffer());
		assertTrue(schiff.istWaagerecht());
		// Ein nicht platziertes Schiff belegt kein Feld
		assertFalse(schiff.belegt(0, 0));
		assertFalse(schiff.belegt(-1, -1));
	}

	@Test
	void testPlatziere() {
		Schiff schiff = new Schiff(2);
		schiff.platziere(4, 1, false);
		assertEquals(4, schiff.getZeile());
		assertEquals(1, schiff.getSpalte());
		assertFalse(schiff.istWaagerecht());
		schiff.platziere(0, 2, true);
		assertEquals(0, schiff.getZeile());
		assertEquals(2, schiff.getSpalte());
		assertTrue(schiff.istWaagerecht());
	}

	@Test
	void testBelegtWaagerecht() {
		Schiff schiff = new Schiff(3);
		schiff.platziere(2, 1, true);
		assertTrue(schiff.belegt(2, 1));
		assertTrue(schiff.belegt(2, 2));
		assertTrue(schiff.belegt(2, 3));
		assertFalse(schiff.belegt(2, 0));
		assertFalse(schiff.belegt(2, 4));
		assertFalse(schiff.belegt(1, 2));
		assertFalse(schiff.belegt(3, 2));
	}

	@Test
	void testBelegtSenkrecht() {
		Schiff schiff = new Schiff(3);
		schiff.platziere(2, 1, false);
		assertTrue(schiff.belegt(2, 1));
		assertTrue(schiff.belegt(3, 1));
		assertTrue(schiff.belegt(4, 1));
		assertFalse(schiff.belegt(1, 1));
		assertFalse(schiff.belegt(5, 1));
		assertFalse(schiff.belegt(3, 0));
		assertFalse(schiff.belegt(3, 2));
	}

	@Test
	void testTreffer() {
		Schiff schiff = new Schiff(3);
		assertEquals(0, schiff.getTreffer());
		schiff.treffer();
		assertEquals(1, schiff.getTreffer());
		schiff.treffer();
		assertEquals(2, schiff.getTreffer());
	}

	@Test
	void testVersenkt() {
		Spieler spieler = vorbereiteterSpieler();
		assertFalse(spieler.getSchiff(0).istVersenkt());
		assertEquals(0, spieler.zaehleVersenkte());
		assertFalse(spieler.alleSchiffeVersenkt());

		// Schiff 1 der Laenge 2 zweimal treffen
		spieler.getSchiff(1).treffer();
		assertFalse(spieler.getSchiff(1).istVersenkt());
		spieler.getSchiff(1).treffer();
		assertTrue(spieler.getSchiff(1).istVersenkt());
		assertEquals(1, spieler.zaehleVersenkte());
		assertFalse(spieler.alleSchiffeVersenkt());

		// die beiden uebrigen Schiffe ebenfalls versenken
		for (int i = 0; i < 3; i++) {
			spieler.getSchiff(0).treffer();
		}
		spieler.getSchiff(2).treffer();
		spieler.getSchiff(2).treffer();
		assertEquals(3, spieler.zaehleVersenkte());
		assertTrue(spieler.alleSchiffeVersenkt());
	}

	@Test
	void testSpielerKonstruktor() {
		Spieler spieler = new Spieler("Alex", 6);
		assertEquals("Alex", spieler.getName());
		assertEquals(6, spieler.getSpielfeld().getN());
		for (int i = 0; i < Spieler.ANZAHL_SCHIFFE; i++) {
			assertEquals(Spieler.LAENGEN[i], spieler.getSchiff(i).getLaenge());
			assertEquals(0, spieler.getSchiff(i).getTreffer());
			assertEquals(-1, spieler.getSchiff(i).getZeile());
		}
	}

	/*
	 * PFLICHTTEST! Dieser Test muss bestanden werden.
	 */
	@Test
	void testSchiffAn() {
		Spieler spieler = vorbereiteterSpieler();

		assertSame(spieler.getSchiff(0), spieler.schiffAn(0, 0));
		assertSame(spieler.getSchiff(0), spieler.schiffAn(0, 1));
		assertSame(spieler.getSchiff(0), spieler.schiffAn(0, 2));

		assertSame(spieler.getSchiff(1), spieler.schiffAn(2, 3));
		assertSame(spieler.getSchiff(1), spieler.schiffAn(3, 3));

		assertSame(spieler.getSchiff(2), spieler.schiffAn(5, 4));
		assertSame(spieler.getSchiff(2), spieler.schiffAn(5, 5));

		assertNull(spieler.schiffAn(0, 3));
		assertNull(spieler.schiffAn(1, 1));
		assertNull(spieler.schiffAn(4, 3));
		assertNull(spieler.schiffAn(5, 3));
	}

	@Test
	void testPasstSchiff() {
		Spieler spieler = new Spieler("Alex", 6);

		// leeres Spielfeld: alles, was hineinpasst, ist gueltig
		assertTrue(spieler.passtSchiff(3, 0, 0, true));
		assertTrue(spieler.passtSchiff(3, 0, 3, true));
		assertTrue(spieler.passtSchiff(3, 3, 5, false));

		// ragt aus dem Spielfeld heraus
		assertFalse(spieler.passtSchiff(3, 0, 4, true));
		assertFalse(spieler.passtSchiff(3, 4, 0, false));
		assertFalse(spieler.passtSchiff(2, 5, 5, true));

		// nach dem Platzieren des ersten Schiffes auf (0,0) (0,1) (0,2)
		spieler.getSchiff(0).platziere(0, 0, true);
		assertFalse(spieler.passtSchiff(2, 0, 2, true));
		assertFalse(spieler.passtSchiff(2, 0, 1, false));
		assertTrue(spieler.passtSchiff(2, 0, 3, true));
		assertTrue(spieler.passtSchiff(2, 1, 0, false));
	}
}
