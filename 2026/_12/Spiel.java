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

	/* NICHT AENDERN! Nach so vielen Runden endet das Spiel spaetestens. */
	public static final int MAX_RUNDEN = 50;

	/* Die Objekte des aktuellen Spiels. */
	private static Spieler spieler1;
	private static Spieler spieler2;
	private static Schlachtfeld schlachtfeld;

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
	 * Liest so lange eine Zahl ein, bis diese mindestens 4 ist, und gibt sie
	 * zurueck.
	 */
	public static int checkEingabeGroesse() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Groesse des Schlachtfeldes ein.
	 * Erzeugt die beiden Spieler (Spieler 1 mit dem Zeichen "X", Spieler 2 mit
	 * dem Zeichen "O"), macht sie zu Gegnern, erzeugt das Schlachtfeld und baut
	 * die Grundaufstellung auf.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Steuert ein komplettes Spiel.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 * Das Spiel endet, sobald einer der beiden Spieler alle Soeldner besitzt
	 * oder MAX_RUNDEN Runden gespielt wurden.
	 * Am Ende wird das Ergebnis ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Fuehrt den Zug des uebergebenen Spielers aus.
	 * Das Schlachtfeld und der Stand werden ausgegeben, danach werden so lange
	 * Zeile und Spalte des eigenen Soeldners sowie Zeile und Spalte des
	 * Zielfeldes eingelesen, bis ein gueltiger Zug genannt wird: entweder ein
	 * Zug auf ein freies Nachbarfeld oder das Anwerben eines schwaecheren
	 * gegnerischen Soeldners.
	 * Anschliessend wird der Zug ausgefuehrt und gemeldet, was geschehen ist.
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
