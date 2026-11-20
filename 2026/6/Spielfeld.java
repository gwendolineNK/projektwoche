/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	private boolean[][] feld;
	private int n;
	private boolean[][][] verlauf;
	private int anzahlVerlauf;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Brett wird als n x n - Feld erzeugt, n wird gesetzt, zu Beginn liegt
	 * kein Stein auf dem Brett.
	 * Das Feld verlauf wird so erzeugt, dass es maxStellungen Stellungen
	 * aufnehmen kann. Noch wurde keine Stellung gemerkt.
	 */
	public Spielfeld(int n, int maxStellungen) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielfeld(boolean[][] feld) {
		this.feld = feld;
		this.n = feld.length;
		this.verlauf = new boolean[100][][];
		this.anzahlVerlauf = 0;
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
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public int getAnzahlVerlauf() {
		return anzahlVerlauf;
	}

	/*
	 * Gibt true zurueck, wenn auf dem Feld (z, s) ein Stein liegt; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public boolean istBesetzt(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Brettes sind;
	 * false sonst.
	 */
	public boolean imFeld(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Fuehrt einen Zug auf dem Feld (z, s) aus:
	 * Liegt dort kein Stein, wird einer gesetzt. Liegt dort ein Stein, wird er
	 * weggenommen.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 *
	 * Rueckgabewert: true, wenn ein Stein GESETZT wurde,
	 *                false, wenn ein Stein WEGGENOMMEN wurde.
	 */
	public boolean zugAusfuehren(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt zurueck, wie viele Steine insgesamt auf dem Brett liegen.
	 */
	public int zaehleSteine() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Merkt sich die aktuelle Stellung im Verlauf.
	 *
	 * Achtung: Legen Sie eine KOPIE der Stellung ab! Wuerden Sie das Feld feld
	 * selbst ablegen, so wuerde sich der Eintrag im Verlauf bei jedem weiteren
	 * Zug mit veraendern.
	 * Hinweis: Stellung.kopie(...)
	 */
	public void merkeStellung() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn die aktuelle Stellung zu einer der bereits
	 * gemerkten Stellungen puan-gleich ist; false sonst.
	 * Hinweis: Stellung.puanGleich(...)
	 */
	public boolean stellungKamSchonVor() {
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
