/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Schiff {

	/*
	 * ACHTUNG: Ein Schiff kennt seinen Besitzer NICHT.
	 * Es weiss nur, wie lang es ist, wo es liegt, wie es ausgerichtet ist
	 * und wie viele Treffer es schon bekommen hat.
	 *
	 * Solange das Schiff noch nicht platziert wurde, sind zeile und spalte -1.
	 */
	private int laenge;
	private int zeile;
	private int spalte;
	private boolean waagerecht;
	private int treffer;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Die Laenge wird aus dem Parameter uebernommen.
	 * Das Schiff ist noch nicht platziert (zeile und spalte sind -1),
	 * es liegt waagerecht und hat noch keine Treffer.
	 */
	public Schiff(int laenge) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getLaenge() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getZeile() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -99;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getSpalte() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -99;
	}

	/*
	 * Gibt true zurueck, wenn das Schiff waagerecht liegt; false sonst.
	 */
	public boolean istWaagerecht() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getTreffer() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Merkt sich Position und Ausrichtung des Schiffes.
	 */
	public void platziere(int zeile, int spalte, boolean waagerecht) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn das Schiff das Feld (z, s) belegt; false sonst.
	 *
	 * Ein waagerechtes Schiff der Laenge 3 ab (2,1) belegt (2,1), (2,2), (2,3).
	 * Ein senkrechtes Schiff der Laenge 3 ab (2,1) belegt (2,1), (3,1), (4,1).
	 *
	 * Ein noch nicht platziertes Schiff (zeile ist -1) belegt kein Feld.
	 */
	public boolean belegt(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Das Schiff erhaelt einen Treffer.
	 */
	public void treffer() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn das Schiff versenkt ist, wenn es also mindestens
	 * so viele Treffer hat wie es lang ist; false sonst.
	 */
	public boolean istVersenkt() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 */
	public String toString() {
		return "Schiff der Laenge " + laenge + " (" + treffer + " Treffer)";
	}
}
