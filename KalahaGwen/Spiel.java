import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static Spielfeld brett;

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
     *     Kalaha
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 14
    	int eingabe;
    	do {
    		System.out.println("Kalaha");
    		System.out.println("1 - Spielen");
    		System.out.println("2 - Programm beenden");
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

        brett = new Spielfeld();
        Spieler aktuell = s1;

        while (!brett.istSpielEnde()) {
            System.out.println(brett);
            int nummer = aktuell.getSpielernummer();
            System.out.println(aktuell.getName() + " ist an der Reihe.");

            int mulde = leseGueltigeMulde(nummer, aktuell);
            int letzte = brett.saee(mulde, nummer);

            if (brett.letzteImEigenenKalaha(letzte, nummer)) {
                System.out.println("Letzter Stein im eigenen Kalaha - nochmal!");
            } else {
                aktuell = (aktuell == s1) ? s2 : s1;
            }
        }

        System.out.println(brett);
        int p1 = brett.getKalaha(1);
        int p2 = brett.getKalaha(2);
        System.out.println("Endstand: " + s1.getName() + " " + p1 + " : " + p2 + " " + s2.getName());
        if (p1 > p2) {
            System.out.println(s1.getName() + " hat gewonnen!");
        } else if (p2 > p1) {
            System.out.println(s2.getName() + " hat gewonnen!");
        } else {
            System.out.println("Unentschieden.");
        }
    }

    /**
     * Fragt so lange nach einer Mulde, bis ein gueltiger Zug moeglich ist,
     * und gibt den Index zurueck. (gegeben - nicht aendern)
     */
    private static int leseGueltigeMulde(int nummer, Spieler sp) {
        int index;
        do {
            System.out.println(sp.getName() + ", waehle eine Mulde:");
            index = sc.nextInt();
        } while (!brett.istZugGueltig(index, nummer));
        return index;
    }
}