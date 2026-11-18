import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spiel {

	/* Verwenden Sie unbedingt diese Klassenattribute in den Methoden der Klasse! */
	private static Scanner sc = new Scanner(System.in);
	public static Spieler spieler1;
	public static Spieler spieler2;
	public static Spielfeld brett;

	/*
	 * In diesem Attribut muss der Spieler gespeichert werden, der keinen
	 * gueltigen Zug mehr hat. Die Methode auswertung() wertet es aus.
	 */
	public static Spieler verlierer;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu Sowing Light");

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
	 * Liest die Namen der beiden Spieler, die Anzahl der Mulden sowie die Anzahl
	 * der Samen pro Mulde ein. Erzeugt die beiden Spieler und das Brett.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Es wird gespielt, bis ein Spieler keinen gueltigen Zug mehr hat.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 *
	 * Vor jedem Zug wird geprueft, ob der aktive Spieler noch einen gueltigen Zug
	 * hat. Hat er keinen, wird er im Attribut verlierer gespeichert, eine
	 * entsprechende Meldung ausgegeben und das Spiel beendet.
	 *
	 * Methoden spielzug(), Spielfeld.hatZugMoeglichkeit() und
	 * Spieler.getNummer() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println(auswertung());
	}

	/*
	 * Fuehrt einen Zug des uebergebenen Spielers aus.
	 * Es wird so lange nach einer Muldennummer gefragt, bis ein gueltiger Zug
	 * eingegeben wurde. Danach wird der Zug ausgefuehrt und die Anzahl der
	 * verteilten Samen gemeldet.
	 *
	 * Methoden Spielfeld.istZugGueltig(), Spielfeld.fuehreZugAus(),
	 * Spieler.getName() und Spieler.getNummer() verwenden.
	 */
	public static void spielzug(Spieler spieler) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.print(spieler.getName() + " ist an der Reihe. Zugrichtung: ");
		if (spieler.getRichtung() == 1) {
			System.out.println("nach rechts");
		} else {
			System.out.println("nach links");
		}
		System.out.println(brett);

		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 * Wertet das Attribut verlierer aus.
	 */
	public static String auswertung() {
		if (verlierer == spieler1) {
			return spieler2.getName() + " hat gewonnen!";
		}

		if (verlierer == spieler2) {
			return spieler1.getName() + " hat gewonnen!";
		}

		return "Das Spiel ist beendet.";
	}
}
