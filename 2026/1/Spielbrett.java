/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielbrett {

	public static final int GROESSE = 6;

	private int[][] feld;
	private int n;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Attribut n erhaelt den Wert GROESSE, das Feld feld wird erzeugt.
	 * Rufen Sie anschliessend grundaufstellung() auf.
	 */
	public Spielbrett() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielbrett(int[][] feld) {
		this.feld = feld;
		this.n = feld.length;
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
	public int getStelle(int zeile, int spalte) {
		return feld[zeile][spalte];
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	public static int betrag(int zahl) {
		if (zahl < 0) {
			return -zahl;
		}
		return zahl;
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	public static int vorzeichen(int zahl) {
		if (zahl > 0) {
			return 1;
		}
		if (zahl < 0) {
			return -1;
		}
		return 0;
	}

	/*
	 * Setzt die Grundaufstellung:
	 * In Zeile n-1 stehen die Figuren 1 bis 6 von Spieler 1 (Spalte 0 erhaelt die 1,
	 * Spalte 1 die 2 usw.), in Zeile 0 die Figuren -1 bis -6 von Spieler 2.
	 * Alle anderen Felder bleiben leer (0).
	 */
	public void grundaufstellung() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Setzt figur an die Stelle (zeile, spalte). Es ist sichergestellt, dass die
	 * Methode nur mit gueltigen Werten aufgerufen wird.
	 */
	public void setStelle(int zeile, int spalte, int figur) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn (zeile, spalte) gueltige Koordinaten des Brettes sind;
	 * false sonst.
	 */
	public boolean imBrett(int zeile, int spalte) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn der Zug von (vonZeile, vonSpalte) nach
	 * (nachZeile, nachSpalte) fuer den Spieler mit der Nummer spieler gueltig ist;
	 * false sonst.
	 */
	public boolean istZugGueltig(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte, int spieler) {

		// Liegen Startfeld UND Zielfeld im Brett?
		// TODO
		// Anweisung(en) hinzufuegen


		// Sind Startfeld und Zielfeld verschieden?
		// TODO
		// Anweisung(en) hinzufuegen


		// Steht auf dem Startfeld eine eigene Figur des Spielers?
		// Hinweis: Figur.besitzer(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Steht auf dem Zielfeld KEINE eigene Figur?
		// TODO
		// Anweisung(en) hinzufuegen


		// Zugrichtung bestimmen: Wie veraendern sich Zeile und Spalte?
		// Ist der Zug gerade (Zeile oder Spalte bleibt gleich)
		// oder diagonal (Zeile und Spalte aendern sich um denselben Betrag)?
		// Jede andere Richtung ist nie erlaubt.
		// TODO
		// Anweisung(en) hinzufuegen


		// Passt die Richtung zum Figurtyp?
		// Hinweis: Figur.ziehtGerade(...), Figur.ziehtDiagonal(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Passt die Reichweite zum Figurtyp?
		// Kleine Figuren duerfen genau ein Feld weit ziehen.
		// Hinweis: Figur.istGross(...), betrag(...)
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
	 * Es ist sichergestellt, dass die Methode nur mit einem gueltigen Zug aufgerufen wird.
	 * Die Figur wird auf das Zielfeld gesetzt, das Startfeld wird leer.
	 * Rueckgabewert: die geschlagene gegnerische Figur, oder 0, wenn nichts geschlagen wurde.
	 */
	public int fuehreZugAus(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt zurueck, wie viele Figuren der Spieler mit der Nummer spieler
	 * noch auf dem Brett hat.
	 */
	public int zaehleFiguren(int spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler mit der Nummer spieler mit einer eigenen
	 * Figur die gegenueberliegende Grundlinie erreicht hat:
	 * Spieler 1 die Zeile 0, Spieler 2 die Zeile n-1.
	 */
	public boolean hatGrundlinieErreicht(int spieler) {
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
		String ausgabe = "";

		ausgabe += "     ";
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
				if (feld[zeile][spalte] == 0) {
					ausgabe += String.format("%4s", "-");
				} else {
					ausgabe += String.format("%4d", feld[zeile][spalte]);
				}
			}

			ausgabe += "\n";
		}

		return ausgabe;
	}
}
