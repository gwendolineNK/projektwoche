/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Spielfeld testBrett() {
        Spielfeld f = new Spielfeld();
        char[][] r = {
            { 'A', 'A', 'B', 'B' },
            { 'A', 'C', 'C', 'B' },
            { 'D', 'D', 'C', 'B' },
            { 'D', 'A', 'A', 'A' }
        };
        f.setFelderFuerTest(r, new int[4][4]);
        return f;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Runenkampf ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1);
            return "Alex".equals(s.getName()) && s.getNummer() == 1 && s.hatRotation();
        });

        pruefe("Spieler: rotationVerbrauchen", () -> {
            Spieler s = new Spieler("Alex", 1);
            s.rotationVerbrauchen();
            return !s.hatRotation();
        });

        pruefe("Computer: neu", () -> {
            Computer c = new Computer(2);
            return "Computer".equals(c.getName()) && c.getNummer() == 2;
        });

        pruefe("runenWert", () -> {
            Spielfeld f = new Spielfeld();
            return f.runenWert('A') == 1 && f.runenWert('D') == 4 && f.runenWert('H') == 8;
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

        pruefe("jede Rune A-H genau 8 Mal", () -> {
            Spielfeld f = new Spielfeld();
            int[] anzahl = new int[128];
            for (int z = 0; z < Spielfeld.GROESSE; z++)
                for (int s = 0; s < Spielfeld.GROESSE; s++)
                    anzahl[f.getRunen()[z][s]]++;
            for (char c = 'A'; c <= 'H'; c++) if (anzahl[c] != 8) return false;
            return true;
        });

        pruefe("getRune / getBesitzer", () -> {
            Spielfeld f = testBrett();
            if (f.getRune(0, 2) != 'B' || f.getBesitzer(0, 2) != 0) return false;
            f.getBesitzerFeld()[0][2] = 1;
            return f.getBesitzer(0, 2) == 1;
        });

        pruefe("istNeutral", () -> {
            Spielfeld f = testBrett();
            boolean ok = f.istNeutral(0, 0) && !f.istNeutral(-1, 0) && !f.istNeutral(4, 0);
            f.getBesitzerFeld()[0][0] = 2;
            return ok && !f.istNeutral(0, 0);
        });

        pruefe("zaehleNeutrale / istSpielEnde", () -> {
            Spielfeld f = testBrett();
            if (f.zaehleNeutrale() != 16 || f.istSpielEnde()) return false;
            for (int z = 0; z < 4; z++)
                for (int s = 0; s < 4; s++) f.getBesitzerFeld()[z][s] = 1;
            return f.zaehleNeutrale() == 0 && f.istSpielEnde();
        });

        pruefe("gebietGroesse", () -> {
            Spielfeld f = testBrett();
            return f.gebietGroesse(0, 0) == 3
                && f.gebietGroesse(0, 2) == 4
                && f.gebietGroesse(1, 1) == 3
                && f.gebietGroesse(2, 0) == 3
                && f.gebietGroesse(3, 1) == 3;
        });

        pruefe("gebietGroesse nicht neutral / ausserhalb", () -> {
            Spielfeld f = testBrett();
            f.getBesitzerFeld()[0][0] = 1;
            return f.gebietGroesse(0, 0) == 0 && f.gebietGroesse(9, 9) == 0;
        });

        pruefe("beansprucheGebiet", () -> {
            Spielfeld f = testBrett();
            if (f.beansprucheGebiet(0, 2, 1) != 4) return false;
            return f.getBesitzer(0, 2) == 1 && f.getBesitzer(0, 3) == 1
                && f.getBesitzer(1, 3) == 1 && f.getBesitzer(2, 3) == 1
                && f.getBesitzer(0, 0) == 0 && f.zaehleNeutrale() == 12;
        });

        pruefe("beansprucheGebiet ungueltig", () -> {
            Spielfeld f = testBrett();
            f.beansprucheGebiet(0, 2, 1);
            return f.beansprucheGebiet(0, 2, 2) == 0
                && f.beansprucheGebiet(9, 9, 1) == 0
                && f.getBesitzer(0, 2) == 1;
        });

        pruefe("punkte", () -> {
            Spielfeld f = testBrett();
            f.beansprucheGebiet(0, 2, 1);
            f.beansprucheGebiet(2, 0, 2);
            return f.punkte(1) == 8 && f.punkte(2) == 12;
        });

        pruefe("rotiereZeile", () -> {
            Spielfeld f = testBrett();
            f.getBesitzerFeld()[0][0] = 1;
            f.rotiereZeile(0);
            return f.getRune(0, 0) == 'B' && f.getRune(0, 1) == 'A'
                && f.getRune(0, 2) == 'A' && f.getRune(0, 3) == 'B'
                && f.getBesitzer(0, 1) == 1 && f.getBesitzer(0, 0) == 0;
        });

        pruefe("rotiereZeile ungueltig", () -> {
            Spielfeld f = testBrett();
            f.rotiereZeile(9);
            return f.getRune(0, 0) == 'A';
        });

        pruefe("toString Besitzer-Zeichen", () -> {
            Spielfeld f = testBrett();
            if (zaehle(f.toString(), '-') != 16) return false;
            f.beansprucheGebiet(0, 2, 1);
            return zaehle(f.toString(), '-') == 12;
        });

        pruefe("Computer waehleZug", () -> {
            int[] zug = new Computer(2).waehleZug(testBrett());
            return zug[0] == 2 && zug[1] == 0;
        });

        pruefe("Computer waehleZug ohne neutrales Feld", () -> {
            Spielfeld f = testBrett();
            for (int z = 0; z < 4; z++)
                for (int s = 0; s < 4; s++) f.getBesitzerFeld()[z][s] = 1;
            int[] zug = new Computer(2).waehleZug(f);
            return zug[0] == -1 && zug[1] == -1;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2) && Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(4));

        pruefe("auswertung", () -> {
            Spieler s1 = new Spieler("Alex", 1);
            Spieler s2 = new Spieler("Mika", 2);
            Spielfeld f = testBrett();
            f.beansprucheGebiet(2, 0, 2);
            String e = Spiel.auswertung(s1, s2, f);
            Spielfeld g = testBrett();
            g.beansprucheGebiet(0, 0, 1);
            g.beansprucheGebiet(3, 1, 2);
            return e.indexOf("Mika") >= 0 && e.indexOf("gewonnen") >= 0
                && Spiel.auswertung(s1, s2, g).indexOf("Unentschieden") >= 0;
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
