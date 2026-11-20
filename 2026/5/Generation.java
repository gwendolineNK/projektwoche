/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Generation {

    /*
     * Die Klasse Generation hat keine Attribute.
     * Alle Methoden sind statisch und kapseln die Regeln einer Generation.
     */

    /*
     * Gibt zurueck, wie viele der bis zu 8 Nachbarzellen von (z, s) lebendig sind.
     * Nachbarn sind die Zellen waagerecht, senkrecht und diagonal daneben.
     * Die Zelle (z, s) selbst zaehlt NICHT mit.
     * Zellen am Rand haben entsprechend weniger Nachbarn; es darf nicht ueber den
     * Rand des Feldes hinaus zugegriffen werden.
     */
    public static int zaehleLebendeNachbarn(boolean[][] feld, int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }

    /*
     * Gibt zurueck, ob eine Zelle in der naechsten Generation lebendig ist.
     *
     * Parameter:
     *   lebt     - ob die Zelle aktuell lebendig ist
     *   nachbarn - Anzahl ihrer lebenden Nachbarn
     *
     * Regeln:
     *   tote Zelle    : wird genau bei 3 Nachbarn geboren
     *   lebende Zelle : ueberlebt bei 2 oder 3 Nachbarn, sonst stirbt sie
     */
    public static boolean naechsterZustand(boolean lebt, int nachbarn) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }

    /*
     * Gibt true zurueck, wenn beide Felder gleich gross sind und an jeder Stelle
     * denselben Wert enthalten; false sonst.
     */
    public static boolean gleich(boolean[][] a, boolean[][] b) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }
}
