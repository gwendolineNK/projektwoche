
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
		System.out.println("=== Soeldnerkrieg Light - Tests ===");
		System.out.println();

		testSpielerKonstruktor();
		testSetzeGegner();
		testSoeldnerKonstruktor();
		testWechsleZuUndGehoert();
		testIstStaerkerAls();
		testSchlachtfeldKonstruktor();
		testImFeldUndIstFrei();
		testSetzeSoeldner();
		testGrundaufstellung();
		testSindNachbarnUndZiehen();
		testKannAnwerben();
		testWerbeAnUndZaehleSoeldner();

		System.out.println();
		System.out.println("=======================================");
		System.out.println("Bestanden: " + bestanden + " von " + gesamt);
		System.out.println("Pflichttest testKannAnwerben: " + (pflichtTestOk ? "BESTANDEN" : "NICHT BESTANDEN"));
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
			if (aktuellerTest.equals("testKannAnwerben")) {
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

	/*
	 *       0   1   2   3   4
	 *   0 |  .   .   .   .   .
	 *   1 |  .   .  O3   .   .
	 *   2 |  .   .  X3  O2   .
	 *   3 |  .   .  X1   .   .
	 *   4 |  .   .   .   .   .
	 */
	private static Schlachtfeld vorbereitetesFeld() {
		Schlachtfeld sf = new Schlachtfeld(5);
		sf.setzeSoeldner(2, 2, 3, alex);
		sf.setzeSoeldner(2, 3, 2, toni);
		sf.setzeSoeldner(1, 2, 3, toni);
		sf.setzeSoeldner(3, 2, 1, alex);
		return sf;
	}

	/* ---------- Tests ---------- */

	private static void testSpielerKonstruktor() {
		starte("testSpielerKonstruktor");
		try {
			Spieler spieler = new Spieler("Alex", "X");
			pruefe("Alex".equals(spieler.getName()), "getName() - war: " + spieler.getName());
			pruefe("X".equals(spieler.getZeichen()), "getZeichen() - war: " + spieler.getZeichen());
			pruefeGleich(0, spieler.getAnwerbungen(), "getAnwerbungen()");
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

	private static void testSoeldnerKonstruktor() {
		starte("testSoeldnerKonstruktor");
		try {
			erzeugeSpieler();
			Soeldner soeldner = new Soeldner(2, 1, 3, alex);
			pruefeGleich(2, soeldner.getStaerke(), "getStaerke()");
			pruefeGleich(1, soeldner.getZeile(), "getZeile()");
			pruefeGleich(3, soeldner.getSpalte(), "getSpalte()");
			pruefe(soeldner.getDienstherr() == alex, "getDienstherr() sollte Alex sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testWechsleZuUndGehoert() {
		starte("testWechsleZuUndGehoert");
		try {
			erzeugeSpieler();
			Soeldner soeldner = new Soeldner(2, 1, 3, alex);
			pruefe(soeldner.gehoert(alex), "gehoert(alex) sollte true sein");
			pruefe(!soeldner.gehoert(toni), "gehoert(toni) sollte false sein");

			soeldner.wechsleZu(toni);
			pruefe(soeldner.getDienstherr() == toni, "getDienstherr() sollte Toni sein");
			pruefe(soeldner.gehoert(toni), "gehoert(toni) sollte true sein");
			pruefe(!soeldner.gehoert(alex), "gehoert(alex) sollte false sein");
			pruefeGleich(2, soeldner.getStaerke(), "getStaerke() bleibt unveraendert");
			pruefeGleich(1, soeldner.getZeile(), "getZeile() bleibt unveraendert");
			pruefeGleich(3, soeldner.getSpalte(), "getSpalte() bleibt unveraendert");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testIstStaerkerAls() {
		starte("testIstStaerkerAls");
		try {
			erzeugeSpieler();
			Soeldner schwach = new Soeldner(1, 0, 0, alex);
			Soeldner mittel = new Soeldner(2, 0, 1, alex);
			Soeldner stark = new Soeldner(3, 0, 2, toni);

			pruefe(stark.istStaerkerAls(mittel), "3 ist staerker als 2");
			pruefe(stark.istStaerkerAls(schwach), "3 ist staerker als 1");
			pruefe(mittel.istStaerkerAls(schwach), "2 ist staerker als 1");
			pruefe(!mittel.istStaerkerAls(stark), "2 ist nicht staerker als 3");
			pruefe(!schwach.istStaerkerAls(mittel), "1 ist nicht staerker als 2");
			pruefe(!mittel.istStaerkerAls(new Soeldner(2, 4, 4, toni)), "gleich stark reicht nicht");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSchlachtfeldKonstruktor() {
		starte("testSchlachtfeldKonstruktor");
		try {
			Schlachtfeld sf = new Schlachtfeld(5);
			pruefeGleich(5, sf.getN(), "getN()");
			for (int z = 0; z < 5; z++) {
				for (int s = 0; s < 5; s++) {
					pruefe(sf.getSoeldner(z, s) == null, "getSoeldner(" + z + "," + s + ") sollte null sein");
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
			Schlachtfeld sf = new Schlachtfeld(5);
			pruefe(sf.imFeld(0, 0), "imFeld(0,0) sollte true sein");
			pruefe(sf.imFeld(4, 4), "imFeld(4,4) sollte true sein");
			pruefe(!sf.imFeld(-1, 0), "imFeld(-1,0) sollte false sein");
			pruefe(!sf.imFeld(0, -1), "imFeld(0,-1) sollte false sein");
			pruefe(!sf.imFeld(5, 0), "imFeld(5,0) sollte false sein");
			pruefe(!sf.imFeld(0, 5), "imFeld(0,5) sollte false sein");

			pruefe(sf.istFrei(2, 2), "istFrei(2,2) sollte true sein");
			sf.setzeSoeldner(2, 2, 3, alex);
			pruefe(!sf.istFrei(2, 2), "istFrei(2,2) sollte nun false sein");
			pruefe(!sf.istFrei(-1, 0), "istFrei(-1,0) sollte false sein");
			pruefe(!sf.istFrei(5, 5), "istFrei(5,5) sollte false sein");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSetzeSoeldner() {
		starte("testSetzeSoeldner");
		try {
			erzeugeSpieler();
			Schlachtfeld sf = new Schlachtfeld(5);
			sf.setzeSoeldner(4, 1, 2, toni);
			Soeldner soeldner = sf.getSoeldner(4, 1);
			pruefe(soeldner != null, "getSoeldner(4,1) darf nicht null sein");
			if (soeldner != null) {
				pruefeGleich(2, soeldner.getStaerke(), "getStaerke()");
				pruefeGleich(4, soeldner.getZeile(), "getZeile()");
				pruefeGleich(1, soeldner.getSpalte(), "getSpalte()");
				pruefe(soeldner.getDienstherr() == toni, "Dienstherr sollte Toni sein");
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
			Schlachtfeld sf = new Schlachtfeld(5);
			sf.grundaufstellung(alex, toni);

			pruefe(sf.getSoeldner(0, 0) != null, "(0,0) darf nicht leer sein");
			if (sf.getSoeldner(0, 0) != null) {
				pruefeGleich(1, sf.getSoeldner(0, 0).getStaerke(), "Staerke auf (0,0)");
				pruefeGleich(2, sf.getSoeldner(0, 1).getStaerke(), "Staerke auf (0,1)");
				pruefeGleich(3, sf.getSoeldner(0, 2).getStaerke(), "Staerke auf (0,2)");
				pruefeGleich(1, sf.getSoeldner(0, 3).getStaerke(), "Staerke auf (0,3)");
				pruefeGleich(2, sf.getSoeldner(0, 4).getStaerke(), "Staerke auf (0,4)");
				pruefe(sf.getSoeldner(0, 0).getDienstherr() == alex, "(0,0) sollte Alex dienen");
				pruefe(sf.getSoeldner(0, 4).getDienstherr() == alex, "(0,4) sollte Alex dienen");
			}
			pruefe(sf.getSoeldner(4, 0) != null && sf.getSoeldner(4, 0).getDienstherr() == toni,
					"(4,0) sollte Toni dienen");
			pruefe(sf.getSoeldner(4, 2) != null && sf.getSoeldner(4, 2).getDienstherr() == toni,
					"(4,2) sollte Toni dienen");
			if (sf.getSoeldner(4, 2) != null) {
				pruefeGleich(3, sf.getSoeldner(4, 2).getStaerke(), "Staerke auf (4,2)");
			}
			pruefe(sf.getSoeldner(1, 0) == null, "(1,0) sollte leer sein");
			pruefe(sf.getSoeldner(2, 2) == null, "(2,2) sollte leer sein");

			pruefeGleich(5, sf.zaehleSoeldner(alex), "zaehleSoeldner(alex)");
			pruefeGleich(5, sf.zaehleSoeldner(toni), "zaehleSoeldner(toni)");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testSindNachbarnUndZiehen() {
		starte("testSindNachbarnUndZiehen");
		try {
			erzeugeSpieler();
			Schlachtfeld sf = new Schlachtfeld(5);
			sf.setzeSoeldner(2, 2, 3, alex);

			pruefe(sf.sindNachbarn(2, 2, 2, 3), "(2,2) und (2,3) sind Nachbarn");
			pruefe(sf.sindNachbarn(2, 2, 2, 1), "(2,2) und (2,1) sind Nachbarn");
			pruefe(sf.sindNachbarn(2, 2, 1, 2), "(2,2) und (1,2) sind Nachbarn");
			pruefe(sf.sindNachbarn(2, 2, 3, 2), "(2,2) und (3,2) sind Nachbarn");
			pruefe(!sf.sindNachbarn(2, 2, 1, 1), "diagonal sind keine Nachbarn");
			pruefe(!sf.sindNachbarn(2, 2, 2, 2), "ein Feld ist nicht sein eigener Nachbar");
			pruefe(!sf.sindNachbarn(2, 2, 2, 4), "(2,2) und (2,4) sind keine Nachbarn");

			pruefe(sf.kannZiehen(2, 2, 2, 3, alex), "Zug auf ein freies Nachbarfeld");
			pruefe(!sf.kannZiehen(2, 2, 2, 4, alex), "kein Nachbarfeld");
			pruefe(!sf.kannZiehen(2, 2, 2, 3, toni), "nicht der eigene Soeldner");
			pruefe(!sf.kannZiehen(0, 0, 0, 1, alex), "Startfeld ist leer");

			sf.setzeSoeldner(2, 3, 1, toni);
			pruefe(!sf.kannZiehen(2, 2, 2, 3, alex), "das Zielfeld ist besetzt");

			Soeldner alter = sf.getSoeldner(2, 2);
			sf.ziehe(2, 2, 1, 2);
			pruefe(sf.getSoeldner(2, 2) == null, "das alte Feld muss frei sein");
			pruefe(sf.getSoeldner(1, 2) == alter, "es muss derselbe Soeldner sein");
			if (sf.getSoeldner(1, 2) != null) {
				pruefeGleich(1, sf.getSoeldner(1, 2).getZeile(), "getZeile() nach dem Zug");
				pruefeGleich(2, sf.getSoeldner(1, 2).getSpalte(), "getSpalte() nach dem Zug");
				pruefeGleich(3, sf.getSoeldner(1, 2).getStaerke(), "getStaerke() bleibt unveraendert");
			}
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	/* PFLICHTTEST! */
	private static void testKannAnwerben() {
		starte("testKannAnwerben");
		try {
			erzeugeSpieler();
			Schlachtfeld sf = vorbereitetesFeld();

			pruefe(sf.kannAnwerben(2, 2, 2, 3, alex), "X3 wirbt O2 an");
			pruefe(!sf.kannAnwerben(2, 2, 1, 2, alex), "gleich stark reicht nicht");
			pruefe(!sf.kannAnwerben(2, 2, 3, 2, alex), "das Ziel ist ein eigener Soeldner");
			pruefe(!sf.kannAnwerben(2, 2, 2, 2, alex), "dasselbe Feld");
			pruefe(!sf.kannAnwerben(2, 2, 0, 2, alex), "kein Nachbarfeld");
			pruefe(!sf.kannAnwerben(2, 2, 2, 1, alex), "das Ziel ist frei");

			sf.setzeSoeldner(4, 2, 2, toni);
			pruefe(!sf.kannAnwerben(3, 2, 4, 2, alex), "der eigene Soeldner ist schwaecher");

			pruefe(!sf.kannAnwerben(2, 2, 2, 3, toni), "der Startpunkt gehoert dem Gegner");
			pruefe(!sf.kannAnwerben(0, 0, 0, 1, alex), "der Startpunkt ist leer");
			pruefe(!sf.kannAnwerben(-1, 2, 2, 2, alex), "ausserhalb des Schlachtfeldes");
			pruefe(!sf.kannAnwerben(2, 2, 2, 5, alex), "ausserhalb des Schlachtfeldes");

			pruefe(!sf.kannAnwerben(1, 2, 2, 2, toni), "O3 gegen X3 reicht nicht");

			sf.setzeSoeldner(3, 3, 1, alex);
			pruefe(sf.kannAnwerben(2, 3, 3, 3, toni), "O2 wirbt X1 an");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}

	private static void testWerbeAnUndZaehleSoeldner() {
		starte("testWerbeAnUndZaehleSoeldner");
		try {
			erzeugeSpieler();
			Schlachtfeld sf = vorbereitetesFeld();
			pruefeGleich(2, sf.zaehleSoeldner(alex), "zaehleSoeldner(alex)");
			pruefeGleich(2, sf.zaehleSoeldner(toni), "zaehleSoeldner(toni)");
			pruefe(!sf.besitztAlle(alex), "besitztAlle(alex) sollte false sein");

			Soeldner alter = sf.getSoeldner(2, 3);
			sf.werbeAn(2, 3, alex);

			pruefe(sf.getSoeldner(2, 3) == alter, "es muss derselbe Soeldner sein");
			pruefe(alter.getDienstherr() == alex, "der Dienstherr sollte nun Alex sein");
			pruefeGleich(2, alter.getStaerke(), "getStaerke() bleibt unveraendert");
			pruefeGleich(2, alter.getZeile(), "getZeile() bleibt unveraendert");
			pruefeGleich(3, alter.getSpalte(), "getSpalte() bleibt unveraendert");

			pruefeGleich(3, sf.zaehleSoeldner(alex), "zaehleSoeldner(alex)");
			pruefeGleich(1, sf.zaehleSoeldner(toni), "zaehleSoeldner(toni)");
			pruefeGleich(1, alex.getAnwerbungen(), "getAnwerbungen()");
			pruefe(!sf.besitztAlle(alex), "besitztAlle(alex) sollte noch false sein");

			sf.werbeAn(1, 2, alex);
			pruefeGleich(4, sf.zaehleSoeldner(alex), "zaehleSoeldner(alex)");
			pruefeGleich(0, sf.zaehleSoeldner(toni), "zaehleSoeldner(toni)");
			pruefeGleich(2, alex.getAnwerbungen(), "getAnwerbungen()");
			pruefe(sf.besitztAlle(alex), "besitztAlle(alex) sollte true sein");
			pruefe(!sf.besitztAlle(toni), "besitztAlle(toni) sollte false sein");

			Schlachtfeld leer = new Schlachtfeld(5);
			pruefeGleich(0, leer.zaehleSoeldner(alex), "zaehleSoeldner auf leerem Feld");
			pruefe(!leer.besitztAlle(alex), "auf leerem Feld besitzt niemand alles");
		} catch (Exception e) {
			pruefe(false, "Ausnahme: " + e);
		}
		beende();
	}
}