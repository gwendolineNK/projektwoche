import java.util.Random;

/**
 * Code - der geheime Zahlencode fuer "Mastermind / Bulls & Cows".
 *
 * Der Code besteht aus LAENGE VERSCHIEDENEN Ziffern (0-9).
 * Auswertung eines Tipps:
 *   - Bull  = richtige Ziffer an richtiger Stelle.
 *   - Kuh   = Ziffer kommt im Code vor, aber an einer anderen Stelle.
 * Man darf annehmen, dass auch der Tipp aus verschiedenen Ziffern besteht.
 *
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Code {

    public static final int LAENGE = 4;

    private int[] code;
    private Random rd;

    /** Erzeugt einen zufaelligen Code aus verschiedenen Ziffern. (gegeben - nicht aendern) */
    public Code() {
        rd = new Random();
        code = new int[LAENGE];
        initialisiereCode();
    }

    /**
     * TODO 10 (schwer): Fuellt code mit LAENGE VERSCHIEDENEN Ziffern (0-9).
     * Eine Zufallsziffer bekommst du mit rd.nextInt(10).
     * Hinweis: Merke mit einem boolean[] der Groesse 10, welche Ziffern schon
     *   benutzt sind. Solange noch nicht LAENGE Ziffern gesetzt sind: eine
     *   Zufallsziffer ziehen; ist sie noch nicht benutzt, ins code-Array setzen,
     *   als benutzt markieren und weiterzaehlen. (while-Schleife)
     */
    private void initialisiereCode() {
        // TODO 10
    }

    /** TODO 5 (leicht): Laenge des Codes zurueckgeben (code.length). */
    public int getLaenge() {
        // TODO 5
        return 0;
    }

    /**
     * TODO 6 (mittel): Kommt 'wert' irgendwo im Code vor?
     * Gibt true zurueck, wenn eine Stelle des Codes gleich wert ist, sonst false.
     */
    public boolean enthaelt(int wert) {
        // TODO 6
        return false;
    }

    /**
     * TODO 7 (mittel): Zaehlt die Bulls.
     * Bull = Stelle i, an der tipp[i] genau gleich code[i] ist.
     */
    public int zaehleBulls(int[] tipp) {
        // TODO 7
        return 0;
    }

    /**
     * TODO 8 (leicht): Ist der Code geloest?
     * true, wenn alle Stellen Bulls sind (zaehleBulls(tipp) == getLaenge()).
     */
    public boolean istGeloest(int[] tipp) {
        // TODO 8
        return false;
    }

    /**
     * TODO 9 (schwer): Zaehlt die Kuehe.
     * Kuh = Stelle i, an der tipp[i] NICHT gleich code[i] ist, die Ziffer tipp[i]
     * aber irgendwo im Code vorkommt.
     * Hinweis: Schleife ueber alle i. Wenn tipp[i] != code[i] UND enthaelt(tipp[i]),
     *   dann Kuh dazuzaehlen.
     */
    public int zaehleKuehe(int[] tipp) {
        // TODO 9
        return 0;
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public int[] getCode() {
        return code;
    }

    /** Setzt einen bekannten Code fuer die Tests. */
    public void setCodeFuerTest(int[] neu) {
        this.code = neu;
    }
}
