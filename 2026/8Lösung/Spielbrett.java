/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 ****************************************************************************************************** */

public class Spielbrett {

	/* NICHT AENDERN! */
	public static final int GROESSE = 7;

	/*
	 * An jeder Stelle von feld steht entweder eine Referenz auf ein
	 * Figur-Objekt oder null, wenn dort keine Figur steht.
	 * In mauer steht true, wenn das Feld durch eine Mauer besetzt ist.
	 */
	private Figur[][] feld;
	private boolean[][] mauer;
	private int n;

	/*
	 * NICHT AENDERN!
	 * Erzeugt das Brett und setzt die Mauern.
	 */
	public Spielbrett() {
		this.n = GROESSE;
		this.feld = new Figur[n][n];
		this.mauer = new boolean[n][n];

		mauer[1][1] = true;
		mauer[1][5] = true;
		mauer[3][3] = true;
		mauer[5][1] = true;
		mauer[5][5] = true;
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 */
	public int getN() {
		return n;
	}

	/*
	 * NICHT AENDERN! Fuer Testzwecke!
	 * Liefert die Figur auf dem Feld (z, s) oder null, wenn dort keine steht.
	 */
	public Figur getFigur(int z, int s) {
		return feld[z][s];
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	private int vorzeichen(int zahl) {
		if (zahl > 0) {
			return 1;
		}
		if (zahl < 0) {
			return -1;
		}
		return 0;
	}

	/*
	 * NICHT AENDERN! Hilfsmethode, da die Klasse Math nicht benutzt werden darf.
	 */
	private int betrag(int zahl) {
		if (zahl < 0) {
			return -zahl;
		}
		return zahl;
	}

	/*
	 * Gibt true zurueck, wenn auf dem Feld (z, s) eine Mauer liegt; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Koordinaten
	 * aufgerufen wird.
	 */
	public boolean istMauer(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return mauer[z][s];
	}

	/*
	 * Stellt die Figur auf das Feld (z, s) und teilt der Figur ihre neue
	 * Position mit. Wird null uebergeben, so wird das Feld einfach geleert.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Koordinaten
	 * aufgerufen wird.
	 */
	public void setzeFigur(int z, int s, Figur figur) {
		// TODO
		// Anweisung(en) hinzufuegen
		if(figur != null) {
			feld[z][s] = figur;
			feld[z][s].setzePosition(z, s);
		}
		else {
			feld[z][s] = figur;
		}
	}

	/*
	 * Gibt true zurueck, wenn das Feld (z, s) betreten werden kann, wenn dort
	 * also WEDER eine Mauer liegt NOCH eine Figur steht; false sonst.
	 * Es ist sichergestellt, dass die Methode nur mit gueltigen Koordinaten
	 * aufgerufen wird.
	 */
	public boolean istFrei(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen
		
		// unbedingt return-Anweisung aendern
		return !mauer[z][s] && feld[z][s] == null;
	}

	/*
	 * Gibt true zurueck, wenn (z, s) existierende Koordinaten des Brettes sind;
	 * false sonst.
	 */
	public boolean imBrett(int z, int s) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return z >= 0 && z < feld.length && s >= 0 && s < feld[0].length;
	}

	/*
	 * Stellt die Figuren beider Spieler in der Grundaufstellung auf:
	 * Die Figuren von spieler1 kommen in die Zeile n-1, die Figuren von
	 * spieler2 in die Zeile 0. In Spalte 0 steht jeweils die Figur mit dem
	 * Index 0, in Spalte 1 die Figur mit dem Index 1 und so weiter.
	 */
	public void grundaufstellung(Spieler spieler1, Spieler spieler2) {
		// TODO
		// Anweisung(en) hinzufuegen
		for(int i = 0; i < feld[0].length; i++) {
			feld[n-1][i] = spieler1.getFigur(i);
			feld[n-1][i].setzePosition(n-1, i);
			feld[0][i] = spieler2.getFigur(i);
			feld[0][i].setzePosition(0, i);
		}
		
	}

	/*
	 * Gibt true zurueck, wenn der Zug von (vonZeile, vonSpalte) nach
	 * (nachZeile, nachSpalte) fuer den uebergebenen Spieler gueltig ist;
	 * false sonst.
	 */
	public boolean istZugGueltig(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte,
			Spieler spieler) {
		

		
		// Liegen Startfeld UND Zielfeld im Brett?
		// TODO
		// Anweisung(en) hinzufuegen
		if(!imBrett(vonZeile,vonSpalte) || !imBrett(nachZeile,nachSpalte)) {
			return false;
		}


		// Sind Startfeld und Zielfeld verschieden?
		// TODO
		// Anweisung(en) hinzufuegen
		
		if(vonZeile == nachZeile && vonSpalte == nachSpalte) {
			return false;
		}
		
		// Steht auf dem Startfeld ueberhaupt eine Figur, und gehoert sie dem Spieler?
		// Hinweis: figur.getBesitzer() == spieler
		// TODO
		// Anweisung(en) hinzufuegen
		if(feld[vonZeile][vonSpalte] == null || feld[vonZeile][vonSpalte].getBesitzer() != spieler) {
			return false;
		}

		// Liegt auf dem Zielfeld eine Mauer?
		// TODO
		// Anweisung(en) hinzufuegen
		if(mauer[nachZeile][nachSpalte]) {
			return false;
		}


		// Steht auf dem Zielfeld eine Figur des Spielers selbst?
		// TODO
		// Anweisung(en) hinzufuegen
		if(feld[nachZeile][nachSpalte]!= null && feld[nachZeile][nachSpalte].getBesitzer() == spieler) {
			return false;
		}


		// Zugrichtung bestimmen: Wie veraendern sich Zeile und Spalte?
		// Ist der Zug gerade (Zeile oder Spalte bleibt gleich)
		// oder diagonal (Zeile und Spalte aendern sich um denselben Betrag)?
		// Jede andere Richtung ist nie erlaubt.
		// TODO
		// Anweisung(en) hinzufuegen
		int dZeile = nachZeile-vonZeile;
		int dSpalte = nachSpalte -vonSpalte ;
		boolean gerade = (vonZeile == nachZeile) || (vonSpalte == nachSpalte);
		boolean diagonal = betrag(dZeile)== betrag(dSpalte);

		if(!gerade && !diagonal) {
			return false;
		}
		// Passt die Richtung zum Typ der Figur?
		// Hinweis: figur.ziehtGerade(), figur.ziehtDiagonal()
		// TODO
		// Anweisung(en) hinzufuegen
		if((gerade && !feld[vonZeile][vonSpalte].ziehtGerade()) || (diagonal && !feld[vonZeile][vonSpalte].ziehtDiagonal())) {
			return false;
		}


		// Ist der Weg frei? Alle Felder ZWISCHEN Start und Ziel muessen frei sein,
		// duerfen also weder eine Mauer noch eine Figur enthalten.
		// Das Zielfeld selbst gehoert nicht zum Weg.
		// Hinweis: vorzeichen(...) liefert die Schrittrichtung, istFrei(...) prueft ein Feld.
		// TODO
		// Anweisung(en) hinzufuegen
		int zeile = vonZeile + vorzeichen(dZeile);
		int spalte = vonSpalte + vorzeichen(dSpalte);
		
		while(imBrett(zeile,spalte) && ((zeile < nachZeile || spalte < nachSpalte))) {
			if(!istFrei(zeile,spalte))return false;
			zeile += vorzeichen(dZeile);
			spalte += vorzeichen(dSpalte);
		}

		// TODO
		// return-Anweisung ggf. aendern
		return true;
//		}
//		return false;
	}

	/*
	 * Fuehrt den Zug von (vonZeile, vonSpalte) nach (nachZeile, nachSpalte) aus.
	 * Es ist sichergestellt, dass die Methode nur mit einem gueltigen Zug
	 * aufgerufen wird.
	 *
	 * Steht auf dem Zielfeld eine gegnerische Figur, so wird sie geschlagen.
	 * Danach steht die gezogene Figur auf dem Zielfeld, das Startfeld ist leer.
	 * Zum Schluss VERWANDELT sich die gezogene Figur.
	 *
	 * Rueckgabewert: die geschlagene Figur, oder null, wenn nichts geschlagen wurde.
	 */
	public Figur fuehreZugAus(int vonZeile, int vonSpalte, int nachZeile, int nachSpalte) {
		// TODO
		// ggf Anweisungen hinzufuegen
		Figur figur = null;
		if(feld[vonZeile][vonSpalte] != null) {
			if(feld[nachZeile][nachSpalte] != null && !mauer[nachZeile][nachSpalte]) {
				
				figur = feld[nachZeile][nachSpalte];
				feld[nachZeile][nachSpalte].schlagen();
				feld[vonZeile][vonSpalte].setzePosition(nachZeile, nachSpalte);
				feld[nachZeile][nachSpalte] = feld[vonZeile][vonSpalte];
				feld[vonZeile][vonSpalte] = null;
				
			}
			else {
				figur = feld[nachZeile][nachSpalte];
				feld[vonZeile][vonSpalte].setzePosition(nachZeile, nachSpalte);
				feld[nachZeile][nachSpalte] = feld[vonZeile][vonSpalte];
				feld[vonZeile][vonSpalte] = null;
			}
		}
		feld[nachZeile][nachSpalte].verwandle();

		// unbedingt return-Anweisung aendern
		return figur;
	}

	/*
	 * Gibt true zurueck, wenn der uebergebene Spieler mindestens einen
	 * gueltigen Zug hat; false sonst.
	 *
	 * Hinweis: Gehen Sie alle Felder des Brettes durch. Steht dort eine Figur
	 * des Spielers, so probieren Sie fuer diese Figur alle moeglichen Zielfelder
	 * durch.
	 */
	public boolean hatZugMoeglichkeit(Spieler spieler) {
		// TODO
		// ggf Anweisungen hinzufuegen
		for(int i = 0; i < feld.length; i++) {
			for(int j = 0; j < feld.length; j++) {
				if(feld[i][j] != null && feld[i][j].getBesitzer() == spieler ) {
					for(int k = 0; k < feld.length; k++) {
						for(int l = 0; l < feld.length; l++) {
							if(istZugGueltig(i, j, k, l,spieler))return true;
						}
					}	
				}
			}
		}

		// unbedingt return-Anweisung aendern
		return false;
	}

	/*
	 * NICHT AENDERN!
	 * Verwenden Sie die Methode zur Ausgabe des Spielbretts.
	 */
	public String toString() {
		String ausgabe = "     ";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += String.format("%4d", spalte);
		}
		ausgabe += "\n";

		ausgabe += "----+";
		for (int spalte = 0; spalte < n; spalte++) {
			ausgabe += "----";
		}
		ausgabe += "\n";

		for (int zeile = 0; zeile < n; zeile++) {
			ausgabe += String.format("%3d |", zeile);
			for (int spalte = 0; spalte < n; spalte++) {
				if (mauer[zeile][spalte]) {
					ausgabe += String.format("%4s", "##");
				} else if (feld[zeile][spalte] == null) {
					ausgabe += String.format("%4s", "-");
				} else {
					ausgabe += String.format("%4s", feld[zeile][spalte]);
				}
			}
			ausgabe += "\n";
		}

		return ausgabe;
	}
}