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

	private static Spieler alex;
	private static Spieler toni;

	public static void main(String[] args) {
		System.out.println("=== Reversi Light - Tests ===");
		System.out.println();

		testSpielerKonstruktor();
		testSetzeGegner();
		testSteinKonstruktor();
		testSetzeBesitzerUndGehoert();
		testSpielbrettKonstruktor();
		testImFeldUndIstFrei();
		testSetzeStein();
		testGrundaufstellung();
		testZaehleRichtung();
		testIstZugGueltig();
		testSetzeUndDrehe();
		testZaehleSteineUndVoll();

		System.out.println();
		System.out.println("=======================================");
		System.out.println("Bestanden: " + bestanden + " von " + gesamt);
		System.out.println("Pflichttest testIstZugGueltig: " + (pflichtTestOk ? "BESTANDEN" : "NICHT BESTANDEN"));
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
			if (aktuellerTest.equals("testIstZugGueltig")) {
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

	private static void erzeugeSpieler() {
		alex = new Spieler("Alex", "X");
		toni = new Spieler("Toni", "O");
		alex.setzeGegner(toni);
	}

	/* ---------- Tests ---------- */

	private static void testSpielerKonstruktor() {
		starte("testSpielerKonstruktor");
		try {
			Spieler spieler = new Spieler("Alex", "X");
			pruefe("Alex".equals(spieler.getName()), "getName() - erwartet: Alex, war: " + spieler.getName());
			pruefe("X".equals(spieler.getZeichen()), "getZeichen() - erwartet: X, war: " + spieler.getZeichen());
			pruefe(spieler.getGegner() == null, "getGegner() sollte anfangs null sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzeGegner() {
		starte("testSetzeGegner");
		try {
			erzeugeSpieler();
			pruefe(alex.getGegner() == toni, "Gegner von Alex sollte Toni sein");
			pruefe(toni.getGegner() == alex, "Gegner von Toni sollte Alex sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSteinKonstruktor() {
		starte("testSteinKonstruktor");
		try {
			erzeugeSpieler();
			Stein stein = new Stein(alex, 2, 3);
			pruefe(stein.getBesitzer() == alex, "getBesitzer() sollte Alex sein");
			pruefeGleich(2, stein.getZeile(), "getZeile()");
			pruefeGleich(3, stein.getSpalte(), "getSpalte()");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzeBesitzerUndGehoert() {
		starte("testSetzeBesitzerUndGehoert");
		try {
			erzeugeSpieler();
			Stein stein = new Stein(alex, 2, 3);
			pruefe(stein.gehoert(alex), "gehoert(alex) sollte true sein");
			pruefe(!stein.gehoert(toni), "gehoert(toni) sollte false sein");

			stein.setzeBesitzer(toni);
			pruefe(stein.getBesitzer() == toni, "getBesitzer() sollte Toni sein");
			pruefe(stein.gehoert(toni), "gehoert(toni) sollte true sein");
			pruefe(!stein.gehoert(alex), "gehoert(alex) sollte false sein");
			pruefeGleich(2, stein.getZeile(), "getZeile() nach dem Umdrehen");
			pruefeGleich(3, stein.getSpalte(), "getSpalte() nach dem Umdrehen");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSpielbrettKonstruktor() {
		starte("testSpielbrettKonstruktor");
		try {
			Spielbrett brett = new Spielbrett(6);
			pruefeGleich(6, brett.getN(), "getN()");
			for (int z = 0; z < 6; z++) {
				for (int s = 0; s < 6; s++) {
					pruefe(brett.getStein(z, s) == null, "getStein(" + z + "," + s + ") sollte null sein");
				}
			}
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testImFeldUndIstFrei() {
		starte("testImFeldUndIstFrei");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			pruefe(brett.imFeld(0, 0), "imFeld(0,0) sollte true sein");
			pruefe(brett.imFeld(5, 5), "imFeld(5,5) sollte true sein");
			pruefe(!brett.imFeld(-1, 0), "imFeld(-1,0) sollte false sein");
			pruefe(!brett.imFeld(0, -1), "imFeld(0,-1) sollte false sein");
			pruefe(!brett.imFeld(6, 0), "imFeld(6,0) sollte false sein");
			pruefe(!brett.imFeld(0, 6), "imFeld(0,6) sollte false sein");

			pruefe(brett.istFrei(2, 2), "istFrei(2,2) sollte true sein");
			brett.setzeStein(2, 2, alex);
			pruefe(!brett.istFrei(2, 2), "istFrei(2,2) sollte nach setzeStein false sein");
			pruefe(!brett.istFrei(-1, 0), "istFrei(-1,0) sollte false sein");
			pruefe(!brett.istFrei(6, 6), "istFrei(6,6) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzeStein() {
		starte("testSetzeStein");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			brett.setzeStein(4, 1, toni);
			Stein stein = brett.getStein(4, 1);
			pruefe(stein != null, "getStein(4,1) darf nicht null sein");
			if (stein != null) {
				pruefe(stein.getBesitzer() == toni, "Besitzer sollte Toni sein");
				pruefeGleich(4, stein.getZeile(), "getZeile()");
				pruefeGleich(1, stein.getSpalte(), "getSpalte()");
			}
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testGrundaufstellung() {
		starte("testGrundaufstellung");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			brett.grundaufstellung(alex, toni);

			pruefe(brett.getStein(2, 2) != null && brett.getStein(2, 2).getBesitzer() == toni,
					"(2,2) sollte Toni gehoeren");
			pruefe(brett.getStein(3, 3) != null && brett.getStein(3, 3).getBesitzer() == toni,
					"(3,3) sollte Toni gehoeren");
			pruefe(brett.getStein(2, 3) != null && brett.getStein(2, 3).getBesitzer() == alex,
					"(2,3) sollte Alex gehoeren");
			pruefe(brett.getStein(3, 2) != null && brett.getStein(3, 2).getBesitzer() == alex,
					"(3,2) sollte Alex gehoeren");
			pruefe(brett.getStein(0, 0) == null, "(0,0) sollte frei sein");
			pruefe(brett.getStein(2, 1) == null, "(2,1) sollte frei sein");
			pruefeGleich(2, brett.zaehleSteine(alex), "zaehleSteine(alex)");
			pruefeGleich(2, brett.zaehleSteine(toni), "zaehleSteine(toni)");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testZaehleRichtung() {
		starte("testZaehleRichtung");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			brett.setzeStein(1, 1, toni);
			brett.setzeStein(1, 2, alex);

			pruefeGleich(1, brett.zaehleRichtung(1, 0, alex, 4), "zaehleRichtung(1,0,alex,4)");
			pruefeGleich(0, brett.zaehleRichtung(1, 0, alex, 3), "zaehleRichtung(1,0,alex,3)");
			pruefeGleich(0, brett.zaehleRichtung(1, 0, alex, 7), "zaehleRichtung(1,0,alex,7)");
			pruefeGleich(0, brett.zaehleRichtung(1, 0, alex, 1), "zaehleRichtung(1,0,alex,1)");
			pruefeGleich(1, brett.zaehleRichtung(1, 3, toni, 3), "zaehleRichtung(1,3,toni,3)");
			pruefeGleich(0, brett.zaehleRichtung(1, 3, alex, 3), "zaehleRichtung(1,3,alex,3)");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	/* PFLICHTTEST! */
	private static void testIstZugGueltig() {
		starte("testIstZugGueltig");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			brett.grundaufstellung(alex, toni);

			pruefe(brett.istZugGueltig(1, 2, alex), "istZugGueltig(1,2,alex) sollte true sein");
			pruefe(brett.istZugGueltig(2, 1, alex), "istZugGueltig(2,1,alex) sollte true sein");
			pruefe(brett.istZugGueltig(3, 4, alex), "istZugGueltig(3,4,alex) sollte true sein");
			pruefe(brett.istZugGueltig(4, 3, alex), "istZugGueltig(4,3,alex) sollte true sein");

			pruefe(!brett.istZugGueltig(2, 4, alex), "istZugGueltig(2,4,alex) sollte false sein");
			pruefe(!brett.istZugGueltig(0, 0, alex), "istZugGueltig(0,0,alex) sollte false sein");
			pruefe(!brett.istZugGueltig(1, 1, alex), "istZugGueltig(1,1,alex) sollte false sein");

			pruefe(!brett.istZugGueltig(2, 2, alex), "belegtes Feld sollte false sein");
			pruefe(!brett.istZugGueltig(2, 3, alex), "belegtes Feld sollte false sein");

			pruefe(!brett.istZugGueltig(-1, 2, alex), "ausserhalb des Brettes sollte false sein");
			pruefe(!brett.istZugGueltig(6, 2, alex), "ausserhalb des Brettes sollte false sein");

			pruefe(brett.istZugGueltig(1, 3, toni), "istZugGueltig(1,3,toni) sollte true sein");
			pruefe(brett.istZugGueltig(2, 4, toni), "istZugGueltig(2,4,toni) sollte true sein");
			pruefe(!brett.istZugGueltig(2, 1, toni), "istZugGueltig(2,1,toni) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzeUndDrehe() {
		starte("testSetzeUndDrehe");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			brett.grundaufstellung(alex, toni);

			Stein alterStein = brett.getStein(2, 2);
			pruefeGleich(1, brett.setzeUndDrehe(2, 1, alex), "setzeUndDrehe(2,1,alex)");
			pruefe(brett.getStein(2, 2) == alterStein, "der umgedrehte Stein muss dasselbe Objekt bleiben");
			pruefe(brett.getStein(2, 2) != null && brett.getStein(2, 2).getBesitzer() == alex,
					"(2,2) sollte nun Alex gehoeren");
			pruefe(brett.getStein(2, 1) != null && brett.getStein(2, 1).getBesitzer() == alex,
					"(2,1) sollte Alex gehoeren");
			pruefeGleich(4, brett.zaehleSteine(alex), "zaehleSteine(alex)");
			pruefeGleich(1, brett.zaehleSteine(toni), "zaehleSteine(toni)");

			Spielbrett brett2 = new Spielbrett(6);
			brett2.setzeStein(2, 1, toni);
			brett2.setzeStein(2, 2, toni);
			brett2.setzeStein(2, 3, alex);
			brett2.setzeStein(3, 1, toni);
			brett2.setzeStein(4, 2, alex);

			pruefeGleich(3, brett2.setzeUndDrehe(2, 0, alex), "setzeUndDrehe(2,0,alex) in zwei Richtungen");
			pruefeGleich(6, brett2.zaehleSteine(alex), "zaehleSteine(alex)");
			pruefeGleich(0, brett2.zaehleSteine(toni), "zaehleSteine(toni)");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testZaehleSteineUndVoll() {
		starte("testZaehleSteineUndVoll");
		try {
			erzeugeSpieler();
			Spielbrett brett = new Spielbrett(6);
			pruefeGleich(0, brett.zaehleSteine(alex), "zaehleSteine auf leerem Brett");
			pruefe(!brett.istVoll(), "leeres Brett ist nicht voll");
			pruefe(!brett.hatGueltigenZug(alex), "auf leerem Brett gibt es keinen gueltigen Zug");

			brett.grundaufstellung(alex, toni);
			pruefeGleich(2, brett.zaehleSteine(alex), "zaehleSteine(alex)");
			pruefeGleich(2, brett.zaehleSteine(toni), "zaehleSteine(toni)");
			pruefe(!brett.istVoll(), "Brett ist nicht voll");
			pruefe(brett.hatGueltigenZug(alex), "Alex hat einen gueltigen Zug");
			pruefe(brett.hatGueltigenZug(toni), "Toni hat einen gueltigen Zug");

			Spielbrett voll = new Spielbrett(4);
			for (int z = 0; z < 4; z++) {
				for (int s = 0; s < 4; s++) {
					voll.setzeStein(z, s, alex);
				}
			}
			pruefe(voll.istVoll(), "volles Brett");
			pruefeGleich(16, voll.zaehleSteine(alex), "zaehleSteine(alex)");
			pruefeGleich(0, voll.zaehleSteine(toni), "zaehleSteine(toni)");
			pruefe(!voll.hatGueltigenZug(alex), "auf vollem Brett kein Zug moeglich");
			pruefe(!voll.hatGueltigenZug(toni), "auf vollem Brett kein Zug moeglich");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}
}
