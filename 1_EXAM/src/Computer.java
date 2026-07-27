public class Computer extends Spieler {

    public Computer(int nummer) {
        // TODO 6
        super("", nummer);
    }

    public int[] waehleZug(Spielfeld feld) {
        // TODO 7
        return new int[] { -1, -1 };
    }
}
