/**
 * TestRunner - Alternative zu JUnit, falls JUnit 5 in Eclipse nicht laeuft.
 *
 * Ausfuehren: Rechtsklick auf TestRunner.java -> Run As -> Java Application.
 * Es werden dieselben 16 Pruefungen wie in UnitTests.java ausgefuehrt und
 * am Ende ein Ergebnis "bestanden/16" ausgegeben. KEINE Bibliothek noetig.
 *
 * Diese Datei ist NUR zum Testen. Sie gehoert nicht zur Abgabe.
 */
public class TestRunner {

    static int bestanden = 0;
    static int gesamt = 0;

    public static void main(String[] args) {
        System.out.println("=== TestRunner: Memory Plus ===\n");

        // ---------- Spieler ----------
        pruefe("Spieler: neu -> Name/Punkte/Versuche", () -> {
            Spieler s = new Spieler("Alex");
            return "Alex".equals(s.getName()) && s.getPunkte() == 0 && s.getVersuche() == 0;
        });
        pruefe("Spieler: punktDazu zaehlt", () -> {
            Spieler s = new Spieler("Alex");
            s.punktDazu(); s.punktDazu();
            return s.getPunkte() == 2;
        });
        pruefe("Spieler: versuchDazu zaehlt", () -> {
            Spieler s = new Spieler("Mika");
            s.versuchDazu();
            return s.getVersuche() == 1;
        });

        // ---------- Memory: Feld ----------
        pruefe("Memory: Feldgroesse 4x6", () -> {
            Memory m = new Memory();
            return m.getFeld().length == 4 && m.getFeld()[0].length == 6;
        });
        pruefe("Memory: Feld enthaelt A-L und a-l genau einmal", () -> {
            Memory m = new Memory();
            int[] anzahl = new int[128];
            for (int z = 0; z < m.getFeld().length; z++)
                for (int s = 0; s < m.getFeld()[z].length; s++)
                    anzahl[m.getFeld()[z][s]]++;
            for (char c = 'A'; c <= 'L'; c++) if (anzahl[c] != 1) return false;
            for (char c = 'a'; c <= 'l'; c++) if (anzahl[c] != 1) return false;
            return true;
        });

        // ---------- passenZusammen ----------
        pruefe("passenZusammen: passende Paare -> true", () -> {
            Memory m = new Memory();
            return m.passenZusammen('A', 'a') && m.passenZusammen('a', 'A')
                && m.passenZusammen('b', 'B') && m.passenZusammen('L', 'l');
        });
        pruefe("passenZusammen: nicht passend -> false", () -> {
            Memory m = new Memory();
            return !m.passenZusammen('A', 'b') && !m.passenZusammen('A', 'A')
                && !m.passenZusammen('a', 'c');
        });

        // ---------- gueltigeEingabe ----------
        pruefe("gueltigeEingabe: frisches Feld", () -> {
            Memory m = new Memory();
            return m.gueltigeEingabe(0, 0) && m.gueltigeEingabe(3, 5)
                && !m.gueltigeEingabe(-1, 0) && !m.gueltigeEingabe(4, 0)
                && !m.gueltigeEingabe(0, 6) && !m.gueltigeEingabe(0, -1);
        });
        pruefe("gueltigeEingabe: nach Aufdecken", () -> {
            Memory m = new Memory();
            m.aufdecken(1, 2);
            return !m.gueltigeEingabe(1, 2) && m.gueltigeEingabe(1, 3);
        });

        // ---------- aufdecken / getZeichen / allesAufgedeckt ----------
        pruefe("getZeichen: liefert Feldwert", () -> {
            Memory m = new Memory();
            return m.getZeichen(2, 3) == m.getFeld()[2][3];
        });
        pruefe("allesAufgedeckt: false -> true", () -> {
            Memory m = new Memory();
            if (m.allesAufgedeckt()) return false;
            for (int z = 0; z < Memory.ZEILEN; z++)
                for (int s = 0; s < Memory.SPALTEN; s++)
                    m.aufdecken(z, s);
            return m.allesAufgedeckt();
        });

        // ---------- toString ----------
        pruefe("toString: frisches Feld = 24 Striche", () -> {
            Memory m = new Memory();
            return zaehle(m.toString(), '-') == 24;
        });
        pruefe("toString: nach Aufdecken = 23 Striche + Zeichen sichtbar", () -> {
            Memory m = new Memory();
            m.aufdecken(0, 0);
            String t = m.toString();
            return zaehle(t, '-') == 23 && t.indexOf(m.getZeichen(0, 0)) >= 0;
        });

        // ---------- Main ----------
        pruefe("checkEingabeMenu: 1/2 true, sonst false", () -> {
            return Main.checkEingabeMenu(1) && Main.checkEingabeMenu(2)
                && !Main.checkEingabeMenu(0) && !Main.checkEingabeMenu(3)
                && !Main.checkEingabeMenu(-1);
        });
        pruefe("auswertung: Sieg Spieler 1", () -> {
            Spieler s1 = new Spieler("Alex");
            Spieler s2 = new Spieler("Mika");
            s1.punktDazu(); s1.punktDazu(); s1.punktDazu();
            s2.punktDazu();
            String e = Main.auswertung(s1, s2);
            return e.indexOf("Alex") >= 0 && e.indexOf("gewonnen") >= 0;
        });
        pruefe("auswertung: unentschieden", () -> {
            Spieler s1 = new Spieler("Alex");
            Spieler s2 = new Spieler("Mika");
            s1.punktDazu(); s2.punktDazu();
            return Main.auswertung(s1, s2).indexOf("unentschieden") >= 0;
        });

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
    }

    // Ein kleiner Test-Rahmen ohne Bibliothek.
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

    static int zaehle(String text, char c) {
        int n = 0;
        for (int i = 0; i < text.length(); i++)
            if (text.charAt(i) == c) n++;
        return n;
    }
}
