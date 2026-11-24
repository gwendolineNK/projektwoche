/* *****************************************************************************************************
 * NICHT AENDERN!
 *
 * Alternative zu den Unit-Tests, falls JUnit in Ihrer Eclipse-Installation nicht laeuft.
 * Starten Sie diese Klasse ueber: Rechtsklick -> Run As -> Java Application
 *
 * Die Tests sind identisch zu denen in UnitTests.java.
 ****************************************************************************************************** */

public class TestRunner {

	private static int bestanden = 0;
	private static int gesamt = 0;
	private static boolean aktuellerTestOk;
	private static String aktuellerTest;
	private static boolean pflichtTestOk = false;

	public static void main(String[] args) {
		System.out.println("=== Dschungelkampf Light - Tests ===");
		System.out.println();

		testTierKonstruktor();
		testSetzePositionUndStehtAuf();
		testSchlagenUndImSpiel();
		testSchlaegt();
		testSpielerKonstruktor();
		testTierAn();
		testZaehleTiereUndVerloren();
		testHatZielErreichtUndFuehreZugAus();
		testSpielfeldKonstruktorUndFluss();
		testImFeldUndIstNachbar();
		testBetretbar();
		testZugErlaubt();

		System.out.println();
		System.out.println("=======================================");
		System.out.println("Bestanden: " + bestanden + " von " + gesamt);
		System.out.println("Pflichttest testTierAn: " + (pflichtTestOk ? "BESTANDEN" : "NICHT BESTANDEN"));
		if (bestanden >= 9 && pflichtTestOk) {
			System.out.println("-> Kriterium erfuellt (mindestens 9 von 12 inkl. Pflichttest).");
		} else {
			System.out.println("-> Kriterium NICHT erfuellt.");
		}
	}

	/* ---------- Hilfsmethoden ---------- */

	private static void starte(String name) {
		aktuellerTest = name;
		aktuellerTestOk = true;
		gesamt++;
	}

	private static void beende() {
		if (aktuellerTestOk) {
			bestanden++;
			System.out.println("[OK]     " + aktuellerTest);
			if (aktuellerTest.equals("testTierAn")) {
				pflichtTestOk = true;
			}
		} else {
			System.out.println("[FEHLER] " + aktuellerTest);
		}
	}

	private static void pruefe(boolean bedingung, String meldung) {
		if (!bedingung) {
			aktuellerTestOk = false;
			System.out.println("         ! " + meldung);
		}
	}

	private static void pruefeGleich(int erwartet, int tatsaechlich, String meldung) {
		pruefe(erwartet == tatsaechlich, meldung + " - erwartet: " + erwartet + ", war: " + tatsaechlich);
	}

	/* ---------- Tests ---------- */

