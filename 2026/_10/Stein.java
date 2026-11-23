/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Stein {

	/*
	 * ACHTUNG: Der Stein kennt seinen Besitzer. Der Spieler kennt seine Steine NICHT.
	 * Beim Umdrehen wird kein neuer Stein erzeugt, sondern nur der Besitzer geaendert.
	 */
	private Spieler besitzer;
	private int zeile;
	private int spalte;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Alle drei Attribute werden aus den Parametern uebernommen.
	 */
	public Stein(Spieler besitzer, int zeile, int spalte) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public Spieler getBesitzer() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
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
	 * Der Stein wechselt den Besitzer.
	 * Position und Objektidentitaet bleiben dabei unveraendert!
	 */
	public void setzeBesitzer(Spieler neuerBesitzer) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn dieser Stein dem uebergebenen Spieler gehoert;
	 * false sonst.
	 * Achtung: Es geht um dasselbe Objekt, nicht um denselben Namen!
	 */
	public boolean gehoert(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 */
	public String toString() {
		return besitzer.getZeichen();
	}
}
