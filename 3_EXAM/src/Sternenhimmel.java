import java.util.Random;

public class Sternenhimmel {

    public static final int GROESSE = 8;
    public static final char LEER = ' ';

    /** Die 8 Richtungen: DZ[r] = Zeilenaenderung, DS[r] = Spaltenaenderung. (gegeben) */
    public static final int[] DZ = { -1, -1, -1, 0, 0, 1, 1, 1 };
    public static final int[] DS = { -1, 0, 1, -1, 1, -1, 0, 1 };

    private char[][] sterne;
    private Random rd;

    /** (gegeben - nicht aendern) */
    public Sternenhimmel() {
        rd = new Random();
        sterne = new char[GROESSE][GROESSE];
        initialisiereSterne();
        mischeSterne();
    }

    private void initialisiereSterne() {
        // TODO 11
    }

    private void mischeSterne() {
        // TODO 12
    }

    public char getStern(int z, int s) {
        // TODO 13
        return LEER;
    }

    public boolean imFeld(int z, int s) {
        // TODO 14
        return false;
    }

    public int wert(char stern) {
        // TODO 15
        return 0;
    }

    public boolean istLeer(int z, int s) {
        // TODO 16
        return false;
    }

    public int zaehleSterne() {
        // TODO 17
        return 0;
    }

    public boolean istSpielEnde() {
        // TODO 18
        return false;
    }

    public int normiere(int wert, int laenge) {
        // TODO 19
        return 0;
    }

    public int zielZeile(int zeile, int richtung, int schritte) {
        // TODO 20
        return 0;
    }

    public int zielSpalte(int spalte, int richtung, int schritte) {
        // TODO 21
        return 0;
    }

    public int schrittweite(int z, int s) {
        // TODO 22
        return 0;
    }

    public int zaehleGleicheNachbarn(int z, int s) {
        // TODO 23
        return 0;
    }

    public int sammle(int z, int s) {
        // TODO 24
        return 0;
    }

    public String toString(Spieler s1, Spieler s2) {
        // TODO 25
        return "";
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public char[][] getSterne() {
        return sterne;
    }

    public void setSterneFuerTest(char[][] neu) {
        this.sterne = neu;
    }
}
