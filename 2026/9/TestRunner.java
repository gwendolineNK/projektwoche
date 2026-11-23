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
		System.out.println("=== Schiffe versenken Light - Tests ===");
		System.out.println();

		testSpielfeldKonstruktor();
		testImFeld();
		testMarkiereUndIstBeschossen();
		testSchiffKonstruktor();
		testPlatziere();
		testBelegtWaagerecht();
		testBelegtSenkrecht();
		testTreffer();
		testVersenkt();
		testSpielerKonstruktor();
		testSchiffAn();
		testPasstSchiff();

		System.out.println();
		System.out.println("=======================================");
		System.out.println("Bestanden: " + bestanden + " von " + gesamt);
		System.out.println("Pflichttest testSchiffAn: " + (pflichtTestOk ? "BESTANDEN" : "NICHT BESTANDEN"));
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
			if (aktuellerTest.equals("testSchiffAn")) {
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

	private static Spieler vorbereiteterSpieler() {
		Spieler spieler = new Spieler("Alex", 6);
		spieler.getSchiff(0).platziere(0, 0, true);
		spieler.getSchiff(1).platziere(2, 3, false);
		spieler.getSchiff(2).platziere(5, 4, true);
		return spieler;
	}

	/* ---------- Tests ---------- */

	private static void testSpielfeldKonstruktor() {
		starte("testSpielfeldKonstruktor");
		try {
			Spielfeld feld = new Spielfeld(6);
			pruefeGleich(6, feld.getN(), "getN()");
			for (int z = 0; z < 6; z++) {
				for (int s = 0; s < 6; s++) {
					pruefeGleich(Spielfeld.LEER, feld.getStelle(z, s), "getStelle(" + z + "," + s + ")");
				}
			}
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testImFeld() {
		starte("testImFeld");
		try {
			Spielfeld feld = new Spielfeld(6);
			pruefe(feld.imFeld(0, 0), "imFeld(0,0) sollte true sein");
			pruefe(feld.imFeld(5, 5), "imFeld(5,5) sollte true sein");
			pruefe(feld.imFeld(3, 2), "imFeld(3,2) sollte true sein");
			pruefe(!feld.imFeld(-1, 0), "imFeld(-1,0) sollte false sein");
			pruefe(!feld.imFeld(0, -1), "imFeld(0,-1) sollte false sein");
			pruefe(!feld.imFeld(6, 0), "imFeld(6,0) sollte false sein");
			pruefe(!feld.imFeld(0, 6), "imFeld(0,6) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testMarkiereUndIstBeschossen() {
		starte("testMarkiereUndIstBeschossen");
		try {
			Spielfeld feld = new Spielfeld(6);
			pruefe(!feld.istBeschossen(2, 3), "istBeschossen(2,3) sollte anfangs false sein");
			feld.markiere(2, 3, Spielfeld.WASSER);
			pruefeGleich(Spielfeld.WASSER, feld.getStelle(2, 3), "getStelle(2,3)");
			pruefe(feld.istBeschossen(2, 3), "istBeschossen(2,3) sollte true sein");
			feld.markiere(4, 1, Spielfeld.TREFFER);
			pruefeGleich(Spielfeld.TREFFER, feld.getStelle(4, 1), "getStelle(4,1)");
			pruefe(feld.istBeschossen(4, 1), "istBeschossen(4,1) sollte true sein");
			pruefe(!feld.istBeschossen(0, 0), "istBeschossen(0,0) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSchiffKonstruktor() {
		starte("testSchiffKonstruktor");
		try {
			Schiff schiff = new Schiff(3);
			pruefeGleich(3, schiff.getLaenge(), "getLaenge()");
			pruefeGleich(-1, schiff.getZeile(), "getZeile()");
			pruefeGleich(-1, schiff.getSpalte(), "getSpalte()");
			pruefeGleich(0, schiff.getTreffer(), "getTreffer()");
			pruefe(schiff.istWaagerecht(), "istWaagerecht() sollte anfangs true sein");
			pruefe(!schiff.belegt(0, 0), "nicht platziertes Schiff belegt kein Feld");
			pruefe(!schiff.belegt(-1, -1), "nicht platziertes Schiff belegt kein Feld");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testPlatziere() {
		starte("testPlatziere");
		try {
			Schiff schiff = new Schiff(2);
			schiff.platziere(4, 1, false);
			pruefeGleich(4, schiff.getZeile(), "getZeile()");
			pruefeGleich(1, schiff.getSpalte(), "getSpalte()");
			pruefe(!schiff.istWaagerecht(), "istWaagerecht() sollte false sein");
			schiff.platziere(0, 2, true);
			pruefeGleich(0, schiff.getZeile(), "getZeile()");
			pruefeGleich(2, schiff.getSpalte(), "getSpalte()");
			pruefe(schiff.istWaagerecht(), "istWaagerecht() sollte true sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testBelegtWaagerecht() {
		starte("testBelegtWaagerecht");
		try {
			Schiff schiff = new Schiff(3);
			schiff.platziere(2, 1, true);
			pruefe(schiff.belegt(2, 1), "belegt(2,1) sollte true sein");
			pruefe(schiff.belegt(2, 2), "belegt(2,2) sollte true sein");
			pruefe(schiff.belegt(2, 3), "belegt(2,3) sollte true sein");
			pruefe(!schiff.belegt(2, 0), "belegt(2,0) sollte false sein");
			pruefe(!schiff.belegt(2, 4), "belegt(2,4) sollte false sein");
			pruefe(!schiff.belegt(1, 2), "belegt(1,2) sollte false sein");
			pruefe(!schiff.belegt(3, 2), "belegt(3,2) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testBelegtSenkrecht() {
		starte("testBelegtSenkrecht");
		try {
			Schiff schiff = new Schiff(3);
			schiff.platziere(2, 1, false);
			pruefe(schiff.belegt(2, 1), "belegt(2,1) sollte true sein");
			pruefe(schiff.belegt(3, 1), "belegt(3,1) sollte true sein");
			pruefe(schiff.belegt(4, 1), "belegt(4,1) sollte true sein");
			pruefe(!schiff.belegt(1, 1), "belegt(1,1) sollte false sein");
			pruefe(!schiff.belegt(5, 1), "belegt(5,1) sollte false sein");
			pruefe(!schiff.belegt(3, 0), "belegt(3,0) sollte false sein");
			pruefe(!schiff.belegt(3, 2), "belegt(3,2) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testTreffer() {
		starte("testTreffer");
		try {
			Schiff schiff = new Schiff(3);
			pruefeGleich(0, schiff.getTreffer(), "getTreffer()");
			schiff.treffer();
			pruefeGleich(1, schiff.getTreffer(), "getTreffer() nach 1 Treffer");
			schiff.treffer();
			pruefeGleich(2, schiff.getTreffer(), "getTreffer() nach 2 Treffern");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testVersenkt() {
		starte("testVersenkt");
		try {
			Spieler spieler = vorbereiteterSpieler();
			pruefe(!spieler.getSchiff(0).istVersenkt(), "Schiff 0 sollte nicht versenkt sein");
			pruefeGleich(0, spieler.zaehleVersenkte(), "zaehleVersenkte()");
			pruefe(!spieler.alleSchiffeVersenkt(), "alleSchiffeVersenkt() sollte false sein");

			spieler.getSchiff(1).treffer();
			pruefe(!spieler.getSchiff(1).istVersenkt(), "Schiff 1 nach 1 Treffer noch nicht versenkt");
			spieler.getSchiff(1).treffer();
			pruefe(spieler.getSchiff(1).istVersenkt(), "Schiff 1 nach 2 Treffern versenkt");
			pruefeGleich(1, spieler.zaehleVersenkte(), "zaehleVersenkte()");
			pruefe(!spieler.alleSchiffeVersenkt(), "alleSchiffeVersenkt() sollte false sein");

			for (int i = 0; i < 3; i++) {
				spieler.getSchiff(0).treffer();
			}
			spieler.getSchiff(2).treffer();
			spieler.getSchiff(2).treffer();
			pruefeGleich(3, spieler.zaehleVersenkte(), "zaehleVersenkte()");
			pruefe(spieler.alleSchiffeVersenkt(), "alleSchiffeVersenkt() sollte true sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSpielerKonstruktor() {
		starte("testSpielerKonstruktor");
		try {
			Spieler spieler = new Spieler("Alex", 6);
			pruefe("Alex".equals(spieler.getName()), "getName() - erwartet: Alex, war: " + spieler.getName());
			pruefe(spieler.getSpielfeld() != null, "getSpielfeld() darf nicht null sein");
			if (spieler.getSpielfeld() != null) {
				pruefeGleich(6, spieler.getSpielfeld().getN(), "Spielfeldgroesse");
			}
			for (int i = 0; i < Spieler.ANZAHL_SCHIFFE; i++) {
				pruefe(spieler.getSchiff(i) != null, "getSchiff(" + i + ") darf nicht null sein");
				if (spieler.getSchiff(i) != null) {
					pruefeGleich(Spieler.LAENGEN[i], spieler.getSchiff(i).getLaenge(), "Laenge von Schiff " + i);
					pruefeGleich(0, spieler.getSchiff(i).getTreffer(), "Treffer von Schiff " + i);
					pruefeGleich(-1, spieler.getSchiff(i).getZeile(), "Zeile von Schiff " + i);
				}
			}
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	/* PFLICHTTEST! */
	private static void testSchiffAn() {
		starte("testSchiffAn");
		try {
			Spieler spieler = vorbereiteterSpieler();

			pruefe(spieler.schiffAn(0, 0) == spieler.getSchiff(0), "schiffAn(0,0) sollte Schiff 0 sein");
			pruefe(spieler.schiffAn(0, 1) == spieler.getSchiff(0), "schiffAn(0,1) sollte Schiff 0 sein");
			pruefe(spieler.schiffAn(0, 2) == spieler.getSchiff(0), "schiffAn(0,2) sollte Schiff 0 sein");

			pruefe(spieler.schiffAn(2, 3) == spieler.getSchiff(1), "schiffAn(2,3) sollte Schiff 1 sein");
			pruefe(spieler.schiffAn(3, 3) == spieler.getSchiff(1), "schiffAn(3,3) sollte Schiff 1 sein");

			pruefe(spieler.schiffAn(5, 4) == spieler.getSchiff(2), "schiffAn(5,4) sollte Schiff 2 sein");
			pruefe(spieler.schiffAn(5, 5) == spieler.getSchiff(2), "schiffAn(5,5) sollte Schiff 2 sein");

			pruefe(spieler.schiffAn(0, 3) == null, "schiffAn(0,3) sollte null sein");
			pruefe(spieler.schiffAn(1, 1) == null, "schiffAn(1,1) sollte null sein");
			pruefe(spieler.schiffAn(4, 3) == null, "schiffAn(4,3) sollte null sein");
			pruefe(spieler.schiffAn(5, 3) == null, "schiffAn(5,3) sollte null sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testPasstSchiff() {
		starte("testPasstSchiff");
		try {
			Spieler spieler = new Spieler("Alex", 6);

			pruefe(spieler.passtSchiff(3, 0, 0, true), "passtSchiff(3,0,0,w) sollte true sein");
			pruefe(spieler.passtSchiff(3, 0, 3, true), "passtSchiff(3,0,3,w) sollte true sein");
			pruefe(spieler.passtSchiff(3, 3, 5, false), "passtSchiff(3,3,5,s) sollte true sein");

			pruefe(!spieler.passtSchiff(3, 0, 4, true), "passtSchiff(3,0,4,w) sollte false sein");
			pruefe(!spieler.passtSchiff(3, 4, 0, false), "passtSchiff(3,4,0,s) sollte false sein");
			pruefe(!spieler.passtSchiff(2, 5, 5, true), "passtSchiff(2,5,5,w) sollte false sein");

			spieler.getSchiff(0).platziere(0, 0, true);
			pruefe(!spieler.passtSchiff(2, 0, 2, true), "passtSchiff(2,0,2,w) sollte false sein");
			pruefe(!spieler.passtSchiff(2, 0, 1, false), "passtSchiff(2,0,1,s) sollte false sein");
			pruefe(spieler.passtSchiff(2, 0, 3, true), "passtSchiff(2,0,3,w) sollte true sein");
			pruefe(spieler.passtSchiff(2, 1, 0, false), "passtSchiff(2,1,0,s) sollte true sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}
}
