import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 *
 * NUR in dieser Klasse duerfen Konsolenein- und -ausgaben erfolgen!
 ****************************************************************************************************** */

public class Spiel {

	/* NICHT AENDERN! */
	private static Scanner sc = new Scanner(System.in);

	/* Die Objekte des aktuellen Spiels. */
	private static Spieler spieler1;
	private static Spieler spieler2;
	private static Spielbrett brett;

	/*
	 * Vervollstaendigen Sie die main-Methode.
	 * Sie begruesst den Nutzer und ruft das Menue auf.
	 */
	public static void main(String[] args) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt das Auswahlmenue aus und liest die Auswahl ein.
	 * Bei 1 wird ein Spiel vorbereitet und gespielt, danach erscheint das
	 * Menue erneut. Bei 2 wird das Programm beendet.
	 */
	public static void menu() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese 1 oder 2 ist, und gibt sie zurueck.
	 */
	public static int checkEingabeMenu() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese gerade und mindestens 4 ist,
	 * und gibt sie zurueck.
	 */
	public static int checkEingabeGroesse() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Brettgroesse ein.
	 * Erzeugt die beiden Spieler (Spieler 1 mit dem Zeichen "X", Spieler 2 mit
	 * dem Zeichen "O"), macht sie zu Gegnern, erzeugt das Brett und baut die
	 * Grundaufstellung auf.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Steuert ein komplettes Spiel.
	 * Spieler 1 beginnt. Kann der Spieler, der an der Reihe ist, nicht ziehen,
	 * wird das gemeldet. Das Spiel endet, wenn das Brett voll ist oder keiner
	 * der beiden Spieler mehr ziehen kann.
	 * Am Ende wird das Ergebnis ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Fuehrt den Zug des uebergebenen Spielers aus.
	 * Das Brett und der Punktestand werden ausgegeben, danach werden so lange
	 * Zeile und Spalte eingelesen, bis ein gueltiger Zug genannt wird.
	 * Anschliessend wird der Zug ausgefuehrt und die Anzahl der umgedrehten
	 * Steine gemeldet.
	 */
	public static void spielzug(Spieler aktiv) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt den Endstand und den Gewinner aus.
	 * Bei Gleichstand wird ein Unentschieden gemeldet.
	 */
	public static void auswertung() {
		// TODO
		// Anweisung(en) hinzufuegen
	}
}
