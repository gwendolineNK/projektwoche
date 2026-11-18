/**
 * TestRunner - Alternative zu JUnit (Run As -> Java Application).
 * Fuehrt exakt dieselben 12 Pruefungen wie UnitTests.java aus, ohne Bibliothek.
 * Diese Datei gehoert NICHT zur Abgabe.
 *
 * Bestehensgrenze laut Aufgabenstellung: mindestens 9 von 12 Tests,
 * darunter UNBEDINGT testZaehleSOSMit.
 */
public class TestRunner {

    static int bestanden = 0, gesamt = 0;

    static char[] sosos() {
        return new char[] { 'S', 'O', 'S', 'O', 'S' };
    }

    static char[] mitteSOS() {
        return new char[] { '.', 'S', 'O', 'S', '.' };
    }

    public static void main(String[] args) {
        System.out.println("=== TestRunner: SOS Light ===\n");

        // ---------- Muster ----------
        pruefe("testIstSOS", () -> {
            char[] feld = sosos();
            if (!Muster.istSOS(feld, 0)) return false;
            if (Muster.istSOS(feld, 1)) return false;
            if (!Muster.istSOS(feld, 2)) return false;
            if (Muster.istSOS(feld, 3)) return false;
            if (Muster.istSOS(feld, 4)) return false;
            if (Muster.istSOS(feld, 5)) return false;
            if (Muster.istSOS(feld, -1)) return false;
            if (Muster.istSOS(feld, -3)) return false;
            char[] f2 = { 'S', 'O', 'O', 'S', '.' };
            return !Muster.istSOS(f2, 0) && !Muster.istSOS(f2, 1);
        });

        pruefe("testZaehleSOS", () ->
               Muster.zaehleSOS(sosos()) == 2
            && Muster.zaehleSOS(mitteSOS()) == 1
            && Muster.zaehleSOS(new char[] { 'S', 'O', 'O', 'S', '.' }) == 0
            && Muster.zaehleSOS(new char[] { '.', '.', '.' }) == 0
            && Muster.zaehleSOS(new char[] { 'S', 'O', 'S', 'O', 'S', 'O', 'S' }) == 3);

        pruefe("testZaehleSOSMit  <-- PFLICHT!", () -> {
            char[] feld = sosos();
            if (Muster.zaehleSOSMit(feld, 0) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 1) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 2) != 2) return false;
            if (Muster.zaehleSOSMit(feld, 3) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 4) != 1) return false;

            feld = mitteSOS();
            if (Muster.zaehleSOSMit(feld, 0) != 0) return false;
            if (Muster.zaehleSOSMit(feld, 1) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 2) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 3) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 4) != 0) return false;

            feld = new char[] { 'S', 'O', 'S', 'O', 'S', 'O', 'S' };
            if (Muster.zaehleSOSMit(feld, 4) != 2) return false;
            if (Muster.zaehleSOSMit(feld, 3) != 1) return false;
            if (Muster.zaehleSOSMit(feld, 6) != 1) return false;

            return Muster.zaehleSOSMit(new char[] { '.', '.', '.' }, 1) == 0;
        });

        // ---------- Spielfeld ----------
        pruefe("testKonstruktor", () -> {
            Spielfeld sf = new Spielfeld(5);
            if (sf.getN() != 5) return false;
            for (int i = 0; i < 5; i++)
                if (sf.getStelle(i) != Muster.LEER) return false;
            sf = new Spielfeld(12);
            return sf.getN() == 12 && sf.getStelle(11) == Muster.LEER;
        });

        pruefe("testSetStelle", () -> {
            Spielfeld sf = new Spielfeld(5);
            sf.setStelle(0, Muster.S);
            if (sf.getStelle(0) != 'S') return false;
            sf.setStelle(3, Muster.O);
            return sf.getStelle(3) == 'O' && sf.getStelle(1) == Muster.LEER;
        });

        pruefe("testGueltigeEingabe", () -> {
            Spielfeld sf = new Spielfeld(5);
            if (!sf.gueltigeEingabe(0) || !sf.gueltigeEingabe(4)) return false;
            if (sf.gueltigeEingabe(-1) || sf.gueltigeEingabe(5)) return false;
            if (sf.gueltigeEingabe(100)) return false;
            sf.setStelle(2, Muster.S);
            return !sf.gueltigeEingabe(2) && sf.gueltigeEingabe(3);
        });

        pruefe("testZaehleFreie", () -> {
            Spielfeld sf = new Spielfeld(5);
            if (sf.zaehleFreie() != 5) return false;
            sf.setStelle(1, Muster.S);
            if (sf.zaehleFreie() != 4) return false;
            if (new Spielfeld(new char[] { 'S', 'O', '.', '.', 'S' }).zaehleFreie() != 2) return false;
            return new Spielfeld(new char[] { 'S', 'O', 'S' }).zaehleFreie() == 0;
        });

        pruefe("testIstVoll", () ->
               !new Spielfeld(5).istVoll()
            && !new Spielfeld(new char[] { 'S', '.', 'S' }).istVoll()
            && new Spielfeld(new char[] { 'S', 'O', 'S' }).istVoll()
            && new Spielfeld(new char[] { 'O', 'O' }).istVoll());

        // ---------- Spieler ----------
        pruefe("testSpielerNeu", () -> {
            Spieler s = new Spieler("Alex");
            if (!"Alex".equals(s.getName()) || s.getPunkte() != 0) return false;
            s = new Spieler("Toni");
            return "Toni".equals(s.getName()) && s.getPunkte() == 0;
        });

        pruefe("testPunkteDazu", () -> {
            Spieler s = new Spieler("Alex");
            s.punkteDazu(2);
            if (s.getPunkte() != 2) return false;
            s.punkteDazu(1);
            if (s.getPunkte() != 3) return false;
            s.punkteDazu(0);
            return s.getPunkte() == 3;
        });

        // ---------- Spiel ----------
        pruefe("testCheckEingabeMenu", () ->
               Spiel.checkEingabeMenu(1) && Spiel.checkEingabeMenu(2)
            && !Spiel.checkEingabeMenu(0) && !Spiel.checkEingabeMenu(3)
            && !Spiel.checkEingabeMenu(-1) && !Spiel.checkEingabeMenu(10));

        pruefe("testCheckBuchstabeUndBuchstabeAus", () ->
               Spiel.checkBuchstabe("S") && Spiel.checkBuchstabe("s")
            && Spiel.checkBuchstabe("O") && Spiel.checkBuchstabe("o")
            && !Spiel.checkBuchstabe("x") && !Spiel.checkBuchstabe("SO")
            && !Spiel.checkBuchstabe("") && !Spiel.checkBuchstabe("0")
            && Spiel.buchstabeAus("S") == Muster.S
            && Spiel.buchstabeAus("s") == Muster.S
            && Spiel.buchstabeAus("O") == Muster.O
            && Spiel.buchstabeAus("o") == Muster.O
            && Spiel.buchstabeAus("x") == Muster.LEER);

        System.out.println("\n=== Ergebnis: " + bestanden + "/" + gesamt + " bestanden ===");
        if (bestanden >= 9) {
            System.out.println("(Bestehensgrenze 9/12 erreicht - testZaehleSOSMit muss dabei sein!)");
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
