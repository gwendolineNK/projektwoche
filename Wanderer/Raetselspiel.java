import java.util.Scanner;

public class Raetselspiel {

	//TODO: d)	Deklarieren Sie die Objektattribute der vier Wanderer und der Taschenlampe und initialisieren Sie diese ensprechend der Aufgabenstellung.
	//Initialisieren Sie in der Klasse einen Scanner als Klassenattribut.

	
	private static Wanderer a = new Wanderer('A', 5);
	private static Wanderer b = new Wanderer('B', 10);
	private static Wanderer c = new Wanderer('C', 20);
	private static Wanderer d = new Wanderer('D', 25);
	private static Taschenlampe t = new Taschenlampe(60);
	private static Scanner sc = new Scanner(System.in);
	
	public static void printSpielstand() {
		System.out.println();
		System.out.println("Taschenlampe: " + t.getBrenndauer());
		System.out.println(
				"Die Lampe ist " + (t.isLampeAmStartUfer() ? "links" : "rechts") + ", diese Wanderer k�nnen gehen:");
		if (t.isLampeAmStartUfer()) {
			if (a.isAmStartUfer())
				System.out.print(a);
			if (b.isAmStartUfer())
				System.out.print(b);
			if (c.isAmStartUfer())
				System.out.print(c);
			if (d.isAmStartUfer())
				System.out.print(d);
		} else {
			if (!a.isAmStartUfer())
				System.out.print(a);
			if (!b.isAmStartUfer())
				System.out.print(b);
			if (!c.isAmStartUfer())
				System.out.print(c);
			if (!d.isAmStartUfer())
				System.out.print(d);
		}
		System.out.println();
	}

	//Implementieren Sie die Methode testeEnde()


	public static void main(String[] args) {
		String auswahl = "";
		boolean gewechselt;
		int vorherZeit = t.getBrenndauer();
		do {
			printSpielstand();
			System.out.print("Welche Wanderer sollen gehen (1 oder 2 Zeichen zwischen A und D): ");
			auswahl = sc.next();
			gewechselt = false;
			int zeit = 0;
			//TODO: Erg�nzen Sie die main-Methode so dass �berpr�ft wird, welche Wanderer in der eingegebenen Zeichenkette (auswahl) angegeben wurden,
			//und veranlassen Sie, dass die angegebenen Wanderer, wenn m�glich, das Ufer wechseln. Dabei soll auch die Taschenlampe das Ufer wechseln 
			//und die Brenndauer der Taschenlampe soll um so viele Minuten reduziert werden, wie der langsamste Wanderer braucht.
			if(auswahl.length() == 1) {
				if(auswahl.charAt(0) == 'A') {
					zeit = a.getMinuten();
					a.welchsleSeite();
				}
				else if(auswahl.charAt(0) == 'B') {
					zeit = b.getMinuten();
					b.welchsleSeite();
				}
				else if(auswahl.charAt(0) == 'C') {
					zeit = c.getMinuten();
					c.welchsleSeite();
				}
				else if(auswahl.charAt(0) == 'D') {
					zeit = d.getMinuten();
					d.welchsleSeite();
				}
				
			}
			else if(auswahl.length() == 2) {
				
				if(auswahl.charAt(0) > auswahl.charAt(1)) {
					/*if(auswahl.charAt(0) == 'A') {
					  zeit = a.getMinuten();
						a.welchsleSeite();
					}*/
					 if(auswahl.charAt(0) == 'B') {
						zeit = b.getMinuten();
						b.welchsleSeite();
						a.welchsleSeite();
					}
					else if(auswahl.charAt(0) == 'C') {
						zeit = c.getMinuten();
						c.welchsleSeite();
						if(auswahl.charAt(1) == 'A') {
							a.welchsleSeite();
						}
						else if(auswahl.charAt(1) == 'B') {
							b.welchsleSeite();
						}
					}
					else if(auswahl.charAt(0) == 'D') {
						zeit = d.getMinuten();
						d.welchsleSeite();
						if(auswahl.charAt(1) == 'A') {
							a.welchsleSeite();
						}
						else if(auswahl.charAt(1) == 'B') {
							b.welchsleSeite();
						}
						else if(auswahl.charAt(1) == 'C') {
							c.welchsleSeite();
						}
					}
				}
				else {
					/*if(auswahl.charAt(1) == 'A') {
						zeit = a.getMinuten();
						a.welchsleSeite();
					}*/
					 if(auswahl.charAt(1) == 'B') {
						zeit = b.getMinuten();
						b.welchsleSeite();
						a.welchsleSeite();
					}
					else if(auswahl.charAt(1) == 'C') {
						zeit = c.getMinuten();
						c.welchsleSeite();
						if(auswahl.charAt(0) == 'A') {
							a.welchsleSeite();
						}
						else if(auswahl.charAt(0) == 'B') {
							b.welchsleSeite();
						}
					}
					else if(auswahl.charAt(1) == 'D') {
						zeit = d.getMinuten();
						d.welchsleSeite();
						if(auswahl.charAt(0) == 'A') {
							a.welchsleSeite();
						}
						else if(auswahl.charAt(0) == 'B') {
							b.welchsleSeite();
						}
						else if(auswahl.charAt(0) == 'C') {
							c.welchsleSeite();
						}
					}
				}
			}
			t.wechsleLampe();
			t.reduziereDauer(zeit);
		
		} while (!testeEnde());

		if (a.isAmStartUfer() || b.isAmStartUfer() || c.isAmStartUfer() || d.isAmStartUfer() || t.getBrenndauer() < 0)
			System.out.println("Leider verloren");
		else
			System.out.println("Geschafft! Bravo!");

		sc.close();
	}

	public static boolean testeEnde() {
		if((!a.isAmStartUfer() && !b.isAmStartUfer() && !c.isAmStartUfer() 
				&& !d.isAmStartUfer()) || t.getBrenndauer() <= 0) {
			return true;
		}
		return false;
	}

}