
import java.util.Random;

/**
 * Memory - das Spielfeld und die Spiellogik.
 *
 * Feld: 4 Zeilen x 6 Spalten = 24 Stellen = 12 Paare.
 * Inhalt: Buchstaben A bis L, jeweils als Gross- UND Kleinbuchstabe.
 *
 * Die TODO-Nummern entsprechen der empfohlenen Reihenfolge (siehe README).
 * Kein 'ae/oe/ue/ss' im Code verwenden.
 */
public class Memory {

    public static final int ZEILEN = 4;
    public static final int SPALTEN = 6;

    private char[][] feld;
    private boolean[][] aufgedeckt;
    private Random rd;

    /** Erzeugt ein neues, gemischtes Memory-Spielfeld. (gegeben - nicht aendern) */
    public Memory() {
        rd = new Random();
        feld = new char[ZEILEN][SPALTEN];
        aufgedeckt = new boolean[ZEILEN][SPALTEN];
        initialisiereFeld();
        mischeFeld();
    }

    /**
     * TODO 9 (schwer): Fuellt das Feld alphabetisch mit Hilfe der ASCII-Zahlen.
     * Zuerst die Grossbuchstaben A(65) bis L(76), dann die Kleinbuchstaben a(97) bis l(108).
     * Vor dem Mischen soll das Feld so aussehen:
     *     A B C D E F
     *     G H I J K L
     *     a b c d e f
     *     g h i j k l
     * Hinweise:
     *   - 'char c = 65;' ist dasselbe wie 'char c = (char) 65;' und ergibt 'A'.
     *   - Du brauchst eine fortlaufende Zaehlung von 0..23 ueber alle 24 Stellen.
     *   - Die ersten 12 Stellen sind gross (65 + index), die letzten 12 klein
     *     (97 + (index - 12)). Mit zwei Schleifen ueber Zeilen/Spalten loesen.
     */
    private void initialisiereFeld() {
        // TODO 9
    	int count = 0;
    	for(int i = 0; i < feld.length/2; i++) {
    		for(int j = 0; j < feld[i].length; j++) {
    			feld[i][j] = (char)('A' + count);
    			count++;
    		}
    	}
    	
    	count = 0;
    	for(int i = 2; i < feld.length; i++) {
    		for(int j = 0; j < feld[i].length; j++) {
    			feld[i][j] = (char)('a' + count);
    			count++;
    		}
    	}
    }

    /** Mischt das Feld mit dem vorgegebenen Algorithmus. (gegeben - nicht aendern) */
    private void mischeFeld() {
        for (int z = ZEILEN - 1; z >= 0; z--) {
            for (int s = SPALTEN - 1; s >= 0; s--) {
                int rd1 = rd.nextInt(z + 1);
                int rd2 = rd.nextInt(s + 1);
                char tmp = feld[z][s];
                feld[z][s] = feld[rd1][rd2];
                feld[rd1][rd2] = tmp;
            }
        }
    }

    /**
     * TODO 10 (mittel - WICHTIG): Prueft mit ASCII-RECHNUNG, ob zwei Zeichen zusammenpassen.
     * Zwei Zeichen passen, wenn es derselbe Buchstabe ist, einmal gross und einmal klein
     * (z. B. 'A' und 'a', oder 'b' und 'B').
     * Der ASCII-Abstand zwischen Gross- und Kleinbuchstabe ist IMMER 32.
     * Eine grosse if-Abfrage (if (c1=='A' && c2=='a' || ...)) ist VERBOTEN.
     * Hinweis: Betrachte die Differenz (c1 - c2). Beachte, dass sie +32 oder -32 sein kann.
     */
    public boolean passenZusammen(char c1, char c2) {
        // TODO 10
    	return c1 == (char)(c2+32) || c1 == (char)(c2 -32);
        
    }

    /**
     * TODO 8 (mittel): Gueltige Eingabe?
     * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Feldes sind UND
     * die Stelle noch NICHT aufgedeckt ist. Sonst false.
     * Hinweis: zuerst die Grenzen pruefen, dann erst auf aufgedeckt[z][s] zugreifen.
     */
    public boolean gueltigeEingabe(int z, int s) {
        // TODO 8
    	if(z >= 0 && z < aufgedeckt.length && s >= 0&& s < aufgedeckt[z].length) {
    		if(!aufgedeckt[z][s]) {
    			return true;
    		}
    	}
        return false;
    }

    /**
     * TODO 6 (leicht): Deckt die Stelle (z, s) auf (Markierung in aufgedeckt setzen).
     */
    public void aufdecken(int z, int s) {
        // TODO 6
    	aufgedeckt[z][s] = true;
    }

    /**
     * TODO 5 (leicht): Gibt das Zeichen an der Stelle (z, s) zurueck.
     */
    public char getZeichen(int z, int s) {
        // TODO 5
        return feld[z][s];
    }

    /**
     * TODO 7 (mittel): Gibt true zurueck, wenn KEINE Stelle mehr verdeckt ist
     * (also alle Stellen aufgedeckt sind), sonst false.
     */
    public boolean allesAufgedeckt() {
        // TODO 7
    	for(int i = 0; i < aufgedeckt.length; i++) {
    		for(int j = 0; j < aufgedeckt.length; j++) {
    			if(aufgedeckt[i][j] == false) {
    				return false;
    			}
    		}
    	}	
        return true;
    }

    /**
     * TODO 11 (schwer): Baut die Anzeige des Feldes als String.
     * - Aufgedeckte Stellen zeigen ihren Buchstaben.
     * - Verdeckte Stellen zeigen ein '-'.
     * - Pro Zeile die 6 Zeichen mit EINEM Leerzeichen getrennt.
     * - Die Zeilen mit einem Zeilenumbruch ('\n') getrennt.
     * Beispiel (nichts aufgedeckt):
     *     - - - - - -
     *     - - - - - -
     *     - - - - - -
     *     - - - - - -
     * Hinweis: Baue den String mit einer String-Variablen Stueck fuer Stueck zusammen.
     */
    public String toString() {
        // TODO 11
    	String x = "";
    	
    	for(int i = 0; i < feld.length; i++) {
    		for(int j = 0; j < feld[i].length; j++) {
    			
    			if(!aufgedeckt[i][j]) {
    				x += "-" + "\t";
    			}
    			else {
    				x += feld[i][j] + "\t";
    			}
    			
    		}
    		x += "\n";
    	}
        return x;
    }

    /** Nur fuer die UnitTests: liefert das interne Feld. (gegeben - nicht aendern) */
    public char[][] getFeld() {
        return feld;
    }
}