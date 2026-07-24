import java.util.Random;
import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Regeln "Pig": Zwei Spieler. Wer dran ist, wuerfelt beliebig oft.
 * - Jeder Wurf (2-6) wird zum Rundenpunktestand addiert.
 * - Bei einer 1 verfaellt der GESAMTE Rundenpunktestand und der Zug endet.
 * - Haelt der Spieler, werden die Rundenpunkte dem Gesamtstand gutgeschrieben.
 * Wer zuerst ZIEL (100) Punkte erreicht, gewinnt.
 *
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static Random rd = new Random();
    public static final int ZIEL = 100;

    public static void main(String[] args) {
        int wahl = menu();
        while (wahl != 2) {
            spielen();
            wahl = menu();
        }
        System.out.println("Programm beendet.");
    }

    /**
     * TODO 10 (mittel): Zeigt das Menue so lange an, bis 1 oder 2 eingegeben wird,
     * und gibt diese Zahl zurueck.
     * Menue-Text (genau so):
     *     Pig
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 10
    	int eingabe;
    	
    	do {
    		System.out.println("Pig");
    		System.out.println("1 - Spielen");
    		System.out.println("2 - Programm beendet");
    		eingabe = sc.nextInt();
    		
    	}while(!checkEingabeMenu(eingabe));
    	
        return eingabe;
    }

    /** TODO 5 (leicht): true, wenn eingabe 1 oder 2 ist, sonst false. */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 5
        return eingabe == 1 || eingabe == 2;
    }

    /**
     * TODO 6 (leicht): Wuerfelt und gibt eine Zahl von 1 bis 6 zurueck.
     * Hinweis: rd.nextInt(6) + 1.
     */
    public static int wuerfeln() {
        // TODO 6
        return rd.nextInt(6) + 1;
    }

    /**
     * TODO 7 (leicht): Pech-Wurf?
     * Gibt true zurueck, wenn der Wurf eine 1 ist, sonst false.
     */
    public static boolean istPech(int wurf) {
        // TODO 7
        return wurf == 1;
    }

    /**
     * TODO 8 (mittel): Neuer Rundenpunktestand nach einem Wurf.
     * - Ist der Wurf eine 1 (Pech): gib 0 zurueck (Rundenpunkte verfallen).
     * - Sonst: gib rundenScore + wurf zurueck.
     * Hinweis: du kannst istPech(...) benutzen.
     */
    public static int neuerRundenScore(int rundenScore, int wurf) {
        // TODO 8
    	if(istPech(wurf)) {
    		return 0;
    	}
    	
        return rundenScore + wurf;
    }

    /**
     * TODO 9 (leicht): Hat der Spieler das Ziel erreicht?
     * Gibt true zurueck, wenn s.getPunkte() >= ziel.
     */
    public static boolean hatGewonnen(Spieler s, int ziel) {
        // TODO 9
    	
        return s.getPunkte() >= ziel;
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib den Namen von Spieler 1 ein:");
        Spieler s1 = new Spieler(sc.next());
        System.out.println("Gib den Namen von Spieler 2 ein:");
        Spieler s2 = new Spieler(sc.next());

        Spieler aktuell = s1;

        while (!hatGewonnen(s1, ZIEL) && !hatGewonnen(s2, ZIEL)) {
            System.out.println("\n" + aktuell.getName() + " ist an der Reihe (Gesamt: "
                    + aktuell.getPunkte() + ").");
            int rundenScore = 0;
            boolean amZug = true;

            while (amZug) {
                int wurf = wuerfeln();
                System.out.println("  Gewuerfelt: " + wurf);

                if (istPech(wurf)) {
                    System.out.println("  Eine 1! Rundenpunkte verfallen. Zug zu Ende.");
                    amZug = false;
                } else {
                    rundenScore = neuerRundenScore(rundenScore, wurf);
                    System.out.println("  Rundenpunkte: " + rundenScore);
                    if (weiterWuerfeln(aktuell)) {
                        amZug = true;
                    } else {
                        aktuell.punkteDazu(rundenScore);
                        System.out.println("  " + aktuell.getName() + " haelt. Gesamt: "
                                + aktuell.getPunkte());
                        amZug = false;
                    }
                }
            }
            aktuell = (aktuell == s1) ? s2 : s1;
        }

        Spieler sieger = hatGewonnen(s1, ZIEL) ? s1 : s2;
        System.out.println("\n" + sieger.getName() + " hat mit " + sieger.getPunkte()
                + " Punkten gewonnen!");
    }

    /**
     * Fragt, ob nochmal gewuerfelt wird (1 = weiter, 2 = halten).
     * (gegeben - nicht aendern)
     */
    private static boolean weiterWuerfeln(Spieler sp) {
        int eingabe;
        do {
            System.out.println("  " + sp.getName() + ": 1 = nochmal wuerfeln, 2 = halten");
            eingabe = sc.nextInt();
        } while (eingabe != 1 && eingabe != 2);
        return eingabe == 1;
    }
}