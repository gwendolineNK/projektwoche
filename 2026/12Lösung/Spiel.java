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

	/* NICHT AENDERN! Nach so vielen Runden endet das Spiel spaetestens. */
	public static final int MAX_RUNDEN = 50;

	/* Die Objekte des aktuellen Spiels. */
	private static Spieler spieler1;
	private static Spieler spieler2;
	private static Schlachtfeld schlachtfeld;

	/*
	 * Vervollstaendigen Sie die main-Methode.
	 * Sie begruesst den Nutzer und ruft das Menue auf.
	 */
	public static void main(String[] args) {
		// TODO
		// Anweisung(en) hinzufuegen
		menu();
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
			else {
				System.out.println("Das Programm wird beendet.");
				spielen = false;
			}
		}while(spielen);
		
		sc.close();
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
		}while(eingabe != 1 && eingabe != 2);
		// unbedingt return-Anweisung aendern
		return eingabe;
	}

	/*
	 * Liest so lange eine Zahl ein, bis diese mindestens 4 ist, und gibt sie
	 * zurueck.
	 */
	public static int checkEingabeGroesse() {
		// TODO
		// ggf Anweisungen hinzufuegen
		int n;
		do {
			System.out.println("Wie gross soll das Spielfeld sein?");
			n = sc.nextInt();
		}while(n < 4);

		// unbedingt return-Anweisung aendern
		return n;
	}

	/*
	 * Liest die Namen der beiden Spieler und die Groesse des Schlachtfeldes ein.
	 * Erzeugt die beiden Spieler (Spieler 1 mit dem Zeichen "X", Spieler 2 mit
	 * dem Zeichen "O"), macht sie zu Gegnern, erzeugt das Schlachtfeld und baut
	 * die Grundaufstellung auf.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
		
		System.out.println("Gib den Namen des Spielers 1 ein:");
		String name1 = sc.next();
		spieler1 = new Spieler(name1,"X");
		
		System.out.println("Gib den Namen des Spielers 2 ein:");
		String name2 = sc.next();
		spieler2 = new Spieler(name2,"O");
	
		spieler1.setzeGegner(spieler2);

		int n = checkEingabeGroesse();
		schlachtfeld = new Schlachtfeld(n);
		schlachtfeld.grundaufstellung(spieler1, spieler2);
		
	}

	/*
	 * Steuert ein komplettes Spiel.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 * Das Spiel endet, sobald einer der beiden Spieler alle Soeldner besitzt
	 * oder MAX_RUNDEN Runden gespielt wurden.
	 * Am Ende wird das Ergebnis ausgegeben.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
		Spieler akt = spieler1;
		int runde = 1;
		while(runde <= MAX_RUNDEN && !schlachtfeld.besitztAlle(akt)) {
			spielzug(akt);
			akt = (akt == spieler1) ? spieler2 : spieler1;
			runde++;
		}
		auswertung();
	}

	/*
	 * Fuehrt den Zug des uebergebenen Spielers aus.
	 * Das Schlachtfeld und der Stand werden ausgegeben, danach werden so lange
	 * Zeile und Spalte des eigenen Soeldners sowie Zeile und Spalte des
	 * Zielfeldes eingelesen, bis ein gueltiger Zug genannt wird: entweder ein
	 * Zug auf ein freies Nachbarfeld oder das Anwerben eines schwaecheren
	 * gegnerischen Soeldners.
	 * Anschliessend wird der Zug ausgefuehrt und gemeldet, was geschehen ist.
	 */
	public static void spielzug(Spieler aktiv) {
		// TODO
		// Anweisung(en) hinzufuegen
		System.out.println(schlachtfeld);
		System.out.println(spieler1.getName() + " (" + spieler1.getZeichen() +"):" + schlachtfeld.zaehleSoeldner(spieler1)  
		+ "\t" + "\t" + spieler2.getName() + " (" + spieler2.getZeichen() +"):" + schlachtfeld.zaehleSoeldner(spieler2) );
		
		System.out.println(aktiv.getName() + " ist an der reihe.");
		
		int z,s,zz,zs;
		do {
			System.out.println("Zeile des eigenen Soeldners: ");
			z = sc.nextInt();
			
			System.out.println("Spalte des eigenen Soeldners: ");
			s = sc.nextInt();
			
			System.out.println("Zeile des Zielfeldes: ");
			zz = sc.nextInt();
			
			System.out.println("Spalte des Zielfeldes: ");
			zs = sc.nextInt();
			
		}while(!(schlachtfeld.kannZiehen(z, s, zz, zs, aktiv)  || schlachtfeld.kannAnwerben(z, s, zz, zs, aktiv)));
		
		if(schlachtfeld.kannZiehen(z, s, zz, zs, aktiv)) {
			schlachtfeld.ziehe(z, s, zz, zs);
			System.out.println(aktiv.getName() + " zieht auf (" + zz + "," + zs + ").");
		}
		else if(schlachtfeld.kannAnwerben(z, s, zz, zs, aktiv)) {
			schlachtfeld.werbeAn(zz, zs, aktiv);
			System.out.println(aktiv.getName() + " wirbt den Soeldner auf (" + zz + "," + zs + ")an.");
		}
		
		
	}

	/*
	 * Gibt den Endstand und den Gewinner aus.
	 * Bei Gleichstand wird ein Unentschieden gemeldet.
	 */
	public static void auswertung() {
		// TODO
		// Anweisung(en) hinzufuegen
		System.out.println("Spielende!");
		System.out.println(spieler1.getName() + " (" + spieler1.getZeichen() +"):" + schlachtfeld.zaehleSoeldner(spieler1) 
		+ "\t" + "\t" + spieler2.getName() + " (" + spieler2.getZeichen() +"):" + schlachtfeld.zaehleSoeldner(spieler2));
		
		if(schlachtfeld.besitztAlle(spieler1)) {
			System.out.println(spieler1.getName() + " hat gewonnen.");
		}
		else if(schlachtfeld.besitztAlle(spieler2)) {
			System.out.println(spieler2.getName() + " hat gewonnen.");
		}
		else {
			System.out.println("Das Spiel ist untentschieden.");
		}
	}
}