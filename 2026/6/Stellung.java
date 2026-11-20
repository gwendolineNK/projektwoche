/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Stellung {

    /*
     * Die Klasse Stellung hat keine Attribute.
     * Alle Methoden sind statisch und arbeiten auf quadratischen boolean-Feldern.
     */

    /*
     * NICHT AENDERN! Erzeugt eine Kopie der uebergebenen Stellung.
     */
    public static boolean[][] kopie(boolean[][] stellung) {
        int n = stellung.length;
        boolean[][] neu = new boolean[n][n];
        for (int z = 0; z < n; z++) {
            for (int s = 0; s < n; s++) {
                neu[z][s] = stellung[z][s];
            }
        }
        return neu;
    }

    /*
     * Gibt eine NEUE Stellung zurueck, die der uebergebenen Stellung entspricht,
     * aber um 90 Grad im Uhrzeigersinn gedreht wurde.
     * Die uebergebene Stellung wird nicht veraendert.
     *
     * Bei einer Drehung um 90 Grad im Uhrzeigersinn wandert das Feld der Zeile z
     * und der Spalte s auf die Zeile s und die Spalte n-1-z.
     *
     * Beispiel (n = 3), ein Stein in der oberen linken Ecke:
     *    X . .              . . X
     *    . . .     wird zu  . . .
     *    . . .              . . .
     */
    public static boolean[][] drehe90(boolean[][] stellung) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return null;
    }

    /*
     * Gibt true zurueck, wenn beide Stellungen gleich gross sind und an jeder
     * Stelle denselben Wert enthalten; false sonst.
     */
    public static boolean gleich(boolean[][] a, boolean[][] b) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }

    /*
     * Gibt true zurueck, wenn die beiden Stellungen PUAN-GLEICH sind, wenn sie
     * also gleich sind oder durch eine Drehung um 90, 180 oder 270 Grad
     * ineinander ueberfuehrt werden koennen; false sonst.
     *
     * Hinweis: Eine Drehung um 180 Grad erhalten Sie durch zweimaliges Drehen
     * um 90 Grad, eine Drehung um 270 Grad durch dreimaliges Drehen.
     */
    public static boolean puanGleich(boolean[][] a, boolean[][] b) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }
}
