public class Spieler {

    private String name;
    private int nummer;
    private boolean rotationVerfuegbar;

    // TODO 1
    public Spieler(String name, int nummer) {
        this.name = name;
        this.nummer = nummer;
        this.rotationVerfuegbar = true;
    }

    // TODO 2
    public String getName() {
        return name;
    }

    // TODO 3
    public int getNummer() {
        return nummer;
    }

    // TODO 4
    public boolean hatRotation() {
        return rotationVerfuegbar;
    }

    // TODO 5
    public void rotationVerbrauchen() {
        rotationVerfuegbar = false;
    }
}
