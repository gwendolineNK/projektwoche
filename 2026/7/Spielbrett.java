/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielbrett {

	/* NICHT AENDERN! */
	public static final int GROESSE = 6;

	/*
	 * An jeder Stelle des Feldes steht entweder eine Referenz auf ein
	 * Figur-Objekt oder null, wenn das Feld leer ist.
	 */
	private Figur[][] feld;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * n erhaelt den Wert GROESSE, das Feld wird als n x n - Feld erzeugt.
	 * Zu Beginn ist jedes Feld leer.
	 */
	public Spielbrett() {
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
	 * Liefert die Figur auf dem Feld (z, s) oder null, wenn das Feld leer ist.
	 */
	public Figur getFigur(int z, int s) {
		return feld[z][s];
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	private int betrag(int zahl) {
		if (zahl < 0) {
			return -zahl;
		}
		return zahl;
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	private int vorzeichen(int zahl) {
		if (zahl > 0) {
			return 1;
		}
		if (zahl < 0) {
			return -1;
		}
		return 0;
	}

	/*
	 * Stellt die Figur auf das Feld (z, s) und teilt der Figur ihre neue
	 * Position mit. Wird null uebergeben, so wird das Feld einfach geleert.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Koordinaten
	 * aufgerufen wird.
	 */
	public void setzeFigur(int z, int s, Figur figur) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn auf dem Feld (z, s) keine Figur steht; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Koordinaten
	 * aufgerufen wird.
	 */
	public boolean istFrei(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Brettes sind;
	 * false sonst.
	 */
	public boolean imBrett(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Stellt die Figuren beider Spieler in der Grundaufstellung auf:
	 * Die Figuren von spieler1 kommen in die Zeile n-1, die Figuren von
	 * spieler2 in die Zeile 0. In Spalte 0 steht jeweils die Figur mit dem
	 * Index 0, in Spalte 1 die Figur mit dem Index 1 und so weiter.
	 * Hinweis: Spieler.getFigur(...) und setzeFigur(...)
	 */
	public void grundaufstellung(Spieler spieler1, Spieler spieler2) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn der Zug von (vonZeile, vonSpalte) nach
	 * (nachZeile, nachSpalte) fuer den uebergebenen Spieler gueltig ist;
	 * false sonst.
	 */
	public boolean istZugGueltig(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte,
			Spieler spieler) {

		// Liegen Startfeld UND Zielfeld im Brett?
		// TODO
		// Anweisung(en) hinzufuegen


		// Sind Startfeld und Zielfeld verschieden?
		// TODO
		// Anweisung(en) hinzufuegen


		// Steht auf dem Startfeld ueberhaupt eine Figur, und gehoert sie dem Spieler?
		// Hinweis: figur.getBesitzer() == spieler
		// TODO
		// Anweisung(en) hinzufuegen


		// Steht auf dem Zielfeld eine Figur des Spielers selbst?
		// TODO
		// Anweisung(en) hinzufuegen


		// Zugrichtung bestimmen: Wie veraendern sich Zeile und Spalte?
		// Ist der Zug gerade (Zeile oder Spalte bleibt gleich)
		// oder diagonal (Zeile und Spalte aendern sich um denselben Betrag)?
		// Jede andere Richtung ist nie erlaubt.
		// TODO
		// Anweisung(en) hinzufuegen


		// Passt die Richtung zum Figurtyp?
		// Hinweis: figur.ziehtGerade(), figur.ziehtDiagonal()
		// TODO
		// Anweisung(en) hinzufuegen


		// Passt die Reichweite zum Figurtyp?
		// Kleine Figuren duerfen genau ein Feld weit ziehen.
		// Hinweis: figur.istGross(), betrag(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Ist der Weg frei? Alle Felder ZWISCHEN Start und Ziel muessen leer sein.
		// Das Zielfeld selbst gehoert nicht zum Weg.
		// Hinweis: vorzeichen(...) liefert die Schrittrichtung fuer Zeile und Spalte.
		// TODO
		// Anweisung(en) hinzufuegen


		// TODO
		// return-Anweisung ggf. aendern
		return false;
	}

	/*
	 * Fuehrt den Zug von (vonZeile, vonSpalte) nach (nachZeile, nachSpalte) aus.
	 * Es ist sichergestellt, dass die Methode nur mit einem gueltigen Zug
	 * aufgerufen wird.
	 *
	 * Steht auf dem Zielfeld eine gegnerische Figur, so wird sie geschlagen.
	 * Danach steht die gezogene Figur auf dem Zielfeld, das Startfeld ist leer.
	 *
	 * Rueckgabewert: die geschlagene Figur, oder null, wenn nichts geschlagen wurde.
	 */
	public Figur fuehreZugAus(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt true zurueck, wenn der uebergebene Spieler mit einer eigenen Figur
	 * die gegenueberliegende Grundlinie erreicht hat:
	 * Spieler 1 die Zeile 0, Spieler 2 die Zeile n-1.
	 */
	public boolean hatGrundlinieErreicht(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Spielbretts.
	 */
	public String toString() {
		String ausgabe = "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%4d", spalte);
		}
		ausgabe += "\n";

		ausgabe += "----+";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += "----";
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {
			ausgabe += String.format("%3d |", zeile);
			for (int spalte = 0; spalte < n; spalte++) {
				if (feld[zeile][spalte] == null) {
					ausgabe += String.format("%4s", "-");
				} else {
					ausgabe += String.format("%4s", feld[zeile][spalte]);
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}
