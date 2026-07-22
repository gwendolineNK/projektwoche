import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static Spielfeld feld;

    public static void main(String[] args) {
        int wahl = menu();
        while (wahl != 2) {
            spielen();
            wahl = menu();
        }
        System.out.println("Programm beendet.");
    }

    /**
     * TODO 15 (mittel): Zeigt das Menue so lange an, bis 1 oder 2 eingegeben wird,
     * und gibt diese Zahl zurueck.
     * Menue-Text (genau so):
     *     Dame
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 15
        return 2;
    }

    /** TODO 14 (leicht): true, wenn eingabe 1 oder 2 ist, sonst false. */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 14
        return false;
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib den Namen von Spieler 1 (X) ein:");
        Spieler s1 = new Spieler(sc.next(), 1);
        System.out.println("Gib den Namen von Spieler 2 (O) ein:");
        Spieler s2 = new Spieler(sc.next(), 2);

        feld = new Spielfeld();
        Spieler aktuell = s1;

        while (!feld.hatGewonnen(1) && !feld.hatGewonnen(2)) {
            System.out.println(feld);
            int nummer = aktuell.getSpielernummer();
            System.out.println(aktuell.getName() + " ist an der Reihe.");

            int[] zug = leseGueltigenZug(nummer, aktuell);
            feld.fuehreZug(zug[0], zug[1], zug[2], zug[3], nummer);

            if (feld.hatGewonnen(nummer)) {
                System.out.println(feld);
                System.out.println(aktuell.getName() + " hat gewonnen!");
                return;
            }
            aktuell = (aktuell == s1) ? s2 : s1;
        }
    }

    /**
     * Fragt so lange nach einem Zug (Start- und Zielfeld), bis ein gueltiger
     * einfacher Zug ODER Schlagzug vorliegt. (gegeben - nicht aendern)
     */
    private static int[] leseGueltigenZug(int nummer, Spieler sp) {
        int z1, s1, z2, s2;
        do {
            System.out.println(sp.getName() + ", Startfeld - Zeile:");
            z1 = sc.nextInt();
            System.out.println("Startfeld - Spalte:");
            s1 = sc.nextInt();
            System.out.println("Zielfeld - Zeile:");
            z2 = sc.nextInt();
            System.out.println("Zielfeld - Spalte:");
            s2 = sc.nextInt();
        } while (!feld.istEinfacherZugGueltig(z1, s1, z2, s2, nummer)
                && !feld.istSchlagzugGueltig(z1, s1, z2, s2, nummer));
        return new int[] { z1, s1, z2, s2 };
    }
}
