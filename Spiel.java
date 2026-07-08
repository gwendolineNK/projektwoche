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
     *     Minesucher
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 14
    	int eingabe;
    	do {
    		System.out.println("Minesucher");
    		System.out.println("1 - Spielen");
    		System.out.println("2 - Programm beendet.");
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
        System.out.println("Gib deinen Namen ein:");
        Spieler sp = new Spieler(sc.next());

        System.out.println("Gib die Groesse des Spielfelds ein:");
        int n = sc.nextInt();
        System.out.println("Gib die Anzahl der Minen ein:");
        int m = sc.nextInt();
        feld = new Spielfeld(n, m);

        while (true) {
            System.out.println(feld);

            int[] stelle = leseGueltigeStelle(sp);
            int z = stelle[0];
            int s = stelle[1];
            feld.aufdecken(z, s);

            if (feld.istMine(z, s)) {
                System.out.println(feld);
                System.out.println("BOOM! Eine Mine. " + sp.getName() + " hat verloren.");
                return;
            }

            sp.punktDazu();

            if (feld.alleSicherenAufgedeckt()) {
                System.out.println(feld);
                System.out.println(sp.getName() + " hat gewonnen! Punkte: " + sp.getPunkte());
                return;
            }
        }
    }

    /**
     * Fragt so lange nach Zeile und Spalte, bis eine gueltige Eingabe erfolgt,
     * und gibt sie als {zeile, spalte} zurueck. (gegeben - nicht aendern)
     */
    private static int[] leseGueltigeStelle(Spieler sp) {
        int z, s;
        do {
            System.out.println(sp.getName() + ", gib eine Zeile an:");
            z = sc.nextInt();
            System.out.println(sp.getName() + ", gib eine Spalte an:");
            s = sc.nextInt();
        } while (!feld.gueltigeEingabe(z, s));
        return new int[] { z, s };
    }
}
