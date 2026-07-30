/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt dieselben Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static Sternenhimmel testHimmel() {
        Sternenhimmel h = new Sternenhimmel();
        char[][] s = {
            { 'A', 'A', 'B', 'C' },
            { 'B', 'C', 'A', 'D' },
            { 'C', 'D', 'B', 'A' },
            { 'D', 'A', 'C', 'B' }
        };
        h.setSterneFuerTest(s);
        return h;
    }

    static int zaehle(String t, char c) {
        int n = 0;
        for (int i = 0; i < t.length(); i++) if (t.charAt(i) == c) n++;
        return n;
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Sternwanderung ===\n");

        pruefe("Spieler: neu", () -> {
            Spieler s = new Spieler("Alex", 1, 0, 0);
            return "Alex".equals(s.getName()) && s.getNummer() == 1
                && s.getZeile() == 0 && s.getSpalte() == 0 && s.getPunkte() == 0;
        });

        pruefe("Spieler: setzePosition / punkteDazu", () -> {
            Spieler s = new Spieler("Alex", 1, 0, 0);
            s.setzePosition(3, 5);
            s.punkteDazu(7);
            s.punkteDazu(3);
            return s.getZeile() == 3 && s.getSpalte() == 5 && s.getPunkte() == 10;
        });

        pruefe("Computer: neu", () -> {
            Computer c = new Computer(2, 7, 7);
            return "Computer".equals(c.getName()) && c.getNummer() == 2
                && c.getZeile() == 7 && c.getSpalte() == 7;
        });

        pruefe("wert", () -> {
            Sternenhimmel h = new Sternenhimmel();
            return h.wert('A') == 1 && h.wert('D') == 4 && h.wert('H') == 8
                && h.wert(Sternenhimmel.LEER) == 0;
        });

        pruefe("imFeld", () -> {
            Sternenhimmel h = new Sternenhimmel();
            return h.imFeld(0, 0) && h.imFeld(7, 7)
                && !h.imFeld(-1, 0) && !h.imFeld(8, 0) && !h.imFeld(0, 8);
        });

        pruefe("jeder Stern A-H genau 8 Mal", () -> {
            Sternenhimmel h = new Sternenhimmel();
            int[] anzahl = new int[128];
            for (int z = 0; z < Sternenhimmel.GROESSE; z++)
                for (int s = 0; s < Sternenhimmel.GROESSE; s++)
                    anzahl[h.getSterne()[z][s]]++;
            for (char c = 'A'; c <= 'H'; c++) if (anzahl[c] != 8) return false;
            return true;
        });

        pruefe("zaehleSterne / istSpielEnde", () -> {
            Sternenhimmel h = new Sternenhimmel();
            if (h.zaehleSterne() != 64 || h.istSpielEnde()) return false;
            Sternenhimmel t = testHimmel();
            if (t.zaehleSterne() != 16) return false;
            for (int z = 0; z < 4; z++)
                for (int s = 0; s < 4; s++) t.getSterne()[z][s] = Sternenhimmel.LEER;
            return t.zaehleSterne() == 0 && t.istSpielEnde();
        });

        pruefe("getStern / istLeer", () -> {
            Sternenhimmel h = testHimmel();
            if (h.getStern(1, 3) != 'D' || h.istLeer(1, 3)) return false;
            h.getSterne()[1][3] = Sternenhimmel.LEER;
            return h.istLeer(1, 3) && !h.istLeer(9, 9);
        });

        pruefe("normiere (modulo mit negativen Zahlen)", () -> {
            Sternenhimmel h = new Sternenhimmel();
            return h.normiere(3, 8) == 3 && h.normiere(8, 8) == 0
                && h.normiere(-1, 8) == 7 && h.normiere(-9, 8) == 7
                && h.normiere(9, 8) == 1 && h.normiere(0, 4) == 0;
        });

        pruefe("zielZeile / zielSpalte", () -> {
            Sternenhimmel h = testHimmel();
            return h.zielZeile(0, 0, 1) == 3 && h.zielSpalte(0, 0, 1) == 3
                && h.zielZeile(3, 7, 2) == 1 && h.zielSpalte(3, 7, 2) == 1
                && h.zielZeile(2, 4, 3) == 2 && h.zielSpalte(2, 4, 3) == 1;
        });

        pruefe("schrittweite", () -> {
            Sternenhimmel h = testHimmel();
            if (h.schrittweite(1, 1) != 3 || h.schrittweite(0, 0) != 1) return false;
            h.getSterne()[0][0] = Sternenhimmel.LEER;
            return h.schrittweite(0, 0) == 1;
        });

        pruefe("zaehleGleicheNachbarn", () -> {
            Sternenhimmel h = testHimmel();
            return h.zaehleGleicheNachbarn(0, 0) == 1
                && h.zaehleGleicheNachbarn(1, 2) == 2
                && h.zaehleGleicheNachbarn(3, 0) == 1;
        });

        pruefe("zaehleGleicheNachbarn leer / ausserhalb", () -> {
            Sternenhimmel h = testHimmel();
            h.getSterne()[0][0] = Sternenhimmel.LEER;
            return h.zaehleGleicheNachbarn(0, 0) == 0
                && h.zaehleGleicheNachbarn(9, 9) == 0;
        });

        pruefe("sammle", () -> {
            Sternenhimmel h = testHimmel();
            return h.sammle(0, 0) == 2 && h.istLeer(0, 0) && h.zaehleSterne() == 15;
        });

        pruefe("sammle mit Bonus", () -> {
            Sternenhimmel h = testHimmel();
            return h.sammle(1, 2) == 3 && h.istLeer(1, 2);
        });

        pruefe("sammle leer / ausserhalb", () -> {
            Sternenhimmel h = testHimmel();
            h.sammle(0, 0);
            return h.sammle(0, 0) == 0 && h.sammle(9, 9) == 0;
        });

        pruefe("toString", () -> {
            Sternenhimmel h = testHimmel();
            Spieler s1 = new Spieler("Alex", 1, 0, 0);
            Spieler s2 = new Spieler("Mika", 2, 2, 2);
            String t = h.toString(s1, s2);
            if (zaehle(t, '.') != 0 || zaehle(t, 'A') != 4) return false;
            h.sammle(1, 2);
            String t2 = h.toString(s1, s2);
            return zaehle(t2, '.') == 1 && zaehle(t2, 'A') == 3;
        });

        pruefe("Computer waehleRichtung", () -> {
            Sternenhimmel h = testHimmel();
            Computer c = new Computer(2, 0, 0);
            return c.waehleRichtung(h, new Spieler("Alex", 1, 2, 2)) == 1;
        });

        pruefe("Computer waehleRichtung blockiert", () -> {
            Sternenhimmel h = testHimmel();
            Computer c = new Computer(2, 0, 0);
            return c.waehleRichtung(h, new Spieler("Alex", 1, 3, 0)) == 5;
        });

        pruefe("checkEingabeMenu", () ->
            Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2) && Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(4));

        pruefe("checkRichtung", () ->
            Spiel.checkRichtung(0) && Spiel.checkRichtung(7)
            && !Spiel.checkRichtung(-1) && !Spiel.checkRichtung(8));

        pruefe("auswertung", () -> {
            Spieler s1 = new Spieler("Alex", 1, 0, 0);
            Spieler s2 = new Spieler("Mika", 2, 7, 7);
            s1.punkteDazu(10);
            s2.punkteDazu(4);
            String e = Spiel.auswertung(s1, s2);
            s2.punkteDazu(6);
            return e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0
                && Spiel.auswertung(s1, s2).indexOf("Unentschieden") >= 0;
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
