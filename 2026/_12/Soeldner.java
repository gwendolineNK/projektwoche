/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Soeldner {

	/*
	 * ACHTUNG: Der Soeldner kennt seinen Dienstherrn. Der Spieler kennt seine
	 * Soeldner NICHT.
	 * Beim Anwerben wird kein neuer Soeldner erzeugt, sondern nur der Dienstherr
	 * geaendert.
	 */
	private int staerke;
	private int zeile;
	private int spalte;
	private Spieler dienstherr;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Alle vier Attribute werden aus den Parametern uebernommen.
	 */
	public Soeldner(int staerke, int zeile, int spalte, Spieler dienstherr) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getStaerke() {
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
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public Spieler getDienstherr() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Der Soeldner zieht auf das Feld (zeile, spalte).
	 */
	public void setzePosition(int zeile, int spalte) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Der Soeldner wechselt zum Spieler neuerHerr.
	 * Staerke und Position bleiben dabei unveraendert!
	 */
	public void wechsleZu(Spieler neuerHerr) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn dieser Soeldner dem uebergebenen Spieler dient;
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
	 * Gibt true zurueck, wenn dieser Soeldner ECHT staerker ist als der Soeldner
	 * anderer; false sonst.
	 */
	public boolean istStaerkerAls(Soeldner anderer) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 */
	public String toString() {
		return dienstherr.getZeichen() + staerke;
	}
}
