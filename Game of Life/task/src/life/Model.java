package life;

public abstract class Model {

    int size;
    int[][] map;

    Model(int size) {
        this.size = size;
        this.map = new int[size][size];
    }
}
