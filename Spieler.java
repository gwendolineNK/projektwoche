/**
 * Spieler - der Spieler bei "Minesucher".
 * Hat einen Namen und Punkte (Anzahl sicher aufgedeckter Felder).
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spieler {

    private String name;
    private int punkte;

    /** TODO 1 (leicht): Konstruktor. this.name setzen, punkte auf 0. */
    public Spieler(String name) {
        // TODO 1
    	this.name = name;
    	this.punkte = 0;
    }

    /** TODO 2 (leicht): name zurueckgeben. */
    public String getName() {
        // TODO 2
        return name;
    }

    /** TODO 3 (leicht): punkte zurueckgeben. */
    public int getPunkte() {
        // TODO 3
        return punkte;
    }

    /** TODO 4 (leicht): punkte um 1 erhoehen. */
    public void punktDazu() {
        // TODO 4
    	punkte++;
    }
}