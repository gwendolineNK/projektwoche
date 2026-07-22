import java.util.Scanner;

/**
 * Spiel - Menuefuehrung und Spielablauf. Ein- und Ausgabe NUR in dieser Klasse.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spiel {

    private static Scanner sc = new Scanner(System.in);
    private static final int MAX_VERSUCHE = 10;

    public static void main(String[] args) {
        int wahl = menu();
        while (wahl != 2) {
            spielen();
            wahl = menu();
        }
        System.out.println("Programm beendet.");
    }

    /**
     * TODO 12 (mittel): Zeigt das Menue so lange an, bis 1 oder 2 eingegeben wird,
     * und gibt diese Zahl zurueck.
     * Menue-Text (genau so):
     *     Mastermind
     *     1 - Spielen
     *     2 - Programm beenden
     * Hinweis: do-while-Schleife + checkEingabeMenu(...).
     */
    public static int menu() {
        // TODO 12
        return 2;
    }

    /** TODO 11 (leicht): true, wenn eingabe 1 oder 2 ist, sonst false. */
    public static boolean checkEingabeMenu(int eingabe) {
        // TODO 11
        return false;
    }

    /** Ein komplettes Spiel. (gegeben - nutzt deine Methoden) */
    private static void spielen() {
        System.out.println("Gib deinen Namen ein:");
        Spieler sp = new Spieler(sc.next());

        Code code = new Code();
        boolean gewonnen = false;

        while (sp.getVersuche() < MAX_VERSUCHE && !gewonnen) {
            int[] tipp = leseTipp(code.getLaenge());
            sp.versuchDazu();

            if (code.istGeloest(tipp)) {
                gewonnen = true;
                System.out.println("Richtig! " + sp.getName() + " hat den Code in "
                        + sp.getVersuche() + " Versuchen geknackt.");
            } else {
                System.out.println("Bulls: " + code.zaehleBulls(tipp)
                        + " | Kuehe: " + code.zaehleKuehe(tipp));
            }
        }

        if (!gewonnen) {
            System.out.print("Verloren! Der Code war: ");
            int[] loesung = code.getCode();
            for (int i = 0; i < loesung.length; i++) System.out.print(loesung[i]);
            System.out.println();
        }
    }

    /**
     * Liest 'laenge' Ziffern ein und gibt sie als Array zurueck.
     * (gegeben - nicht aendern; es wird angenommen, dass sinnvolle Ziffern kommen)
     */
    private static int[] leseTipp(int laenge) {
        System.out.println("Gib deinen Tipp ein (" + laenge + " Ziffern, je eine Zeile):");
        int[] tipp = new int[laenge];
        for (int i = 0; i < laenge; i++) {
            tipp[i] = sc.nextInt();
        }
        return tipp;
    }
}
