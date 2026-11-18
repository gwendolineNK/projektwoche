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
	public static Spielfeld feld;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu SOS Light");

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
	 * Gibt true zurueck, wenn eingabe ein erlaubter Buchstabe ist,
	 * also "S", "s", "O" oder "o"; false sonst.
	 * Hinweis: Aus der Klasse String darf nur equals() verwendet werden.
	 */
	public static boolean checkBuchstabe(String eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Wandelt die Eingabe in den passenden Buchstaben um:
	 * "S" und "s" ergeben Muster.S, "O" und "o" ergeben Muster.O.
	 * Bei einer unerlaubten Eingabe wird Muster.LEER zurueckgegeben.
	 */
	public static char buchstabeAus(String eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return 'x';
	}

	/*
	 * Liest die Namen der beiden Spieler sowie die Anzahl der Felder ein.
	 * Erzeugt die beiden Spieler und das Spielfeld.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Es wird gespielt, bis das Spielfeld voll ist.
	 * Spieler 1 beginnt. Hat ein Spieler durch seinen Zug mindestens ein SOS
	 * erzeugt, ist er noch einmal an der Reihe; sonst ist der andere Spieler dran.
	 * Methoden spielzug(), Spielfeld.istVoll() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println("Das Spielfeld ist voll.");
		System.out.println(auswertung());
	}

	/*
	 * Fuehrt einen Zug des uebergebenen Spielers aus.
	 * Es wird so lange nach einer Feldnummer gefragt, bis eine gueltige
	 * Feldnummer eingegeben wurde, und so lange nach einem Buchstaben,
	 * bis ein erlaubter Buchstabe eingegeben wurde.
	 * Danach wird der Buchstabe gesetzt und geprueft, wie viele SOS dadurch
	 * entstanden sind. Diese werden dem Spieler gutgeschrieben.
	 *
	 * Rueckgabewert: die Anzahl der durch diesen Zug entstandenen SOS.
	 *
	 * Methoden Spielfeld.gueltigeEingabe(), Spielfeld.setStelle(),
	 * Muster.zaehleSOSMit(), Spieler.punkteDazu(), checkBuchstabe()
	 * und buchstabeAus() verwenden.
	 */
	public static int spielzug(Spieler spieler) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.println(spieler.getName() + " ist an der Reihe.");
		System.out.println(feld);
		System.out.println("Punkte: " + spieler1.getName() + " " + spieler1.getPunkte()
				+ " - " + spieler2.getName() + " " + spieler2.getPunkte());

		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 */
	public static String auswertung() {
		String stand = "Endstand: " + spieler1.getName() + " " + spieler1.getPunkte()
				+ " - " + spieler2.getName() + " " + spieler2.getPunkte() + "\n";

		if (spieler1.getPunkte() > spieler2.getPunkte()) {
			return stand + spieler1.getName() + " hat gewonnen!";
		}

		if (spieler2.getPunkte() > spieler1.getPunkte()) {
			return stand + spieler2.getName() + " hat gewonnen!";
		}

		return stand + "Unentschieden.";
	}
}
