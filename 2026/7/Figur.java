/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Figur {

	/*
	 * Eine Figur kennt ihren Typ (1 bis 6), ihren Besitzer und ihre Position
	 * auf dem Spielbrett. Solange eine Figur noch nicht auf dem Brett steht
	 * oder geschlagen wurde, sind zeile und spalte -1.
	 */
	private int typ;
	private Spieler besitzer;
	private int zeile;
	private int spalte;
	private boolean imSpiel;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Typ und Besitzer werden aus den Parametern uebernommen.
	 * Die Figur ist zu Beginn im Spiel und steht noch auf keinem Feld
	 * (zeile und spalte sind -1).
	 */
	public Figur(int typ, Spieler besitzer) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getTyp() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
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
	 * Merkt sich die neue Position der Figur.
	 */
	public void setzePosition(int zeile, int spalte) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn die Figur noch im Spiel ist; false sonst.
	 */
	public boolean istImSpiel() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Die Figur wird geschlagen: Sie ist nicht mehr im Spiel und steht auf
	 * keinem Feld mehr (zeile und spalte werden -1).
	 */
	public void schlagen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn es sich um eine GROSSE Figur handelt, also um
	 * ein grosses Quadrat (Typ 2), ein grosses Dreieck (Typ 4) oder einen
	 * grossen Kreis (Typ 6); false sonst.
	 */
	public boolean istGross() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn die Figur waagerecht oder senkrecht ziehen darf,
	 * also bei den Quadraten (Typ 1, 2) und den Kreisen (Typ 5, 6); false sonst.
	 */
	public boolean ziehtGerade() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn die Figur diagonal ziehen darf, also bei den
	 * Dreiecken (Typ 3, 4) und den Kreisen (Typ 5, 6); false sonst.
	 */
	public boolean ziehtDiagonal() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Liefert die Spielernummer des Besitzers, gefolgt vom Typ der Figur.
	 */
	public String toString() {
		return "" + besitzer.getNummer() + typ;
	}
}
