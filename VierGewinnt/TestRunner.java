/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Vier gewinnt ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getSpielernummer() == 1;
        });

        pruefe("getZelle leer", () -> {
            Spielfeld f = new Spielfeld();
            return f.getZelle(0, 0) == 0 && f.getZelle(5, 6) == 0;
        });

        pruefe("spalteGueltig", () -> {
            Spielfeld f = new Spielfeld();
            boolean ok = f.spalteGueltig(0) && f.spalteGueltig(6)
                      && !f.spalteGueltig(-1) && !f.spalteGueltig(7);
            f.getFeld()[0][2] = 1;
            return ok && !f.spalteGueltig(2);
        });

        pruefe("einwerfen", () -> {
            Spielfeld f = new Spielfeld();
            boolean a = f.einwerfen(3, 1) == 5 && f.getZelle(5, 3) == 1;
            boolean b = f.einwerfen(3, 2) == 4 && f.getZelle(4, 3) == 2;
            return a && b;
        });

        pruefe("istVoll", () -> {
            Spielfeld f = new Spielfeld();
            if (f.istVoll()) return false;
            for (int z = 0; z < Spielfeld.ZEILEN; z++)
                for (int s = 0; s < Spielfeld.SPALTEN; s++)
                    f.getFeld()[z][s] = 1;
            return f.istVoll();
        });

        pruefe("waagerecht", () -> {
            Spielfeld f = new Spielfeld();
            for (int s = 0; s < 4; s++) f.getFeld()[5][s] = 1;
            return f.vierInReiheWaagerecht(1) && !f.vierInReiheWaagerecht(2);
        });

        pruefe("senkrecht", () -> {
            Spielfeld f = new Spielfeld();
            for (int z = 2; z <= 5; z++) f.getFeld()[z][3] = 1;
            return f.vierInReiheSenkrecht(1) && !f.vierInReiheSenkrecht(2);
        });

        pruefe("diagonal unten-rechts", () -> {
            Spielfeld f = new Spielfeld();
            f.getFeld()[2][0] = 1; f.getFeld()[3][1] = 1;
            f.getFeld()[4][2] = 1; f.getFeld()[5][3] = 1;
            return f.vierInReiheDiagonal(1);
        });

        pruefe("diagonal unten-links", () -> {
            Spielfeld f = new Spielfeld();
            f.getFeld()[2][6] = 2; f.getFeld()[3][5] = 2;
            f.getFeld()[4][4] = 2; f.getFeld()[5][3] = 2;
            return f.vierInReiheDiagonal(2);
        });

        pruefe("hatGewonnen", () -> {
            Spielfeld f = new Spielfeld();
            for (int s = 0; s < 4; s++) f.getFeld()[0][s] = 1;
            return f.hatGewonnen(1) && !f.hatGewonnen(2);
        });

        pruefe("toString leer = 42 Punkte", () -> zaehle(new Spielfeld().toString(), '.') == 42);

        pruefe("toString mit Stein", () -> {
            Spielfeld f = new Spielfeld();
            f.einwerfen(3, 1);
            String t = f.toString();
            return zaehle(t, '.') == 41 && t.indexOf("X") >= 0;
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