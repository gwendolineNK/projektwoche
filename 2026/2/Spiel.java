import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spiel {

	/* Verwenden Sie unbedingt diese Klassenattribute in den Methoden der Klasse! */
	private static Scanner sc = new Scanner(System.in);
	public static Spieler spieler;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu 2048 Light");

		int eingabe;
		boolean spielen = true;

		do {
			eingabe = menu();

			if (eingabe == 1) {
				/*
				 * Methoden spielVorbereiten() und spielen() aufrufen
				 */
				// TODO
				// Anweisung(en) hinzufuegen

			}

			/* Dafuer sorgen, dass bei Eingabe 2 das Programm endet */
			// TODO
			// Anweisung(en) hinzufuegen

		} while (spielen);

		System.out.println("Programm beendet.");
		sc.close();
	}

	/*
	 * Fragt solange nach einer Menu-Eingabe bis eine erlaubte Zahl eingegeben wird.
	 * Methode checkEingabeMenu() verwenden
	 */
	public static int menu() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt bei Eingabe von 1 oder 2 true zurueck. Sonst false.
	 */
	public static boolean checkEingabeMenu(int eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn eingabe eine erlaubte Richtung ist,
	 * also "l", "r", "o" oder "u"; false sonst.
	 * Hinweis: Aus der Klasse String darf nur equals() verwendet werden.
	 */
	public static boolean checkRichtung(String eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Wandelt die Eingabe in die passende Konstante der Klasse Spielfeld um:
	 * "l" -> Spielfeld.LINKS, "r" -> Spielfeld.RECHTS,
	 * "o" -> Spielfeld.OBEN,  "u" -> Spielfeld.UNTEN.
	 * Bei einer unerlaubten Eingabe wird -1 zurueckgegeben.
	 */
	public static int richtungAus(String eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -2;
	}

	/*
	 * Liest den Namen des Spielers sowie die Groesse des Spielfelds ein
	 * und erzeugt den Spieler.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Es wird gespielt, bis der Spieler das Ziel erreicht hat
	 * oder kein Zug mehr moeglich ist.
	 * Methoden spielzug(), Spieler.getSpielfeld(), Spielfeld.hatGewonnen()
	 * und Spielfeld.zugMoeglich() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println(auswertung());
	}

	/*
	 * Gibt das Spielfeld und den Punktestand aus und fragt nach der Richtung.
	 * Solange keine erlaubte Richtung eingegeben wurde, wird erneut gefragt.
	 * Danach wird der Zug ausgefuehrt. Hat sich dabei etwas veraendert,
	 * erscheint eine neue zufaellige Kachel; sonst wird eine Meldung ausgegeben.
	 * Methoden checkRichtung(), richtungAus(), Spielfeld.verschiebe(),
	 * Spielfeld.zufaelligeKachel() und Spielfeld.getPunkte() verwenden.
	 */
	public static void spielzug(Spieler spieler) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.println(spieler.getName() + ", dein Spielfeld:");
		System.out.println(spieler.getSpielfeld());

		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 */
	public static String auswertung() {
		if (spieler.getSpielfeld().hatGewonnen()) {
			return "Glueckwunsch " + spieler.getName() + "! Du hast " + Spielfeld.ZIEL
					+ " erreicht!\nEndpunktestand: " + spieler.getSpielfeld().getPunkte();
		}

		return "Kein Zug mehr moeglich. Verloren!\nEndpunktestand: "
				+ spieler.getSpielfeld().getPunkte();
	}
}
