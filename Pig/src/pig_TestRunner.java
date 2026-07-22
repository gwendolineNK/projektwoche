/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Pig ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex");
            return "Alex".equals(s.getName()) && s.getPunkte() == 0;
        });

        pruefe("Spieler: punkteDazu", () -> {
            Spieler s = new Spieler("Alex");
            s.punkteDazu(5); s.punkteDazu(3);
            return s.getPunkte() == 8;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3));

        pruefe("wuerfeln im Bereich 1-6", () -> {
            for (int i = 0; i < 200; i++) {
                int w = Spiel.wuerfeln();
                if (w < 1 || w > 6) return false;
            }
            return true;
        });

        pruefe("istPech", () ->
            Spiel.istPech(1) && !Spiel.istPech(2) && !Spiel.istPech(6));

        pruefe("neuerRundenScore", () ->
            Spiel.neuerRundenScore(10, 4) == 14
            && Spiel.neuerRundenScore(0, 6) == 6
            && Spiel.neuerRundenScore(10, 1) == 0);

        pruefe("hatGewonnen", () -> {
            Spieler s = new Spieler("Alex");
            s.punkteDazu(99);
            boolean a = !Spiel.hatGewonnen(s, 100);
            s.punkteDazu(1);
            boolean b = Spiel.hatGewonnen(s, 100);
            return a && b;
        });

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
