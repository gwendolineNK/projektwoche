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

    private void initialisiereRunen() {
        // TODO 8
    }

    private void mischeRunen() {
        // TODO 9
    }

    public char getRune(int z, int s) {
        // TODO 10
        return ' ';
    }

    public int getBesitzer(int z, int s) {
        // TODO 11
        return 0;
    }

    public boolean imFeld(int z, int s) {
        // TODO 12
        return false;
    }

    public int gegner(int nummer) {
        // TODO 13
        return 0;
    }

    public int runenWert(char rune) {
        // TODO 14
        return 0;
    }

    public boolean istNeutral(int z, int s) {
        // TODO 15
        return false;
    }

    public int zaehleNeutrale() {
        // TODO 16
        return 0;
    }

    public boolean istSpielEnde() {
        // TODO 17
        return false;
    }

    public int gebietGroesse(int z, int s) {
        // TODO 18
        return 0;
    }

    public int beansprucheGebiet(int z, int s, int nummer) {
        // TODO 19
        return 0;
    }

    public int punkte(int nummer) {
        // TODO 20
        return 0;
    }

    public void rotiereZeile(int z) {
        // TODO 21
    }

    public String toString() {
        // TODO 22
        return "";
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
