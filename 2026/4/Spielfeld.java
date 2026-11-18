/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	private int[] mulden;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Brett erhaelt n Mulden, in jeder Mulde liegen samenProMulde Samen.
	 */
	public Spielfeld(int n, int samenProMulde) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielfeld(int[] mulden) {
		this.mulden = mulden;
		this.n = mulden.length;
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
	public int getMulde(int nummer) {
		return mulden[nummer];
	}

	/*
	 * Legt anzahl Samen in die Mulde nummer. Es ist sichergestellt, dass die
	 * Methode nur mit gueltigen Werten aufgerufen wird.
	 */
	public void setMulde(int nummer, int anzahl) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt zurueck, wie viele Samen insgesamt auf dem Brett liegen.
	 */
	public int zaehleSamen() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler mit der Nummer spielernummer die Mulde
	 * nummer verteilen darf; false sonst.
	 */
	public boolean istZugGueltig(int nummer, int spielernummer) {

		// Existiert die Mulde nummer ueberhaupt?
		// TODO
		// Anweisung(en) hinzufuegen


		// Liegt mindestens ein Samen in dieser Mulde?
		// TODO
		// Anweisung(en) hinzufuegen


		// Zugrichtung des Spielers bestimmen.
		// Hinweis: Zug.richtungVon(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Passen alle Samen noch auf das Brett?
		// Hinweis: Zug.passtInsFeld(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Faellt der letzte Samen in eine Mulde, die VOR dem Zug nicht leer ist?
		// Hinweis: Zug.letzteMulde(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// TODO
		// return-Anweisung ggf. aendern
		return false;
	}

	/*
	 * Fuehrt den Zug aus: Der gesamte Inhalt der Mulde nummer wird entnommen und
	 * einzeln auf die in Zugrichtung folgenden Mulden verteilt.
	 * Es ist sichergestellt, dass die Methode nur mit einem gueltigen Zug
	 * aufgerufen wird.
	 *
	 * Rueckgabewert: die Anzahl der verteilten Samen.
	 */
	public int fuehreZugAus(int nummer, int spielernummer) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler mit der Nummer spielernummer
	 * mindestens einen gueltigen Zug hat; false sonst.
	 */
	public boolean hatZugMoeglichkeit(int spielernummer) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Brettes.
	 */
	public String toString() {
		String ausgabe = "Mulde:";
		for (int i = 0; i < n; i++) {
			ausgabe += String.format("%4d", i);
		}
		ausgabe += "\n";

		ausgabe += "Samen:";
		for (int i = 0; i < n; i++) {
			ausgabe += String.format("%4d", mulden[i]);
		}
		ausgabe += "\n";

		return ausgabe;
	}
}
