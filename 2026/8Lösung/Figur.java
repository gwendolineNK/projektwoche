/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Figur {

	/* NICHT AENDERN! Die beiden Figurtypen. */
	public static final int QUADRAT = 1;
	public static final int KREIS = 2;

	/*
	 * Eine Figur kennt ihren Typ, ihren Besitzer und ihre Position auf dem
	 * Spielbrett. Solange eine Figur noch nicht auf dem Brett steht oder
	 * geschlagen wurde, sind zeile und spalte -1.
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
		this.typ = typ;
		this.besitzer = besitzer;
		this.imSpiel = true;
		this.spalte = -1;
		this.zeile = -1;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getTyp() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return typ;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public Spieler getBesitzer() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return besitzer;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getZeile() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return zeile;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getSpalte() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return spalte;
	}

	/*
	 * Merkt sich die neue Position der Figur.
	 */
	public void setzePosition(int zeile, int spalte) {
		// TODO
		// Anweisung(en) hinzufuegen
		this.zeile = zeile;
		this.spalte = spalte;
	}

	/*
	 * Gibt true zurueck, wenn die Figur noch im Spiel ist; false sonst.
	 */
	public boolean istImSpiel() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return imSpiel;
	}

	/*
	 * Die Figur wird geschlagen: Sie ist nicht mehr im Spiel und steht auf
	 * keinem Feld mehr (zeile und spalte werden -1).
	 */
	public void schlagen() {
		// TODO
		// Anweisung(en) hinzufuegen
		imSpiel = false;
		zeile = -1;
		spalte = -1;
	}

	/*
	 * Die Figur verwandelt sich: Aus einem Quadrat wird ein Kreis,
	 * aus einem Kreis wird ein Quadrat.
	 */
	public void verwandle() {
		// TODO
		// Anweisung(en) hinzufuegen
		if(typ == QUADRAT) {
			typ = KREIS;
		}
		else {
			typ = QUADRAT;
		}
	}

	/*
	 * Gibt true zurueck, wenn die Figur waagerecht oder senkrecht ziehen darf,
	 * also wenn sie ein Quadrat ist; false sonst.
	 */
	public boolean ziehtGerade() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return typ == QUADRAT;
	}

	/*
	 * Gibt true zurueck, wenn die Figur diagonal ziehen darf,
	 * also wenn sie ein Kreis ist; false sonst.
	 */
	public boolean ziehtDiagonal() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return typ == KREIS;
	}

	/*
	 * NICHT AENDERN!
	 * Liefert die Spielernummer des Besitzers, gefolgt von Q oder K.
	 */
	public String toString() {
		if (typ == QUADRAT) {
			return "" + besitzer.getNummer() + "Q";
		}
		return "" + besitzer.getNummer() + "K";
	}
}