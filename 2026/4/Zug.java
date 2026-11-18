/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Zug {

    /*
     * Die Klasse Zug hat keine Attribute.
     * Alle Methoden sind statisch und rechnen mit Muldennummern und Zugrichtungen.
     */

    /*
     * Gibt die Zugrichtung des Spielers mit der Nummer spielernummer zurueck:
     *    1 fuer Spieler 1 (von links nach rechts)
     *   -1 fuer Spieler 2 (von rechts nach links)
     */
    public static int richtungVon(int spielernummer) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return 0;
    }

    /*
     * Gibt die Nummer der Mulde zurueck, in die der LETZTE Samen faellt,
     * wenn ab der Mulde start in Richtung richtung genau anzahl Samen
     * verteilt werden.
     *
     * Beispiele:
     *   letzteMulde(2, 1, 3)   liefert 5    (Samen fallen in 3, 4, 5)
     *   letzteMulde(4, -1, 4)  liefert 0    (Samen fallen in 3, 2, 1, 0)
     *   letzteMulde(0, 1, 1)   liefert 1
     */
    public static int letzteMulde(int start, int richtung, int anzahl) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -99;
    }

    /*
     * Gibt true zurueck, wenn alle anzahl Samen noch auf das Brett passen,
     * wenn also keine Mulde ausserhalb des Brettes getroffen wuerde; false sonst.
     * Das Brett hat n Mulden mit den Nummern 0 bis n-1.
     *
     * Hinweis: Es genuegt, die zuletzt getroffene Mulde zu betrachten.
     *
     * Beispiele fuer n = 6:
     *   passtInsFeld(6, 0, 1, 3)   liefert true    (letzte Mulde 3)
     *   passtInsFeld(6, 4, 1, 4)   liefert false   (letzte Mulde 8)
     *   passtInsFeld(6, 4, -1, 4)  liefert true    (letzte Mulde 0)
     *   passtInsFeld(6, 0, -1, 1)  liefert false   (letzte Mulde -1)
     */
    public static boolean passtInsFeld(int n, int start, int richtung, int anzahl) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }
}
