/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt exakt dieselben 12 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 *
 * Bestehensgrenze laut Aufgabenstellung: mindestens 9 von 12 Tests,
 * darunter UNBEDINGT testVerschiebe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld testFeld() {
        int[][] feld = {
                { 2, 2, 0, 4 },
                { 0, 0, 0, 0 },
                { 4, 4, 4, 4 },
                { 2, 0, 2, 0 }
        };
        return new Spielfeld(feld);
    }

    static boolean zeileIst(Spielfeld sf, int zeile, int a, int b, int c, int d) {
        return sf.getStelle(zeile, 0) == a && sf.getStelle(zeile, 1) == b
            && sf.getStelle(zeile, 2) == c && sf.getStelle(zeile, 3) == d;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: 2048 Light ===\n");

        // ---------- Reihe ----------
        pruefe("testUmdrehen", () -> {
            int[] original = { 2, 4, 0, 8 };
            int[] g = Reihe.umdrehen(original);
            return g[0] == 8 && g[1] == 0 && g[2] == 4 && g[3] == 2
                && original[0] == 2 && original[3] == 8;
        });

        pruefe("testZusammenschieben", () -> {
            int[] r = Reihe.zusammenschieben(new int[] { 0, 2, 0, 4 });
            if (!(r[0] == 2 && r[1] == 4 && r[2] == 0 && r[3] == 0)) return false;
            r = Reihe.zusammenschieben(new int[] { 2, 4, 0, 0 });
            if (!(r[0] == 2 && r[1] == 4 && r[2] == 0)) return false;
            r = Reihe.zusammenschieben(new int[] { 0, 0, 0, 0 });
            if (!(r[0] == 0 && r[3] == 0)) return false;
            r = Reihe.zusammenschieben(new int[] { 0, 0, 0, 8 });
            if (!(r[0] == 8 && r[1] == 0)) return false;
            int[] original = { 0, 2, 0, 4 };
            Reihe.zusammenschieben(original);
            return original[0] == 0 && original[1] == 2;
        });

        pruefe("testVerschmelzen", () -> {
            int[] r = { 2, 2, 4, 0 };
            if (Reihe.verschmelzen(r) != 4) return false;
            if (!(r[0] == 4 && r[1] == 0 && r[2] == 4 && r[3] == 0)) return false;

            r = new int[] { 4, 4, 4, 4 };
            if (Reihe.verschmelzen(r) != 16) return false;
            if (!(r[0] == 8 && r[1] == 0 && r[2] == 8 && r[3] == 0)) return false;

            r = new int[] { 2, 2, 2, 0 };
            if (Reihe.verschmelzen(r) != 4) return false;
            if (!(r[0] == 4 && r[1] == 0 && r[2] == 2)) return false;

            r = new int[] { 2, 4, 8, 0 };
            if (Reihe.verschmelzen(r) != 0) return false;
            return r[0] == 2 && r[1] == 4 && r[2] == 8;
        });

        pruefe("testGleich", () ->
               Reihe.gleich(new int[] { 1, 2, 3 }, new int[] { 1, 2, 3 })
            && Reihe.gleich(new int[] { 0, 0 }, new int[] { 0, 0 })
            && !Reihe.gleich(new int[] { 1, 2, 3 }, new int[] { 1, 2, 4 })
            && !Reihe.gleich(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));

        // ---------- Spielfeld ----------
        pruefe("testKonstruktorUndZufaelligeKachel", () -> {
            for (int versuch = 0; versuch < 5; versuch++) {
                Spielfeld sf = new Spielfeld(4);
                if (sf.getN() != 4 || sf.getPunkte() != 0 || sf.zaehleLeere() != 14) return false;
                int zweien = 0;
                for (int z = 0; z < 4; z++)
                    for (int s = 0; s < 4; s++) {
                        int wert = sf.getStelle(z, s);
                        if (wert != 0 && wert != 2) return false;
                        if (wert == 2) zweien++;
                    }
                if (zweien != 2) return false;
            }
            int[][] feld = {
                    { 2, 4, 8, 16 }, { 2, 4, 8, 16 }, { 2, 4, 8, 0 }, { 2, 4, 8, 16 }
            };
            Spielfeld sf = new Spielfeld(feld);
            if (sf.zaehleLeere() != 1) return false;
            sf.zufaelligeKachel();
            return sf.getStelle(2, 3) == 2 && sf.zaehleLeere() == 0;
        });

        pruefe("testSetStelleUndZaehleLeere", () -> {
            int[][] feld = {
                    { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }
            };
            Spielfeld sf = new Spielfeld(feld);
            if (sf.zaehleLeere() != 16) return false;
            sf.setStelle(1, 2, 8);
            if (sf.getStelle(1, 2) != 8 || sf.zaehleLeere() != 15) return false;
            sf.setStelle(3, 3, 2048);
            return sf.getStelle(3, 3) == 2048 && sf.zaehleLeere() == 14;
        });

        pruefe("testVerschiebe  <-- PFLICHT!", () -> {
            // LINKS
            Spielfeld sf = testFeld();
            if (!sf.verschiebe(Spielfeld.LINKS)) return false;
            if (!zeileIst(sf, 0, 4, 4, 0, 0)) return false;
            if (!zeileIst(sf, 1, 0, 0, 0, 0)) return false;
            if (!zeileIst(sf, 2, 8, 8, 0, 0)) return false;
            if (!zeileIst(sf, 3, 4, 0, 0, 0)) return false;
            if (sf.getPunkte() != 24) return false;

            // RECHTS
            sf = testFeld();
            if (!sf.verschiebe(Spielfeld.RECHTS)) return false;
            if (!zeileIst(sf, 0, 0, 0, 4, 4)) return false;
            if (!zeileIst(sf, 2, 0, 0, 8, 8)) return false;
            if (!zeileIst(sf, 3, 0, 0, 0, 4)) return false;
            if (sf.getPunkte() != 24) return false;

            // OBEN
            sf = testFeld();
            if (!sf.verschiebe(Spielfeld.OBEN)) return false;
            if (!zeileIst(sf, 0, 2, 2, 4, 8)) return false;
            if (!zeileIst(sf, 1, 4, 4, 2, 0)) return false;
            if (!zeileIst(sf, 2, 2, 0, 0, 0)) return false;
            if (!zeileIst(sf, 3, 0, 0, 0, 0)) return false;
            if (sf.getPunkte() != 8) return false;

            // UNTEN
            sf = testFeld();
            if (!sf.verschiebe(Spielfeld.UNTEN)) return false;
            if (!zeileIst(sf, 0, 0, 0, 0, 0)) return false;
            if (!zeileIst(sf, 1, 2, 0, 0, 0)) return false;
            if (!zeileIst(sf, 2, 4, 2, 4, 0)) return false;
            if (!zeileIst(sf, 3, 2, 4, 2, 8)) return false;
            if (sf.getPunkte() != 8) return false;

            // keine Veraenderung
            int[][] feld = {
                    { 2, 4, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }
            };
            sf = new Spielfeld(feld);
            if (sf.verschiebe(Spielfeld.LINKS)) return false;
            return zeileIst(sf, 0, 2, 4, 0, 0) && sf.getPunkte() == 0;
        });

        pruefe("testHatGewonnen", () -> {
            if (testFeld().hatGewonnen()) return false;
            int[][] feld = {
                    { 2, 4, 8, 16 }, { 32, 64, 128, 256 },
                    { 512, 1024, 2048, 2 }, { 4, 8, 16, 32 }
            };
            return new Spielfeld(feld).hatGewonnen();
        });

        pruefe("testZugMoeglich", () -> {
            if (!testFeld().zugMoeglich()) return false;
            int[][] mitPaar = {
                    { 2, 2, 4, 8 }, { 4, 8, 16, 32 }, { 2, 4, 8, 16 }, { 4, 8, 16, 32 }
            };
            if (!new Spielfeld(mitPaar).zugMoeglich()) return false;
            int[][] ohnePaar = {
                    { 2, 4, 2, 4 }, { 4, 2, 4, 2 }, { 2, 4, 2, 4 }, { 4, 2, 4, 2 }
            };
            return !new Spielfeld(ohnePaar).zugMoeglich();
        });

        // ---------- Spieler ----------
        pruefe("testSpieler", () -> {
            Spieler s = new Spieler("Alex", 4);
            if (!"Alex".equals(s.getName())) return false;
            if (s.getSpielfeld() == null) return false;
            if (s.getSpielfeld().getN() != 4 || s.getSpielfeld().zaehleLeere() != 14) return false;
            s = new Spieler("Toni", 5);
            return "Toni".equals(s.getName()) && s.getSpielfeld().getN() == 5
                && s.getSpielfeld().zaehleLeere() == 23;
        });

        // ---------- Spiel ----------
        pruefe("testCheckEingabeMenu", () ->
               Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(-1) && !Spiel.checkEingabeMenu(10));

        pruefe("testCheckRichtungUndRichtungAus", () ->
               Spiel.checkRichtung("l") && Spiel.checkRichtung("r")
            && Spiel.checkRichtung("o") && Spiel.checkRichtung("u")
            && !Spiel.checkRichtung("x") && !Spiel.checkRichtung("links")
            && !Spiel.checkRichtung("")
            && Spiel.richtungAus("l") == Spielfeld.LINKS
            && Spiel.richtungAus("r") == Spielfeld.RECHTS
            && Spiel.richtungAus("o") == Spielfeld.OBEN
            && Spiel.richtungAus("u") == Spielfeld.UNTEN
            && Spiel.richtungAus("x") == -1);

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
        if (bestanden >= 9) {
            System.out.println("(Bestehensgrenze 9/12 erreicht - testVerschiebe muss dabei sein!)");
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
