class Wanderer {
	//Objektattribute 
	//TODO: Implementieren Sie die Objektattribute aus dem UML-Diagramm des Wanderers 

	private char name;
	private int minuten;
	private boolean amStartUfer;
	//Konstruktor 
	//TODO: Implementieren Sie einen Konstruktor f�r Exemplare der Klasse Wanderer
	public Wanderer(char name, int minuten) {
		this.name = name;
		this.minuten = minuten;
		this.amStartUfer = true;
	}
	//Methoden
	//TODO: Implementieren Sie die Methoden aus dem UML-Diagramm des Wanderers
	public char getName() {
		return name;
	}
	
	public int getMinuten() {
		return minuten;
	}
	
	public boolean isAmStartUfer() {
		return amStartUfer;
	}
	
	public int welchsleSeite() {
		
		if(amStartUfer == true ) {
			amStartUfer = false;
		}
		else {
			amStartUfer = true;
		}
		return minuten ;
	}
	
	public String toString() {
		return name + "(" + minuten + ")";
	}
}
