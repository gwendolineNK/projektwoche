/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spieler {

	/* NICHT AENDERN! Jeder Spieler besitzt 4 Quadrate und 3 Kreise. */
	public static final int ANZAHL_FIGUREN = 7;
	public static final int ANZAHL_QUADRATE = 4;

	private String name;
	private int nummer;
	private Figur[] figuren;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Name und Nummer werden aus den Parametern uebernommen.
	 *
	 * Ausserdem legt der Spieler sein Feld mit ANZAHL_FIGUREN Figuren an und
	 * erzeugt diese: Die Figuren an den Stellen 0 bis ANZAHL_QUADRATE - 1 sind
	 * Quadrate, alle weiteren sind Kreise. Besitzer jeder dieser Figuren ist
	 * dieser Spieler selbst.
	 */
	public Spieler(String name, int nummer) {
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
	public int getNummer() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt die Figur an der Stelle index zurueck.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public Figur getFigur(int index) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt zurueck, wie viele Figuren dieses Spielers noch im Spiel sind.
	 */
	public int zaehleFigurenImSpiel() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}
}
