/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld mitteBoard() {
        Spielfeld f = new Spielfeld(3, 1);
        boolean[][] karte = new boolean[3][3];
        karte[1][1] = true;
        f.setMinenFuerTest(karte);
        return f;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Minesucher ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex");
            return "Alex".equals(s.getName()) && s.getPunkte() == 0;
        });
        pruefe("Spieler: punktDazu", () -> {
            Spieler s = new Spieler("Alex");
            s.punktDazu(); s.punktDazu();
            return s.getPunkte() == 2;
        });

        pruefe("getN", () -> new Spielfeld(5, 3).getN() == 5);

        pruefe("anzahl Minen", () -> {
            int[][] faelle = { {4, 5}, {5, 3}, {6, 10} };
            for (int[] fall : faelle) {
                Spielfeld f = new Spielfeld(fall[0], fall[1]);
                int c = 0;
                for (int z = 0; z < fall[0]; z++)
                    for (int s = 0; s < fall[0]; s++)
                        if (f.getMinen()[z][s]) c++;
                if (c != fall[1]) return false;
            }
            return true;
        });

        pruefe("istMine", () -> {
            Spielfeld f = mitteBoard();
            return f.istMine(1, 1) && !f.istMine(0, 0);
        });

        pruefe("gueltigeEingabe", () -> {
            Spielfeld f = mitteBoard();
            boolean ok = f.gueltigeEingabe(0, 0) && !f.gueltigeEingabe(-1, 0)
                      && !f.gueltigeEingabe(3, 0) && !f.gueltigeEingabe(0, 3);
            f.aufdecken(0, 0);
            return ok && !f.gueltigeEingabe(0, 0);
        });

        pruefe("Nachbarminen (Mitte, Diagonalen, Selbst)", () -> {
            Spielfeld f = mitteBoard();
            return f.zaehleNachbarminen(0, 0) == 1
                && f.zaehleNachbarminen(0, 1) == 1
                && f.zaehleNachbarminen(2, 2) == 1
                && f.zaehleNachbarminen(1, 1) == 0;
        });

        pruefe("Nachbarminen (mehrere)", () -> {
            Spielfeld f = new Spielfeld(3, 1);
            boolean[][] karte = new boolean[3][3];
            karte[0][0] = true; karte[0][1] = true;
            f.setMinenFuerTest(karte);
            return f.zaehleNachbarminen(1, 1) == 2;
        });

        pruefe("alleSicherenAufgedeckt", () -> {
            Spielfeld f = mitteBoard();
            if (f.alleSicherenAufgedeckt()) return false;
            for (int z = 0; z < 3; z++)
                for (int s = 0; s < 3; s++)
                    if (!f.getMinen()[z][s]) f.aufdecken(z, s);
            return f.alleSicherenAufgedeckt();
        });

        pruefe("toString verdeckt = 16 #", () -> zaehle(new Spielfeld(4, 3).toString(), '#') == 16);

        pruefe("toString zeigt Zahl", () -> {
            Spielfeld f = mitteBoard();
            f.aufdecken(0, 0);
            String t = f.toString();
            return zaehle(t, '#') == 8 && t.indexOf("1") >= 0;
        });

        pruefe("toString zeigt Mine *", () -> {
            Spielfeld f = mitteBoard();
            f.aufdecken(1, 1);
            return f.toString().indexOf("*") >= 0;
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