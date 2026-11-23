/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spieler {

	/*
	 * ACHTUNG: Der Spieler hat KEIN Feld mit Steinen!
	 * Er kennt seinen Namen, sein Zeichen und seinen Gegner.
	 */
	private String name;
	private String zeichen;
	private Spieler gegner;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Name und Zeichen werden aus den Parametern uebernommen.
	 * Der Gegner ist zu diesem Zeitpunkt noch nicht bekannt.
	 */
	public Spieler(String name, String zeichen) {
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
	public String getZeichen() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return "";
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public Spieler getGegner() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Setzt den Gegner dieses Spielers.
	 * Dabei wird auch bei dem uebergebenen Spieler dieser Spieler als Gegner
	 * eingetragen, so dass beide Spieler einander kennen.
	 * Achten Sie darauf, dass dabei keine Endlosschleife entsteht!
	 */
	public void setzeGegner(Spieler gegner) {
		// TODO
		// Anweisung(en) hinzufuegen
	}

	/*
	 * NICHT AENDERN!
	 */
	public String toString() {
		return name + " (" + zeichen + ")";
	}
}
