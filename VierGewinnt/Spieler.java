/**
 * Spieler - ein Spieler bei "Vier gewinnt".
 * Hat einen Namen und eine Spielernummer (1 oder 2).
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spieler {

    private String name;
    private int spielernummer;   // 1 oder 2

    /**
     * TODO 1 (leicht): Konstruktor.
     * Setze this.name und this.spielernummer aus den Parametern.
     */
    public Spieler(String name, int spielernummer) {
        // TODO 1
    	this.name = name;
    	this.spielernummer = spielernummer;
    }

    /** TODO 2 (leicht): name zurueckgeben. */
    public String getName() {
        // TODO 2
        return name;
    }

    /** TODO 3 (leicht): spielernummer zurueckgeben. */
    public int getSpielernummer() {
        // TODO 3
        return spielernummer;
    }
}