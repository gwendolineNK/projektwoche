/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielfeld {

	/*
	 * ACHTUNG: Das Spielfeld kennt nur die Landschaft, nicht die Tiere!
	 * wasser[z][s] ist genau dann true, wenn (z, s) ein Flussfeld ist.
	 */
	private int n;
	private boolean[][] wasser;

	/* NICHT AENDERN! Die vier Richtungen n, o, s, w. */
	private int[] dz = { -1, 0, 1, 0 };
	private int[] ds = { 0, 1, 0, -1 };

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * n wird gesetzt, das Feld wasser wird als n x n - Feld erzeugt.
	 * Danach wird der Fluss angelegt.
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
	public boolean istWasser(int z, int s) {
		return wasser[z][s];
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 * Die Richtung mit dem Index k geht um dz[k] Zeilen und ds[k] Spalten weiter.
	 * Richtung 0 = n (nach oben), 1 = o, 2 = s, 3 = w.
	 */
	public int getDz(int k) {
		return dz[k];
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public int getDs(int k) {
		return ds[k];
	}

	/*
	 * Legt den Fluss an: In der Zeile n/2 sind die Spalten 1 bis n-2
	 * Flussfelder.
	 */
	public void erzeugeFluss() {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Spielfeldes
	 * sind; false sonst.
	 */
	public boolean imFeld(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn die beiden Felder (z1, s1) und (z2, s2) waagerecht
	 * oder senkrecht direkt nebeneinander liegen; false sonst.
	 * Diagonale Nachbarn sind KEINE Nachbarn, und ein Feld ist nicht sein
	 * eigener Nachbar.
	 */
	public boolean istNachbar(int z1, int s1, int z2, int s2) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn das Tier tier das Feld (z, s) betreten darf;
	 * false sonst.
	 *
	 * Betreten werden darf ein Feld, das im Spielfeld liegt. Ist es ein
	 * Flussfeld, so darf es nur ein Tier der Staerke 1 betreten.
	 */
	public boolean betretbar(int z, int s, Tier tier) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Spielfeldes.
	 *
	 * Beachten Sie: Das Spielfeld kennt die Tiere nicht. Es muss die beiden
	 * Spieler fragen, wer wo steht.
	 */
	public String zeichne(Spieler spieler1, Spieler spieler2) {
		String ausgabe = "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%4d", spalte);
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {
			ausgabe += String.format("%3d |", zeile);
			for (int spalte = 0; spalte < n; spalte++) {
				Tier tier = spieler1.tierAn(zeile, spalte);
				String zeichen = spieler1.getZeichen();
				if (tier == null) {
					tier = spieler2.tierAn(zeile, spalte);
					zeichen = spieler2.getZeichen();
				}
				if (tier != null) {
					ausgabe += String.format("%4s", zeichen + tier.getStaerke());
				} else if (wasser[zeile][spalte]) {
					ausgabe += String.format("%4s", "~");
				} else {
					ausgabe += String.format("%4s", ".");
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}
