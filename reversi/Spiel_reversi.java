import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spiel_reversi {

    private static Scanner sc = new Scanner(System.in);
    private static Spielfeld_reversi feld;

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
     *     Reversi
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 15
    	int eingabe;
    	do {
    		System.out.println("Reversi");
    		System.out.println("1 - Spielen");
    		System.out.println("2 - Programm beendet");
    		
    		eingabe = sc.nextInt();
    	}while(!checkEingabeMenu(eingabe));
        return eingabe;
    }

    /** TODO 14 (leicht): true, wenn eingabe 1 oder 2 ist, sonst false. */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 14
        return eingabe == 1 || eingabe == 2;
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib den Namen von Spieler 1 (X) ein:");
        Spieler_reversi s1 = new Spieler_reversi(sc.next(), 1);
        System.out.println("Gib den Namen von Spieler 2 (O) ein:");
        Spieler_reversi s2 = new Spieler_reversi(sc.next(), 2);

        feld = new Spielfeld_reversi();
        Spieler_reversi aktuell = s1;

        while (!feld.istVoll() && (feld.hatGueltigenZug(1) || feld.hatGueltigenZug(2))) {
            System.out.println(feld);
            int nummer = aktuell.getSpielernummer();

            if (feld.hatGueltigenZug(nummer)) {
                System.out.println(aktuell.getName() + " ist an der Reihe.");
                int[] zug = leseGueltigenZug(aktuell);
                feld.setzeStein(zug[0], zug[1], nummer);
            } else {
                System.out.println(aktuell.getName() + " kann nicht ziehen und setzt aus.");
            }
            aktuell = (aktuell == s1) ? s2 : s1;
        }

        System.out.println(feld);
        int p1 = feld.zaehleSteine(1);
        int p2 = feld.zaehleSteine(2);
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
     * Fragt so lange nach Zeile und Spalte, bis ein gueltiger Zug moeglich ist,
     * und gibt ihn als {zeile, spalte} zurueck. (gegeben - nicht aendern)
     */
    private static int[] leseGueltigenZug(Spieler_reversi sp) {
        int z, s;
        int nummer = sp.getSpielernummer();
        do {
            System.out.println(sp.getName() + ", gib eine Zeile an:");
            z = sc.nextInt();
            System.out.println(sp.getName() + ", gib eine Spalte an:");
            s = sc.nextInt();
        } while (!feld.istZugGueltig(z, s, nummer));
        return new int[] { z, s };
    }
}