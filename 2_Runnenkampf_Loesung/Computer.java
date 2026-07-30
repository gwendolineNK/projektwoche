public class Computer extends Spieler {

    // TODO 6
    public Computer(int nummer) {
        super("Computer", nummer);
    }

    /**
     * TODO 7
     * Waehlt das neutrale Feld mit dem groessten Punktgewinn
     * (Gebietsgroesse x Runenwert).
     * Bei Gleichstand gewinnt das Feld mit der kleinsten Zeile, dann der
     * kleinsten Spalte - das ergibt sich automatisch daraus, dass wir das
     * Feld zeilenweise durchlaufen und nur bei ECHT groesserem Gewinn (>)
     * die Auswahl ersetzen.
     */
    public int[] waehleZug(Spielfeld feld) {
        int besteZeile = -1;
        int besteSpalte = -1;
        int besterGewinn = 0;

        char[][] runen = feld.getRunen();
        for (int z = 0; z < runen.length; z++) {
            for (int s = 0; s < runen[z].length; s++) {
                if (feld.istNeutral(z, s)) {
                    int gewinn = feld.gebietGroesse(z, s) * feld.runenWert(feld.getRune(z, s));
                    if (gewinn > besterGewinn) {
                        besterGewinn = gewinn;
                        besteZeile = z;
                        besteSpalte = s;
                    }
                }
            }
        }
        return new int[] { besteZeile, besteSpalte };
    }
}
