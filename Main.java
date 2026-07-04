

import java.util.Scanner;


/**
 * Main - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int wahl = menu();
        while (wahl != 2) {
            spielen();
            wahl = menu();
        }
        System.out.println("Programm beendet.");
    }

    /**
     * TODO 13 (mittel): Zeigt das Menue so lange an, bis 1 oder 2 eingegeben wird,
     * und gibt diese Zahl zurueck.
     * Menue-Text (genau so):
     *     MEMORY
     *     1 - Ein neues Spiel starten
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 13
    	int eingabe;
    	do {
    		System.out.println("MEMORY");
    		System.out.println("1 - Ein neues Spiel starten");
    		System.out.println("2 - Programm beenden");
    		eingabe = sc.nextInt();
    	}while(!checkEingabeMenu(eingabe));
        return eingabe;
    }

    /**
     * TODO 12 (leicht): Gibt true zurueck, wenn eingabe 1 oder 2 ist, sonst false.
     */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 12
        return eingabe ==1 || eingabe ==2;
    }

    /**
     * TODO 16 (mittel): Liefert den Ergebnis-Satz.
     * - Hat Spieler 1 mehr Punkte:  "<Name1> hat dieses Spiel gewonnen!"
     * - Hat Spieler 2 mehr Punkte:  "<Name2> hat dieses Spiel gewonnen!"
     * - Gleichstand:                "Das Spiel endet unentschieden."
     */
    public static String auswertung(Spieler s1, Spieler s2) {
        // TODO 16
    	if(s1.getPunkte() < s2.getPunkte()) {
    		return s2.getName() + "hat dieses Spiel gewonnen!";
    	}
    	else if(s1.getPunkte() > s2.getPunkte()) {
    		return s1.getName() + "hat dieses Spiel gewonnen!";
    	}
    	else if(s1.getPunkte() == s2.getPunkte()) {
    		return "Das Spiel endet unentschieden.";
    	}
        return "";
    }

    /** Ein komplettes Spiel. (Geruest gegeben, TODO 14 und 15 im Inneren). */
    private static void spielen() {
        System.out.println("Geben Sie den Namen von Spieler 1 ein:");
        Spieler s1 = new Spieler(sc.next());
        System.out.println("Geben Sie den Namen von Spieler 2 ein:");
        Spieler s2 = new Spieler(sc.next());

        Memory spiel = new Memory();
        Spieler aktuell = s1;

        while (!spiel.allesAufgedeckt()) {
            System.out.println(aktuell.getName() + " ist an der Reihe.");
            System.out.println(spiel);

            // 1. Karte (gegeben)
            int[] karte1 = leseGueltigeKarte(spiel, "Zeile 1:", "Spalte 1:");

            // 2. Karte (gegeben)
            int[] karte2 = leseGueltigeKarte(spiel, "Zeile 2:", "Spalte 2:");
            // TODO 14 (mittel): Stelle sicher, dass karte2 NICHT dieselbe Stelle wie karte1 ist.
            //   Solange karte2 dieselbe Zeile UND Spalte wie karte1 hat, erneut einlesen
            //   (karte2 = leseGueltigeKarte(...)). Hinweis: while-Schleife mit &&.

            while(karte1[0] == karte2[0] && karte1[1] == karte2[1]) {
            	karte2 = leseGueltigeKarte(spiel, "Zeile 2:", "Spalte 2:");
            }
            
            char c1 = spiel.getZeichen(karte1[0], karte1[1]);
            char c2 = spiel.getZeichen(karte2[0], karte2[1]);
            System.out.println("An der Stelle befindet sich ein " + c1 + ".");
            System.out.println("An der Stelle befindet sich ein " + c2 + ".");

            aktuell.versuchDazu();

            // TODO 15 (mittel): Treffer-Logik.
            //   Wenn c1 und c2 zusammenpassen (passenZusammen):
            //       - beide Stellen aufdecken (spiel.aufdecken(...))
            //       - Punkt fuer 'aktuell' (punktDazu)
            //       - 'aktuell' bleibt dran (KEIN Wechsel)
            //   Sonst:
            //       - Spielerwechsel: aktuell wird der jeweils andere Spieler.
            // Ersetze die folgende (immer wechselnde) Platzhalter-Zeile durch deine Logik:
            
            if(spiel.passenZusammen(c1,c2)) {
            	spiel.aufdecken(karte1[0], karte1[1]);
            	spiel.aufdecken(karte2[0], karte2[1]);
            	aktuell.punktDazu();
            	aktuell = (aktuell == s1) ? s1 : s2;
            }
            else {
            	aktuell = (aktuell == s1) ? s2 : s1;
            }
            

            zeigeSpielstand(s1, s2);
        }

        // Statistik (gegeben)
        System.out.println("Statistik:");
        System.out.println(s1.getName() + ": " + s1.getVersuche() + " Versuche");
        System.out.println(s2.getName() + ": " + s2.getVersuche() + " Versuche");

        // Endergebnis (gegeben, nutzt deine auswertung)
        System.out.println(auswertung(s1, s2));
    }

    /**
     * Liest so lange Zeile und Spalte ein, bis eine gueltige Eingabe erfolgt,
     * und gibt sie als {zeile, spalte} zurueck. (gegeben - nicht aendern)
     */
    private static int[] leseGueltigeKarte(Memory spiel, String labelZeile, String labelSpalte) {
        int z, s;
        do {
            System.out.println(labelZeile);
            z = sc.nextInt();
            System.out.println(labelSpalte);
            s = sc.nextInt();
        } while (!spiel.gueltigeEingabe(z, s));
        return new int[] { z, s };
    }

    /** Gibt den aktuellen Spielstand aus. (gegeben - nicht aendern) */
    private static void zeigeSpielstand(Spieler s1, Spieler s2) {
        System.out.println("Der aktuelle Spielstand lautet:");
        System.out.println(s1.getName() + ": " + s1.getPunkte() + " Punkte");
        System.out.println(s2.getName() + ": " + s2.getPunkte() + " Punkte");
    }
}