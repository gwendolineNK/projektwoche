/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spieler {

	/* NICHT AENDERN! Die Staerken der drei Tiere jedes Spielers. */
	public static final int[] STAERKEN = { 1, 2, 3 };
	public static final int ANZAHL_TIERE = 3;

	/*
	 * Der Spieler kennt seine Tiere. Ein Tier kennt seinen Spieler NICHT.
	 */
	private String name;
	private String zeichen;
	private int heimatZeile;
	private int zielZeile;
	private Tier[] tiere;

	/*
	 * Vervollstaendigen Sie den Konstruktor.
	 * Name, Zeichen, Heimatzeile und Zielzeile werden aus den Parametern
	 * uebernommen.
	 * Der Spieler erzeugt seine Tiere: ein Feld mit ANZAHL_TIERE Tieren. Das Tier
	 * an der Stelle i hat die Staerke STAERKEN[i] und steht in der Heimatzeile
	 * auf der Spalte i.
	 */
	public Spieler(String name, String zeichen, int heimatZeile, int zielZeile) {
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
	public int getHeimatZeile() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Vervollstaendigen Sie die Getter-Methode
	 */
	public int getZielZeile() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt das Tier an der Stelle index zurueck.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Werten
	 * aufgerufen wird.
	 */
	public Tier getTier(int index) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * PFLICHTMETHODE!
	 *
	 * Gibt das Tier dieses Spielers zurueck, das auf dem Feld (z, s) steht.
	 * Steht dort keines seiner Tiere, wird null zurueckgegeben.
	 *
	 * Da ein Tier seinen Besitzer nicht kennt und das Spielfeld die Tiere gar
	 * nicht kennt, kann diese Frage nur der Spieler beantworten: Durchsuchen Sie
	 * dazu Ihre eigenen Tiere.
	 * Hinweis: Tier.stehtAuf(...)
	 */
	public Tier tierAn(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return null;
	}

	/*
	 * Gibt zurueck, wie viele Tiere dieses Spielers noch im Spiel sind.
	 */
	public int zaehleTiere() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return -1;
	}

	/*
	 * Gibt true zurueck, wenn kein Tier dieses Spielers mehr im Spiel ist;
	 * false sonst.
	 */
	public boolean hatVerloren() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn mindestens ein Tier dieses Spielers in seiner
	 * Zielzeile steht; false sonst.
	 * Ein geschlagenes Tier zaehlt dabei nicht mit!
	 */
	public boolean hatZielErreicht() {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Gibt true zurueck, wenn das Tier mit der Nummer index auf das Feld
	 * (zielZ, zielS) ziehen darf; false sonst.
	 *
	 * Erlaubt ist der Zug genau dann, wenn
	 * - das Tier noch im Spiel ist, und
	 * - (zielZ, zielS) ein Nachbarfeld des Tieres ist, und
	 * - das Tier das Zielfeld betreten darf, und
	 * - dort kein eigenes Tier steht, und
	 * - dort kein gegnerisches Tier steht oder ein gegnerisches Tier, das
	 *   geschlagen werden darf.
	 *
	 * Hinweis: Spielfeld.istNachbar(...), Spielfeld.betretbar(...), tierAn(...),
	 * gegner.tierAn(...) und Tier.schlaegt(...)
	 *
	 * Es ist sichergestellt, dass index gueltig ist.
	 */
	public boolean zugErlaubt(int index, int zielZ, int zielS, Spielfeld feld, Spieler gegner) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * Fuehrt den Zug des Tieres mit der Nummer index auf das Feld (zielZ, zielS)
	 * aus: Steht dort ein gegnerisches Tier, so wird es geschlagen. Danach zieht
	 * das eigene Tier auf das Zielfeld.
	 *
	 * Es ist sichergestellt, dass die Methode nur mit erlaubten Zuegen
	 * aufgerufen wird.
	 */
	public void fuehreZugAus(int index, int zielZ, int zielS, Spieler gegner) {
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
