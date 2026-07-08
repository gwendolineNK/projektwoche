/**
 * Spieler - ein Spieler bei "Schatzsuche".
 * Hat einen Namen, eine Spielernummer (1 oder 2) und Punkte.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spieler {

    private String name;
    private int spielernummer;   // 1 oder 2
    private int punkte;

    /**
     * TODO 1 (leicht): Konstruktor.
     * Setze this.name und this.spielernummer aus den Parametern, punkte auf 0.
     */
    public Spieler(String name, int spielernummer) {
        // TODO 1
    	this.name = name;
    	this.spielernummer = spielernummer;
    	this.punkte = 0;
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

    /** TODO 4 (leicht): punkte zurueckgeben. */
    public int getPunkte() {
        // TODO 4
        return punkte;
    }

    /** TODO 5 (leicht): punkte um 1 erhoehen. */
    public void punktDazu() {
        // TODO 5
    	punkte += 1;
    }
}