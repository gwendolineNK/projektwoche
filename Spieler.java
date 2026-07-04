
/**
 * Spieler - ein Memory-Spieler mit Name, Punkten und Versuchen.
 *
 * Die TODO-Nummern entsprechen der EMPFOHLENEN Bearbeitungsreihenfolge
 * (siehe README_Memory_Plus.md), nicht der Reihenfolge im Code.
 */
public class Spieler {

    private String name;
    private int punkte;
    private int versuche;   // Anzahl der Aufdeck-Versuche (zwei Karten = ein Versuch)

    /**
     * Erzeugt einen Spieler mit dem gegebenen Namen.
     * Punkte und Versuche starten bei 0.
     */
    public Spieler(String name) {
        // TODO 1 (leicht): this.name setzen, punkte und versuche auf 0 setzen.
    	this.name = name;
    	this.punkte = 0;
    	this.versuche = 0;
    }

    /** Gibt den Namen zurueck. */
    public String getName() {
        // TODO 2 (leicht): name zurueckgeben.
        return name;
    }

    /** Gibt die Punkte zurueck. */
    public int getPunkte() {
        // TODO 3 (leicht): punkte zurueckgeben.
        return punkte;
    }

    /** Erhoeht die Punkte um 1. */
    public void punktDazu() {
    	punkte ++ ;
        // TODO 4 (leicht): punkte um 1 erhoehen.
    }

    /** Gibt die Anzahl der Versuche zurueck. (gegeben) */
    public int getVersuche() {
        return versuche;
    }

    /** Erhoeht die Anzahl der Versuche um 1. (gegeben) */
    public void versuchDazu() {
        versuche++;
    }
}