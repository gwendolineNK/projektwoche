/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Schlachtfeld {

	/*
	 * Das Schlachtfeld verwaltet die Soeldner. Ein freies Feld ist null.
	 */
	private Soeldner[][] feld;
	private int n;

	/* NICHT AENDERN! Die vier Richtungen. */
	private int[] dz = { -1, 0, 1, 0 };
	private int[] ds = { 0, 1, 0, -1 };

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Das Schlachtfeld wird als n x n - Feld erzeugt, n wird gesetzt.
	 * Zu Beginn steht kein Soeldner auf dem Feld.
	 */
	public Schlachtfeld(int n) {
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
	public Soeldner getSoeldner(int z, int s) {
		return feld[z][s];
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Schlachtfeldes
	 * sind; false sonst.
	 */
	public boolean imFeld(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn das Feld (z, s) auf dem Schlachtfeld liegt und dort
	 * kein Soeldner steht; false sonst.
	 */
	public boolean istFrei(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Stellt einen neuen Soeldner der uebergebenen Staerke im Dienst des Spielers
	 * herr auf das Feld (z, s).
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public void setzeSoeldner(int z, int s, int staerke, Spieler herr) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Baut die Grundaufstellung auf: In der Zeile 0 steht fuer jede Spalte c ein
	 * Soeldner des Spielers spieler1, in der Zeile n-1 einer des Spielers
	 * spieler2. Beide haben die Staerke c % 3 + 1.
	 */
	public void grundaufstellung(Spieler spieler1, Spieler spieler2) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt true zurueck, wenn die beiden Felder (z1, s1) und (z2, s2) waagerecht
	 * oder senkrecht direkt nebeneinander liegen; false sonst.
	 * Diagonale Nachbarn sind KEINE Nachbarn, und ein Feld ist nicht sein
	 * eigener Nachbar.
	 */
	public boolean sindNachbarn(int z1, int s1, int z2, int s2) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn der Spieler spieler seinen Soeldner von (z, s) auf
	 * das Feld (zz, zs) ziehen darf; false sonst.
	 *
	 * Erlaubt ist das genau dann, wenn beide Felder auf dem Schlachtfeld liegen,
	 * auf (z, s) ein Soeldner dieses Spielers steht, die beiden Felder Nachbarn
	 * sind und das Zielfeld frei ist.
	 */
	public boolean kannZiehen(int z, int s, int zz, int zs, Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Zieht den Soeldner von (z, s) auf das Feld (zz, zs).
	 * Es ist derselbe Soeldner, er steht danach nur woanders - es darf also kein
	 * neuer Soeldner erzeugt werden!
	 * Denken Sie daran, auch das alte Feld wieder frei zu machen.
	 *
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Zuegen
	 * aufgerufen wird.
	 */
	public void ziehe(int z, int s, int zz, int zs) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * PFLICHTMETHODE!
	 *
	 * Gibt true zurueck, wenn der Spieler spieler mit seinem Soeldner auf (z, s)
	 * den Soeldner auf dem Feld (zz, zs) anwerben darf; false sonst.
	 *
	 * Erlaubt ist das genau dann, wenn
	 * - beide Felder auf dem Schlachtfeld liegen, und
	 * - auf (z, s) ein Soeldner dieses Spielers steht, und
	 * - die beiden Felder Nachbarn sind, und
	 * - auf (zz, zs) ein Soeldner des Gegners steht, und
	 * - der eigene Soeldner echt staerker ist als dieser.
	 *
	 * Hinweis: Soeldner.gehoert(...) und Soeldner.istStaerkerAls(...)
	 */
	public boolean kannAnwerben(int z, int s, int zz, int zs, Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Wirbt den Soeldner auf dem Feld (zz, zs) fuer den Spieler spieler an: Der
	 * Soeldner wechselt die Seite und der Spieler merkt sich die Anwerbung.
	 * Der Soeldner bleibt dabei stehen und behaelt seine Staerke - er darf also
	 * nicht ersetzt werden!
	 *
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Zuegen
	 * aufgerufen wird.
	 */
	public void werbeAn(int zz, int zs, Spieler spieler) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Gibt zurueck, wie viele Soeldner auf dem Schlachtfeld dem Spieler spieler
	 * dienen.
	 *
	 * Da ein Spieler seine Soeldner nicht kennt, muss das Schlachtfeld alle
	 * Felder durchlaufen.
	 */
	public int zaehleSoeldner(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn jeder Soeldner auf dem Schlachtfeld dem Spieler
	 * spieler dient; false sonst.
	 * Auf einem Schlachtfeld ohne Soeldner gibt die Methode false zurueck.
	 */
	public boolean besitztAlle(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 * Die Richtung mit dem Index k geht um dz[k] Zeilen und ds[k] Spalten weiter.
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
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Schlachtfeldes.
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
