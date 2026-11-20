/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt exakt dieselben 12 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 *
 * Bestehensgrenze laut Aufgabenstellung: mindestens 9 von 12 Tests,
 * darunter UNBEDINGT testStellungKamSchonVor.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static boolean[][] einStein(int z, int s) {
        boolean[][] f = new boolean[3][3];
        f[z][s] = true;
        return f;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Puan-Puan Light ===\n");

        // ---------- Stellung ----------
        pruefe("testDrehe90", () -> {
            boolean[][] g = Stellung.drehe90(einStein(0, 0));
            if (!g[0][2] || g[0][0] || g[2][0] || g[2][2]) return false;

            g = Stellung.drehe90(einStein(0, 2));
            if (!g[2][2]) return false;

            boolean[][] oben = new boolean[3][3];
            oben[0][0] = true;
            oben[0][1] = true;
            g = Stellung.drehe90(oben);
            if (!g[0][2] || !g[1][2] || g[2][2]) return false;
            if (!oben[0][0] || !oben[0][1] || oben[0][2]) return false;

            boolean[][] start = einStein(0, 1);
            boolean[][] rund = Stellung.drehe90(
                    Stellung.drehe90(Stellung.drehe90(Stellung.drehe90(start))));
            return Stellung.gleich(start, rund);
        });

        pruefe("testGleich", () ->
               Stellung.gleich(einStein(0, 0), einStein(0, 0))
            && Stellung.gleich(new boolean[3][3], new boolean[3][3])
            && !Stellung.gleich(einStein(0, 0), einStein(0, 2))
            && !Stellung.gleich(einStein(0, 0), new boolean[3][3])
            && !Stellung.gleich(new boolean[3][3], new boolean[4][4]));

        pruefe("testPuanGleich", () -> {
            boolean[][] ecke = einStein(0, 0);
            if (!Stellung.puanGleich(ecke, einStein(0, 0))) return false;
            if (!Stellung.puanGleich(ecke, einStein(0, 2))) return false;
            if (!Stellung.puanGleich(ecke, einStein(2, 2))) return false;
            if (!Stellung.puanGleich(ecke, einStein(2, 0))) return false;
            if (Stellung.puanGleich(ecke, einStein(0, 1))) return false;
            if (Stellung.puanGleich(ecke, einStein(1, 1))) return false;
            if (!Stellung.puanGleich(einStein(0, 1), einStein(1, 2))) return false;
            if (!Stellung.puanGleich(einStein(0, 1), einStein(2, 1))) return false;
            if (!Stellung.puanGleich(new boolean[3][3], new boolean[3][3])) return false;
            return !Stellung.puanGleich(new boolean[3][3], ecke);
        });

        // ---------- Spielfeld ----------
        pruefe("testKonstruktor", () -> {
            Spielfeld b = new Spielfeld(3, 100);
            if (b.getN() != 3 || b.zaehleSteine() != 0 || b.getAnzahlVerlauf() != 0) return false;
            for (int z = 0; z < 3; z++)
                for (int s = 0; s < 3; s++)
                    if (b.istBesetzt(z, s)) return false;
            b = new Spielfeld(5, 50);
            return b.getN() == 5 && !b.istBesetzt(4, 4);
        });

        pruefe("testZugAusfuehren", () -> {
            Spielfeld b = new Spielfeld(3, 100);
            if (!b.zugAusfuehren(0, 0)) return false;          // Stein gesetzt
            if (!b.istBesetzt(0, 0) || b.zaehleSteine() != 1) return false;
            if (b.zugAusfuehren(0, 0)) return false;           // Stein weggenommen
            if (b.istBesetzt(0, 0) || b.zaehleSteine() != 0) return false;
            b.zugAusfuehren(1, 1);
            b.zugAusfuehren(2, 2);
            return b.zaehleSteine() == 2;
        });

        pruefe("testImFeld", () -> {
            Spielfeld b = new Spielfeld(3, 100);
            return b.imFeld(0, 0) && b.imFeld(2, 2)
                && !b.imFeld(-1, 0) && !b.imFeld(0, -1)
                && !b.imFeld(3, 0) && !b.imFeld(0, 3);
        });

        pruefe("testZaehleSteine", () -> {
            if (new Spielfeld(3, 100).zaehleSteine() != 0) return false;
            if (new Spielfeld(einStein(1, 1)).zaehleSteine() != 1) return false;
            boolean[][] drei = new boolean[3][3];
            drei[0][0] = true;
            drei[1][1] = true;
            drei[2][2] = true;
            return new Spielfeld(drei).zaehleSteine() == 3;
        });

        pruefe("testMerkeStellung", () -> {
            Spielfeld b = new Spielfeld(3, 100);
            if (b.getAnzahlVerlauf() != 0) return false;
            b.merkeStellung();
            if (b.getAnzahlVerlauf() != 1) return false;
            b.zugAusfuehren(0, 0);
            b.merkeStellung();
            return b.getAnzahlVerlauf() == 2;
        });

        pruefe("testStellungKamSchonVor  <-- PFLICHT!", () -> {
            Spielfeld b = new Spielfeld(3, 100);
            if (b.stellungKamSchonVor()) return false;

            b.merkeStellung();                 // leere Stellung
            b.zugAusfuehren(0, 0);
            if (b.stellungKamSchonVor()) return false;

            b.merkeStellung();                 // Ecke
            b.zugAusfuehren(0, 0);             // wieder leer
            if (!b.stellungKamSchonVor()) return false;

            // gedrehte Stellung
            b = new Spielfeld(3, 100);
            b.zugAusfuehren(0, 0);
            b.merkeStellung();
            b.zugAusfuehren(0, 0);
            b.zugAusfuehren(0, 2);
            if (!b.stellungKamSchonVor()) return false;

            // keine Uebereinstimmung
            b = new Spielfeld(3, 100);
            b.zugAusfuehren(0, 0);
            b.merkeStellung();
            b.zugAusfuehren(0, 0);
            b.zugAusfuehren(1, 1);
            return !b.stellungKamSchonVor();
        });

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

        // ---------- Spiel ----------
        pruefe("testCheckEingabeMenu", () ->
               Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(-1) && !Spiel.checkEingabeMenu(10));

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
        if (bestanden >= 9) {
            System.out.println("(Bestehensgrenze 9/12 erreicht - testStellungKamSchonVor muss dabei sein!)");
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
