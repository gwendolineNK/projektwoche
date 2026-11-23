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

	/* Die beiden Spieler des aktuellen Spiels. */
	private static Spieler spieler1;
	private static Spieler spieler2;

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
	 * Liest so lange eine Zeichenkette ein, bis diese "w" oder "s" ist.
	 * Gibt true zurueck, wenn "w" eingegeben wurde (waagerecht), false bei "s".
	 * Hinweis: String.equals(...)
	 */
	public static boolean checkAusrichtung() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Spielfeldgroesse ein und
	 * erzeugt damit die beiden Spieler-Objekte.
	 * Danach platziert jeder Spieler seine Schiffe.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Der uebergebene Spieler platziert nacheinander alle seine Schiffe.
	 * Fuer jedes Schiff werden Zeile, Spalte und Ausrichtung eingelesen.
	 * Ist die Platzierung nicht gueltig, wird erneut gefragt.
	 * Hinweis: Spieler.passtSchiff(...) und Schiff.platziere(...)
	 */
	public static void schiffePlatzieren(Spieler spieler) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Steuert ein komplettes Spiel: Die beiden Spieler sind abwechselnd an der
	 * Reihe, bis alle Schiffe eines Spielers versenkt sind.
	 * Am Ende wird der Gewinner ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Fuehrt den Zug des Spielers aktiv gegen den Spieler gegner aus.
	 * Es wird das Spielfeld des Gegners ausgegeben, danach werden so lange
	 * Zeile und Spalte eingelesen, bis ein Feld genannt wird, das im Spielfeld
	 * des Gegners liegt und auf das noch nicht geschossen wurde.
	 * Anschliessend wird der Schuss ausgewertet.
	 */
	public static void spielzug(Spieler aktiv, Spieler gegner) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Wertet den Schuss auf das Feld (z, s) des Spielfeldes von gegner aus:
	 * Liegt dort ein Schiff des Gegners, erhaelt dieses einen Treffer und das
	 * Feld wird als Treffer markiert, sonst wird es als Wasser markiert.
	 * Gibt eine passende Meldung aus.
	 *
	 * Hinweis: Nur der Gegner selbst kann sagen, ob auf (z, s) ein Schiff liegt.
	 */
	public static void auswertung(Spieler gegner, int z, int s) {
		// TODO
		// Anweisung(en) hinzufuegen
	}
}
