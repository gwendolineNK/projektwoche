import java.util.Random;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	/* NICHT AENDERN! Die vier Richtungen und das Spielziel. */
	public static final int LINKS = 0;
	public static final int RECHTS = 1;
	public static final int OBEN = 2;
	public static final int UNTEN = 3;
	public static final int ZIEL = 2048;

	private int[][] feld;
	private int n;
	private int punkte;
	private Random rd;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Feld feld wird als n x n - Feld erzeugt, n wird gesetzt,
	 * punkte beginnt bei 0 und rd wird erzeugt.
	 * Legen Sie anschliessend ZWEI zufaellige Kacheln auf das Spielfeld.
	 */
	public Spielfeld(int n) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public Spielfeld(int[][] feld) {
		this.feld = feld;
		this.n = feld.length;
		this.punkte = 0;
		this.rd = new Random();
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
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public int getPunkte() {
		return punkte;
	}

	/*
	 * Setzt kachel an die Stelle (zeile, spalte). Es ist sichergestellt, dass die
	 * Methode nur mit gueltigen Werten aufgerufen wird.
	 */
	public void setStelle(int zeile, int spalte, int kachel) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt zurueck, wie viele Felder des Spielfelds leer sind (den Wert 0 haben).
	 */
	public int zaehleLeere() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Legt eine neue Kachel mit dem Wert 2 auf ein zufaellig gewaehltes LEERES Feld.
	 * Gibt es kein leeres Feld mehr, wird nichts veraendert.
	 * Hinweis: Ziehen Sie so lange zufaellige Koordinaten, bis Sie ein leeres Feld
	 * gefunden haben. Eine Zufallszahl zwischen 0 und n-1 erhalten Sie mit rd.nextInt(n).
	 */
	public void zufaelligeKachel() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Fuehrt einen kompletten Zug in die angegebene Richtung aus
	 * (LINKS, RECHTS, OBEN oder UNTEN).
	 *
	 * Gibt true zurueck, wenn sich dabei mindestens eine Kachel veraendert hat;
	 * false sonst. Erzeugt KEINE neue Kachel.
	 *
	 * Die beim Verschmelzen gewonnenen Punkte werden zum Attribut punkte addiert.
	 */
	public boolean verschiebe(int richtung) {

		// Fuer jede Zeile (bei LINKS/RECHTS) bzw. jede Spalte (bei OBEN/UNTEN):
		// Lesen Sie die Reihe aus dem Spielfeld in ein eindimensionales Feld.
		// TODO
		// Anweisung(en) hinzufuegen


		// Bei RECHTS und UNTEN: die Reihe zuerst umdrehen,
		// damit Sie immer "nach links" rechnen koennen.
		// Hinweis: Reihe.umdrehen(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Die drei Schritte ausfuehren: zusammenschieben, verschmelzen, zusammenschieben.
		// Die gewonnenen Punkte aufaddieren.
		// Hinweis: Reihe.zusammenschieben(...), Reihe.verschmelzen(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// Bei RECHTS und UNTEN: die fertige Reihe wieder umdrehen.
		// TODO
		// Anweisung(en) hinzufuegen


		// Pruefen, ob sich die Reihe veraendert hat, und sie zurueck ins Spielfeld schreiben.
		// Hinweis: Reihe.gleich(...)
		// TODO
		// Anweisung(en) hinzufuegen


		// TODO
		// return-Anweisung ggf. aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn auf dem Spielfeld eine Kachel mit dem Wert ZIEL liegt;
	 * false sonst.
	 */
	public boolean hatGewonnen() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn noch ein Zug moeglich ist; false sonst.
	 * Ein Zug ist moeglich, wenn es mindestens ein leeres Feld gibt ODER wenn zwei
	 * waagerecht oder senkrecht benachbarte Felder denselben Wert haben.
	 */
	public boolean zugMoeglich() {
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
		String ausgabe = "";

		ausgabe += "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%6d", spalte);
		}
		ausgabe += "\n";

		ausgabe += "----+";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += "------";
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {

			ausgabe += String.format("%3d |", zeile);

			for (int spalte = 0; spalte < n; spalte++) {
				if (feld[zeile][spalte] == 0) {
					ausgabe += String.format("%6s", ".");
				} else {
					ausgabe += String.format("%6d", feld[zeile][spalte]);
				}
			}

			ausgabe += "\n";
		}

		return ausgabe;
	}
}
