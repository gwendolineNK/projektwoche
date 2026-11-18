/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Muster {

    /* NICHT AENDERN! Die drei moeglichen Zeichen eines Feldes. */
    public static final char LEER = '.';
    public static final char S = 'S';
    public static final char O = 'O';

    /*
     * Die Klasse Muster hat keine weiteren Attribute.
     * Alle Methoden sind statisch und durchsuchen ein char-Feld nach der
     * Teilfolge SOS.
     */

    /*
     * Gibt true zurueck, wenn an der Stelle start ein SOS beginnt, wenn also
     * feld[start] == S, feld[start+1] == O und feld[start+2] == S gilt.
     *
     * Achtung: Die drei Felder muessen existieren! Liegt start ausserhalb des
     * Feldes oder passen die drei Felder nicht mehr hinein, wird false
     * zurueckgegeben.
     *
     * Beispiele fuer feld = {'S','O','S','O','S'}:
     *   istSOS(feld, 0)  liefert true
     *   istSOS(feld, 1)  liefert false   (O S O)
     *   istSOS(feld, 2)  liefert true
     *   istSOS(feld, 3)  liefert false   (passt nicht mehr ins Feld)
     *   istSOS(feld, -1) liefert false
     */
    public static boolean istSOS(char[] feld, int start) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }

    /*
     * Gibt zurueck, wie viele SOS im gesamten Feld vorkommen.
     * Hinweis: Pruefen Sie jede moegliche Startstelle.
     *
     * Beispiele:
     *   zaehleSOS({'S','O','S','O','S'})          liefert 2
     *   zaehleSOS({'.','S','O','S','.'})          liefert 1
     *   zaehleSOS({'S','O','O','S','.'})          liefert 0
     */
    public static int zaehleSOS(char[] feld) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }

    /*
     * Gibt zurueck, an wie vielen SOS die Stelle index beteiligt ist.
     *
     * Eine Stelle ist an einem SOS beteiligt, wenn sie eines der drei Felder
     * dieses SOS ist. Ein SOS, das bei start beginnt, belegt die Felder
     * start, start+1 und start+2.
     *
     * Ueberlegen Sie also, welche Startstellen ueberhaupt in Frage kommen,
     * wenn die Stelle index beteiligt sein soll.
     *
     * Beispiele fuer feld = {'S','O','S','O','S'}:
     *   zaehleSOSMit(feld, 0) liefert 1   (nur das SOS ab 0)
     *   zaehleSOSMit(feld, 2) liefert 2   (das SOS ab 0 und das SOS ab 2)
     *   zaehleSOSMit(feld, 4) liefert 1   (nur das SOS ab 2)
     */
    public static int zaehleSOSMit(char[] feld, int index) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }
}
