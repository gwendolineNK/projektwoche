import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/* *****************************************************************************************************
 * NICHT AENDERN!
 * Diese Klasse dient Ihnen zur Ueberpruefung Ihres Codes.
 ****************************************************************************************************** */

class UnitTests {

	private Spieler alex;
	private Spieler toni;

	/*
	 * Hilfsmethode: erzeugt die beiden Spieler und macht sie zu Gegnern.
	 */
	private void erzeugeSpieler() {
		alex = new Spieler("Alex", "X");
		toni = new Spieler("Toni", "O");
		alex.setzeGegner(toni);
	}

	@Test
	void testSpielerKonstruktor() {
		Spieler spieler = new Spieler("Alex", "X");
		assertEquals("Alex", spieler.getName());
		assertEquals("X", spieler.getZeichen());
		assertNull(spieler.getGegner());
	}

	@Test
	void testSetzeGegner() {
		erzeugeSpieler();
		assertSame(toni, alex.getGegner());
		assertSame(alex, toni.getGegner());
	}

	@Test
	void testSteinKonstruktor() {
		erzeugeSpieler();
		Stein stein = new Stein(alex, 2, 3);
		assertSame(alex, stein.getBesitzer());
		assertEquals(2, stein.getZeile());
		assertEquals(3, stein.getSpalte());
	}

	@Test
	void testSetzeBesitzerUndGehoert() {
		erzeugeSpieler();
		Stein stein = new Stein(alex, 2, 3);
		assertTrue(stein.gehoert(alex));
		assertFalse(stein.gehoert(toni));

		stein.setzeBesitzer(toni);
		assertSame(toni, stein.getBesitzer());
		assertTrue(stein.gehoert(toni));
		assertFalse(stein.gehoert(alex));
		// Position bleibt unveraendert
		assertEquals(2, stein.getZeile());
		assertEquals(3, stein.getSpalte());
	}

	@Test
	void testSpielbrettKonstruktor() {
		Spielbrett brett = new Spielbrett(6);
		assertEquals(6, brett.getN());
		for (int z = 0; z < 6; z++) {
			for (int s = 0; s < 6; s++) {
				assertNull(brett.getStein(z, s));
			}
		}
	}

	@Test
	void testImFeldUndIstFrei() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		assertTrue(brett.imFeld(0, 0));
		assertTrue(brett.imFeld(5, 5));
		assertFalse(brett.imFeld(-1, 0));
		assertFalse(brett.imFeld(0, -1));
		assertFalse(brett.imFeld(6, 0));
		assertFalse(brett.imFeld(0, 6));

