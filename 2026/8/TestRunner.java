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
        b.setzeFigur(2, 2, s1.getFigur(0));
        b.setzeFigur(2, 5, s2.getFigur(4));
        b.setzeFigur(4, 4, s1.getFigur(4));
        b.setzeFigur(3, 2, s2.getFigur(0));
        return b;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Metamorphose Light ===\n");

        // ---------- Figur ----------
        pruefe("testFigurNeu", () -> {
            Spieler s = new Spieler("Alex", 1);
            Figur f = s.getFigur(0);
            return f != null && f.getTyp() == Figur.QUADRAT && f.getBesitzer() == s
                && f.istImSpiel() && f.getZeile() == -1 && f.getSpalte() == -1;
        });

        pruefe("testVerwandle", () -> {
            Spieler s = new Spieler("Alex", 1);
            Figur f = s.getFigur(0);
            if (f.getTyp() != Figur.QUADRAT || !f.ziehtGerade() || f.ziehtDiagonal()) return false;
            f.verwandle();
            if (f.getTyp() != Figur.KREIS || f.ziehtGerade() || !f.ziehtDiagonal()) return false;
            f.verwandle();
            if (f.getTyp() != Figur.QUADRAT || !f.ziehtGerade()) return false;
            Figur k = s.getFigur(4);
            if (k.getTyp() != Figur.KREIS) return false;
            k.verwandle();
            return k.getTyp() == Figur.QUADRAT;
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
                if (s.getFigur(i).getBesitzer() != s) return false;
                int erwartet = (i < Spieler.ANZAHL_QUADRATE) ? Figur.QUADRAT : Figur.KREIS;
                if (s.getFigur(i).getTyp() != erwartet) return false;
            }
            Spieler t = new Spieler("Toni", 2);
            return t.getNummer() == 2 && t.getFigur(6).getBesitzer() == t;
        });

        pruefe("testZaehleFigurenImSpiel", () -> {
            Spieler s = new Spieler("Alex", 1);
            if (s.zaehleFigurenImSpiel() != 7) return false;
            s.getFigur(0).schlagen();
            if (s.zaehleFigurenImSpiel() != 6) return false;
            s.getFigur(5).schlagen();
            return s.zaehleFigurenImSpiel() == 5;
        });

        // ---------- Spielbrett ----------
        pruefe("testKonstruktorUndMauern", () -> {
            Spielbrett b = new Spielbrett();
            if (b.getN() != 7) return false;
            if (!b.istMauer(1, 1) || !b.istMauer(1, 5) || !b.istMauer(3, 3)) return false;
            if (!b.istMauer(5, 1) || !b.istMauer(5, 5)) return false;
            if (b.istMauer(0, 0) || b.istMauer(2, 2) || b.istMauer(6, 6)) return false;
            for (int z = 0; z < 7; z++)
                for (int s = 0; s < 7; s++)
                    if (b.getFigur(z, s) != null) return false;
            if (b.istFrei(1, 1) || !b.istFrei(0, 0)) return false;
            return b.imBrett(0, 0) && b.imBrett(6, 6)
                && !b.imBrett(-1, 0) && !b.imBrett(0, -1)
                && !b.imBrett(7, 0) && !b.imBrett(0, 7);
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
            for (int s = 0; s < 7; s++) {
                if (b.getFigur(6, s) != s1.getFigur(s)) return false;
                if (b.getFigur(0, s) != s2.getFigur(s)) return false;
            }
            if (b.getFigur(6, 0).getTyp() != Figur.QUADRAT) return false;
            if (b.getFigur(6, 3).getTyp() != Figur.QUADRAT) return false;
            if (b.getFigur(6, 4).getTyp() != Figur.KREIS) return false;
            if (b.getFigur(6, 6).getTyp() != Figur.KREIS) return false;
            if (b.getFigur(2, 2) != null) return false;
            return s1.getFigur(0).getZeile() == 6 && s1.getFigur(0).getSpalte() == 0
                && s2.getFigur(4).getZeile() == 0 && s2.getFigur(4).getSpalte() == 4;
        });

        pruefe("testIstZugGueltig  <-- PFLICHT!", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);

            if (b.istZugGueltig(-1, 0, 0, 0, s1)) return false;
            if (b.istZugGueltig(2, 2, 7, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 2, 7, s1)) return false;
            if (b.istZugGueltig(2, 2, 2, 2, s1)) return false;
            if (b.istZugGueltig(0, 0, 1, 0, s1)) return false;
            if (b.istZugGueltig(3, 2, 2, 2, s1)) return false;

            // Quadrat von Spieler 1 auf (2,2)
            if (!b.istZugGueltig(2, 2, 2, 0, s1)) return false;
            if (!b.istZugGueltig(2, 2, 2, 5, s1)) return false;
            if (!b.istZugGueltig(2, 2, 0, 2, s1)) return false;
            if (!b.istZugGueltig(2, 2, 3, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 2, 6, s1)) return false;
            if (b.istZugGueltig(2, 2, 4, 2, s1)) return false;
            if (b.istZugGueltig(2, 2, 3, 3, s1)) return false;

            // Kreis von Spieler 1 auf (4,4)
            if (!b.istZugGueltig(4, 4, 3, 5, s1)) return false;
            if (!b.istZugGueltig(4, 4, 2, 6, s1)) return false;
            if (!b.istZugGueltig(4, 4, 5, 3, s1)) return false;
            if (!b.istZugGueltig(4, 4, 6, 2, s1)) return false;
            if (b.istZugGueltig(4, 4, 3, 3, s1)) return false;   // Mauer als Ziel
            if (b.istZugGueltig(4, 4, 2, 2, s1)) return false;   // Weg durch Mauer
            if (b.istZugGueltig(4, 4, 5, 5, s1)) return false;
            if (b.istZugGueltig(4, 4, 6, 6, s1)) return false;
            if (b.istZugGueltig(4, 4, 4, 6, s1)) return false;

            // Spieler 2
            if (!b.istZugGueltig(3, 2, 3, 0, s2)) return false;
            if (!b.istZugGueltig(3, 2, 2, 2, s2)) return false;
            if (!b.istZugGueltig(3, 2, 4, 2, s2)) return false;
            if (b.istZugGueltig(3, 2, 3, 4, s2)) return false;   // Weg durch Mauer
            if (b.istZugGueltig(3, 2, 2, 1, s2)) return false;
            if (b.istZugGueltig(3, 2, 3, 0, s1)) return false;   // falscher Spieler

            if (!b.istZugGueltig(2, 5, 1, 4, s2)) return false;
            if (!b.istZugGueltig(2, 5, 4, 3, s2)) return false;
            return !b.istZugGueltig(2, 5, 2, 2, s2);
        });

        pruefe("testFuehreZugAusVerwandelt", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);

            Figur quadrat = s1.getFigur(0);
            if (b.fuehreZugAus(2, 2, 2, 0) != null) return false;
            if (b.getFigur(2, 0) != quadrat || b.getFigur(2, 2) != null) return false;
            if (quadrat.getZeile() != 2 || quadrat.getSpalte() != 0) return false;
            if (quadrat.getTyp() != Figur.KREIS || !quadrat.ziehtDiagonal()) return false;

            s1 = new Spieler("Alex", 1);
            s2 = new Spieler("Toni", 2);
            b = testBrett(s1, s2);

            Figur zieher = s1.getFigur(0);
            Figur gegner = s2.getFigur(0);
            if (b.fuehreZugAus(2, 2, 3, 2) != gegner) return false;
            if (b.getFigur(3, 2) != zieher || b.getFigur(2, 2) != null) return false;
            if (gegner.istImSpiel() || s2.zaehleFigurenImSpiel() != 6) return false;
            return zieher.getTyp() == Figur.KREIS;
        });

        pruefe("testHatZugMoeglichkeit", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Toni", 2);
            Spielbrett b = testBrett(s1, s2);
            if (!b.hatZugMoeglichkeit(s1) || !b.hatZugMoeglichkeit(s2)) return false;

            Spieler allein = new Spieler("Solo", 1);
            Spielbrett leer = new Spielbrett();
            leer.setzeFigur(0, 0, allein.getFigur(4));   // Kreis, durch Mauer (1,1) eingesperrt
            if (leer.hatZugMoeglichkeit(allein)) return false;

            Spieler zweiter = new Spieler("Solo2", 1);
            Spielbrett leer2 = new Spielbrett();
            leer2.setzeFigur(0, 0, zweiter.getFigur(0));  // Quadrat kann ziehen
            return leer2.hatZugMoeglichkeit(zweiter);
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
