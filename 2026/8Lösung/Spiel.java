import java.util.Scanner;

/* *****************************************************************************************************
 * Folgen Sie den Todo-Anweisungen und beachten Sie die Kommentare!
 * Fuegen Sie Code hinzu!
 * Aendern Sie vom vorhandenen Code nur return-Anweisungen!
 ****************************************************************************************************** */

public class Spiel {

	/* Verwenden Sie unbedingt diese Klassenattribute in den Methoden der Klasse! */
	private static Scanner sc = new Scanner(System.in);
	public static Spieler spieler1;
	public static Spieler spieler2;
	public static Spielbrett brett;

	/*
	 * In diesem Attribut muss der Spieler gespeichert werden, der verloren hat.
	 * Die Methode auswertung() wertet es aus.
	 */
	public static Spieler verlierer;

	public static void main(String[] args) {
		System.out.println("Herzlich Willkommen zu Metamorphose Light");

		int eingabe;
		boolean spielen = true;

		do {
			eingabe = menu();

			if (eingabe == 1) {
				/*
				 * Methoden spielVorbereiten() und spielen() aufrufen
				 */
				// TODO
				// Anweisung(en) hinzufuegen
				spielVorbereiten();
				spielen();

			}

			/* Dafuer sorgen, dass bei Eingabe 2 das Programm endet */
			// TODO
			// Anweisung(en) hinzufuegen
			else {
				spielen = false;
			}
		} while (spielen);

		System.out.println("Programm beendet.");
		sc.close();
	}

	/*
	 * Fragt solange nach einer Menu-Eingabe bis eine erlaubte Zahl eingegeben wird.
	 * Methode checkEingabeMenu() verwenden
	 */
	public static int menu() {
		// TODO
		// ggf Anweisungen hinzufuegen
		int eingabe;
		do {
			System.out.println("1 - Spiel starten");
			System.out.println("2 - Programm beenden");
			eingabe = sc.nextInt();
		}while(!checkEingabeMenu(eingabe));
		// unbedingt return-Anweisung aendern
		return eingabe;
	}

	/*
	 * Gibt bei Eingabe von 1 oder 2 true zurueck. Sonst false.
	 */
	public static boolean checkEingabeMenu(int eingabe) {
		// TODO
		// ggf Anweisungen hinzufuegen

		// unbedingt return-Anweisung aendern
		return eingabe == 1 || eingabe == 2;
	}

	/*
	 * Liest die Namen der beiden Spieler ein, erzeugt die beiden Spieler
	 * (Nummern 1 und 2) und das Spielbrett, stellt die Figuren in der
	 * Grundaufstellung auf und setzt das Attribut verlierer zurueck.
	 */
	public static void spielVorbereiten() {
		// TODO
		// Anweisung(en) hinzufuegen
		
		System.out.println("Geben Sie den Namen des Spielers 1 ein:");
		String name1 = sc.next();
		spieler1 = new Spieler(name1,1);
		
		System.out.println("Geben Sie den Namen des Spielers 2 ein:");
		String name2 = sc.next();
		spieler2 = new Spieler(name2,2);
		
		brett = new Spielbrett();
		brett.grundaufstellung(spieler1, spieler2);
		
		
	}

	/*
	 * Es wird gespielt, bis ein Spieler verloren hat.
	 * Spieler 1 beginnt, danach sind die Spieler abwechselnd an der Reihe.
	 *
	 * Vor jedem Zug wird geprueft, ob der aktive Spieler noch Figuren im Spiel
	 * hat und noch einen gueltigen Zug machen kann. Ist das nicht der Fall,
	 * wird er im Attribut verlierer gespeichert, eine entsprechende Meldung
	 * ausgegeben und das Spiel beendet.
	 *
	 * Methoden spielzug(), Spielbrett.hatZugMoeglichkeit() und
	 * Spieler.zaehleFigurenImSpiel() verwenden.
	 */
	public static void spielen() {
		// TODO
		// Anweisung(en) hinzufuegen
		Spieler aktuell = spieler1;
		while(aktuell.zaehleFigurenImSpiel() != 0 && brett.hatZugMoeglichkeit(aktuell)) {
			spielzug(aktuell);
			aktuell = (aktuell == spieler1) ? spieler2 : spieler1;
		}
		verlierer = aktuell;

		// NICHT AENDERN
		System.out.println(auswertung());
	}

	/*
	 * Der Spieler gibt Start- und Zielfeld seines Zuges an.
	 * Solange der Zug nicht gueltig ist, wird eine Meldung ausgegeben und
	 * erneut gefragt. Danach wird der Zug ausgefuehrt. Wird dabei eine Figur
	 * geschlagen, wird dies gemeldet. Ausserdem wird gemeldet, in was sich die
	 * gezogene Figur verwandelt hat.
	 * Methoden Spielbrett.istZugGueltig(), Spielbrett.fuehreZugAus(),
	 * Spielbrett.getFigur() und Figur.getTyp() verwenden.
	 */
	public static void spielzug(Spieler spieler) {
		// Diese Zeilen NICHT AENDERN
		System.out.println("--------------------------------");
		System.out.println(spieler.getName() + " ist an der Reihe.");
		System.out.println(brett);

		int vonZeile;
		int vonSpalte;
		int nachZeile;
		int nachSpalte;

		do {
			System.out.print("Startfeld - Zeile: ");
			vonZeile = sc.nextInt();

			System.out.print("Startfeld - Spalte: ");
			vonSpalte = sc.nextInt();

			System.out.print("Zielfeld - Zeile: ");
			nachZeile = sc.nextInt();

			System.out.print("Zielfeld - Spalte: ");
			nachSpalte = sc.nextInt();

			// TODO
			// Anweisung(en) hinzufuegen
			if(!brett.istZugGueltig(vonZeile, vonSpalte, nachZeile, nachSpalte, spieler)) {
				System.out.println("Diese Zug ist ungueltig.");
			}

		} while (!brett.istZugGueltig(vonZeile, vonSpalte, nachZeile, nachSpalte, spieler)); // TODO: Schleifenbedingung anpassen
		
		int typ = brett.getFigur(vonZeile, vonSpalte).getTyp();
		Figur figur = brett.fuehreZugAus(vonZeile, vonSpalte, nachZeile, nachSpalte);
		
		System.out.println(brett);
		if(figur != null) {
			System.out.println("Die Figur " + figur + " wurde geschlagen.");
		}
		System.out.println("Der Zug wurde ausgefuehrt." );
		if( typ == 1) {
			System.out.println("Die Figur hat sich in einen Kreis verwandelt " );
		}
		else if(typ == 2) {
			System.out.println("Die Figur hat sich in einen Quadrat verwandelt " );
		}
	}

	/*
	 * NICHT AENDERN!
	 * Wird bereits an der richtigen Stelle aufgerufen.
	 */
	public static String auswertung() {
		if (verlierer == spieler1) {
			return spieler2.getName() + " hat gewonnen!";
		}

		if (verlierer == spieler2) {
			return spieler1.getName() + " hat gewonnen!";
		}

		return "Das Spiel ist beendet.";
	}
}