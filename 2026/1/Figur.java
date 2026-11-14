/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Figur {

    /*
     * Die Klasse Figur hat keine Attribute.
     * Alle Methoden sind statisch und werten den Zahlenwert einer Figur aus:
     *   0        leeres Feld
     *   1 bis 6  Figuren von Spieler 1
     *  -1 bis -6 Figuren von Spieler 2
     */

    /*
     * Gibt den Typ der Figur zurueck, also den Betrag ihres Zahlenwertes.
     * Beispiele: typ(4) == 4, typ(-4) == 4, typ(0) == 0
     * Eine Aufzaehlung aller Faelle ist NICHT erlaubt - berechnen Sie den Wert.
     */
    public static int typ(int figur) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }

    /*
     * Gibt die Nummer des Spielers zurueck, dem die Figur gehoert:
     *   1, wenn der Zahlenwert positiv ist
     *   2, wenn der Zahlenwert negativ ist
     *   0 bei einem leeren Feld
     */
    public static int besitzer(int figur) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return -1;
    }

    /*
     * Gibt true zurueck, wenn es sich um eine GROSSE Figur handelt,
     * also um ein grosses Quadrat (Typ 2), ein grosses Dreieck (Typ 4)
     * oder einen grossen Kreis (Typ 6); false sonst.
     * Fuer ein leeres Feld wird false zurueckgegeben.
     */
    public static boolean istGross(int figur) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }

    /*
     * Gibt true zurueck, wenn die Figur waagerecht oder senkrecht ziehen darf,
     * also bei den Quadraten (Typ 1, 2) und den Kreisen (Typ 5, 6); false sonst.
     */
    public static boolean ziehtGerade(int figur) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }

    /*
     * Gibt true zurueck, wenn die Figur diagonal ziehen darf,
     * also bei den Dreiecken (Typ 3, 4) und den Kreisen (Typ 5, 6); false sonst.
     */
    public static boolean ziehtDiagonal(int figur) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
        return false;
    }
}
