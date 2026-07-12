import java.util.Random;

/**
 * Spielfeld - das quadratische Feld (n x n) fuer "Schatzsuche".
 *
 * Zwei Felder:
 *   - schatzkarte[z][s]      : true = hier liegt ein Schatz.
 *   - offenesSpielfeld[z][s] : was der Spieler sieht.
 *         0  = noch verdeckt         Anzeige "_"
 *        -1  = aufgedeckt, kein Schatz in der Naehe   Anzeige "X"
 *        -2  = aufgedeckt, Schatz angrenzend          Anzeige "?"
 *         1  = Schatz von Spieler 1 gefunden          Anzeige "S1"
 *         2  = Schatz von Spieler 2 gefunden          Anzeige "S2"
 *
 * Kein 'ae/oe/ue/ss' im Code verwenden.
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 */
public class Spielfeld {

    /** Rueckgabe-Strings von deckeAuf (gegeben - genau diese verwenden). */
    public static final String TREFFER = "Schatz gefunden!";
    public static final String NAH     = "Ein Schatz ist angrenzend.";
    public static final String FERN    = "Kein Schatz in der Naehe.";

    private int n;
    private boolean[][] schatzkarte;
    private int[][] offenesSpielfeld;
    private Random rd;

    /** Erzeugt ein n x n Feld und versteckt n Schaetze. (gegeben - nicht aendern) */
    public Spielfeld(int n) {
        this.n = n;
        this.schatzkarte = new boolean[n][n];
        this.offenesSpielfeld = new int[n][n];
        this.rd = new Random();
        initialisiereSpielfeld();
    }

    /**
     * TODO 10 (schwer): Versteckt genau n Schaetze an n VERSCHIEDENEN Stellen.
     * Eine Zufallszahl zwischen 0 und n-1 bekommst du mit rd.nextInt(n).
     * Hinweis: Zaehle, wie viele Schaetze schon gesetzt sind. Solange noch nicht n
     *   gesetzt sind: eine zufaellige Stelle (z, s) waehlen. Ist dort noch KEIN Schatz,
     *   setze schatzkarte[z][s] = true und erhoehe den Zaehler. (while-Schleife)
     */
    private void initialisiereSpielfeld() {
        // TODO 10
    	
    	int z,s;
    	while(n > 0) {
    		do {
    			z = rd.nextInt(n);
        		s = rd.nextInt(n);
    		}while(schatzkarte[z][s] == true);
    		schatzkarte[z][s] = true;
    		n--;
    	}
    }

    /** TODO 6 (leicht): n zurueckgeben. */
    public int getN() {
        // TODO 6
        return n;
    	
    }

    /** TODO 7 (leicht): true, wenn an (z, s) ein Schatz liegt. */
    public boolean treffer(int z, int s) {
        // TODO 7
    
        return schatzkarte[z][s];
    	
    }

    /**
     * TODO 8 (mittel): Gueltige Eingabe?
     * true, wenn (z, s) existierende Koordinaten sind UND die Stelle noch verdeckt ist
     * (offenesSpielfeld[z][s] == 0). Sonst false.
     * Hinweis: zuerst die Grenzen (0..n-1) pruefen, dann erst auf das Feld zugreifen.
     */
    public boolean gueltigeEingabe(int z, int s) {
        // TODO 8
    	if(z >= 0 && z < offenesSpielfeld.length && s >= 0 && s < offenesSpielfeld[0].length ) {
    		if(offenesSpielfeld[z][s] == 0) {
    			return true;
    		}
    	}
        return false;
    }

    /**
     * TODO 9 (schwer): Angrenzend ein Schatz?
     * true, wenn OBEN, UNTEN, LINKS oder RECHTS (nicht diagonal, nicht die Stelle selbst)
     * ein Schatz liegt. Sonst false.
     * Hinweis: jede Richtung einzeln pruefen und die Grenze VOR dem Zugriff testen
     *   (z. B. z > 0 && schatzkarte[z-1][s]).
     */
    public boolean angrenzend(int z, int s) {
        // TODO 9
    	boolean erg ;
    	//Links
    	if(s+1 < schatzkarte[0].length && schatzkarte[z][s+1] ) {
    		erg = true;
    	}
    	//recht
    	else if(s-1 >= 0 && schatzkarte[z][s-1] ) {
    		erg = true;
    	}
    	//oben
    	else if(z-1 >= 0 && schatzkarte[z-1][s] ) {
    		erg = true;
    	}
    	//unten
    	else if(z+1 < schatzkarte.length && schatzkarte[z+1][s] ) {
    		erg = true;
    	}
    	else {
    		erg = false;
    	}
        return erg;
    }

    /**
     * TODO 11 (mittel): Deckt die Stelle (z, s) auf.
     * - Liegt dort ein Schatz: offenesSpielfeld[z][s] = Spielernummer von sp; return TREFFER.
     * - Sonst, wenn angrenzend ein Schatz liegt: offenesSpielfeld[z][s] = -2; return NAH.
     * - Sonst: offenesSpielfeld[z][s] = -1; return FERN.
     * Nutze treffer(...) und angrenzend(...).
     */
    public String deckeAuf(int z, int s, Spieler sp) {
        // TODO 11
    	if(treffer(z,s)) {
    		offenesSpielfeld[z][s] = sp.getSpielernummer();
    		return TREFFER;
    	}
    	else if(angrenzend(z,s)) {
    		offenesSpielfeld[z][s] = -2;
    		return NAH;
    	}
    	else {
    		offenesSpielfeld[z][s] = -1;
    		return FERN;
    	}
    	
        
    }

    /**
     * TODO 12 (schwer): Baut die Anzeige von offenesSpielfeld als String.
     * Pro Stelle ein Token, in einer Zeile mit einem Leerzeichen getrennt,
     * Zeilen mit '\n' getrennt. Token je nach Wert:
     *    0 -> "_"   -1 -> "X"   -2 -> "?"   1 -> "S1"   2 -> "S2"
     * Hinweis: mit if/else oder switch je Wert das passende Token anhaengen.
     */
    public String toString() {
        // TODO 12
    	String erg = "\t";
    	
    	for(int i = 0; i < offenesSpielfeld[0].length; i++) {
    		erg += i +"\t";
    	}
    	
    	erg += "\n";
    	
    	for(int i = 0; i < offenesSpielfeld.length; i++) {
    		erg += i + "\t";
    		for( int j = 0; j < offenesSpielfeld[i].length; j++) {
    			if(offenesSpielfeld[i][j] == 0) {
    				erg += "_" + "\t";
    			}
    			else if(offenesSpielfeld[i][j] == -1) {
    				erg += "X" +"\t";
    			}
    			else if(offenesSpielfeld[i][j] == -2) {
    				erg += "?" +"\t";
    			}
    			else if(offenesSpielfeld[i][j] == 1) {
    				erg += "S1" +"\t";
    			}
    			else if(offenesSpielfeld[i][j] == 2) {
    				erg += "S2" +"\t";
    			}
    			
    		}
    		erg += "\n";
    	}
        return erg;
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public int[][] getOffenesSpielfeld() {
        return offenesSpielfeld;
    }

    public boolean[][] getSchatzkarte() {
        return schatzkarte;
    }

    /** Setzt eine bekannte Schatzkarte fuer die Tests und leert das offene Feld. */
    public void setSchatzkarteFuerTest(boolean[][] karte) {
        this.schatzkarte = karte;
        this.n = karte.length;
        this.offenesSpielfeld = new int[n][n];
    }
}