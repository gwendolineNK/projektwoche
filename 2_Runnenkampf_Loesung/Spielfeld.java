import java.util.Random;

public class Spielfeld {

    public static final int GROESSE = 8;

    private char[][] runen;
    private int[][] besitzer;
    private Random rd;

    /** (gegeben - nicht aendern) */
    public Spielfeld() {
        rd = new Random();
        runen = new char[GROESSE][GROESSE];
        besitzer = new int[GROESSE][GROESSE];
        initialisiereRunen();
        mischeRunen();
    }

    /**
     * TODO 8
     * Zeile 0 -> 'A', Zeile 1 -> 'B', ... Zeile 7 -> 'H'.
     * 'A' hat die ASCII-Zahl 65, also ist die Rune der Zeile z: (char)('A' + z).
     */
    private void initialisiereRunen() {
        for (int z = 0; z < runen.length; z++) {
            for (int s = 0; s < runen[z].length; s++) {
                runen[z][s] = (char) ('A' + z);
            }
        }
    }

    /** TODO 9 - vorgegebener Mischalgorithmus */
    private void mischeRunen() {
        for (int z = runen.length - 1; z >= 0; z--) {
            for (int s = runen[z].length - 1; s >= 0; s--) {
                int rd1 = rd.nextInt(z + 1);
                int rd2 = rd.nextInt(s + 1);
                char tmp = runen[z][s];
                runen[z][s] = runen[rd1][rd2];
                runen[rd1][rd2] = tmp;
            }
        }
    }

    /** TODO 10 */
    public char getRune(int z, int s) {
        return runen[z][s];
    }

    /** TODO 11 */
    public int getBesitzer(int z, int s) {
        return besitzer[z][s];
    }

    /** TODO 12 */
    public boolean imFeld(int z, int s) {
        return z >= 0 && z < runen.length && s >= 0 && s < runen[0].length;
    }

    /** TODO 13 */
    public int gegner(int nummer) {
        if (nummer == 1) {
            return 2;
        }
        return 1;
    }

    /**
     * TODO 14
     * 'A' = 65 -> Wert 1, 'B' = 66 -> Wert 2, ...
     * Also: ASCII-Zahl minus 'A' plus 1.
     */
    public int runenWert(char rune) {
        return rune - 'A' + 1;
    }

    /** TODO 15 - Grenzen ZUERST pruefen, dann erst zugreifen */
    public boolean istNeutral(int z, int s) {
        return imFeld(z, s) && besitzer[z][s] == 0;
    }

    /** TODO 16 */
    public int zaehleNeutrale() {
        int anzahl = 0;
        for (int z = 0; z < besitzer.length; z++) {
            for (int s = 0; s < besitzer[z].length; s++) {
                if (besitzer[z][s] == 0) {
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    /** TODO 17 */
    public boolean istSpielEnde() {
        return zaehleNeutrale() == 0;
    }

    /**
     * Hilfsmethode (nicht im UML, aber erlaubt): markiert alle Felder des
     * Gebietes von (z, s) in einem Hilfsfeld.
     *
     * Iteratives Verfahren statt Rekursion:
     *   1. nur (z, s) markieren
     *   2. das gesamte Feld wiederholt durchlaufen. Ein Feld wird markiert,
     *      wenn es neutral ist, dieselbe Rune traegt und einen bereits
     *      markierten waagerechten/senkrechten Nachbarn hat.
     *   3. abbrechen, sobald ein kompletter Durchlauf nichts mehr veraendert.
     */
    private boolean[][] gebietMarkierung(int z, int s) {
        boolean[][] markiert = new boolean[runen.length][runen[0].length];
        if (!istNeutral(z, s)) {
            return markiert;                 // alles false
        }
        char rune = runen[z][s];
        markiert[z][s] = true;

        boolean geaendert = true;
        while (geaendert) {
            geaendert = false;
            for (int i = 0; i < runen.length; i++) {
                for (int j = 0; j < runen[i].length; j++) {
                    if (!markiert[i][j] && istNeutral(i, j) && runen[i][j] == rune) {
                        boolean nachbarMarkiert =
                                   (i > 0                   && markiert[i - 1][j])
                                || (i < runen.length - 1    && markiert[i + 1][j])
                                || (j > 0                   && markiert[i][j - 1])
                                || (j < runen[i].length - 1 && markiert[i][j + 1]);
                        if (nachbarMarkiert) {
                            markiert[i][j] = true;
                            geaendert = true;
                        }
                    }
                }
            }
        }
        return markiert;
    }

    /** TODO 18 */
    public int gebietGroesse(int z, int s) {
        boolean[][] markiert = gebietMarkierung(z, s);
        int anzahl = 0;
        for (int i = 0; i < markiert.length; i++) {
            for (int j = 0; j < markiert[i].length; j++) {
                if (markiert[i][j]) {
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    /** TODO 19 */
    public int beansprucheGebiet(int z, int s, int nummer) {
        if (!istNeutral(z, s)) {
            return 0;
        }
        boolean[][] markiert = gebietMarkierung(z, s);
        int anzahl = 0;
        for (int i = 0; i < markiert.length; i++) {
            for (int j = 0; j < markiert[i].length; j++) {
                if (markiert[i][j]) {
                    besitzer[i][j] = nummer;
                    anzahl++;
                }
            }
        }
        return anzahl;
    }

    /** TODO 20 */
    public int punkte(int nummer) {
        int summe = 0;
        for (int z = 0; z < runen.length; z++) {
            for (int s = 0; s < runen[z].length; s++) {
                if (besitzer[z][s] == nummer) {
                    summe = summe + runenWert(runen[z][s]);
                }
            }
        }
        return summe;
    }

    /**
     * TODO 21
     * Zyklisch eine Position nach rechts: der Inhalt von Spalte s landet in
     * Spalte (s + 1) % n. Das ist die modulare Arithmetik - die letzte Spalte
     * landet dadurch automatisch in Spalte 0.
     */
    public void rotiereZeile(int z) {
        if (!imFeld(z, 0)) {
            return;
        }
        int n = runen[z].length;
        char[] neueRunen = new char[n];
        int[] neueBesitzer = new int[n];

        for (int s = 0; s < n; s++) {
            neueRunen[(s + 1) % n] = runen[z][s];
            neueBesitzer[(s + 1) % n] = besitzer[z][s];
        }
        for (int s = 0; s < n; s++) {
            runen[z][s] = neueRunen[s];
            besitzer[z][s] = neueBesitzer[s];
        }
    }

    /** TODO 22 */
    public String toString() {
        String erg = "   ";
        for (int s = 0; s < runen[0].length; s++) {
            erg = erg + s + "  ";
        }
        erg = erg + "\n";

        for (int z = 0; z < runen.length; z++) {
            erg = erg + z + "  ";
            for (int s = 0; s < runen[z].length; s++) {
                erg = erg + runen[z][s];
                if (besitzer[z][s] == 0) {
                    erg = erg + "-";
                } else if (besitzer[z][s] == 1) {
                    erg = erg + "1";
                } else {
                    erg = erg + "2";
                }
                erg = erg + " ";
            }
            erg = erg + "\n";
        }
        return erg;
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public char[][] getRunen() {
        return runen;
    }

    public int[][] getBesitzerFeld() {
        return besitzer;
    }

    public void setFelderFuerTest(char[][] neueRunen, int[][] neueBesitzer) {
        this.runen = neueRunen;
        this.besitzer = neueBesitzer;
    }
}
