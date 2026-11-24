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

	@Test
	void testTierKonstruktor() {
		Tier tier = new Tier(2, 3, 4);
		assertEquals(2, tier.getStaerke());
		assertEquals(3, tier.getZeile());
		assertEquals(4, tier.getSpalte());
		assertTrue(tier.istImSpiel());
	}

	@Test
	void testSetzePositionUndStehtAuf() {
		Tier tier = new Tier(2, 3, 4);
		assertTrue(tier.stehtAuf(3, 4));
		assertFalse(tier.stehtAuf(3, 3));
		assertFalse(tier.stehtAuf(4, 4));

		tier.setzePosition(1, 0);
		assertEquals(1, tier.getZeile());
		assertEquals(0, tier.getSpalte());
		assertTrue(tier.stehtAuf(1, 0));
		assertFalse(tier.stehtAuf(3, 4));
		// die Staerke aendert sich dabei nicht
		assertEquals(2, tier.getStaerke());
	}

	@Test
	void testSchlagenUndImSpiel() {
		Tier tier = new Tier(3, 2, 2);
		assertTrue(tier.istImSpiel());
		tier.schlagen();
		assertFalse(tier.istImSpiel());
		// ein geschlagenes Tier steht auf keinem Feld mehr
		assertFalse(tier.stehtAuf(2, 2));
	}

	@Test
	void testSchlaegt() {
		Tier maus = new Tier(1, 0, 0);
		Tier wolf = new Tier(2, 0, 1);
		Tier elefant = new Tier(3, 0, 2);

		assertTrue(maus.schlaegt(maus));
		assertFalse(maus.schlaegt(wolf));
		assertTrue(maus.schlaegt(elefant));

		assertTrue(wolf.schlaegt(maus));
		assertTrue(wolf.schlaegt(wolf));
		assertFalse(wolf.schlaegt(elefant));

		assertTrue(elefant.schlaegt(maus));
		assertTrue(elefant.schlaegt(wolf));
		assertTrue(elefant.schlaegt(elefant));
	}

	@Test
	void testSpielerKonstruktor() {
		Spieler alex = new Spieler("Alex", "A", 0, 4);
		assertEquals("Alex", alex.getName());
		assertEquals("A", alex.getZeichen());
		assertEquals(0, alex.getHeimatZeile());
		assertEquals(4, alex.getZielZeile());

		for (int i = 0; i < Spieler.ANZAHL_TIERE; i++) {
			assertEquals(Spieler.STAERKEN[i], alex.getTier(i).getStaerke());
			assertEquals(0, alex.getTier(i).getZeile());
			assertEquals(i, alex.getTier(i).getSpalte());
			assertTrue(alex.getTier(i).istImSpiel());
		}

		Spieler toni = new Spieler("Toni", "B", 4, 0);
		assertEquals(4, toni.getTier(2).getZeile());
		assertEquals(2, toni.getTier(2).getSpalte());
	}

	/*
	 * PFLICHTTEST! Dieser Test muss bestanden werden.
	 */
	@Test
	void testTierAn() {
		Spieler alex = new Spieler("Alex", "A", 0, 4);

		assertSame(alex.getTier(0), alex.tierAn(0, 0));
		assertSame(alex.getTier(1), alex.tierAn(0, 1));
		assertSame(alex.getTier(2), alex.tierAn(0, 2));

		assertNull(alex.tierAn(0, 3));
		assertNull(alex.tierAn(1, 0));
		assertNull(alex.tierAn(4, 0));

		// nach dem Ziehen
		alex.getTier(1).setzePosition(2, 2);
		assertNull(alex.tierAn(0, 1));
		assertSame(alex.getTier(1), alex.tierAn(2, 2));

		// ein geschlagenes Tier steht auf keinem Feld mehr
		alex.getTier(0).schlagen();
		assertNull(alex.tierAn(0, 0));
		assertSame(alex.getTier(2), alex.tierAn(0, 2));
	}

	@Test
	void testZaehleTiereUndVerloren() {
		Spieler alex = new Spieler("Alex", "A", 0, 4);
		assertEquals(3, alex.zaehleTiere());
		assertFalse(alex.hatVerloren());

		alex.getTier(0).schlagen();
		assertEquals(2, alex.zaehleTiere());
		assertFalse(alex.hatVerloren());

		alex.getTier(1).schlagen();
		alex.getTier(2).schlagen();
		assertEquals(0, alex.zaehleTiere());
		assertTrue(alex.hatVerloren());
	}

	@Test
	void testHatZielErreichtUndFuehreZugAus() {
		Spieler alex = new Spieler("Alex", "A", 0, 4);
		Spieler toni = new Spieler("Toni", "B", 4, 0);
		assertFalse(alex.hatZielErreicht());

		// ein Zug auf ein freies Feld
		alex.fuehreZugAus(1, 1, 1, toni);
		assertEquals(1, alex.getTier(1).getZeile());
		assertEquals(1, alex.getTier(1).getSpalte());
		assertEquals(3, toni.zaehleTiere());

		// ein Zug, der ein gegnerisches Tier schlaegt
		toni.getTier(0).setzePosition(2, 0);
		alex.getTier(1).setzePosition(1, 0);
		alex.fuehreZugAus(1, 2, 0, toni);
		assertFalse(toni.getTier(0).istImSpiel());
		assertEquals(2, toni.zaehleTiere());
		assertEquals(2, alex.getTier(1).getZeile());
		assertEquals(0, alex.getTier(1).getSpalte());

		// Zielzeile erreicht
		alex.getTier(2).setzePosition(4, 3);
		assertTrue(alex.hatZielErreicht());

		// ein geschlagenes Tier auf der Zielzeile zaehlt nicht
		toni.getTier(0).setzePosition(0, 4);
		assertFalse(toni.hatZielErreicht());
	}

	@Test
	void testSpielfeldKonstruktorUndFluss() {
		Spielfeld feld = new Spielfeld(5);
		assertEquals(5, feld.getN());

		assertTrue(feld.istWasser(2, 1));
		assertTrue(feld.istWasser(2, 2));
		assertTrue(feld.istWasser(2, 3));

		assertFalse(feld.istWasser(2, 0));
		assertFalse(feld.istWasser(2, 4));
		assertFalse(feld.istWasser(1, 2));
		assertFalse(feld.istWasser(3, 2));
		assertFalse(feld.istWasser(0, 0));
	}

	@Test
	void testImFeldUndIstNachbar() {
		Spielfeld feld = new Spielfeld(5);
		assertTrue(feld.imFeld(0, 0));
		assertTrue(feld.imFeld(4, 4));
		assertFalse(feld.imFeld(-1, 0));
		assertFalse(feld.imFeld(0, -1));
		assertFalse(feld.imFeld(5, 0));
		assertFalse(feld.imFeld(0, 5));

		assertTrue(feld.istNachbar(1, 1, 1, 2));
		assertTrue(feld.istNachbar(1, 1, 1, 0));
		assertTrue(feld.istNachbar(1, 1, 0, 1));
		assertTrue(feld.istNachbar(1, 1, 2, 1));

		assertFalse(feld.istNachbar(1, 1, 2, 2));
		assertFalse(feld.istNachbar(1, 1, 0, 0));
		assertFalse(feld.istNachbar(1, 1, 1, 1));
		assertFalse(feld.istNachbar(1, 1, 1, 3));
		assertFalse(feld.istNachbar(1, 1, 3, 1));
	}

	@Test
	void testBetretbar() {
		Spielfeld feld = new Spielfeld(5);
		Tier maus = new Tier(1, 1, 1);
		Tier elefant = new Tier(3, 1, 2);

		// Landfelder darf jeder betreten
		assertTrue(feld.betretbar(1, 2, elefant));
		assertTrue(feld.betretbar(2, 0, elefant));
		assertTrue(feld.betretbar(2, 4, elefant));

		// Flussfelder nur die Maus
		assertTrue(feld.betretbar(2, 2, maus));
		assertFalse(feld.betretbar(2, 2, elefant));
		assertFalse(feld.betretbar(2, 1, elefant));

		// ausserhalb des Spielfeldes darf niemand hin
		assertFalse(feld.betretbar(-1, 0, maus));
		assertFalse(feld.betretbar(5, 0, maus));
		assertFalse(feld.betretbar(0, 5, maus));
	}

	@Test
	void testZugErlaubt() {
		Spielfeld feld = new Spielfeld(5);
		Spieler alex = new Spieler("Alex", "A", 0, 4);
		Spieler toni = new Spieler("Toni", "B", 4, 0);

		// freies Nachbarfeld
		assertTrue(alex.zugErlaubt(0, 1, 0, feld, toni));
		// eigenes Tier im Weg
		assertFalse(alex.zugErlaubt(0, 0, 1, feld, toni));
		// kein Nachbarfeld
		assertFalse(alex.zugErlaubt(0, 2, 0, feld, toni));
		// ausserhalb des Spielfeldes
		assertFalse(alex.zugErlaubt(0, -1, 0, feld, toni));

		// in den Fluss darf nur die Maus
		alex.getTier(2).setzePosition(1, 2);
		assertFalse(alex.zugErlaubt(2, 2, 2, feld, toni));
		alex.getTier(0).setzePosition(1, 1);
		assertTrue(alex.zugErlaubt(0, 2, 1, feld, toni));

		// die Maus schlaegt den Wolf nicht
		toni.getTier(1).setzePosition(1, 0);
		assertFalse(alex.zugErlaubt(0, 1, 0, feld, toni));

		// aber den Elefanten
		toni.getTier(1).setzePosition(4, 1);
		toni.getTier(2).setzePosition(1, 0);
		assertTrue(alex.zugErlaubt(0, 1, 0, feld, toni));

		// ein geschlagenes Tier kann nicht mehr ziehen
		alex.getTier(0).schlagen();
		assertFalse(alex.zugErlaubt(0, 1, 0, feld, toni));
	}
}
