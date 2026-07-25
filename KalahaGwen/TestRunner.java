/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld leer() {
        Spielfeld f = new Spielfeld();
        f.setMuldenFuerTest(new int[Spielfeld.MULDEN]);
        return f;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Kalaha ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getSpielernummer() == 1;
        });

        pruefe("Startbrett", () -> {
            Spielfeld f = new Spielfeld();
            return f.getMulde(0) == 4 && f.getMulde(6) == 0 && f.getMulde(13) == 0;
        });

        pruefe("kalahaIndex", () -> {
            Spielfeld f = new Spielfeld();
            return f.kalahaIndex(1) == 6 && f.kalahaIndex(2) == 13;
        });

        pruefe("getKalaha", () -> {
            Spielfeld f = leer();
            if (f.getKalaha(1) != 0) return false;
            f.getMulden()[6] = 5;
            return f.getKalaha(1) == 5;
        });

        pruefe("eigeneMulde", () -> {
            Spielfeld f = new Spielfeld();
            return f.eigeneMulde(0, 1) && f.eigeneMulde(5, 1)
                && !f.eigeneMulde(6, 1) && !f.eigeneMulde(7, 1)
                && f.eigeneMulde(7, 2) && f.eigeneMulde(12, 2)
                && !f.eigeneMulde(13, 2) && !f.eigeneMulde(0, 2);
        });

        pruefe("istZugGueltig", () -> {
            Spielfeld f = new Spielfeld();
            boolean ok = f.istZugGueltig(0, 1) && !f.istZugGueltig(7, 1);
            f.getMulden()[0] = 0;
            return ok && !f.istZugGueltig(0, 1);
        });

        pruefe("saee einfach", () -> {
            Spielfeld f = leer();
            f.getMulden()[2] = 3;
            int letzte = f.saee(2, 1);
            return f.getMulde(2) == 0 && f.getMulde(3) == 1 && f.getMulde(4) == 1
                && f.getMulde(5) == 1 && letzte == 5;
        });

        pruefe("saee ueberspringt Gegner-Kalaha", () -> {
            Spielfeld f = leer();
            f.getMulden()[5] = 10;
            int letzte = f.saee(5, 1);
            return f.getMulde(13) == 0 && f.getMulde(6) == 1
                && f.getMulde(0) == 1 && letzte == 2;
        });

        pruefe("letzteImEigenenKalaha", () -> {
            Spielfeld f = new Spielfeld();
            return f.letzteImEigenenKalaha(6, 1)
                && !f.letzteImEigenenKalaha(6, 2)
                && f.letzteImEigenenKalaha(13, 2);
        });

        pruefe("istSpielEnde", () -> {
            Spielfeld f = new Spielfeld();
            if (f.istSpielEnde()) return false;
            for (int i = 0; i <= 5; i++) f.getMulden()[i] = 0;
            return f.istSpielEnde();
        });

        pruefe("toString enthaelt 4", () -> new Spielfeld().toString().indexOf("4") >= 0);

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
