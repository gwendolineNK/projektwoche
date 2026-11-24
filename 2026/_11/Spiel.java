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
	private static Spielfeld feld;

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
	 * Liest so lange eine Zahl ein, bis diese mindestens 5 ist, und gibt sie
	 * zurueck.
	 */
	public static int checkEingabeGroesse() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese zwischen 0 und ANZAHL_TIERE - 1
	 * liegt, und gibt sie zurueck.
	 */
	public static int checkEingabeTier() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest so lange eine Zeichenkette ein, bis diese "n", "o", "s" oder "w" ist.
	 * Gibt den Index der Richtung zurueck: "n" ergibt 0, "o" ergibt 1,
	 * "s" ergibt 2 und "w" ergibt 3.
	 * Hinweis: String.equals(...)
	 */
	public static int checkEingabeRichtung() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Spielfeldgroesse ein.
	 * Erzeugt das Spielfeld sowie die beiden Spieler: Spieler 1 mit dem Zeichen
	 * "A", der Heimatzeile 0 und der Zielzeile n-1; Spieler 2 mit dem Zeichen
	 * "B", der Heimatzeile n-1 und der Zielzeile 0.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Steuert ein komplettes Spiel: Die beiden Spieler sind abwechselnd an der
	 * Reihe, bis einer von ihnen alle Tiere verloren hat oder seine Zielzeile
	 * erreicht hat. Spieler 1 beginnt.
	 * Am Ende wird der Gewinner ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Fuehrt den Zug des Spielers aktiv gegen den Spieler gegner aus.
	 * Das Spielfeld wird ausgegeben, danach werden so lange die Nummer des
	 * Tieres und die Richtung eingelesen, bis ein erlaubter Zug genannt wird.
	 * Anschliessend wird der Zug ausgefuehrt.
	 *
	 * Hinweis: Aus der Position des Tieres und der Richtung berechnen Sie das
	 * Zielfeld mit Spielfeld.getDz(...) und Spielfeld.getDs(...).
	 */
	public static void spielzug(Spieler aktiv, Spieler gegner) {
		// TODO
		// Anweisung(en) hinzufuegen
	}
}
