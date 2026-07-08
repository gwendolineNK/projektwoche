import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static Spielfeld feld;
    private static int zeile;
    private static int spalte;

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
     *     Herzlich Willkommen zur Schatzsuche
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 14
    	int eingabe;
    	do {
    		System.out.println("Herzlich Willkommen zur Schatzsuche");
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

    /**
     * TODO 15 (mittel): Gibt true zurueck, wenn noch NICHT alle Schaetze gefunden wurden.
     * Es gibt anzahlSchaetze Schaetze. Gefunden = Summe der Punkte beider Spieler.
     * Hinweis: return (Summe der Punkte) < anzahlSchaetze;
     */
    public static boolean weiterspielen(Spieler s1, Spieler s2, int anzahlSchaetze) {
        // TODO 15
    	
        return (s1.getPunkte() + s2.getPunkte()) < anzahlSchaetze;
    }

    /**
     * TODO 16 (mittel): Ergebnis-Satz.
     * - Hat s1 mehr Punkte:  "<Name1> hat gewonnen!"
     * - Hat s2 mehr Punkte:  "<Name2> hat gewonnen!"
     * - Gleichstand:         "Unentschieden."
     */
    public static String auswertung(Spieler s1, Spieler s2) {
        // TODO 16
    	if(s1.getPunkte() > s2.getPunkte()) {
    		System.out.println(s1.getName() + " hat gewonnen!");
    	}
    	else if(s2.getPunkte() > s1.getPunkte()) {
    		System.out.println(s2.getName() + " hat gewonnen!");
    	}
    	else if(s2.getPunkte() == s1.getPunkte()) {
    		System.out.println("Unentschieden.");
    	}
        return "";
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib den Namen von Spieler 1 ein:");
        Spieler s1 = new Spieler(sc.next(), 1);
        System.out.println("Gib den Namen von Spieler 2 ein:");
        Spieler s2 = new Spieler(sc.next(), 2);

        System.out.println("Gib die Groesse des Spielfelds ein:");
        int n = sc.nextInt();
        feld = new Spielfeld(n);
        int anzahlSchaetze = n;   // n Schaetze auf einem n x n Feld

        Spieler aktuell = s1;
        while (weiterspielen(s1, s2, anzahlSchaetze)) {
            System.out.println(aktuell.getName() + " ist an der Reihe.");
            System.out.println(feld);

            eingabe(aktuell);   // setzt zeile und spalte
            String ergebnis = feld.deckeAuf(zeile, spalte, aktuell);
            System.out.println(ergebnis);

            if (ergebnis.equals(Spielfeld.TREFFER)) {
                aktuell.punktDazu();          // Punkt und nochmal dran
            } else {
                aktuell = (aktuell == s1) ? s2 : s1;   // Spielerwechsel
            }

            zeigeSpielstand(s1, s2);
        }

        System.out.println(auswertung(s1, s2));
    }

    /**
     * Liest so lange Zeile und Spalte ein, bis eine gueltige Eingabe erfolgt,
     * und setzt die Attribute zeile und spalte. (gegeben - nicht aendern)
     */
    private static void eingabe(Spieler sp) {
        int z, s;
        do {
            System.out.println(sp.getName() + ", gib eine Zeile an:");
            z = sc.nextInt();
            System.out.println(sp.getName() + ", gib eine Spalte an:");
            s = sc.nextInt();
        } while (!feld.gueltigeEingabe(z, s));
        zeile = z;
        spalte = s;
    }

    /** Gibt den aktuellen Spielstand aus. (gegeben - nicht aendern) */
    private static void zeigeSpielstand(Spieler s1, Spieler s2) {
        System.out.println("Der aktuelle Spielstand lautet:");
        System.out.println(s1.getName() + ": " + s1.getPunkte() + " Punkte");
        System.out.println(s2.getName() + ": " + s2.getPunkte() + " Punkte");
    }
}