	private static void testTierKonstruktor() {
		starte("testTierKonstruktor");
		try {
			Tier tier = new Tier(2, 3, 4);
			pruefeGleich(2, tier.getStaerke(), "getStaerke()");
			pruefeGleich(3, tier.getZeile(), "getZeile()");
			pruefeGleich(4, tier.getSpalte(), "getSpalte()");
			pruefe(tier.istImSpiel(), "ein neues Tier ist im Spiel");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzePositionUndStehtAuf() {
		starte("testSetzePositionUndStehtAuf");
		try {
			Tier tier = new Tier(2, 3, 4);
			pruefe(tier.stehtAuf(3, 4), "stehtAuf(3,4) sollte true sein");
			pruefe(!tier.stehtAuf(3, 3), "stehtAuf(3,3) sollte false sein");
			pruefe(!tier.stehtAuf(4, 4), "stehtAuf(4,4) sollte false sein");

			tier.setzePosition(1, 0);
			pruefeGleich(1, tier.getZeile(), "getZeile()");
			pruefeGleich(0, tier.getSpalte(), "getSpalte()");
			pruefe(tier.stehtAuf(1, 0), "stehtAuf(1,0) sollte true sein");
			pruefe(!tier.stehtAuf(3, 4), "stehtAuf(3,4) sollte false sein");
			pruefeGleich(2, tier.getStaerke(), "getStaerke() bleibt unveraendert");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSchlagenUndImSpiel() {
		starte("testSchlagenUndImSpiel");
		try {
			Tier tier = new Tier(3, 2, 2);
			pruefe(tier.istImSpiel(), "istImSpiel() sollte anfangs true sein");
			tier.schlagen();
			pruefe(!tier.istImSpiel(), "istImSpiel() sollte nach schlagen() false sein");
			pruefe(!tier.stehtAuf(2, 2), "ein geschlagenes Tier steht auf keinem Feld");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSchlaegt() {
		starte("testSchlaegt");
		try {
			Tier maus = new Tier(1, 0, 0);
			Tier wolf = new Tier(2, 0, 1);
			Tier elefant = new Tier(3, 0, 2);

			pruefe(maus.schlaegt(maus), "Maus schlaegt Maus");
			pruefe(!maus.schlaegt(wolf), "Maus schlaegt Wolf NICHT");
			pruefe(maus.schlaegt(elefant), "Maus schlaegt Elefant");

			pruefe(wolf.schlaegt(maus), "Wolf schlaegt Maus");
			pruefe(wolf.schlaegt(wolf), "Wolf schlaegt Wolf");
			pruefe(!wolf.schlaegt(elefant), "Wolf schlaegt Elefant NICHT");

			pruefe(elefant.schlaegt(maus), "Elefant schlaegt Maus");
			pruefe(elefant.schlaegt(wolf), "Elefant schlaegt Wolf");
			pruefe(elefant.schlaegt(elefant), "Elefant schlaegt Elefant");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSpielerKonstruktor() {
		starte("testSpielerKonstruktor");
		try {
			Spieler alex = new Spieler("Alex", "A", 0, 4);
			pruefe("Alex".equals(alex.getName()), "getName() - war: " + alex.getName());
			pruefe("A".equals(alex.getZeichen()), "getZeichen() - war: " + alex.getZeichen());
			pruefeGleich(0, alex.getHeimatZeile(), "getHeimatZeile()");
			pruefeGleich(4, alex.getZielZeile(), "getZielZeile()");

			for (int i = 0; i < Spieler.ANZAHL_TIERE; i++) {
				pruefe(alex.getTier(i) != null, "getTier(" + i + ") darf nicht null sein");
				if (alex.getTier(i) != null) {
					pruefeGleich(Spieler.STAERKEN[i], alex.getTier(i).getStaerke(), "Staerke von Tier " + i);
					pruefeGleich(0, alex.getTier(i).getZeile(), "Zeile von Tier " + i);
					pruefeGleich(i, alex.getTier(i).getSpalte(), "Spalte von Tier " + i);
					pruefe(alex.getTier(i).istImSpiel(), "Tier " + i + " ist im Spiel");
				}
			}

			Spieler toni = new Spieler("Toni", "B", 4, 0);
			pruefeGleich(4, toni.getTier(2).getZeile(), "Zeile von Tier 2 bei Toni");
			pruefeGleich(2, toni.getTier(2).getSpalte(), "Spalte von Tier 2 bei Toni");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	/* PFLICHTTEST! */
	private static void testTierAn() {
		starte("testTierAn");
		try {
			Spieler alex = new Spieler("Alex", "A", 0, 4);

			pruefe(alex.tierAn(0, 0) == alex.getTier(0), "tierAn(0,0) sollte Tier 0 sein");
			pruefe(alex.tierAn(0, 1) == alex.getTier(1), "tierAn(0,1) sollte Tier 1 sein");
			pruefe(alex.tierAn(0, 2) == alex.getTier(2), "tierAn(0,2) sollte Tier 2 sein");

			pruefe(alex.tierAn(0, 3) == null, "tierAn(0,3) sollte null sein");
			pruefe(alex.tierAn(1, 0) == null, "tierAn(1,0) sollte null sein");
			pruefe(alex.tierAn(4, 0) == null, "tierAn(4,0) sollte null sein");

			alex.getTier(1).setzePosition(2, 2);
			pruefe(alex.tierAn(0, 1) == null, "tierAn(0,1) nach dem Ziehen null");
			pruefe(alex.tierAn(2, 2) == alex.getTier(1), "tierAn(2,2) sollte Tier 1 sein");

			alex.getTier(0).schlagen();
			pruefe(alex.tierAn(0, 0) == null, "geschlagenes Tier steht auf keinem Feld");
			pruefe(alex.tierAn(0, 2) == alex.getTier(2), "tierAn(0,2) sollte Tier 2 sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testZaehleTiereUndVerloren() {
		starte("testZaehleTiereUndVerloren");
		try {
			Spieler alex = new Spieler("Alex", "A", 0, 4);
			pruefeGleich(3, alex.zaehleTiere(), "zaehleTiere()");
			pruefe(!alex.hatVerloren(), "hatVerloren() sollte false sein");

			alex.getTier(0).schlagen();
			pruefeGleich(2, alex.zaehleTiere(), "zaehleTiere()");
			pruefe(!alex.hatVerloren(), "hatVerloren() sollte false sein");

			alex.getTier(1).schlagen();
			alex.getTier(2).schlagen();
			pruefeGleich(0, alex.zaehleTiere(), "zaehleTiere()");
			pruefe(alex.hatVerloren(), "hatVerloren() sollte true sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testHatZielErreichtUndFuehreZugAus() {
		starte("testHatZielErreichtUndFuehreZugAus");
		try {
			Spieler alex = new Spieler("Alex", "A", 0, 4);
			Spieler toni = new Spieler("Toni", "B", 4, 0);
			pruefe(!alex.hatZielErreicht(), "hatZielErreicht() sollte anfangs false sein");

			alex.fuehreZugAus(1, 1, 1, toni);
			pruefeGleich(1, alex.getTier(1).getZeile(), "Zeile nach dem Zug");
			pruefeGleich(1, alex.getTier(1).getSpalte(), "Spalte nach dem Zug");
			pruefeGleich(3, toni.zaehleTiere(), "Toni hat noch alle Tiere");

			toni.getTier(0).setzePosition(2, 0);
			alex.getTier(1).setzePosition(1, 0);
			alex.fuehreZugAus(1, 2, 0, toni);
			pruefe(!toni.getTier(0).istImSpiel(), "das geschlagene Tier ist aus dem Spiel");
			pruefeGleich(2, toni.zaehleTiere(), "zaehleTiere() von Toni");
			pruefeGleich(2, alex.getTier(1).getZeile(), "Zeile nach dem Schlagen");
			pruefeGleich(0, alex.getTier(1).getSpalte(), "Spalte nach dem Schlagen");

			alex.getTier(2).setzePosition(4, 3);
			pruefe(alex.hatZielErreicht(), "hatZielErreicht() sollte true sein");

			toni.getTier(0).setzePosition(0, 4);
			pruefe(!toni.hatZielErreicht(), "ein geschlagenes Tier zaehlt nicht mit");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSpielfeldKonstruktorUndFluss() {
		starte("testSpielfeldKonstruktorUndFluss");
		try {
			Spielfeld feld = new Spielfeld(5);
			pruefeGleich(5, feld.getN(), "getN()");
			pruefe(feld.istWasser(2, 1), "(2,1) sollte Wasser sein");
			pruefe(feld.istWasser(2, 2), "(2,2) sollte Wasser sein");
			pruefe(feld.istWasser(2, 3), "(2,3) sollte Wasser sein");
			pruefe(!feld.istWasser(2, 0), "(2,0) sollte Land sein");
			pruefe(!feld.istWasser(2, 4), "(2,4) sollte Land sein");
			pruefe(!feld.istWasser(1, 2), "(1,2) sollte Land sein");
			pruefe(!feld.istWasser(3, 2), "(3,2) sollte Land sein");
			pruefe(!feld.istWasser(0, 0), "(0,0) sollte Land sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testImFeldUndIstNachbar() {
		starte("testImFeldUndIstNachbar");
		try {
			Spielfeld feld = new Spielfeld(5);
			pruefe(feld.imFeld(0, 0), "imFeld(0,0) sollte true sein");
			pruefe(feld.imFeld(4, 4), "imFeld(4,4) sollte true sein");
			pruefe(!feld.imFeld(-1, 0), "imFeld(-1,0) sollte false sein");
			pruefe(!feld.imFeld(0, -1), "imFeld(0,-1) sollte false sein");
			pruefe(!feld.imFeld(5, 0), "imFeld(5,0) sollte false sein");
			pruefe(!feld.imFeld(0, 5), "imFeld(0,5) sollte false sein");

			pruefe(feld.istNachbar(1, 1, 1, 2), "(1,1) und (1,2) sind Nachbarn");
			pruefe(feld.istNachbar(1, 1, 1, 0), "(1,1) und (1,0) sind Nachbarn");
			pruefe(feld.istNachbar(1, 1, 0, 1), "(1,1) und (0,1) sind Nachbarn");
			pruefe(feld.istNachbar(1, 1, 2, 1), "(1,1) und (2,1) sind Nachbarn");

			pruefe(!feld.istNachbar(1, 1, 2, 2), "diagonal sind keine Nachbarn");
			pruefe(!feld.istNachbar(1, 1, 0, 0), "diagonal sind keine Nachbarn");
			pruefe(!feld.istNachbar(1, 1, 1, 1), "ein Feld ist nicht sein eigener Nachbar");
			pruefe(!feld.istNachbar(1, 1, 1, 3), "(1,1) und (1,3) sind keine Nachbarn");
			pruefe(!feld.istNachbar(1, 1, 3, 1), "(1,1) und (3,1) sind keine Nachbarn");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testBetretbar() {
		starte("testBetretbar");
		try {
			Spielfeld feld = new Spielfeld(5);
			Tier maus = new Tier(1, 1, 1);
			Tier elefant = new Tier(3, 1, 2);

			pruefe(feld.betretbar(1, 2, elefant), "Landfeld (1,2) ist betretbar");
			pruefe(feld.betretbar(2, 0, elefant), "Landfeld (2,0) ist betretbar");
			pruefe(feld.betretbar(2, 4, elefant), "Landfeld (2,4) ist betretbar");

			pruefe(feld.betretbar(2, 2, maus), "die Maus darf in den Fluss");
			pruefe(!feld.betretbar(2, 2, elefant), "der Elefant darf nicht in den Fluss");
			pruefe(!feld.betretbar(2, 1, elefant), "der Elefant darf nicht in den Fluss");

			pruefe(!feld.betretbar(-1, 0, maus), "ausserhalb des Feldes");
			pruefe(!feld.betretbar(5, 0, maus), "ausserhalb des Feldes");
			pruefe(!feld.betretbar(0, 5, maus), "ausserhalb des Feldes");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testZugErlaubt() {
		starte("testZugErlaubt");
		try {
			Spielfeld feld = new Spielfeld(5);
			Spieler alex = new Spieler("Alex", "A", 0, 4);
			Spieler toni = new Spieler("Toni", "B", 4, 0);

			pruefe(alex.zugErlaubt(0, 1, 0, feld, toni), "Zug auf ein freies Nachbarfeld");
			pruefe(!alex.zugErlaubt(0, 0, 1, feld, toni), "eigenes Tier im Weg");
			pruefe(!alex.zugErlaubt(0, 2, 0, feld, toni), "kein Nachbarfeld");
			pruefe(!alex.zugErlaubt(0, -1, 0, feld, toni), "ausserhalb des Spielfeldes");

			alex.getTier(2).setzePosition(1, 2);
			pruefe(!alex.zugErlaubt(2, 2, 2, feld, toni), "der Elefant darf nicht in den Fluss");
			alex.getTier(0).setzePosition(1, 1);
			pruefe(alex.zugErlaubt(0, 2, 1, feld, toni), "die Maus darf in den Fluss");

			toni.getTier(1).setzePosition(1, 0);
			pruefe(!alex.zugErlaubt(0, 1, 0, feld, toni), "die Maus schlaegt den Wolf nicht");

			toni.getTier(1).setzePosition(4, 1);
			toni.getTier(2).setzePosition(1, 0);
			pruefe(alex.zugErlaubt(0, 1, 0, feld, toni), "die Maus schlaegt den Elefanten");

			alex.getTier(0).schlagen();
			pruefe(!alex.zugErlaubt(0, 1, 0, feld, toni), "ein geschlagenes Tier zieht nicht mehr");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}
}
