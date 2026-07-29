public class Taschenlampe {
	//Objektattribute 
	//TODO: Implementieren Sie die Objektattribute aus dem UML-Diagramm der Taschenlampe 

	private int brenndauer ;
	private boolean lampeAmStartUfer;

	//Methoden
	//TODO: Implementieren Sie die Methoden aus dem UML-Diagramm der Taschenlampe
    public Taschenlampe(int brenndauer) {
    	this.brenndauer = brenndauer;
    	this.lampeAmStartUfer = true;
    }
	
	public int getBrenndauer() {
		return brenndauer;
	}
	
	public boolean isLampeAmStartUfer() {
		return lampeAmStartUfer;
	}
	
	public boolean reduziereDauer(int brenndauer) {
		this.brenndauer = this.brenndauer - brenndauer;
		if(this.brenndauer < 0) {
			return false;
		}
		return true;
		
	}
	
	public void wechsleLampe() {
		if(this.lampeAmStartUfer == true) {
			this.lampeAmStartUfer = false;
		}
		else {
			this.lampeAmStartUfer = true;
		}
	}
}
