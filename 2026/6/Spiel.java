import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spiel {

	/* NICHT AENDERN! Hoechstzahl der Zuege. */
	public static final int MAX_ZUEGE = 100;

	/* Verwenden Sie unbedingt diese Klassenattribute in den Methoden der Klasse! */
	private static Scanner sc = new Scanner(System.in);
	public static Spieler spieler1;
	public static Spieler spieler2;
	public static Spielfeld brett;

	/*
	 * In diesem Attribut muss der Spieler gespeichert werden, der eine bereits
	 * aufgetretene Stellung erzeugt hat. Die Methode auswertung() wertet es aus.
	 * Bleibt es null, endet das Spiel unentschieden.
	 */
	public static Spieler verlierer;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu Puan-Puan Light");

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
	 * Liest die Namen der beiden Spieler sowie die Spielfeldgroesse ein.
	 * Erzeugt die beiden Spieler und das Brett (es muessen MAX_ZUEGE + 1
	 * Stellungen gemerkt werden koennen).
	 * Setzt das Attribut verlierer zurueck und merkt die leere Anfangsstellung.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Es wird gespielt, bis ein Spieler eine bereits aufgetretene Stellung
	 * erzeugt hat oder bis MAX_ZUEGE Zuege gespielt wurden.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 *
	 * Nach jedem Zug wird geprueft, ob die entstandene Stellung schon einmal
	 * aufgetreten ist. Ist das der Fall, wird der ziehende Spieler im Attribut
	 * verlierer gespeichert, eine entsprechende Meldung ausgegeben und das Spiel
	 * beendet. Sonst wird die neue Stellung gemerkt.
	 *
	 * Methoden spielzug(), Spielfeld.stellungKamSchonVor() und
	 * Spielfeld.merkeStellung() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println(auswertung());
	}

	/*
	 * Fuehrt einen Zug des uebergebenen Spielers aus.
	 * Es wird so lange nach Zeile und Spalte gefragt, bis ein existierendes Feld
	 * genannt wurde. Danach wird der Zug ausgefuehrt und gemeldet, ob ein Stein
	 * gesetzt oder weggenommen wurde.
	 *
	 * Methoden Spielfeld.imFeld(), Spielfeld.zugAusfuehren() und
	 * Spieler.getName() verwenden.
	 */
	public static void spielzug(Spieler spieler, int zugnummer) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.println("Zug " + zugnummer + " - " + spieler.getName() + " ist an der Reihe.");
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
			return spieler1.getName() + " hat verloren. " + spieler2.getName() + " hat gewonnen!";
		}

		if (verlierer == spieler2) {
			return spieler2.getName() + " hat verloren. " + spieler1.getName() + " hat gewonnen!";
		}

		return "Die Hoechstzahl an Zuegen ist erreicht. Unentschieden.";
	}
}