		assertTrue(brett.istFrei(2, 2));
		brett.setzeStein(2, 2, alex);
		assertFalse(brett.istFrei(2, 2));
		// ausserhalb des Brettes ist nichts frei
		assertFalse(brett.istFrei(-1, 0));
		assertFalse(brett.istFrei(6, 6));
	}

	@Test
	void testSetzeStein() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		brett.setzeStein(4, 1, toni);
		Stein stein = brett.getStein(4, 1);
		assertNotNull(stein);
		assertSame(toni, stein.getBesitzer());
		assertEquals(4, stein.getZeile());
		assertEquals(1, stein.getSpalte());
	}

	@Test
	void testGrundaufstellung() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		brett.grundaufstellung(alex, toni);

		assertSame(toni, brett.getStein(2, 2).getBesitzer());
		assertSame(toni, brett.getStein(3, 3).getBesitzer());
		assertSame(alex, brett.getStein(2, 3).getBesitzer());
		assertSame(alex, brett.getStein(3, 2).getBesitzer());

		assertNull(brett.getStein(0, 0));
		assertNull(brett.getStein(2, 1));
		assertEquals(2, brett.zaehleSteine(alex));
		assertEquals(2, brett.zaehleSteine(toni));
	}

	@Test
	void testZaehleRichtung() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		brett.setzeStein(1, 1, toni);
		brett.setzeStein(1, 2, alex);

		// Alex auf (1,0): nach rechts (k=4) wird ein Stein eingeschlossen
		assertEquals(1, brett.zaehleRichtung(1, 0, alex, 4));
		// nach links (k=3) liegt der Rand
		assertEquals(0, brett.zaehleRichtung(1, 0, alex, 3));
		// nach unten rechts (k=7) ist das Feld frei
		assertEquals(0, brett.zaehleRichtung(1, 0, alex, 7));
		// nach oben (k=1) ist das Feld frei
		assertEquals(0, brett.zaehleRichtung(1, 0, alex, 1));

		// Toni auf (1,3): nach links (k=3) wird ein Stein eingeschlossen
		assertEquals(1, brett.zaehleRichtung(1, 3, toni, 3));
		// Alex auf (1,3): nach links liegt direkt sein eigener Stein -> nichts
		assertEquals(0, brett.zaehleRichtung(1, 3, alex, 3));
	}

	/*
	 * PFLICHTTEST! Dieser Test muss bestanden werden.
	 */
	@Test
	void testIstZugGueltig() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		brett.grundaufstellung(alex, toni);

		// die vier gueltigen Zuege von Alex
		assertTrue(brett.istZugGueltig(1, 2, alex));
		assertTrue(brett.istZugGueltig(2, 1, alex));
		assertTrue(brett.istZugGueltig(3, 4, alex));
		assertTrue(brett.istZugGueltig(4, 3, alex));

		// kein Gegner eingeschlossen
		assertFalse(brett.istZugGueltig(2, 4, alex));
		assertFalse(brett.istZugGueltig(0, 0, alex));
		assertFalse(brett.istZugGueltig(1, 1, alex));

		// Feld ist belegt
		assertFalse(brett.istZugGueltig(2, 2, alex));
		assertFalse(brett.istZugGueltig(2, 3, alex));

		// ausserhalb des Brettes
		assertFalse(brett.istZugGueltig(-1, 2, alex));
		assertFalse(brett.istZugGueltig(6, 2, alex));

		// die vier gueltigen Zuege von Toni
		assertTrue(brett.istZugGueltig(1, 3, toni));
		assertTrue(brett.istZugGueltig(2, 4, toni));
		assertFalse(brett.istZugGueltig(2, 1, toni));
	}

	@Test
	void testSetzeUndDrehe() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		brett.grundaufstellung(alex, toni);

		Stein alterStein = brett.getStein(2, 2);
		assertEquals(1, brett.setzeUndDrehe(2, 1, alex));

		// der umgedrehte Stein ist DASSELBE Objekt mit neuem Besitzer
		assertSame(alterStein, brett.getStein(2, 2));
		assertSame(alex, brett.getStein(2, 2).getBesitzer());
		assertSame(alex, brett.getStein(2, 1).getBesitzer());
		assertEquals(4, brett.zaehleSteine(alex));
		assertEquals(1, brett.zaehleSteine(toni));

		// Zug, der in zwei Richtungen gleichzeitig dreht
		Spielbrett brett2 = new Spielbrett(6);
		brett2.setzeStein(2, 1, toni);
		brett2.setzeStein(2, 2, toni);
		brett2.setzeStein(2, 3, alex);
		brett2.setzeStein(3, 1, toni);
		brett2.setzeStein(4, 2, alex);

		assertEquals(3, brett2.setzeUndDrehe(2, 0, alex));
		assertEquals(6, brett2.zaehleSteine(alex));
		assertEquals(0, brett2.zaehleSteine(toni));
	}

	@Test
	void testZaehleSteineUndVoll() {
		erzeugeSpieler();
		Spielbrett brett = new Spielbrett(6);
		assertEquals(0, brett.zaehleSteine(alex));
		assertFalse(brett.istVoll());
		assertFalse(brett.hatGueltigenZug(alex));

		brett.grundaufstellung(alex, toni);
		assertEquals(2, brett.zaehleSteine(alex));
		assertEquals(2, brett.zaehleSteine(toni));
		assertFalse(brett.istVoll());
		assertTrue(brett.hatGueltigenZug(alex));
		assertTrue(brett.hatGueltigenZug(toni));

		// volles Brett: niemand kann mehr ziehen
		Spielbrett voll = new Spielbrett(4);
		for (int z = 0; z < 4; z++) {
			for (int s = 0; s < 4; s++) {
				voll.setzeStein(z, s, alex);
			}
		}
		assertTrue(voll.istVoll());
		assertEquals(16, voll.zaehleSteine(alex));
		assertEquals(0, voll.zaehleSteine(toni));
		assertFalse(voll.hatGueltigenZug(alex));
		assertFalse(voll.hatGueltigenZug(toni));
	}
}
