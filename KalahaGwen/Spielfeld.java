/**
 * Spielfeld - das Kalaha-Brett.
 *
 * 14 Mulden in einem int-Array (im Kreis, gegen den Uhrzeigersinn):
 *   Index 0-5   : die 6 Mulden von Spieler 1
 *   Index 6     : Kalaha (Speicher) von Spieler 1
 *   Index 7-12  : die 6 Mulden von Spieler 2
 *   Index 13    : Kalaha (Speicher) von Spieler 2
 *
 * Zug (saeen): alle Steine aus einer eigenen Mulde nehmen und einen nach dem
 * anderen im Kreis in die folgenden Mulden legen. Das GEGNERISCHE Kalaha wird
 * dabei UEBERSPRUNGEN.
 *
 * Kein 'ae/oe/ue/ss' im Code. TODO-Nummern = empfohlene Reihenfolge (siehe README).
 */
public class Spielfeld {

    public static final int MULDEN = 14;
    public static final int P1_KALAHA = 6;
    public static final int P2_KALAHA = 13;
    public static final int STEINE_PRO_MULDE = 4;

    private int[] mulden;

    /** Erzeugt das Startbrett: jede Spielmulde 4 Steine, Kalahas leer. (gegeben) */
    public Spielfeld() {
        mulden = new int[MULDEN];
        for (int i = 0; i < MULDEN; i++) {
            if (i != P1_KALAHA && i != P2_KALAHA) {
                mulden[i] = STEINE_PRO_MULDE;
            }
        }
    }

    /** TODO 4 (leicht): Anzahl Steine in Mulde i zurueckgeben. */
    public int getMulde(int i) {
        // TODO 4
  
        return mulden[i];
    }

    /**
     * TODO 5 (leicht): Index des Kalaha (Speicher) von nummer.
     * Spieler 1 -> P1_KALAHA (6), Spieler 2 -> P2_KALAHA (13).
     */
    public int kalahaIndex(int nummer) {
        // TODO 5
    	if(nummer == 1) {
    		return P1_KALAHA;
    	}
        return P2_KALAHA;
    }

    /**
     * TODO 6 (leicht): Anzahl Steine im Kalaha von nummer.
     * Hinweis: mulden[kalahaIndex(nummer)].
     */
    public int getKalaha(int nummer) {
        // TODO 6
        return mulden[kalahaIndex(nummer)];
    }

    /**
     * TODO 7 (mittel): Gehoert die Mulde 'index' dem Spieler 'nummer'?
     * Spieler 1: 0 <= index <= 5. Spieler 2: 7 <= index <= 12.
     * (Die Kalahas 6 und 13 gehoeren NICHT dazu.)
     */
    public boolean eigeneMulde(int index, int nummer) {
        // TODO 7
    	if(nummer == 1) {
    		return index >= 0 && index <= 5;
    	}
        return index >= 7 && index <= 12;
    }

    /**
     * TODO 8 (mittel): Ist der Zug aus Mulde 'index' fuer nummer gueltig?
     * true, wenn es eine EIGENE Mulde ist UND dort mindestens ein Stein liegt.
     */
    public boolean istZugGueltig(int index, int nummer) {
        // TODO 8
 
        return eigeneMulde(index,nummer) && mulden[index] != 0;
    }

    /**
     * TODO 9 (schwer): Saeen. Nimmt alle Steine aus Mulde 'index' und verteilt sie
     * einen nach dem anderen im Kreis auf die folgenden Mulden.
     * Das GEGNERISCHE Kalaha wird uebersprungen. Gibt den Index der ZULETZT
     * befuellten Mulde zurueck.
     * Algorithmus:
     *   1. steine = mulden[index]; mulden[index] = 0; pos = index.
     *   2. solange steine > 0:
     *        pos = (pos + 1) % MULDEN;                 // im Kreis weiter
     *        wenn pos == gegnerisches Kalaha: continue; // ueberspringen
     *        mulden[pos]++; steine--;
     *   3. return pos.
     * Hinweis: gegnerisches Kalaha = (nummer == 1) ? P2_KALAHA : P1_KALAHA.
     */
    public int saee(int index, int nummer) {
        // TODO 9
    	int steine = mulden[index];
    	mulden[index] = 0;
    	int pos = index;
    	while(steine > 0) {
    		pos = (pos + 1) % MULDEN; 
    		if(pos == ((nummer == 1) ? P2_KALAHA : P1_KALAHA)) {
    			continue;
    		}
    		mulden[pos]++;
    		steine--;
    	}
        return pos;
    }

    /**
     * TODO 10 (leicht): Landete der letzte Stein im EIGENEN Kalaha?
     * (Dann darf der Spieler nochmal ziehen.)
     * true, wenn letzterIndex == kalahaIndex(nummer).
     */
    public boolean letzteImEigenenKalaha(int letzterIndex, int nummer) {
        // TODO 10
        return letzterIndex == kalahaIndex(nummer);
    }

    /**
     * TODO 11 (mittel): Ist das Spiel zu Ende?
     * true, wenn ALLE Mulden von Spieler 1 (0-5) leer sind ODER ALLE Mulden von
     * Spieler 2 (7-12) leer sind.
     */
    public boolean istSpielEnde() {
        // TODO 11
    	int counts1 = 0;
    	for(int i = 0; i < 6; i++) {
    		if(getMulde(i) == 0) {
    			counts1++;
    		}
    	}
    	int counts2 = 0;
    	for(int i = 7; i < 13; i++) {
    		if(getMulde(i) == 0) {
    			counts2++;
    		}
    	}
    	if(counts1 == 6 || counts2 == 6) {
    		return true;
    	}
        return false;
    }

    /**
     * TODO 12 (mittel): Baut eine Anzeige des Bretts als String.
     * Zeige mindestens die 14 Werte des Arrays lesbar an (Format frei waehlbar,
     * aber die Zahlen muessen vorkommen). Zeilen mit '\n' trennen.
     */
    public String toString() {
        // TODO 12
    	String erg = "";
    	for(int i = 0; i < 6; i++) {
    		erg += mulden[i] + "\t";
    		
    	}	
    		erg += "\n";
    		
    		erg += mulden[6] + "\n";
    	for(int i = 7; i < 13; i++) {
    		erg += mulden[i] + "\t";
    		
    	}
    	erg += "\n";
    	erg += mulden[13] + "\n";
    		
        return erg;
    }

    // ---------- Test-Hooks (gegeben - nicht aendern) ----------

    public int[] getMulden() {
        return mulden;
    }

    /** Setzt ein bekanntes Brett fuer die Tests. */
    public void setMuldenFuerTest(int[] neu) {
        this.mulden = neu;
    }
}