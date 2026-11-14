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

    static Spielbrett testBrett() {
        int[][] feld = {
                { 0, 0, 0, 0, 0, 0 },
                { 0, 0, 5, 0, 0, 0 },
                { 0, 0, 2, 0, 0, 0 },
                { 0, 0, -3, 0, 0, 0 },
                { 1, 0, 0, 4, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };
        return new Spielbrett(feld);
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Opala Light ===\n");

        // ---------- Figur ----------
        pruefe("testTypUndBesitzer", () ->
               Figur.typ(0) == 0 && Figur.typ(1) == 1 && Figur.typ(4) == 4
            && Figur.typ(-4) == 4 && Figur.typ(-6) == 6
            && Figur.besitzer(0) == 0 && Figur.besitzer(3) == 1
            && Figur.besitzer(6) == 1 && Figur.besitzer(-3) == 2
            && Figur.besitzer(-1) == 2);

        pruefe("testIstGross", () ->
               Figur.istGross(2) && Figur.istGross(4) && Figur.istGross(6)
            && Figur.istGross(-2) && Figur.istGross(-6)
            && !Figur.istGross(1) && !Figur.istGross(3) && !Figur.istGross(5)
            && !Figur.istGross(-1) && !Figur.istGross(-5) && !Figur.istGross(0));

        pruefe("testZugarten", () ->
               Figur.ziehtGerade(1) && Figur.ziehtGerade(2) && Figur.ziehtGerade(5)
            && Figur.ziehtGerade(6) && Figur.ziehtGerade(-1) && Figur.ziehtGerade(-6)
            && !Figur.ziehtGerade(3) && !Figur.ziehtGerade(4)
            && !Figur.ziehtGerade(-4) && !Figur.ziehtGerade(0)
            && Figur.ziehtDiagonal(3) && Figur.ziehtDiagonal(4) && Figur.ziehtDiagonal(5)
            && Figur.ziehtDiagonal(6) && Figur.ziehtDiagonal(-3) && Figur.ziehtDiagonal(-5)
            && !Figur.ziehtDiagonal(1) && !Figur.ziehtDiagonal(2)
            && !Figur.ziehtDiagonal(-2) && !Figur.ziehtDiagonal(0));

        // ---------- Spieler ----------
        pruefe("testSpielerNeu", () -> {
            Spieler s = new Spieler("Alex", 1);
            if (!"Alex".equals(s.getName()) || s.getNummer() != 1) return false;
            s = new Spieler("Toni", 2);
            return "Toni".equals(s.getName()) && s.getNummer() == 2;
        });

        pruefe("testGetGegner", () ->
               new Spieler("Alex", 1).getGegner() == 2
            && new Spieler("Toni", 2).getGegner() == 1);

        // ---------- Spielbrett ----------
        pruefe("testGrundaufstellung", () -> {
            Spielbrett b = new Spielbrett();
            if (b.getN() != 6) return false;
            for (int spalte = 0; spalte < 6; spalte++) {
                if (b.getStelle(5, spalte) != spalte + 1) return false;
                if (b.getStelle(0, spalte) != -(spalte + 1)) return false;
            }
            for (int zeile = 1; zeile <= 4; zeile++)
                for (int spalte = 0; spalte < 6; spalte++)
                    if (b.getStelle(zeile, spalte) != 0) return false;
            return true;
        });

        pruefe("testSetStelleUndImBrett", () -> {
            Spielbrett b = new Spielbrett();
            b.setStelle(2, 3, 6);
            if (b.getStelle(2, 3) != 6) return false;
            b.setStelle(0, 0, -4);
            if (b.getStelle(0, 0) != -4) return false;
            return b.imBrett(0, 0) && b.imBrett(5, 5)
                && !b.imBrett(-1, 0) && !b.imBrett(0, -1)
                && !b.imBrett(6, 0) && !b.imBrett(0, 6);
        });

        pruefe("testIstZugGueltig  <-- PFLICHT!", () -> {
            Spielbrett b = testBrett();

            if (b.istZugGueltig(-1, 0, 0, 0, 1)) return false;
            if (b.istZugGueltig(0, -1, 0, 0, 1)) return false;
            if (b.istZugGueltig(2, 2, 6, 2, 1)) return false;
            if (b.istZugGueltig(2, 2, 2, 6, 1)) return false;

            if (b.istZugGueltig(2, 2, 2, 2, 1)) return false;   // Start == Ziel
            if (b.istZugGueltig(0, 0, 1, 1, 1)) return false;   // Startfeld leer
            if (b.istZugGueltig(3, 2, 2, 2, 1)) return false;   // Startfeld dem Gegner
            if (b.istZugGueltig(2, 2, 1, 2, 1)) return false;   // Ziel eigene Figur

            // Grosses Quadrat (2,2)
            if (!b.istZugGueltig(2, 2, 2, 5, 1)) return false;
            if (!b.istZugGueltig(2, 2, 2, 0, 1)) return false;
            if (!b.istZugGueltig(2, 2, 3, 2, 1)) return false;  // schlagen
            if (b.istZugGueltig(2, 2, 4, 2, 1)) return false;   // Weg blockiert
            if (b.istZugGueltig(2, 2, 4, 4, 1)) return false;   // nicht diagonal

            // Kleiner Kreis (1,2)
            if (!b.istZugGueltig(1, 2, 0, 1, 1)) return false;
            if (!b.istZugGueltig(1, 2, 1, 3, 1)) return false;
            if (b.istZugGueltig(1, 2, 1, 4, 1)) return false;
            if (b.istZugGueltig(1, 2, 3, 4, 1)) return false;

            // Kleines Quadrat (4,0)
            if (!b.istZugGueltig(4, 0, 3, 0, 1)) return false;
            if (b.istZugGueltig(4, 0, 2, 0, 1)) return false;
            if (b.istZugGueltig(4, 0, 3, 1, 1)) return false;

            // Grosses Dreieck (4,3)
            if (!b.istZugGueltig(4, 3, 2, 5, 1)) return false;
            if (!b.istZugGueltig(4, 3, 3, 2, 1)) return false;  // schlagen
            if (b.istZugGueltig(4, 3, 2, 1, 1)) return false;   // Weg blockiert
            if (b.istZugGueltig(4, 3, 4, 5, 1)) return false;   // nicht gerade
            if (b.istZugGueltig(4, 3, 2, 4, 1)) return false;   // Springerzug

            // Spieler 2
            if (!b.istZugGueltig(3, 2, 4, 1, 2)) return false;
            if (!b.istZugGueltig(3, 2, 4, 3, 2)) return false;  // schlagen
            if (b.istZugGueltig(3, 2, 2, 2, 2)) return false;
            if (b.istZugGueltig(3, 2, 5, 0, 2)) return false;
            return !b.istZugGueltig(3, 2, 4, 1, 1);             // falscher Spieler
        });

        pruefe("testFuehreZugAus", () -> {
            Spielbrett b = testBrett();
            if (b.fuehreZugAus(4, 0, 3, 0) != 0) return false;
            if (b.getStelle(3, 0) != 1 || b.getStelle(4, 0) != 0) return false;

            b = testBrett();
            if (b.fuehreZugAus(2, 2, 3, 2) != -3) return false;
            return b.getStelle(3, 2) == 2 && b.getStelle(2, 2) == 0;
        });

        pruefe("testZaehleFiguren", () -> {
            Spielbrett b = testBrett();
            if (b.zaehleFiguren(1) != 4 || b.zaehleFiguren(2) != 1) return false;
            b = new Spielbrett();
            return b.zaehleFiguren(1) == 6 && b.zaehleFiguren(2) == 6;
        });

        pruefe("testHatGrundlinieErreicht", () -> {
            Spielbrett b = testBrett();
            if (b.hatGrundlinieErreicht(1) || b.hatGrundlinieErreicht(2)) return false;

            b = new Spielbrett();
            if (b.hatGrundlinieErreicht(1) || b.hatGrundlinieErreicht(2)) return false;

            int[][] feld1 = {
                    { 0, 0, 0, 0, 3, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }
            };
            b = new Spielbrett(feld1);
            if (!b.hatGrundlinieErreicht(1) || b.hatGrundlinieErreicht(2)) return false;

            int[][] feld2 = {
                    { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, -2, 0, 0, 0, 0 }
            };
            b = new Spielbrett(feld2);
            return !b.hatGrundlinieErreicht(1) && b.hatGrundlinieErreicht(2);
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
