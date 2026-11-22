/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt exakt dieselben 12 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 *
 * Bestehensgrenze laut Aufgabenstellung: mindestens 9 von 12 Tests,
 * darunter UNBEDINGT testIstZugGueltig.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielbrett testBrett(Spieler s1, Spieler s2) {
        Spielbrett b = new Spielbrett();
        b.setzeFigur(1, 2, s1.getFigur(4));
        b.setzeFigur(2, 2, s1.getFigur(1));
        b.setzeFigur(3, 2, s2.getFigur(2));
        b.setzeFigur(4, 0, s1.getFigur(0));
        b.setzeFigur(4, 3, s1.getFigur(3));
        return b;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Opala Light (objektorientiert) ===\n");

        // ---------- Figur ----------
        pruefe("testFigurNeu", () -> {
            Spieler s = new Spieler("Alex", 1);
            Figur f = s.getFigur(3);
            return f != null && f.getTyp() == 4 && f.getBesitzer() == s
                && f.istImSpiel() && f.getZeile() == -1 && f.getSpalte() == -1;
        });

        pruefe("testFigurEigenschaften", () -> {
            Spieler s = new Spieler("Alex", 1);
            boolean[] gross    = { false, true, false, true, false, true };
            boolean[] gerade   = { true, true, false, false, true, true };
            boolean[] diagonal = { false, false, true, true, true, true };
            for (int i = 0; i < 6; i++) {
                if (s.getFigur(i).istGross() != gross[i]) return false;
                if (s.getFigur(i).ziehtGerade() != gerade[i]) return false;
                if (s.getFigur(i).ziehtDiagonal() != diagonal[i]) return false;
            }
            return true;
        });

        pruefe("testSetzePositionUndSchlagen", () -> {
            Spieler s = new Spieler("Alex", 1);
            Figur f = s.getFigur(0);
            f.setzePosition(2, 3);
            if (f.getZeile() != 2 || f.getSpalte() != 3 || !f.istImSpiel()) return false;
            f.schlagen();
            return !f.istImSpiel() && f.getZeile() == -1 && f.getSpalte() == -1;
        });

        // ---------- Spieler ----------
        pruefe("testSpielerNeu", () -> {
            Spieler s = new Spieler("Alex", 1);
            if (!"Alex".equals(s.getName()) || s.getNummer() != 1) return false;
            for (int i = 0; i < Spieler.ANZAHL_FIGUREN; i++) {
                if (s.getFigur(i) == null) return false;
                if (s.getFigur(i).getTyp() != i + 1) return false;
                if (s.getFigur(i).getBesitzer() != s) return false;
            }
            Spieler t = new Spieler("Toni", 2);
            return "Toni".equals(t.getName()) && t.getNummer() == 2
                && t.getFigur(0).getBesitzer() == t;
        });

        pruefe("testZaehleFigurenImSpiel", () -> {
            Spieler s = new Spieler("Alex", 1);
            if (s.zaehleFigurenImSpiel() != 6) return false;
            s.getFigur(0).schlagen();
            if (s.zaehleFigurenImSpiel() != 5) return false;
            s.getFigur(3).schlagen();
            return s.zaehleFigurenImSpiel() == 4;
        });

        // ---------- Spielbrett ----------
        pruefe("testKonstruktorUndImBrett", () -> {
            Spielbrett b = new Spielbrett();
            if (b.getN() != 6) return false;
            for (int z = 0; z < 6; z++)
                for (int s = 0; s < 6; s++)
                    if (b.getFigur(z, s) != null) return false;
            return b.imBrett(0, 0) && b.imBrett(5, 5)
                && !b.imBrett(-1, 0) && !b.imBrett(0, -1)
                && !b.imBrett(6, 0) && !b.imBrett(0, 6);
        });

        pruefe("testSetzeFigurUndIstFrei", () -> {
            Spieler s = new Spieler("Alex", 1);
            Spielbrett b = new Spielbrett();
            Figur f = s.getFigur(2);
            if (!b.istFrei(2, 3)) return false;
            b.setzeFigur(2, 3, f);
            if (b.getFigur(2, 3) != f || b.istFrei(2, 3)) return false;
            if (f.getZeile() != 2 || f.getSpalte() != 3) return false;
            b.setzeFigur(2, 3, null);
            return b.istFrei(2, 3) && b.getFigur(2, 3) == null;
        });

        pruefe("testGrundaufstellung", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = new Spielbrett();
            b.grundaufstellung(s1, s2);
            for (int s = 0; s < 6; s++) {
                if (b.getFigur(5, s) != s1.getFigur(s)) return false;
                if (b.getFigur(0, s) != s2.getFigur(s)) return false;
                if (b.getFigur(5, s).getTyp() != s + 1) return false;
            }
            if (b.getFigur(2, 2) != null || b.getFigur(3, 0) != null) return false;
            return s1.getFigur(0).getZeile() == 5 && s1.getFigur(0).getSpalte() == 0
                && s2.getFigur(3).getZeile() == 0 && s2.getFigur(3).getSpalte() == 3;
        });

        pruefe("testIstZugGueltig  <-- PFLICHT!", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);

            if (b.istZugGueltig(-1, 0, 0, 0, s1)) return false;
            if (b.istZugGueltig(0, -1, 0, 0, s1)) return false;
            if (b.istZugGueltig(2, 2, 6, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 2, 6, s1)) return false;

            if (b.istZugGueltig(2, 2, 2, 2, s1)) return false;   // Start == Ziel
            if (b.istZugGueltig(0, 0, 1, 1, s1)) return false;   // Startfeld leer
            if (b.istZugGueltig(3, 2, 2, 2, s1)) return false;   // gehoert dem Gegner
            if (b.istZugGueltig(2, 2, 1, 2, s1)) return false;   // Ziel eigene Figur

            // Grosses Quadrat (2,2)
            if (!b.istZugGueltig(2, 2, 2, 5, s1)) return false;
            if (!b.istZugGueltig(2, 2, 2, 0, s1)) return false;
            if (!b.istZugGueltig(2, 2, 3, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 4, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 4, 4, s1)) return false;

            // Kleiner Kreis (1,2)
            if (!b.istZugGueltig(1, 2, 0, 1, s1)) return false;
            if (!b.istZugGueltig(1, 2, 1, 3, s1)) return false;
            if (b.istZugGueltig(1, 2, 1, 4, s1)) return false;
            if (b.istZugGueltig(1, 2, 3, 4, s1)) return false;

            // Kleines Quadrat (4,0)
            if (!b.istZugGueltig(4, 0, 3, 0, s1)) return false;
            if (b.istZugGueltig(4, 0, 2, 0, s1)) return false;
            if (b.istZugGueltig(4, 0, 3, 1, s1)) return false;

            // Grosses Dreieck (4,3)
            if (!b.istZugGueltig(4, 3, 2, 5, s1)) return false;
            if (!b.istZugGueltig(4, 3, 3, 2, s1)) return false;
            if (b.istZugGueltig(4, 3, 2, 1, s1)) return false;
            if (b.istZugGueltig(4, 3, 4, 5, s1)) return false;
            if (b.istZugGueltig(4, 3, 2, 4, s1)) return false;

            // Spieler 2
            if (!b.istZugGueltig(3, 2, 4, 1, s2)) return false;
            if (!b.istZugGueltig(3, 2, 4, 3, s2)) return false;
            if (b.istZugGueltig(3, 2, 2, 2, s2)) return false;
            if (b.istZugGueltig(3, 2, 5, 0, s2)) return false;
            return !b.istZugGueltig(3, 2, 4, 1, s1);
        });

        pruefe("testFuehreZugAus", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);

            Figur kleinesQuadrat = s1.getFigur(0);
            if (b.fuehreZugAus(4, 0, 3, 0) != null) return false;
            if (b.getFigur(3, 0) != kleinesQuadrat || b.getFigur(4, 0) != null) return false;
            if (kleinesQuadrat.getZeile() != 3 || kleinesQuadrat.getSpalte() != 0) return false;

            s1 = new Spieler("Alex", 1);
            s2 = new Spieler("Toni", 2);
            b = testBrett(s1, s2);

            Figur grossesQuadrat = s1.getFigur(1);
            Figur gegner = s2.getFigur(2);

            if (b.fuehreZugAus(2, 2, 3, 2) != gegner) return false;
            if (b.getFigur(3, 2) != grossesQuadrat || b.getFigur(2, 2) != null) return false;
            return !gegner.istImSpiel() && s2.zaehleFigurenImSpiel() == 5;
        });

        pruefe("testHatGrundlinieErreicht", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);

            if (b.hatGrundlinieErreicht(s1) || b.hatGrundlinieErreicht(s2)) return false;

            b.setzeFigur(0, 3, s1.getFigur(5));
            if (!b.hatGrundlinieErreicht(s1) || b.hatGrundlinieErreicht(s2)) return false;

            b.setzeFigur(5, 1, s2.getFigur(5));
            return b.hatGrundlinieErreicht(s2);
        });

        // ---------- Spiel ----------
        pruefe("testCheckEingabeMenu", () ->
               Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(-1) && !Spiel.checkEingabeMenu(10));

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
        if (bestanden >= 9) {
            System.out.println("(Bestehensgrenze 9/12 erreicht - testIstZugGueltig muss dabei sein!)");
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
