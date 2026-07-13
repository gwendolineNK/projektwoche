/**
 * Spielfeld - das 6x6-Brett fuer "Reversi/Othello".
 *
 * feld[z][s]: 0 = leer, 1 = Stein von Spieler 1, 2 = Stein von Spieler 2.
 * Anzeige (toString): 0 -> ".", 1 -> "X", 2 -> "O".
 *
 * Regel: Ein Zug ist gueltig, wenn man in mindestens einer der 8 Richtungen
 * eine ununterbrochene Reihe GEGNERISCHER Steine "einklemmt", die von einem
 * EIGENEN Stein abgeschlossen wird. Alle so eingeklemmten Steine werden umgedreht.
 *
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spielfeld_reversi{

    public static final int GROESSE = 6;

    /** Die 8 Richtungen (gegeben - nutze diese in den Schleifen). */
    private static final int[] DZ = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] DS = { -1, 0, 1, -1, 1, -1, 0, 1 };

    private int[][] feld;

    /** Erzeugt das Brett mit der Startaufstellung in der Mitte. (gegeben - nicht aendern) */
    public Spielfeld_reversi() {
        feld = new int[GROESSE][GROESSE];
        feld[2][2] = 1; feld[3][3] = 1;
        feld[2][3] = 2; feld[3][2] = 2;
    }

    /** TODO 4 (leicht): Wert an der Stelle (z, s) zurueckgeben. */
    public int getZelle(int z, int s) {
        // TODO 4
        return feld[z][s];
    }

    /**
     * TODO 5 (leicht): Gegner-Nummer.
     * Gib 2 zurueck, wenn nummer 1 ist, sonst 1.
     */
    public int gegner(int nummer) {
        // TODO 5
    	int n = 0; ;
    	if(nummer == 1) {
    		n = 2;
    	}
    	else {
    		n = 1;
    	}
        return n;
    }

    /**
     * TODO 6 (leicht): Liegt (z, s) im Feld?
     * true, wenn 0 <= z < Zeilenanzahl UND 0 <= s < Spaltenanzahl.
     * Hinweis: feld.length und feld[0].length.
     */
    public boolean imFeld(int z, int s) {
        // TODO 6
        return (z >= 0 && z < feld.length) && (s >= 0 && s < feld[0].length) ;
    }

    /**
     * TODO 7 (mittel): Zaehlt die Steine von nummer auf dem ganzen Feld.
     */
    public int zaehleSteine(int nummer) {
        // TODO 7
    	int count = 0;
    	for(int i = 0; i < feld.length; i++) {
    		for(int j = 0; j < feld[0].length; j++) {
    			if(feld[i][j] == nummer) {
    				count++;
    			}
    		}
    	}
        return count;
    }

    /**
     * TODO 8 (mittel): true, wenn KEINE Zelle mehr 0 ist (Feld voll).
     */
    public boolean istVoll() {
        // TODO 8
    	for(int i = 0; i < feld.length; i++) {
    		for(int j = 0; j < feld[0].length; j++) {
    			if(feld[i][j] == 0) {
    				return false;
    			}
    		}
    	}	
        return true;
    }

    /**
     * TODO 9 (schwer): Prueft EINE Richtung (dz, ds) ab Stelle (z, s) fuer nummer.
     * Gibt true zurueck, wenn direkt neben (z, s) in dieser Richtung mindestens ein
     * GEGNERISCHER Stein liegt und die Reihe von einem EIGENEN Stein (nummer)
     * abgeschlossen wird.
     * Algorithmus:
     *   1. gehe einen Schritt: cz = z+dz, cs = s+ds.
     *   2. Ist (cz, cs) nicht im Feld oder KEIN Gegnerstein? -> return false.
     *   3. Laufe weiter, solange dort Gegnersteine liegen.
     *   4. Stehst du am Ende im Feld auf einem EIGENEN Stein? -> return true, sonst false.
     */
    public boolean pruefeRichtung(int z, int s, int dz, int ds, int nummer) {
        // TODO 9
    	int cz = z + dz;
    	int cs = s + ds;
    	if(!imFeld(cz,cs) || gegner(getZelle(cz,cs)) != nummer) { 
    		return false;
    	}
    	
    	do {
    		cz++;
    		cs++;
    	}while(imFeld(cz,cs) && gegner(getZelle(cz,cs)) != nummer);
    	
    	if((cz == feld.length - 1 ) && (cs == feld[0].length - 1) && gegner(getZelle(cz,cs)) == nummer ) {
    		return true; // eigenen stein gegner(getZelle(cz,cs)) == nummer
    	}
    	else {
    		return false;
    	}
        
    }

    /**
     * TODO 10 (mittel): Ist der Zug (z, s) fuer nummer gueltig?
     * true, wenn die Stelle leer ist (== 0) UND in mindestens einer der 8 Richtungen
     * pruefeRichtung(...) true ergibt.
     * Hinweis: ueber die Arrays DZ/DS laufen.
     */
    public boolean istZugGueltig(int z, int s, int nummer) {
        // TODO 10
    	int count = 0;
    	if(feld[z][s] == 0) {
    		for(int i = 0; i < DZ.length; i++) {
    			for(int j = 0; j < DS.length; j++) {
    				if(pruefeRichtung(z,s,i, j,  nummer)) {
    					count++;
    				}
    			}
    		}
    	}
    	
    	if(count >= 1) {
    		return true;
    	}
    	else {
    		return false;
    	}
       
    }

    /**
     * TODO 11 (schwer): Setzt einen Stein von nummer auf (z, s) und dreht um.
     * 1. feld[z][s] = nummer.
     * 2. Fuer jede der 8 Richtungen, in der pruefeRichtung(...) true ist:
     *      laufe von (z+dz, s+ds) weiter und setze jeden GEGNERSTEIN auf nummer,
     *      bis ein eigener Stein kommt.
     * Hinweis: DZ/DS benutzen; pruefeRichtung garantiert, dass die Reihe endet.
     */
    public void setzeStein(int z, int s, int nummer) {
        // TODO 11
    	feld[z][s] = nummer;
    	for(int i = 0; i < DZ.length; i++) {
			for(int j = 0; j < DS.length; j++) {
				if(pruefeRichtung(z,s,i, j, nummer)) {
					int cz = z+i;
					int cs = s+j;
					while(imFeld(cz,cs) && feld[cz][cs] != nummer) {
						if(feld[cz][cs] != nummer) {
							feld[cz][cs] = nummer;
						}
						cz++;
						cs++;
					}
				}
			}
    	}	
    }
    

    /**
     * TODO 12 (mittel): Hat nummer irgendwo einen gueltigen Zug?
     * true, wenn es mindestens eine Stelle (z, s) mit istZugGueltig(...) gibt.
     */
    public boolean hatGueltigenZug(int nummer) {
        // TODO 12
    	int count = 0;
    	for(int i = 0; i < feld.length; i++) {
    		for(int j = 0; j < feld[0].length; j++) {
    			if(istZugGueltig(i,j,nummer)) {
    				count ++;
    			}
    		}
    	}	
    	
    	if(count >= 1) {
    		return true;
    	}
        return false;
    }

    /**
     * TODO 13 (mittel-schwer): Baut die Anzeige des Feldes als String.
     * Token: 0 -> ".", 1 -> "X", 2 -> "O". Zellen mit Leerzeichen getrennt,
     * Zeilen mit '\n' getrennt.
     */
    public String toString() {
        // TODO 13
    	
    	String erg = "\t";
    	
    	for(int i = 0; i < feld.length; i++) {
    		erg += i + "\t";
    	}	
    	
    	erg+= "\n";
    	for(int i = 0; i < feld.length; i++) {
    		erg += i + "\t";
    		for(int j = 0; j < feld[0].length; j++) {
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

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public int[][] getFeld() {
        return feld;
    }

    /** Setzt ein bekanntes Feld fuer die Tests. */
    public void setFeldFuerTest(int[][] neu) {
        this.feld = neu;
    }
}