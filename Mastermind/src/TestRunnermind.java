/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Code codeBoard() {
        Code c = new Code();
        c.setCodeFuerTest(new int[] { 1, 2, 3, 4 });
        return c;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Mastermind ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex");
            return "Alex".equals(s.getName()) && s.getVersuche() == 0;
        });
        pruefe("Spieler: versuchDazu", () -> {
            Spieler s = new Spieler("Alex");
            s.versuchDazu(); s.versuchDazu();
            return s.getVersuche() == 2;
        });

        pruefe("getLaenge", () -> new Code().getLaenge() == 4);

        pruefe("Code zufaellig und verschieden", () -> {
            for (int v = 0; v < 20; v++) {
                int[] c = new Code().getCode();
                if (c.length != 4) return false;
                boolean[] gesehen = new boolean[10];
                for (int x : c) {
                    if (x < 0 || x > 9) return false;
                    if (gesehen[x]) return false;
                    gesehen[x] = true;
                }
            }
            return true;
        });

        pruefe("enthaelt", () -> {
            Code c = codeBoard();
            return c.enthaelt(3) && !c.enthaelt(5);
        });

        pruefe("zaehleBulls", () -> {
            Code c = codeBoard();
            return c.zaehleBulls(new int[] { 1, 0, 3, 0 }) == 2
                && c.zaehleBulls(new int[] { 0, 0, 0, 0 }) == 0
                && c.zaehleBulls(new int[] { 1, 2, 3, 4 }) == 4;
        });

        pruefe("istGeloest", () -> {
            Code c = codeBoard();
            return c.istGeloest(new int[] { 1, 2, 3, 4 })
                && !c.istGeloest(new int[] { 1, 2, 3, 0 });
        });

        pruefe("zaehleKuehe", () -> {
            Code c = codeBoard();
            return c.zaehleKuehe(new int[] { 4, 3, 2, 1 }) == 4
                && c.zaehleKuehe(new int[] { 1, 2, 3, 4 }) == 0
                && c.zaehleKuehe(new int[] { 5, 6, 7, 8 }) == 0
                && c.zaehleKuehe(new int[] { 2, 1, 3, 4 }) == 2;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3));

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
    }

    interface Pruefung { boolean test() throws Exception; }

    static void pruefe(String name, Pruefung p) {
        gesamt++;
        boolean ok;
        try {
            ok = p.test();
        } catch (Throwable t) {
            ok = false;
            name = name + "  (Ausnahme: " + t.getClass().getSimpleName() + ")";
        }
        if (ok) bestanden++;
        System.out.println((ok ? "[ OK ]  " : "[FAIL]  ") + name);
    }
}
