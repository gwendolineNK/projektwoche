/**
 * Spielfeld - das 6x7-Brett fuer "Vier gewinnt".
 *
 * feld[zeile][spalte]: 0 = leer, 1 = Stein von Spieler 1, 2 = Stein von Spieler 2.
 * Zeile 0 ist OBEN, Zeile 5 ist UNTEN (Steine fallen nach unten).
 *
 * Anzeige: 0 -> ".", 1 -> "X", 2 -> "O".
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spielfeld {

    public static final int ZEILEN = 6;
    public static final int SPALTEN = 7;
    public static final int GEWINN = 4;   // so viele in einer Reihe zum Sieg

    private int[][] feld;

    /** Erzeugt ein leeres Feld. (gegeben - nicht aendern) */
    public Spielfeld() {
        feld = new int[ZEILEN][SPALTEN];
    }

    /** TODO 4 (leicht): Wert an der Stelle (z, s) zurueckgeben. */
    public int getZelle(int z, int s) {
        // TODO 4
        return feld[z][s];
    }

    /**
     * TODO 5 (mittel): Ist der Einwurf in diese Spalte gueltig?
     * true, wenn spalte existiert (0..SPALTEN-1) UND die Spalte noch nicht voll ist.
     * Hinweis: Voll ist eine Spalte, wenn die OBERSTE Zelle (feld[0][spalte]) belegt ist.
     */
    public boolean spalteGueltig(int spalte) {
        // TODO 5
    	if(spalte >= 0 && spalte < SPALTEN) {
    		if(feld[0][spalte] == 0) {
        		return true;
        	}
    	}
  
        return false;
        
       
    }

    /**
     * TODO 6 (mittel): Ist das ganze Feld voll?
     * true, wenn KEINE Zelle mehr 0 ist, sonst false.
     */
    public boolean istVoll() {
        // TODO 6
    	for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld[i].length; j++) {
				if(feld[i][j] == 0) {
					return false;
				}
			}
		}
        return true;
    }

    /**
     * TODO 7 (mittel-schwer): Wirft einen Stein von 'spielernummer' in 'spalte'.
     * Der Stein faellt nach UNTEN: finde die unterste freie Zelle der Spalte
     * (groesster Zeilenindex mit Wert 0), setze sie auf spielernummer und gib
     * die Zeile zurueck. Ist die Spalte voll, gib -1 zurueck.
     * Hinweis: Schleife von z = ZEILEN-1 hoch bis 0.
     */
    public int einwerfen(int spalte, int spielernummer) {
        // TODO 7
    	for(int i = feld.length - 1; i >= 0; i--) {
			if(feld[i][spalte] == 0) {
				feld[i][spalte] = spielernummer;
				return i;
			}
		}
        return -1;
    }

    /**
     * TODO 8 (mittel): Vier gleiche WAAGERECHT (in einer Zeile) fuer spielernummer?
     * Hinweis: fuer jede Zeile z und jede Startspalte s von 0 bis SPALTEN-GEWINN
     *   pruefen, ob feld[z][s], feld[z][s+1], feld[z][s+2], feld[z][s+3] alle
     *   gleich spielernummer sind.
     */
    public boolean vierInReiheWaagerecht(int spielernummer) {
        // TODO 8
    	for(int i = feld.length - 1; i >= 0; i--) {
    		for(int j = 0; j < feld[i].length; j++) {
    			if(feld[i][j] == spielernummer) {
    				if(j + 1 < feld[i].length && j + 2 < feld[i].length 
    						&& j + 3 < feld[i].length && feld[i][j + 1] == spielernummer &&
    						feld[i][j + 2] == spielernummer && feld[i][j + 3] == spielernummer) {
    					return true;
    				}
    			}
    		}
    	}
    	
        return false;
    }

    /**
     * TODO 9 (mittel): Vier gleiche SENKRECHT (in einer Spalte) fuer spielernummer?
     * Hinweis: analog, aber Startzeile z von 0 bis ZEILEN-GEWINN, feste Spalte,
     *   Zeilen z..z+3 pruefen.
     */
    public boolean vierInReiheSenkrecht(int spielernummer) {
        // TODO 9
    	for(int i = 0 ; i < feld.length ; i++) {
    		for(int j = 0; j < feld[i].length; j++) {
    			if(feld[i][j] == spielernummer) {
    				if(i + 1 < feld.length && i + 2 < feld.length 
    						&& i + 3 < feld.length && feld[i+1][j] == spielernummer &&
    						feld[i+2][j] == spielernummer && feld[i+3][j] == spielernummer) {
    					return true;
    				}
    			}
    		}
    	}
        return false;
    }

    /**
     * TODO 10 (schwer): Vier gleiche DIAGONAL fuer spielernummer?
     * Es gibt ZWEI Diagonalrichtungen:
     *   - nach unten-rechts: feld[z+i][s+i]  (z von 0..ZEILEN-GEWINN, s von 0..SPALTEN-GEWINN)
     *   - nach unten-links:  feld[z+i][s-i]  (z von 0..ZEILEN-GEWINN, s von GEWINN-1..SPALTEN-1)
     * fuer i von 0 bis GEWINN-1 muessen alle gleich spielernummer sein.
     */
    public boolean vierInReiheDiagonal(int spielernummer) {
        // TODO 10
    	
    	//nach unten-rechts
    	for(int i = 0 ; i < GEWINN ; i++) {
    		for(int j = 0; j < GEWINN; j++) {
    			if(feld[i][j] == spielernummer) {
    				if(i + 1 < feld.length && i + 2 < feld.length 
    						&& i + 3 < feld.length && j + 1 < feld[i].length && j + 2 < feld[i].length 
    						&& j + 3 < feld[i].length
    						&& feld[i+1][j+1] == spielernummer &&
    						feld[i+2][j+2] == spielernummer && feld[i+3][j+3] == spielernummer) {
    					return true;
    				}
    			}
    		}
    	}	
    	
    	//nach unten-links
    	for(int i = 0 ; i < GEWINN ; i++) {
    		for(int j = GEWINN - 1; j < feld[i].length; j++) {
    			if(feld[i][j] == spielernummer) {
    				if(i + 1 < feld.length && i + 2 < feld.length 
    						&& i + 3 < feld.length && j - 1 >= 0 && j - 2 >= 0
    						&& j - 3 >= 0
    						&& feld[i+1][j-1] == spielernummer &&
    						feld[i+2][j-2] == spielernummer && feld[i+3][j-3] == spielernummer) {
    					return true;
    				}
    			}
    		}
    	}
        return false;
    }

    /**
     * TODO 11 (leicht): Hat spielernummer gewonnen?
     * Gib true zurueck, wenn eine der drei Richtungen vier in Reihe hat.
     * Hinweis: die drei obigen Methoden mit || verknuepfen.
     */
    public boolean hatGewonnen(int spielernummer) {
        // TODO 11
    	if(vierInReiheDiagonal(spielernummer) || vierInReiheSenkrecht( spielernummer) 
    			||  vierInReiheWaagerecht( spielernummer)) {
    		return true;
    	}
    		
        return false;
    }

    /**
     * TODO 12 (schwer): Baut die Anzeige des Feldes als String.
     * Token: 0 -> ".", 1 -> "X", 2 -> "O". Pro Zeile die Zellen mit einem
     * Leerzeichen getrennt, Zeilen mit '\n' getrennt.
     * Beispiel (leeres Feld, 6 Zeilen x 7 Spalten):
     *     . . . . . . .
     *     . . . . . . .
     *     . . . . . . .
     *     . . . . . . .
     *     . . . . . . .
     *     . . . . . . .
     */
    public String toString() {
        // TODO 12
    	String erg = "\t";
    	
    	for(int i = 0 ; i < feld[0].length ; i++) {
    		erg += i +"\t";
    	}
    	erg += "\n";
    	
    	for(int i = 0 ; i < feld.length ; i++) {
    		erg += i + "\t";
    		for(int j = 0; j < feld[i].length; j++) {
    			if(feld[i][j] == 0) {
    				erg += "." + "\t";
    			}
    			else if(feld[i][j] == 1) {
    				erg += "X" + "\t";
    			}
    			else if(feld[i][j] == 2) {
    				erg += "O" + "\t";
    			}
    				
    		}
    		erg += "\n";
    	}
        return erg;
    }

    /** Nur fuer die Tests: liefert das interne Feld. (gegeben - nicht aendern) */
    public int[][] getFeld() {
        return feld;
    }
}