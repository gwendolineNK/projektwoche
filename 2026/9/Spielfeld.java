/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	/* NICHT AENDERN! Die moeglichen Werte eines Feldes. */
	public static final int LEER = 0;
	public static final int WASSER = 1;
	public static final int TREFFER = 2;

	/*
	 * ACHTUNG: Das Spielfeld speichert nur die SCHUESSE, nicht die Schiffe!
	 * Die Schiffe gehoeren den Spielern.
	 */
	private int[][] feld;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Feld wird als n x n - Feld erzeugt, n wird gesetzt.
	 * Zu Beginn wurde auf kein Feld geschossen.
	 */
	public Spielfeld(int n) {
		// TODO
		// Anweisung(en) hinzufuegen
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
	public int getStelle(int z, int s) {
		return feld[z][s];
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
	 * Traegt wert an der Stelle (z, s) ein.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public void markiere(int z, int s, int wert) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn auf das Feld (z, s) bereits geschossen wurde,
	 * wenn dort also nicht mehr LEER steht; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public boolean istBeschossen(int z, int s) {
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
		String ausgabe = "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%4d", spalte);
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {
			ausgabe += String.format("%3d |", zeile);
			for (int spalte = 0; spalte < n; spalte++) {
				if (feld[zeile][spalte] == TREFFER) {
					ausgabe += String.format("%4s", "X");
				} else if (feld[zeile][spalte] == WASSER) {
					ausgabe += String.format("%4s", "o");
				} else {
					ausgabe += String.format("%4s", ".");
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}
