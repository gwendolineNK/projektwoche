/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	private char[] feld;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Feld feld wird mit n Stellen erzeugt, n wird gesetzt.
	 * Alle Stellen sind zu Beginn leer (Muster.LEER).
	 */
	public Spielfeld(int n) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielfeld(char[] feld) {
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
	public char getStelle(int index) {
		return feld[index];
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public char[] getFeld() {
		return feld;
	}

	/*
	 * Schreibt buchstabe an die Stelle index. Es ist sichergestellt, dass die
	 * Methode nur mit gueltigen Werten aufgerufen wird.
	 */
	public void setStelle(int index, char buchstabe) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn index eine gueltige Feldnummer bezeichnet,
	 * also wenn die Stelle existiert UND noch leer ist; false sonst.
	 */
	public boolean gueltigeEingabe(int index) {

		// Ist index eine existierende Feldnummer?
		// TODO
		// Anweisung(en) hinzufuegen


		// Ist die Stelle noch leer?
		// TODO
		// Anweisung(en) hinzufuegen


		// TODO
		// return-Anweisung ggf. aendern
		return false;
	}

	/*
	 * Gibt zurueck, wie viele Stellen des Feldes noch leer sind.
	 */
	public int zaehleFreie() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn keine Stelle mehr leer ist; false sonst.
	 */
	public boolean istVoll() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Spielfeldes.
	 */
	public String toString() {
		String ausgabe = "Index:";
		for (int i = 0; i < n; i++) {
			ausgabe += String.format("%4d", i);
		}
		ausgabe += "\n";

		ausgabe += "Feld :";
		for (int i = 0; i < n; i++) {
			ausgabe += String.format("%4s", feld[i]);
		}
		ausgabe += "\n";

		return ausgabe;
	}
}
