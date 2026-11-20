import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spiel {

	/* NICHT AENDERN! Hoechstzahl der berechneten Generationen. */
	public static final int MAX_GENERATIONEN = 20;

	/* Verwenden Sie unbedingt diese Klassenattribute in den Methoden der Klasse! */
	private static Scanner sc = new Scanner(System.in);
	public static Spieler spieler1;
	public static Spieler spieler2;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu Game of Life Light");

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
	 * Liest die Namen der beiden Spieler, die Spielfeldgroesse sowie die Anzahl
	 * der zu setzenden Zellen ein. Erzeugt die beiden Spieler.
	 * Laesst anschliessend beide Spieler ihre Zellen setzen (Methode zellenSetzen()).
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Laesst den uebergebenen Spieler anzahl Zellen auf seinem Spielfeld setzen.
	 * Es wird so lange nach Zeile und Spalte gefragt, bis eine existierende und
	 * noch tote Zelle genannt wurde.
	 * Methoden Spieler.getSpielfeld(), Spielfeld.imFeld(), Spielfeld.istLebendig()
	 * und Spielfeld.setzeZelle() verwenden.
	 */
	public static void zellenSetzen(Spieler spieler, int anzahl) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Die Simulationsphase: Es werden hoechstens MAX_GENERATIONEN Generationen
	 * berechnet. Die Simulation endet frueher, wenn sich BEIDE Spielfelder nicht
	 * mehr veraendert haben.
	 * Nach jeder Generation werden die Nummer der Generation, beide Spielfelder
	 * und die Anzahl der lebenden Zellen ausgegeben.
	 * Methoden Spielfeld.naechsteGeneration() und Spielfeld.zaehleLebende() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen

		// NICHT AENDERN
		System.out.println("Die Simulation ist beendet.");
		System.out.println(auswertung());
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 */
	public static String auswertung() {
		int a = spieler1.getSpielfeld().zaehleLebende();
		int b = spieler2.getSpielfeld().zaehleLebende();

		String stand = spieler1.getName() + ": " + a + " lebende Zellen\n"
				+ spieler2.getName() + ": " + b + " lebende Zellen\n";

		if (a > b) {
			return stand + spieler1.getName() + " hat gewonnen!";
		}

		if (b > a) {
			return stand + spieler2.getName() + " hat gewonnen!";
		}

		return stand + "Unentschieden.";
	}
}
