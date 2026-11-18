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

    static Spielfeld testBrett() {
        return new Spielfeld(new int[] { 3, 1, 0, 2, 4, 0 });
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Sowing Light ===\n");

        // ---------- Zug ----------
        pruefe("testRichtungVon", () ->
               Zug.richtungVon(1) == 1 && Zug.richtungVon(2) == -1);

        pruefe("testLetzteMulde", () ->
               Zug.letzteMulde(2, 1, 3) == 5
            && Zug.letzteMulde(4, -1, 4) == 0
            && Zug.letzteMulde(0, 1, 1) == 1
            && Zug.letzteMulde(5, -1, 2) == 3
            && Zug.letzteMulde(4, 1, 4) == 8
            && Zug.letzteMulde(0, -1, 3) == -3);

        pruefe("testPasstInsFeld", () ->
               Zug.passtInsFeld(6, 0, 1, 3)
            && Zug.passtInsFeld(6, 4, -1, 4)
            && Zug.passtInsFeld(6, 2, 1, 3)
            && Zug.passtInsFeld(6, 2, -1, 2)
            && !Zug.passtInsFeld(6, 4, 1, 4)
            && !Zug.passtInsFeld(6, 0, -1, 1)
            && !Zug.passtInsFeld(6, 5, 1, 1)
            && !Zug.passtInsFeld(6, 2, -1, 3));

        // ---------- Spielfeld ----------
        pruefe("testKonstruktor", () -> {
            Spielfeld b = new Spielfeld(6, 2);
            if (b.getN() != 6) return false;
            for (int i = 0; i < 6; i++)
                if (b.getMulde(i) != 2) return false;
            b = new Spielfeld(12, 3);
            return b.getN() == 12 && b.getMulde(0) == 3 && b.getMulde(11) == 3;
        });

        pruefe("testSetMulde", () -> {
            Spielfeld b = new Spielfeld(6, 2);
            b.setMulde(2, 7);
            if (b.getMulde(2) != 7) return false;
            b.setMulde(5, 0);
            return b.getMulde(5) == 0 && b.getMulde(0) == 2;
        });

        pruefe("testZaehleSamen", () ->
               testBrett().zaehleSamen() == 10
            && new Spielfeld(6, 2).zaehleSamen() == 12
            && new Spielfeld(12, 3).zaehleSamen() == 36
            && new Spielfeld(new int[] { 0, 0, 0 }).zaehleSamen() == 0);

        pruefe("testIstZugGueltig  <-- PFLICHT!", () -> {
            Spielfeld b = testBrett();   // { 3, 1, 0, 2, 4, 0 }

            // Spieler 1, Richtung +1
            if (!b.istZugGueltig(0, 1)) return false;
            if (b.istZugGueltig(1, 1)) return false;   // letzte Mulde leer
            if (b.istZugGueltig(2, 1)) return false;   // Mulde leer
            if (b.istZugGueltig(3, 1)) return false;   // letzte Mulde leer
            if (b.istZugGueltig(4, 1)) return false;   // passt nicht aufs Brett
            if (b.istZugGueltig(5, 1)) return false;   // Mulde leer

            // Spieler 2, Richtung -1
            if (!b.istZugGueltig(1, 2)) return false;
            if (!b.istZugGueltig(3, 2)) return false;
            if (!b.istZugGueltig(4, 2)) return false;
            if (b.istZugGueltig(0, 2)) return false;   // passt nicht aufs Brett
            if (b.istZugGueltig(2, 2)) return false;   // Mulde leer
            if (b.istZugGueltig(5, 2)) return false;   // Mulde leer

            // ausserhalb des Brettes
            return !b.istZugGueltig(-1, 1) && !b.istZugGueltig(6, 1)
                && !b.istZugGueltig(-1, 2) && !b.istZugGueltig(6, 2);
        });

        pruefe("testFuehreZugAus", () -> {
            Spielfeld b = testBrett();
            if (b.fuehreZugAus(0, 1) != 3) return false;
            if (b.getMulde(0) != 0 || b.getMulde(1) != 2 || b.getMulde(2) != 1) return false;
            if (b.getMulde(3) != 3 || b.getMulde(4) != 4 || b.getMulde(5) != 0) return false;
            if (b.zaehleSamen() != 10) return false;

            b = testBrett();
            if (b.fuehreZugAus(4, 2) != 4) return false;
            if (b.getMulde(0) != 4 || b.getMulde(1) != 2 || b.getMulde(2) != 1) return false;
            if (b.getMulde(3) != 3 || b.getMulde(4) != 0 || b.getMulde(5) != 0) return false;
            return b.zaehleSamen() == 10;
        });

        pruefe("testHatZugMoeglichkeit", () -> {
            Spielfeld b = testBrett();
            if (!b.hatZugMoeglichkeit(1) || !b.hatZugMoeglichkeit(2)) return false;

            b = new Spielfeld(new int[] { 1, 3, 0, 0, 0, 0 });
            if (!b.hatZugMoeglichkeit(1) || b.hatZugMoeglichkeit(2)) return false;

            b = new Spielfeld(new int[] { 0, 0, 0, 0, 0, 2 });
            return !b.hatZugMoeglichkeit(1) && !b.hatZugMoeglichkeit(2);
        });

        // ---------- Spieler ----------
        pruefe("testSpielerNeu", () -> {
            Spieler s = new Spieler("Alex", 1);
            if (!"Alex".equals(s.getName()) || s.getNummer() != 1) return false;
            s = new Spieler("Toni", 2);
            return "Toni".equals(s.getName()) && s.getNummer() == 2;
        });

        pruefe("testGetRichtung", () ->
               new Spieler("Alex", 1).getRichtung() == 1
            && new Spieler("Toni", 2).getRichtung() == -1);

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
