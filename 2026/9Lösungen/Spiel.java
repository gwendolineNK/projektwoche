import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare der einzelnen Methoden!
 * Fuegen Sie Code, insbesondere weitere Methoden, hinzu!
 * Aendern Sie vom vorhandenen Code nur die return-Anweisungen
 *
 * NUR in dieser Klasse duerfen Konsolenein- und -ausgaben erfolgen!
 ****************************************************************************************************** */

public class Spiel {

	/* NICHT AENDERN! */
	private static Scanner sc = new Scanner(System.in);

	/* Die Objekte des aktuellen Spiels. */
	private static Spieler spieler1;
	private static Spieler spieler2;
	private static Spielbrett brett;

	/*
	 * Vervollstaendigen Sie die main-Methode.
	 * Sie begruesst den Nutzer und ruft das Menue auf.
	 */
	public static void main(String[] args) {
		// TODO
		// Anweisung(en) hinzufuegen
		System.out.println("Herzlich Willkomen zu Reversi Light");
		menu();
		sc.close();
	}

	/*
	 * Gibt das Auswahlmenue aus und liest die Auswahl ein.
	 * Bei 1 wird ein Spiel vorbereitet und gespielt, danach erscheint das
	 * Menue erneut. Bei 2 wird das Programm beendet.
	 */
	public static void menu() {
		// TODO
		// Anweisung(en) hinzufuegen
		boolean spielen = true;
		do {
			int eingabe = checkEingabeMenu();
			if(eingabe == 1) {
				spielVorbereiten();
				spielen();
			}
			else if(eingabe == 2) {
				System.out.println("Das Programm wird beendet.");
				spielen = false;
			}
		}while(spielen);
		
		
	
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese 1 oder 2 ist, und gibt sie zurueck.
	 */
	public static int checkEingabeMenu() {
		// TODO
		// ggf Anweisungen hinzufuegen
		int eingabe;
		do {
			System.out.println("1 - Spiel starten");
			System.out.println("2 - Programm beenden");
			eingabe = sc.nextInt();
		}while(!(eingabe == 1 || eingabe == 2));

		// unbedingt return-Anweisung aendern
		return eingabe;
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese gerade und mindestens 4 ist,
	 * und gibt sie zurueck.
	 */
	public static int checkEingabeGroesse() {
		// TODO
		// ggf Anweisungen hinzufuegen
		int zahl;
		do {
			//System.out.println("Wie gross Soll das Brett sein?");
			zahl = sc.nextInt();
		}while(!(zahl %2 == 0 && zahl >= 4));

		// unbedingt return-Anweisung aendern
		return zahl;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Brettgroesse ein.
	 * Erzeugt die beiden Spieler (Spieler 1 mit dem Zeichen "X", Spieler 2 mit
	 * dem Zeichen "O"), macht sie zu Gegnern, erzeugt das Brett und baut die
	 * Grundaufstellung auf.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
		
		System.out.println("Gib den Namen von Spieler 1 ein");
		String name1 = sc.next();
		spieler1 = new Spieler(name1,"X");
	
		System.out.println("Gib den Namen von Spieler 2 ein");
		String name2 = sc.next();
		spieler2 = new Spieler(name2,"O");
		
		spieler1.setzeGegner(spieler2);
		spieler2.setzeGegner(spieler1);
		
		System.out.println("Wie gross Soll das Brett sein?");
		int n = checkEingabeGroesse();
		brett = new Spielbrett(n);
		
		brett.grundaufstellung(spieler1, spieler2);
	}

	/*
	 * Steuert ein komplettes Spiel.
	 * Spieler 1 beginnt. Kann der Spieler, der an der Reihe ist, nicht ziehen,
	 * wird das gemeldet. Das Spiel endet, wenn das Brett voll ist oder keiner
	 * der beiden Spieler mehr ziehen kann.
	 * Am Ende wird das Ergebnis ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
		Spieler aktuell = spieler1;
		while(!brett.istVoll() && brett.hatGueltigenZug(aktuell)) {
			spielzug(aktuell);
			aktuell = (aktuell == spieler1) ? spieler2 : spieler1 ;
		}
		auswertung();
	}

	/*
	 * Fuehrt den Zug des uebergebenen Spielers aus.
	 * Das Brett und der Punktestand werden ausgegeben, danach werden so lange
	 * Zeile und Spalte eingelesen, bis ein gueltiger Zug genannt wird.
	 * Anschliessend wird der Zug ausgefuehrt und die Anzahl der umgedrehten
	 * Steine gemeldet.
	 */
	public static void spielzug(Spieler aktiv) {
		// TODO
		// Anweisung(en) hinzufuegen
		System.out.println(brett);
		System.out.println(spieler1.getName() + "(" + spieler1.getZeichen() + ") :" + brett.zaehleSteine(spieler1) + "\t" + "\t"
		+ spieler2.getName() + "(" + spieler2.getZeichen() + ") :" + brett.zaehleSteine(spieler2));
		
		System.out.println(aktiv.getName() + " ist an der Reihe.");
		int z,s;		
		do {
			System.out.println("Zeile:");
			z = sc.nextInt();
			System.out.println("Spalte:");
			s = sc.nextInt();
			if(!brett.istZugGueltig(z, s, aktiv)) {
				System.out.println("Diese Zug ist nicht gueltig.");
			}
		}while(!brett.istZugGueltig(z, s, aktiv));
		
		brett.setzeStein(z, s, aktiv);
		int stein = brett.setzeUndDrehe(z, s, aktiv);
		System.out.println(aktiv.getName() + " dreht " + stein + " um.");
	}

	/*
	 * Gibt den Endstand und den Gewinner aus.
	 * Bei Gleichstand wird ein Unentschieden gemeldet.
	 */
	public static void auswertung() {
		// TODO
		// Anweisung(en) hinzufuegen
		if(brett.zaehleSteine(spieler1) > brett.zaehleSteine(spieler2)) {
			System.out.println(spieler1.getName() + " hat gewonnen.");
		}
		else if(brett.zaehleSteine(spieler1) < brett.zaehleSteine(spieler2)) {
			System.out.println(spieler2.getName() + " hat gewonnen.");
		}
		else if(brett.zaehleSteine(spieler1) == brett.zaehleSteine(spieler2)) {
			System.out.println("Das Spiel ist untentschieden.");
		}
	}
}