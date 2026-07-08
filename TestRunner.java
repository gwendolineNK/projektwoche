/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben 15 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld mitteBoard() {
        Spielfeld f = new Spielfeld(3);
        boolean[][] karte = new boolean[3][3];
        karte[1][1] = true;
        f.setSchatzkarteFuerTest(karte);
        return f;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Schatzsuche ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getSpielernummer() == 1 && s.getPunkte() == 0;
        });
        pruefe("Spieler: punktDazu", () -> {
            Spieler s = new Spieler("Alex", 1);
            s.punktDazu(); s.punktDazu();
            return s.getPunkte() == 2;
        });

        pruefe("getN", () -> new Spielfeld(5).getN() == 5);

        pruefe("genau n Schaetze", () -> {
            for (int n : new int[] { 3, 4, 6 }) {
                Spielfeld f = new Spielfeld(n);
                int c = 0;
                for (int z = 0; z < n; z++)
                    for (int s = 0; s < n; s++)
                        if (f.getSchatzkarte()[z][s]) c++;
                if (c != n) return false;
            }
            return true;
        });

        pruefe("treffer", () -> {
            Spielfeld f = mitteBoard();
            return f.treffer(1, 1) && !f.treffer(0, 0);
        });

        pruefe("gueltigeEingabe", () -> {
            Spielfeld f = mitteBoard();
            boolean ok = f.gueltigeEingabe(0, 0) && !f.gueltigeEingabe(-1, 0)
                      && !f.gueltigeEingabe(3, 0) && !f.gueltigeEingabe(0, 3);
            f.deckeAuf(0, 0, new Spieler("A", 1));
            return ok && !f.gueltigeEingabe(0, 0);
        });

        pruefe("angrenzend", () -> {
            Spielfeld f = mitteBoard();
            return f.angrenzend(0, 1) && f.angrenzend(1, 0)
                && !f.angrenzend(0, 0) && !f.angrenzend(1, 1);
        });

        pruefe("deckeAuf: Treffer", () -> {
            Spielfeld f = mitteBoard();
            String r = f.deckeAuf(1, 1, new Spieler("Alex", 1));
            return Spielfeld.TREFFER.equals(r) && f.getOffenesSpielfeld()[1][1] == 1;
        });
        pruefe("deckeAuf: Nah", () -> {
            Spielfeld f = mitteBoard();
            String r = f.deckeAuf(0, 1, new Spieler("A", 1));
            return Spielfeld.NAH.equals(r) && f.getOffenesSpielfeld()[0][1] == -2;
        });
        pruefe("deckeAuf: Fern", () -> {
            Spielfeld f = mitteBoard();
            String r = f.deckeAuf(0, 0, new Spieler("A", 1));
            return Spielfeld.FERN.equals(r) && f.getOffenesSpielfeld()[0][0] == -1;
        });

        pruefe("toString: verdeckt = 16 _", () -> zaehle(new Spielfeld(4).toString(), '_') == 16);
        pruefe("toString: zeigt S1", () -> {
            Spielfeld f = mitteBoard();
            f.deckeAuf(1, 1, new Spieler("Alex", 1));
            return f.toString().indexOf("S1") >= 0;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3));

        pruefe("weiterspielen", () -> {
            Spieler s1 = new Spieler("A", 1);
            Spieler s2 = new Spieler("B", 2);
            s1.punktDazu();
            boolean a = Spiel.weiterspielen(s1, s2, 4);
            s2.punktDazu(); s2.punktDazu(); s2.punktDazu();
            return a && !Spiel.weiterspielen(s1, s2, 4);
        });

        pruefe("auswertung", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Mika", 2);
            s1.punktDazu(); s1.punktDazu(); s2.punktDazu();
            String e = Spiel.auswertung(s1, s2);
            Spieler g1 = new Spieler("A", 1);
            Spieler g2 = new Spieler("B", 2);
            g1.punktDazu(); g2.punktDazu();
            return e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0
                && Spiel.auswertung(g1, g2).indexOf("Unentschieden") >= 0;
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