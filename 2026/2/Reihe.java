/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Reihe {

    /*
     * Die Klasse Reihe hat keine Attribute.
     * Alle Methoden sind statisch und arbeiten auf einer einzelnen Reihe,
     * also auf einem eindimensionalen int-Feld. Der Wert 0 bedeutet "leer".
     */

    /*
     * NICHT AENDERN! Erzeugt eine Kopie der uebergebenen Reihe.
     */
    public static int[] kopie(int[] reihe) {
        int[] neu = new int[reihe.length];
        for (int i = 0; i < reihe.length; i++) {
            neu[i] = reihe[i];
        }
        return neu;
    }

    /*
     * Gibt eine NEUE Reihe zurueck, die die uebergebene Reihe in umgekehrter
     * Reihenfolge enthaelt. Die uebergebene Reihe wird nicht veraendert.
     * Beispiel: umdrehen({2, 4, 0, 8}) liefert {8, 0, 4, 2}
     */
    public static int[] umdrehen(int[] reihe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return null;
    }

    /*
     * Gibt eine NEUE Reihe zurueck, in der alle Kacheln (Werte ungleich 0) nach
     * vorne geschoben wurden. Die Reihenfolge der Kacheln bleibt dabei erhalten,
     * hinten wird mit 0 aufgefuellt. Die uebergebene Reihe wird nicht veraendert.
     * Beispiele:
     *   zusammenschieben({0, 2, 0, 4}) liefert {2, 4, 0, 0}
     *   zusammenschieben({2, 4, 0, 0}) liefert {2, 4, 0, 0}
     */
    public static int[] zusammenschieben(int[] reihe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return null;
    }

    /*
     * Verschmilzt in der uebergebenen Reihe benachbarte gleiche Kacheln.
     * Die Reihe wird dabei DIREKT veraendert (nicht kopiert).
     * Vorgehen: von links nach rechts pruefen, ob zwei benachbarte Felder
     * denselben Wert ungleich 0 haben. Ist das der Fall, erhaelt das linke Feld
     * die Summe, das rechte Feld wird 0.
     * Achtung: Jede Kachel darf pro Aufruf hoechstens einmal verschmelzen!
     * Rueckgabewert: die Summe aller neu entstandenen Kacheln (die Punkte).
     * Beispiele (die Reihe ist bereits zusammengeschoben):
     *   verschmelzen({2, 2, 4, 0}) -> Reihe wird {4, 0, 4, 0}, Rueckgabe 4
     *   verschmelzen({4, 4, 4, 4}) -> Reihe wird {8, 0, 8, 0}, Rueckgabe 16
     *   verschmelzen({2, 4, 8, 0}) -> Reihe bleibt {2, 4, 8, 0}, Rueckgabe 0
     */
    public static int verschmelzen(int[] reihe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }

    /*
     * Gibt true zurueck, wenn beide Reihen gleich lang sind und an jeder Stelle
     * denselben Wert enthalten; false sonst.
     */
    public static boolean gleich(int[] a, int[] b) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }
}
