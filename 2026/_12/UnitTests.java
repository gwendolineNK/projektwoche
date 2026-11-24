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

	/*
	 * Hilfsmethode: Schlachtfeld 5x5 mit vier Soeldnern rund um (2,2).
	 *
	 *       0   1   2   3   4
	 *   0 |  .   .   .   .   .
	 *   1 |  .   .  O3   .   .
	 *   2 |  .   .  X3  O2   .
	 *   3 |  .   .  X1   .   .
	 *   4 |  .   .   .   .   .
	 */
	private Schlachtfeld vorbereitetesFeld() {
		Schlachtfeld sf = new Schlachtfeld(5);
		sf.setzeSoeldner(2, 2, 3, alex);
		sf.setzeSoeldner(2, 3, 2, toni);
		sf.setzeSoeldner(1, 2, 3, toni);
		sf.setzeSoeldner(3, 2, 1, alex);
		return sf;
	}

	@Test
	void testSpielerKonstruktor() {
		Spieler spieler = new Spieler("Alex", "X");
		assertEquals("Alex", spieler.getName());
		assertEquals("X", spieler.getZeichen());
		assertEquals(0, spieler.getAnwerbungen());
		assertNull(spieler.getGegner());
	}

	@Test
	void testSetzeGegner() {
		erzeugeSpieler();
		assertSame(toni, alex.getGegner());
		assertSame(alex, toni.getGegner());
	}

	@Test
	void testSoeldnerKonstruktor() {
		erzeugeSpieler();
		Soeldner soeldner = new Soeldner(2, 1, 3, alex);
		assertEquals(2, soeldner.getStaerke());
		assertEquals(1, soeldner.getZeile());
		assertEquals(3, soeldner.getSpalte());
		assertSame(alex, soeldner.getDienstherr());
	}

	@Test
	void testWechsleZuUndGehoert() {
		erzeugeSpieler();
		Soeldner soeldner = new Soeldner(2, 1, 3, alex);
		assertTrue(soeldner.gehoert(alex));
		assertFalse(soeldner.gehoert(toni));

		soeldner.wechsleZu(toni);
		assertSame(toni, soeldner.getDienstherr());
		assertTrue(soeldner.gehoert(toni));
		assertFalse(soeldner.gehoert(alex));
		// Staerke und Position bleiben unveraendert
		assertEquals(2, soeldner.getStaerke());
		assertEquals(1, soeldner.getZeile());
		assertEquals(3, soeldner.getSpalte());
	}

	@Test
	void testIstStaerkerAls() {
		erzeugeSpieler();
		Soeldner schwach = new Soeldner(1, 0, 0, alex);
		Soeldner mittel = new Soeldner(2, 0, 1, alex);
		Soeldner stark = new Soeldner(3, 0, 2, toni);

		assertTrue(stark.istStaerkerAls(mittel));
		assertTrue(stark.istStaerkerAls(schwach));
		assertTrue(mittel.istStaerkerAls(schwach));

		assertFalse(mittel.istStaerkerAls(stark));
		assertFalse(schwach.istStaerkerAls(mittel));
		// gleich stark reicht nicht
		assertFalse(mittel.istStaerkerAls(new Soeldner(2, 4, 4, toni)));
	}

	@Test
	void testSchlachtfeldKonstruktor() {
		Schlachtfeld sf = new Schlachtfeld(5);
		assertEquals(5, sf.getN());
		for (int z = 0; z < 5; z++) {
			for (int s = 0; s < 5; s++) {
				assertNull(sf.getSoeldner(z, s));
			}
		}
	}

	@Test
	void testImFeldUndIstFrei() {
		erzeugeSpieler();
		Schlachtfeld sf = new Schlachtfeld(5);
		assertTrue(sf.imFeld(0, 0));
		assertTrue(sf.imFeld(4, 4));
		assertFalse(sf.imFeld(-1, 0));
		assertFalse(sf.imFeld(0, -1));
		assertFalse(sf.imFeld(5, 0));
		assertFalse(sf.imFeld(0, 5));

		assertTrue(sf.istFrei(2, 2));
		sf.setzeSoeldner(2, 2, 3, alex);
		assertFalse(sf.istFrei(2, 2));
		assertFalse(sf.istFrei(-1, 0));
		assertFalse(sf.istFrei(5, 5));
	}

	@Test
	void testSetzeSoeldner() {
		erzeugeSpieler();
		Schlachtfeld sf = new Schlachtfeld(5);
		sf.setzeSoeldner(4, 1, 2, toni);
		Soeldner soeldner = sf.getSoeldner(4, 1);
		assertNotNull(soeldner);
		assertEquals(2, soeldner.getStaerke());
		assertEquals(4, soeldner.getZeile());
		assertEquals(1, soeldner.getSpalte());
		assertSame(toni, soeldner.getDienstherr());
	}

	@Test
	void testGrundaufstellung() {
		erzeugeSpieler();
		Schlachtfeld sf = new Schlachtfeld(5);
		sf.grundaufstellung(alex, toni);

		assertEquals(1, sf.getSoeldner(0, 0).getStaerke());
		assertEquals(2, sf.getSoeldner(0, 1).getStaerke());
		assertEquals(3, sf.getSoeldner(0, 2).getStaerke());
		assertEquals(1, sf.getSoeldner(0, 3).getStaerke());
		assertEquals(2, sf.getSoeldner(0, 4).getStaerke());

		assertSame(alex, sf.getSoeldner(0, 0).getDienstherr());
		assertSame(alex, sf.getSoeldner(0, 4).getDienstherr());
		assertSame(toni, sf.getSoeldner(4, 0).getDienstherr());
		assertSame(toni, sf.getSoeldner(4, 2).getDienstherr());
		assertEquals(3, sf.getSoeldner(4, 2).getStaerke());

		assertNull(sf.getSoeldner(1, 0));
		assertNull(sf.getSoeldner(2, 2));

		assertEquals(5, sf.zaehleSoeldner(alex));
		assertEquals(5, sf.zaehleSoeldner(toni));
	}

	@Test
	void testSindNachbarnUndZiehen() {
		erzeugeSpieler();
		Schlachtfeld sf = new Schlachtfeld(5);
		sf.setzeSoeldner(2, 2, 3, alex);

		assertTrue(sf.sindNachbarn(2, 2, 2, 3));
		assertTrue(sf.sindNachbarn(2, 2, 2, 1));
		assertTrue(sf.sindNachbarn(2, 2, 1, 2));
		assertTrue(sf.sindNachbarn(2, 2, 3, 2));
		assertFalse(sf.sindNachbarn(2, 2, 1, 1));
		assertFalse(sf.sindNachbarn(2, 2, 2, 2));
		assertFalse(sf.sindNachbarn(2, 2, 2, 4));

		assertTrue(sf.kannZiehen(2, 2, 2, 3, alex));
		// kein Nachbarfeld
		assertFalse(sf.kannZiehen(2, 2, 2, 4, alex));
		// nicht der eigene Soeldner
		assertFalse(sf.kannZiehen(2, 2, 2, 3, toni));
		// Startfeld ist leer
		assertFalse(sf.kannZiehen(0, 0, 0, 1, alex));

		// das Zielfeld ist besetzt
		sf.setzeSoeldner(2, 3, 1, toni);
		assertFalse(sf.kannZiehen(2, 2, 2, 3, alex));

		// ziehen
		Soeldner alter = sf.getSoeldner(2, 2);
		sf.ziehe(2, 2, 1, 2);
		assertNull(sf.getSoeldner(2, 2));
		assertSame(alter, sf.getSoeldner(1, 2));
		assertEquals(1, sf.getSoeldner(1, 2).getZeile());
		assertEquals(2, sf.getSoeldner(1, 2).getSpalte());
		assertEquals(3, sf.getSoeldner(1, 2).getStaerke());
	}

	/*
	 * PFLICHTTEST! Dieser Test muss bestanden werden.
	 */
	@Test
	void testKannAnwerben() {
		erzeugeSpieler();
		Schlachtfeld sf = vorbereitetesFeld();

		// X3 auf (2,2) wirbt O2 auf (2,3) an
		assertTrue(sf.kannAnwerben(2, 2, 2, 3, alex));

		// gleich stark reicht nicht
		assertFalse(sf.kannAnwerben(2, 2, 1, 2, alex));
		// das Ziel ist ein eigener Soeldner
		assertFalse(sf.kannAnwerben(2, 2, 3, 2, alex));
		// dasselbe Feld
		assertFalse(sf.kannAnwerben(2, 2, 2, 2, alex));
		// kein Nachbarfeld
		assertFalse(sf.kannAnwerben(2, 2, 0, 2, alex));
		// das Ziel ist frei
		assertFalse(sf.kannAnwerben(2, 2, 2, 1, alex));
		// der eigene Soeldner ist schwaecher als das Ziel
		sf.setzeSoeldner(4, 2, 2, toni);
		assertFalse(sf.kannAnwerben(3, 2, 4, 2, alex));
		// der Startpunkt gehoert dem Gegner
		assertFalse(sf.kannAnwerben(2, 2, 2, 3, toni));
		// der Startpunkt ist leer
		assertFalse(sf.kannAnwerben(0, 0, 0, 1, alex));
		// ausserhalb des Schlachtfeldes
		assertFalse(sf.kannAnwerben(-1, 2, 2, 2, alex));
		assertFalse(sf.kannAnwerben(2, 2, 2, 5, alex));

		// aus der Sicht von Toni: O3 auf (1,2) gegen X3 auf (2,2)
		assertFalse(sf.kannAnwerben(1, 2, 2, 2, toni));
		// aber O2 auf (2,3) gegen X1 waere erlaubt
		sf.setzeSoeldner(3, 3, 1, alex);
		assertTrue(sf.kannAnwerben(2, 3, 3, 3, toni));
	}

	@Test
	void testWerbeAnUndZaehleSoeldner() {
		erzeugeSpieler();
		Schlachtfeld sf = vorbereitetesFeld();
		assertEquals(2, sf.zaehleSoeldner(alex));
		assertEquals(2, sf.zaehleSoeldner(toni));
		assertFalse(sf.besitztAlle(alex));

		Soeldner alter = sf.getSoeldner(2, 3);
		sf.werbeAn(2, 3, alex);

		// derselbe Soeldner, nur ein anderer Dienstherr
		assertSame(alter, sf.getSoeldner(2, 3));
		assertSame(alex, alter.getDienstherr());
		assertEquals(2, alter.getStaerke());
		assertEquals(2, alter.getZeile());
		assertEquals(3, alter.getSpalte());

		assertEquals(3, sf.zaehleSoeldner(alex));
		assertEquals(1, sf.zaehleSoeldner(toni));
		assertEquals(1, alex.getAnwerbungen());
		assertFalse(sf.besitztAlle(alex));

		// auch den letzten Soeldner uebernehmen
		sf.werbeAn(1, 2, alex);
		assertEquals(4, sf.zaehleSoeldner(alex));
		assertEquals(0, sf.zaehleSoeldner(toni));
		assertEquals(2, alex.getAnwerbungen());
		assertTrue(sf.besitztAlle(alex));
		assertFalse(sf.besitztAlle(toni));

		// auf einem leeren Schlachtfeld besitzt niemand alles
		Schlachtfeld leer = new Schlachtfeld(5);
		assertEquals(0, leer.zaehleSoeldner(alex));
		assertFalse(leer.besitztAlle(alex));
	}
}
