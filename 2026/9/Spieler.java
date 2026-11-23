/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spieler {

	/* NICHT AENDERN! Die Laengen der drei Schiffe jedes Spielers. */
	public static final int[] LAENGEN = { 3, 2, 2 };
	public static final int ANZAHL_SCHIFFE = 3;

	/*
	 * Der Spieler kennt seine Schiffe und sein eigenes Spielfeld,
	 * auf dem die Schuesse des Gegners festgehalten werden.
	 */
	private String name;
	private Schiff[] flotte;
	private Spielfeld feld;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Der Name wird aus dem Parameter uebernommen.
	 * Der Spieler erzeugt sein eigenes Spielfeld der Groesse n sowie seine
	 * Flotte: ein Feld mit ANZAHL_SCHIFFE Schiffen, deren Laengen in LAENGEN
	 * stehen (das Schiff an der Stelle 0 hat die Laenge LAENGEN[0] usw.).
	 */
	public Spieler(String name, int n) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public String getName() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return "";
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public Spielfeld getSpielfeld() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt das Schiff an der Stelle index der Flotte zurueck.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public Schiff getSchiff(int index) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt das Schiff dieses Spielers zurueck, das das Feld (z, s) belegt.
	 * Belegt keines seiner Schiffe dieses Feld, wird null zurueckgegeben.
	 *
	 * Da ein Schiff seinen Besitzer nicht kennt, kann diese Frage nur der
	 * Spieler beantworten: Durchsuchen Sie dazu Ihre eigene Flotte.
	 * Hinweis: Schiff.belegt(...)
	 */
	public Schiff schiffAn(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt true zurueck, wenn ein Schiff der Laenge laenge ab (zeile, spalte)
	 * in der angegebenen Ausrichtung platziert werden darf; false sonst.
	 *
	 * Gueltig bedeutet: ALLE Felder des Schiffes liegen im Spielfeld und
	 * KEINES davon wird bereits von einem eigenen Schiff belegt.
	 * Hinweis: Spielfeld.imFeld(...) und schiffAn(...)
	 */
	public boolean passtSchiff(int laenge, int zeile, int spalte, boolean waagerecht) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt zurueck, wie viele Schiffe dieses Spielers bereits versenkt sind.
	 */
	public int zaehleVersenkte() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn alle Schiffe dieses Spielers versenkt sind;
	 * false sonst.
	 */
	public boolean alleSchiffeVersenkt() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}
}
