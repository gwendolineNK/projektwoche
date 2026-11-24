/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Tier {

	/*
	 * ACHTUNG: Ein Tier kennt seinen Besitzer NICHT.
	 * Es weiss nur, wie stark es ist, wo es steht und ob es noch im Spiel ist.
	 */
	private int staerke;
	private int zeile;
	private int spalte;
	private boolean imSpiel;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Staerke und Position werden aus den Parametern uebernommen.
	 * Ein neues Tier ist im Spiel.
	 */
	public Tier(int staerke, int zeile, int spalte) {
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
	 * Gibt true zurueck, wenn das Tier noch im Spiel ist; false sonst.
	 */
	public boolean istImSpiel() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Das Tier zieht auf das Feld (zeile, spalte).
	 */
	public void setzePosition(int zeile, int spalte) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Das Tier wurde geschlagen und ist damit nicht mehr im Spiel.
	 */
	public void schlagen() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn das Tier noch im Spiel ist UND auf dem Feld (z, s)
	 * steht; false sonst.
	 * Ein geschlagenes Tier steht auf keinem Feld mehr!
	 */
	public boolean stehtAuf(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn dieses Tier das Tier anderes schlagen darf;
	 * false sonst.
	 *
	 * Geschlagen werden darf, wer hoechstens so stark ist wie das schlagende Tier.
	 * Ausnahme: Ein Tier der Staerke 1 darf ein Tier der Staerke 3 schlagen.
	 */
	public boolean schlaegt(Tier anderes) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 */
	public String toString() {
		return "Tier der Staerke " + staerke;
	}
}
