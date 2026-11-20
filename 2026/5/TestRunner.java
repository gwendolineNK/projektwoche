/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt exakt dieselben 12 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 *
 * Bestehensgrenze laut Aufgabenstellung: mindestens 9 von 12 Tests,
 * darunter UNBEDINGT testNaechsteGeneration.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static boolean[][] blinker() {
        boolean[][] f = new boolean[5][5];
        f[2][1] = true;
        f[2][2] = true;
        f[2][3] = true;
        return f;
    }

    static boolean[][] block() {
        boolean[][] f = new boolean[4][4];
        f[0][0] = true;
        f[0][1] = true;
        f[1][0] = true;
        f[1][1] = true;
        return f;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Game of Life Light ===\n");

        // ---------- Generation ----------
        pruefe("testZaehleLebendeNachbarn", () -> {
            boolean[][] f = blinker();
            if (Generation.zaehleLebendeNachbarn(f, 2, 2) != 2) return false;
            if (Generation.zaehleLebendeNachbarn(f, 1, 2) != 3) return false;
            if (Generation.zaehleLebendeNachbarn(f, 3, 2) != 3) return false;
            if (Generation.zaehleLebendeNachbarn(f, 2, 1) != 1) return false;
            if (Generation.zaehleLebendeNachbarn(f, 1, 1) != 2) return false;
            if (Generation.zaehleLebendeNachbarn(f, 0, 0) != 0) return false;
            if (Generation.zaehleLebendeNachbarn(f, 2, 0) != 1) return false;
            if (Generation.zaehleLebendeNachbarn(f, 4, 4) != 0) return false;

            f = block();
            return Generation.zaehleLebendeNachbarn(f, 0, 0) == 3
                && Generation.zaehleLebendeNachbarn(f, 2, 2) == 1
                && Generation.zaehleLebendeNachbarn(f, 1, 2) == 2;
        });

        pruefe("testNaechsterZustand", () ->
               !Generation.naechsterZustand(true, 0)
            && !Generation.naechsterZustand(true, 1)
            && Generation.naechsterZustand(true, 2)
            && Generation.naechsterZustand(true, 3)
            && !Generation.naechsterZustand(true, 4)
            && !Generation.naechsterZustand(true, 8)
            && !Generation.naechsterZustand(false, 0)
            && !Generation.naechsterZustand(false, 2)
            && Generation.naechsterZustand(false, 3)
            && !Generation.naechsterZustand(false, 4)
            && !Generation.naechsterZustand(false, 8));

        pruefe("testGleich", () -> {
            if (!Generation.gleich(blinker(), blinker())) return false;
            if (!Generation.gleich(new boolean[3][3], new boolean[3][3])) return false;
            boolean[][] a = blinker();
            boolean[][] b = blinker();
            b[0][0] = true;
            if (Generation.gleich(a, b)) return false;
            return !Generation.gleich(new boolean[3][3], new boolean[4][4]);
        });

        // ---------- Spielfeld ----------
        pruefe("testKonstruktor", () -> {
            Spielfeld sf = new Spielfeld(5);
            if (sf.getN() != 5) return false;
            for (int z = 0; z < 5; z++)
                for (int s = 0; s < 5; s++)
                    if (sf.istLebendig(z, s)) return false;
            sf = new Spielfeld(10);
            return sf.getN() == 10 && !sf.istLebendig(9, 9);
        });

        pruefe("testSetzeZelleUndIstLebendig", () -> {
            Spielfeld sf = new Spielfeld(5);
            sf.setzeZelle(2, 3, true);
            if (!sf.istLebendig(2, 3) || sf.istLebendig(2, 2)) return false;
            sf.setzeZelle(2, 3, false);
            return !sf.istLebendig(2, 3);
        });

        pruefe("testImFeld", () -> {
            Spielfeld sf = new Spielfeld(5);
            return sf.imFeld(0, 0) && sf.imFeld(4, 4)
                && !sf.imFeld(-1, 0) && !sf.imFeld(0, -1)
                && !sf.imFeld(5, 0) && !sf.imFeld(0, 5);
        });

        pruefe("testZaehleLebende", () ->
               new Spielfeld(5).zaehleLebende() == 0
            && new Spielfeld(blinker()).zaehleLebende() == 3
            && new Spielfeld(block()).zaehleLebende() == 4);

        pruefe("testNaechsteGeneration  <-- PFLICHT!", () -> {
            Spielfeld sf = new Spielfeld(blinker());

            if (!sf.naechsteGeneration()) return false;
            if (!sf.istLebendig(1, 2) || !sf.istLebendig(2, 2) || !sf.istLebendig(3, 2)) return false;
            if (sf.istLebendig(2, 1) || sf.istLebendig(2, 3)) return false;
            if (sf.istLebendig(1, 1) || sf.istLebendig(0, 2) || sf.istLebendig(4, 2)) return false;
            if (sf.zaehleLebende() != 3) return false;

            if (!sf.naechsteGeneration()) return false;
            if (!sf.istLebendig(2, 1) || !sf.istLebendig(2, 2) || !sf.istLebendig(2, 3)) return false;
            if (sf.istLebendig(1, 2)) return false;
            return sf.zaehleLebende() == 3;
        });

        pruefe("testStabileGeneration", () -> {
            Spielfeld sf = new Spielfeld(block());
            if (sf.naechsteGeneration()) return false;
            if (!sf.istLebendig(0, 0) || !sf.istLebendig(0, 1)) return false;
            if (!sf.istLebendig(1, 0) || !sf.istLebendig(1, 1)) return false;
            if (sf.zaehleLebende() != 4) return false;

            sf = new Spielfeld(5);
            return !sf.naechsteGeneration() && sf.zaehleLebende() == 0;
        });

        // ---------- Spieler ----------
        pruefe("testSpielerNeu", () -> {
            Spieler s = new Spieler("Alex", 5);
            if (!"Alex".equals(s.getName())) return false;
            s = new Spieler("Toni", 7);
            return "Toni".equals(s.getName());
        });

        pruefe("testGetSpielfeld", () -> {
            Spieler s = new Spieler("Alex", 5);
            if (s.getSpielfeld() == null) return false;
            if (s.getSpielfeld().getN() != 5) return false;
            if (s.getSpielfeld().zaehleLebende() != 0) return false;
            s = new Spieler("Toni", 7);
            return s.getSpielfeld().getN() == 7 && s.getSpielfeld().zaehleLebende() == 0;
        });

        // ---------- Spiel ----------
        pruefe("testCheckEingabeMenu", () ->
               Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(-1) && !Spiel.checkEingabeMenu(10));

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
        if (bestanden >= 9) {
            System.out.println("(Bestehensgrenze 9/12 erreicht - testNaechsteGeneration muss dabei sein!)");
        } else {
            System.out.println("(Bestehensgrenze 9/12 noch NICHT erreicht.)");
        }
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
