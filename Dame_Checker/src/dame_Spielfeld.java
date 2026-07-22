/**
 * Spielfeld - das 8x8-Brett fuer "Dame (vereinfacht)".
 *
 * feld[z][s]: 0 = leer, 1 = Stein von Spieler 1, 2 = Stein von Spieler 2.
 * Zeile 0 ist OBEN. Spieler 1 zieht nach UNTEN (Zeile waechst),
 * Spieler 2 zieht nach OBEN (Zeile sinkt).
 *
 * Vereinfachte Regeln (keine Dame/Umwandlung, keine Mehrfachspruenge):
 *  - Einfacher Zug: ein Feld diagonal VORWAERTS auf ein leeres Feld.
 *  - Schlagzug: zwei Felder diagonal VORWAERTS auf ein leeres Feld, wobei in der
 *    Mitte ein GEGNERISCHER Stein liegt (der dann entfernt wird).
 *
 * Anzeige (toString): 0 -> ".", 1 -> "X", 2 -> "O".
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spielfeld {

    public static final int GROESSE = 8;

    private int[][] feld;

    /** Erzeugt das Brett mit der Startaufstellung. (gegeben - nicht aendern) */
    public Spielfeld() {
        feld = new int[GROESSE][GROESSE];
        // Spieler 1 oben (Zeilen 0-2), Spieler 2 unten (Zeilen 5-7), je auf dunklen Feldern.
        for (int z = 0; z < GROESSE; z++) {
            for (int s = 0; s < GROESSE; s++) {
                if ((z + s) % 2 == 1) {
                    if (z <= 2) feld[z][s] = 1;
                    else if (z >= 5) feld[z][s] = 2;
                }
            }
        }
    }

    /** TODO 4 (leicht): Wert an der Stelle (z, s) zurueckgeben. */
    public int getZelle(int z, int s) {
        // TODO 4
        return 0;
    }

    /**
     * TODO 5 (leicht): Gegner-Nummer. Gib 2 zurueck, wenn nummer 1 ist, sonst 1.
     */
    public int gegner(int nummer) {
        // TODO 5
        return 0;
    }

    /**
     * TODO 6 (leicht): Liegt (z, s) im Feld?
     * true, wenn 0 <= z < feld.length UND 0 <= s < feld[0].length.
     */
    public boolean imFeld(int z, int s) {
        // TODO 6
        return false;
    }

    /**
     * TODO 7 (leicht): Vorwaertsrichtung von nummer.
     * Spieler 1 zieht nach unten -> +1. Spieler 2 zieht nach oben -> -1.
     */
    public int richtung(int nummer) {
        // TODO 7
        return 0;
    }

    /**
     * TODO 8 (mittel): Zaehlt die Steine von nummer auf dem ganzen Feld.
     */
    public int zaehleSteine(int nummer) {
        // TODO 8
        return 0;
    }

    /**
     * TODO 9 (mittel): Ist ein EINFACHER Zug von (z1,s1) nach (z2,s2) fuer nummer gueltig?
     * Bedingungen (alle muessen gelten):
     *   - (z1,s1) und (z2,s2) liegen im Feld.
     *   - auf (z1,s1) steht ein eigener Stein (== nummer).
     *   - (z2,s2) ist leer (== 0).
     *   - genau ein Feld diagonal VORWAERTS:
     *       z2 - z1 == richtung(nummer)  UND  (s2 - s1 == 1 ODER s2 - s1 == -1).
     */
    public boolean istEinfacherZugGueltig(int z1, int s1, int z2, int s2, int nummer) {
        // TODO 9
        return false;
    }

    /**
     * TODO 10 (schwer): Ist ein SCHLAGZUG von (z1,s1) nach (z2,s2) fuer nummer gueltig?
     * Bedingungen:
     *   - (z1,s1) und (z2,s2) liegen im Feld.
     *   - auf (z1,s1) steht ein eigener Stein (== nummer).
     *   - (z2,s2) ist leer (== 0).
     *   - zwei Felder diagonal VORWAERTS:
     *       z2 - z1 == 2 * richtung(nummer)  UND  (s2 - s1 == 2 ODER s2 - s1 == -2).
     *   - auf dem Feld DAZWISCHEN steht ein GEGNERSTEIN:
     *       zm = (z1 + z2) / 2, sm = (s1 + s2) / 2, feld[zm][sm] == gegner(nummer).
     */
    public boolean istSchlagzugGueltig(int z1, int s1, int z2, int s2, int nummer) {
        // TODO 10
        return false;
    }

    /**
     * TODO 11 (schwer): Fuehrt den Zug von (z1,s1) nach (z2,s2) fuer nummer aus.
     * (Es wird angenommen, dass der Zug gueltig ist.)
     *   - Setze feld[z2][s2] = nummer und feld[z1][s1] = 0.
     *   - War es ein Schlagzug (z2 - z1 == 2 ODER z2 - z1 == -2), entferne den
     *     geschlagenen Stein in der Mitte: feld[(z1+z2)/2][(s1+s2)/2] = 0.
     */
    public void fuehreZug(int z1, int s1, int z2, int s2, int nummer) {
        // TODO 11
    }

    /**
     * TODO 12 (leicht): Hat nummer gewonnen?
     * true, wenn der Gegner keine Steine mehr hat (zaehleSteine(gegner(nummer)) == 0).
     */
    public boolean hatGewonnen(int nummer) {
        // TODO 12
        return false;
    }

    /**
     * TODO 13 (mittel): Baut die Anzeige des Feldes als String.
     * Token: 0 -> ".", 1 -> "X", 2 -> "O". Zellen mit Leerzeichen getrennt,
     * Zeilen mit '\n' getrennt.
     */
    public String toString() {
        // TODO 13
        return "";
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public int[][] getFeld() {
        return feld;
    }

    /** Setzt ein bekanntes Feld fuer die Tests. */
    public void setFeldFuerTest(int[][] neu) {
        this.feld = neu;
    }
}
