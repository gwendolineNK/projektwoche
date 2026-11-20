/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	private boolean[][] feld;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Feld wird als n x n - Feld erzeugt, n wird gesetzt.
	 * Zu Beginn sind alle Zellen tot.
	 */
	public Spielfeld(int n) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielfeld(boolean[][] feld) {
		this.feld = feld;
		this.n = feld.length;
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public int getN() {
		return n;
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public boolean[][] getFeld() {
		return feld;
	}

	/*
	 * Gibt true zurueck, wenn die Zelle (z, s) lebendig ist; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public boolean istLebendig(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Setzt die Zelle (z, s) auf den Wert lebt.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public void setzeZelle(int z, int s, boolean lebt) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Feldes sind;
	 * false sonst.
	 */
	public boolean imFeld(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt zurueck, wie viele Zellen des Feldes lebendig sind.
	 */
	public int zaehleLebende() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Berechnet die naechste Generation und uebernimmt sie als neues Spielfeld.
	 *
	 * Gibt true zurueck, wenn sich dabei mindestens eine Zelle veraendert hat;
	 * false sonst.
	 */
	public boolean naechsteGeneration() {

		// Legen Sie ein ZWEITES Feld fuer die neue Generation an.
		// Das alte Feld darf waehrend der Berechnung nicht veraendert werden!
		// TODO
		// Anweisung(en) hinzufuegen


		// Berechnen Sie fuer jede Zelle ihren Zustand in der neuen Generation.
		// Hinweis: Generation.zaehleLebendeNachbarn(...) und
		//          Generation.naechsterZustand(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Pruefen Sie, ob sich etwas veraendert hat.
		// Hinweis: Generation.gleich(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Uebernehmen Sie die neue Generation als Spielfeld.
		// TODO
		// Anweisung(en) hinzufuegen


		// TODO
		// return-Anweisung ggf. aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Spielfeldes.
	 */
	public String toString() {
		String ausgabe = "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%4d", spalte);
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {
			ausgabe += String.format("%3d |", zeile);
			for (int spalte = 0; spalte < n; spalte++) {
				if (feld[zeile][spalte]) {
					ausgabe += String.format("%4s", "X");
				} else {
					ausgabe += String.format("%4s", ".");
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}
