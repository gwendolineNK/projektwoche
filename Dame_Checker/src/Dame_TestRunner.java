/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld leer() {
        Spielfeld f = new Spielfeld();
        f.setFeldFuerTest(new int[8][8]);
        return f;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Dame ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getSpielernummer() == 1;
        });

        pruefe("gegner", () -> {
            Spielfeld f = new Spielfeld();
            return f.gegner(1) == 2 && f.gegner(2) == 1;
        });

        pruefe("imFeld", () -> {
            Spielfeld f = new Spielfeld();
            return f.imFeld(0, 0) && f.imFeld(7, 7)
                && !f.imFeld(-1, 0) && !f.imFeld(8, 0) && !f.imFeld(0, 8);
        });

        pruefe("richtung", () -> {
            Spielfeld f = new Spielfeld();
            return f.richtung(1) == 1 && f.richtung(2) == -1;
        });

        pruefe("Startaufstellung 12:12", () -> {
            Spielfeld f = new Spielfeld();
            return f.zaehleSteine(1) == 12 && f.zaehleSteine(2) == 12;
        });

        pruefe("getZelle", () -> {
            Spielfeld f = leer();
            if (f.getZelle(0, 0) != 0) return false;
            f.getFeld()[0][0] = 1;
            return f.getZelle(0, 0) == 1;
        });

        pruefe("einfacher Zug (Spieler 1)", () -> {
            Spielfeld f = leer();
            f.getFeld()[2][2] = 1;
            boolean ok = f.istEinfacherZugGueltig(2, 2, 3, 3, 1)
                      && !f.istEinfacherZugGueltig(2, 2, 3, 2, 1)
                      && !f.istEinfacherZugGueltig(2, 2, 1, 1, 1);
            f.getFeld()[3][3] = 2;
            return ok && !f.istEinfacherZugGueltig(2, 2, 3, 3, 1);
        });

        pruefe("einfacher Zug (Spieler 2)", () -> {
            Spielfeld f = leer();
            f.getFeld()[5][5] = 2;
            return f.istEinfacherZugGueltig(5, 5, 4, 4, 2)
                && !f.istEinfacherZugGueltig(5, 5, 6, 6, 2);
        });

        pruefe("Schlagzug", () -> {
            Spielfeld f = leer();
            f.getFeld()[2][2] = 1;
            f.getFeld()[3][3] = 2;
            boolean a = f.istSchlagzugGueltig(2, 2, 4, 4, 1);
            f.getFeld()[3][3] = 0;
            boolean b = !f.istSchlagzugGueltig(2, 2, 4, 4, 1);
            f.getFeld()[3][3] = 1;
            boolean c = !f.istSchlagzugGueltig(2, 2, 4, 4, 1);
            return a && b && c;
        });

        pruefe("fuehreZug einfach", () -> {
            Spielfeld f = leer();
            f.getFeld()[2][2] = 1;
            f.fuehreZug(2, 2, 3, 3, 1);
            return f.getZelle(3, 3) == 1 && f.getZelle(2, 2) == 0;
        });

        pruefe("fuehreZug Schlag", () -> {
            Spielfeld f = leer();
            f.getFeld()[2][2] = 1;
            f.getFeld()[3][3] = 2;
            f.fuehreZug(2, 2, 4, 4, 1);
            return f.getZelle(4, 4) == 1 && f.getZelle(2, 2) == 0 && f.getZelle(3, 3) == 0;
        });

        pruefe("hatGewonnen", () -> {
            Spielfeld f = leer();
            f.getFeld()[0][0] = 1;
            return f.hatGewonnen(1) && !f.hatGewonnen(2);
        });

        pruefe("toString", () -> {
            Spielfeld f = new Spielfeld();
            String t = f.toString();
            return zaehle(t, '.') == 40 && t.indexOf("X") >= 0 && t.indexOf("O") >= 0;
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
