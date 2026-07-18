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
     * TODO 14 (mittel): Zeigt das Menue so lange an, bis 1 oder 2 eingegeben wird,
     * und gibt diese Zahl zurueck.
     * Menue-Text (genau so):
     *     Vier gewinnt
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 14
    	int eingabe;
    	do {
    		System.out.println("Vier gewinnt");
    		System.out.println("1 - Spielen");
    		System.out.println("2 - Programm beenden.");
    		eingabe = sc.nextInt();
    	}while(!checkEingabeMenu(eingabe));
        return eingabe;
    }

    /** TODO 13 (leicht): true, wenn eingabe 1 oder 2 ist, sonst false. */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 13
        return eingabe == 1 || eingabe == 2;
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib den Namen von Spieler 1 ein:");
        Spieler s1 = new Spieler(sc.next(), 1);
        System.out.println("Gib den Namen von Spieler 2 ein:");
        Spieler s2 = new Spieler(sc.next(), 2);

        feld = new Spielfeld();
        Spieler aktuell = s1;

        while (true) {
            System.out.println(feld);
            System.out.println(aktuell.getName() + " ist an der Reihe.");

            int spalte = leseGueltigeSpalte(aktuell);
            feld.einwerfen(spalte, aktuell.getSpielernummer());

            if (feld.hatGewonnen(aktuell.getSpielernummer())) {
                System.out.println(feld);
                System.out.println(aktuell.getName() + " hat gewonnen!");
                return;
            }
            if (feld.istVoll()) {
                System.out.println(feld);
                System.out.println("Unentschieden.");
                return;
            }
            aktuell = (aktuell == s1) ? s2 : s1;
        }
    }

    /**
     * Fragt so lange nach einer Spalte, bis ein gueltiger Einwurf moeglich ist,
     * und gibt die Spalte zurueck. (gegeben - nicht aendern)
     */
    private static int leseGueltigeSpalte(Spieler sp) {
        int spalte;
        do {
            System.out.println(sp.getName() + ", waehle eine Spalte (0-6):");
            spalte = sc.nextInt();
        } while (!feld.spalteGueltig(spalte));
        return spalte;
    }
}