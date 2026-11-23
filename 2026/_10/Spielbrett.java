/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielbrett {

	/*
	 * Das Brett verwaltet die Steine. Ein freies Feld ist null.
	 */
	private Stein[][] feld;
	private int n;

	/*
	 * NICHT AENDERN! Die acht Richtungen.
	 * Die Richtung mit dem Index k geht um dz[k] Zeilen und ds[k] Spalten weiter.
	 *
	 * k:       0   1   2   3   4   5   6   7
	 * dz[k]:  -1  -1  -1   0   0   1   1   1
	 * ds[k]:  -1   0   1  -1   1  -1   0   1
	 */
	private int[] dz = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private int[] ds = { -1, 0, 1, -1, 1, -1, 0, 1 };

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Brett wird als n x n - Feld erzeugt, n wird gesetzt.
	 * Zu Beginn liegt kein Stein auf dem Brett.
	 */
	public Spielbrett(int n) {
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
	public Stein getStein(int z, int s) {
		return feld[z][s];
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
	 * Gibt true zurueck, wenn das Feld (z, s) auf dem Brett liegt und dort kein
	 * Stein liegt; false sonst.
	 */
	public boolean istFrei(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Legt einen neuen Stein des uebergebenen Spielers auf das Feld (z, s).
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public void setzeStein(int z, int s, Spieler spieler) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Baut die Grundaufstellung auf.
	 * Mit m = n/2 - 1 gehoeren (m, m) und (m+1, m+1) dem Spieler spieler2,
	 * (m, m+1) und (m+1, m) dem Spieler spieler1.
	 */
	public void grundaufstellung(Spieler spieler1, Spieler spieler2) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt zurueck, wie viele gegnerische Steine der Spieler spieler einschliessen
	 * wuerde, wenn er einen Stein auf (z, s) legt und nur die Richtung mit dem
	 * Index k betrachtet wird. Schliesst diese Richtung nichts ein, ist das
	 * Ergebnis 0.
	 *
	 * Gehen Sie von (z, s) aus Schritt fuer Schritt in die Richtung k weiter:
	 * - Solange dort Steine des Gegners liegen, zaehlen Sie mit.
	 * - Kommen Sie an den Rand oder auf ein freies Feld, wird nichts eingeschlossen.
	 * - Kommen Sie auf einen eigenen Stein, so sind alle bis dahin gezaehlten
	 *   gegnerischen Steine eingeschlossen. Achtung: Liegt der eigene Stein direkt
	 *   daneben, wurde kein Gegner uebersprungen - dann wird nichts eingeschlossen.
	 *
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public int zaehleRichtung(int z, int s, Spieler spieler, int k) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * PFLICHTMETHODE!
	 *
	 * Gibt true zurueck, wenn der Spieler spieler einen Stein auf das Feld (z, s)
	 * legen darf; false sonst.
	 *
	 * Erlaubt ist der Zug genau dann, wenn das Feld auf dem Brett liegt, frei ist
	 * und in mindestens einer der acht Richtungen mindestens ein gegnerischer
	 * Stein eingeschlossen wird.
	 */
	public boolean istZugGueltig(int z, int s, Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Fuehrt den Zug des Spielers spieler auf das Feld (z, s) aus: Der neue Stein
	 * wird gelegt und alle eingeschlossenen Steine wechseln den Besitzer.
	 * Gibt die Anzahl der umgedrehten Steine zurueck.
	 *
	 * Die umgedrehten Steine duerfen NICHT durch neue Steine ersetzt werden!
	 * Hinweis: Stein.setzeBesitzer(...)
	 *
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Zuegen
	 * aufgerufen wird.
	 */
	public int setzeUndDrehe(int z, int s, Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt zurueck, wie viele Steine auf dem Brett dem Spieler spieler gehoeren.
	 *
	 * Da ein Spieler seine Steine nicht kennt, muss das Brett alle Felder
	 * durchlaufen. Hinweis: Stein.gehoert(...)
	 */
	public int zaehleSteine(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler spieler mindestens einen gueltigen Zug
	 * machen kann; false sonst.
	 */
	public boolean hatGueltigenZug(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn auf jedem Feld des Brettes ein Stein liegt;
	 * false sonst.
	 */
	public boolean istVoll() {
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
				if (feld[zeile][spalte] == null) {
					ausgabe += String.format("%4s", ".");
				} else {
					ausgabe += String.format("%4s", feld[zeile][spalte].toString());
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}
