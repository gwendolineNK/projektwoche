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
	public static Spielbrett brett;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu Opala Light");

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
	 * Liest die Namen der beiden Spieler ein, erzeugt die beiden Spieler
	 * (Nummern 1 und 2) und das Spielbrett und stellt die Figuren in der
	 * Grundaufstellung auf.
	 * Methode Spielbrett.grundaufstellung() verwenden.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Es wird gespielt, bis ein Spieler gewonnen hat.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 * Nach jedem Zug wird geprueft, ob das Spiel beendet ist.
	 * Methoden spielzug(), Spielbrett.hatGrundlinieErreicht() und
	 * Spieler.zaehleFigurenImSpiel() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println(auswertung());
	}

	/*
	 * Der Spieler gibt Start- und Zielfeld seines Zuges an.
	 * Solange der Zug nicht gueltig ist, wird eine Meldung ausgegeben und
	 * erneut gefragt. Danach wird der Zug ausgefuehrt. Wird dabei eine Figur
	 * geschlagen, wird dies gemeldet.
	 * Methoden Spielbrett.istZugGueltig(), Spielbrett.fuehreZugAus() und
	 * Spieler.getName() verwenden.
	 */
	public static void spielzug(Spieler spieler) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.println(spieler.getName() + " ist an der Reihe.");
		System.out.println(brett);

		int vonZeile;
		int vonSpalte;
		int nachZeile;
		int nachSpalte;

		do {
			System.out.print("Startfeld - Zeile: ");
			vonZeile = sc.nextInt();

			System.out.print("Startfeld - Spalte: ");
			vonSpalte = sc.nextInt();

			System.out.print("Zielfeld - Zeile: ");
			nachZeile = sc.nextInt();

			System.out.print("Zielfeld - Spalte: ");
			nachSpalte = sc.nextInt();

			// TODO
			// Anweisung(en) hinzufuegen

		} while (true); // TODO: Schleifenbedingung anpassen
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 */
	public static String auswertung() {
		if (brett.hatGrundlinieErreicht(spieler1) || spieler2.zaehleFigurenImSpiel() == 0) {
			return spieler1.getName() + " hat gewonnen!";
		}

		if (brett.hatGrundlinieErreicht(spieler2) || spieler1.zaehleFigurenImSpiel() == 0) {
			return spieler2.getName() + " hat gewonnen!";
		}

		return "Das Spiel ist beendet.";
	}
}
