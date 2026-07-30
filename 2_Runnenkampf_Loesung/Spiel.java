import java.util.Scanner;

public class Spiel {

    private static Scanner sc = new Scanner(System.in);

    /** TODO 26 */
    public static void main(String[] args) {
        int wahl = menu();

        while (wahl != 3) {
            Spieler s1;
            Spieler s2;

            if (wahl == 1) {
                System.out.println("Gib deinen Namen ein:");
                s1 = new Spieler(sc.next(), 1);
                s2 = new Computer(2);
            } else {
                System.out.println("Gib den Namen von Spieler 1 ein:");
                s1 = new Spieler(sc.next(), 1);
                System.out.println("Gib den Namen von Spieler 2 ein:");
                s2 = new Spieler(sc.next(), 2);
            }

            Spielfeld feld = new Spielfeld();
            Spieler aktuell = s1;

            while (!feld.istSpielEnde()) {
                System.out.println();
                System.out.print(feld);
                System.out.println(aktuell.getName() + " ist an der Reihe.");

                if (aktuell instanceof Computer) {
                    int[] zug = ((Computer) aktuell).waehleZug(feld);
                    beansprucheUndMelde(feld, aktuell, zug[0], zug[1]);
                } else {
                    int zugart = leseZugart(aktuell);
                    if (zugart == 2) {
                        int z = leseZeile(aktuell, feld);
                        feld.rotiereZeile(z);
                        aktuell.rotationVerbrauchen();
                        System.out.println("Zeile " + z + " wurde rotiert.");
                    } else {
                        int[] zug = leseNeutralesFeld(aktuell, feld);
                        beansprucheUndMelde(feld, aktuell, zug[0], zug[1]);
                    }
                }

                zeigePunktestand(s1, s2, feld);
                aktuell = (aktuell == s1) ? s2 : s1;
            }

            System.out.println();
            System.out.print(feld);
            System.out.println("Das Spiel ist zu Ende.");
            zeigePunktestand(s1, s2, feld);
            System.out.println(auswertung(s1, s2, feld));
            System.out.println();

            wahl = menu();
        }

        System.out.println("Programm beendet.");
        sc.close();
    }

    /** TODO 23 */
    public static boolean checkEingabeMenu(int eingabe) {
        return eingabe >= 1 && eingabe <= 3;
    }

    /** TODO 24 */
    public static int menu() {
        int eingabe;
        do {
            System.out.println("Runenkampf");
            System.out.println("1 - Ein Spieler");
            System.out.println("2 - Zwei Spieler");
            System.out.println("3 - Programm beenden");
            eingabe = sc.nextInt();
        } while (!checkEingabeMenu(eingabe));
        return eingabe;
    }

    /** TODO 25 */
    public static String auswertung(Spieler s1, Spieler s2, Spielfeld feld) {
        int p1 = feld.punkte(s1.getNummer());
        int p2 = feld.punkte(s2.getNummer());
        if (p1 > p2) {
            return s1.getName() + " hat gewonnen!";
        } else if (p2 > p1) {
            return s2.getName() + " hat gewonnen!";
        } else {
            return "Unentschieden.";
        }
    }

    // ---------- Hilfsmethoden fuer Ein- und Ausgabe ----------

    private static void beansprucheUndMelde(Spielfeld feld, Spieler sp, int z, int s) {
        char rune = feld.getRune(z, s);
        int anzahl = feld.beansprucheGebiet(z, s, sp.getNummer());
        System.out.println(sp.getName() + " beansprucht " + anzahl
                + " Feld(er) mit der Rune " + rune + ".");
    }

    private static void zeigePunktestand(Spieler s1, Spieler s2, Spielfeld feld) {
        System.out.println("Der aktuelle Punktestand lautet:");
        System.out.println(s1.getName() + ": " + feld.punkte(s1.getNummer()) + " Punkte");
        System.out.println(s2.getName() + ": " + feld.punkte(s2.getNummer()) + " Punkte");
    }

    /**
     * Fragt die Zugart. Die 2 wird nur akzeptiert, wenn der Sonderzug
     * noch verfuegbar ist.
     */
    private static int leseZugart(Spieler sp) {
        System.out.println("1 - Gebiet beanspruchen");
        if (sp.hatRotation()) {
            System.out.println("2 - Zeile rotieren");
        }
        int zugart;
        do {
            zugart = sc.nextInt();
        } while (zugart != 1 && (zugart != 2 || !sp.hatRotation()));
        return zugart;
    }

    /** Fragt so lange nach Zeile und Spalte, bis ein neutrales Feld genannt wird. */
    private static int[] leseNeutralesFeld(Spieler sp, Spielfeld feld) {
        int z;
        int s;
        do {
            System.out.println(sp.getName() + ", gib eine Zeile an:");
            z = sc.nextInt();
            System.out.println(sp.getName() + ", gib eine Spalte an:");
            s = sc.nextInt();
        } while (!feld.istNeutral(z, s));
        return new int[] { z, s };
    }

    /** Fragt so lange nach einer Zeile, bis sie im Spielfeld liegt. */
    private static int leseZeile(Spieler sp, Spielfeld feld) {
        int z;
        do {
            System.out.println(sp.getName() + ", welche Zeile soll rotiert werden?");
            z = sc.nextInt();
        } while (!feld.imFeld(z, 0));
        return z;
    }
}
