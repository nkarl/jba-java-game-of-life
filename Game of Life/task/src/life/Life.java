package life;

import java.io.IOException;
import java.util.Random;

public class Life extends Model {

    int numAlive;
    // Constructor: with seed
    public Life(int size, int seed) {
        super(size);

        Random random = new Random(seed);
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                this.map[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
    }

    // Constructor: without seed
    public Life(int size) {
        super(size);

        Random random = new Random();
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                this.map[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
    }

    // propagate forward <gens> generations
    public void propagate(int gens) {
//        Thread thread = new Thread(new Runnable() {
//            public void run() {
//                generate();
//                view();
//                try {
//                    Thread.sleep((long) (0.5 * 1000));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        for (int i = 0; i < gens; ++i) {
            generate();
            System.out.println("Generations #" + i);
            System.out.println("Alive: " + this.numAlive);
            view();
            // apply Thread and Callback here
//            thread.start();
            try {
                if (System.getProperties().contains("Windows"))
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // generate a new future from the current generation
    private void generate() {
        // create a new model of the next generation
        Model future = new Model(this.size) {};

        this.numAlive = 0;
        // check every cell for its survival conditions
        for (int row = 0; row < this.size; ++row) {
            for (int col = 0; col < this.size; ++col) {
                int neighbors = neighbors(row, col);
                if (neighbors == 3) {
                    future.map[row][col] = 1;
                } else if (neighbors == 2) {
                    future.map[row][col] = this.map[row][col];
                } else {
                    future.map[row][col] = 0;
                }
                this.numAlive += (future.map[row][col] == 1) ? 1 : 0;
            }
        }
        this.map = future.map;
    }

    // count the number of neighbors around a cell
    private int neighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i < 2; ++i) {
            for (int j = -1; j < 2; ++j) {
                if (i == 0 && j == 0) continue;
                count += checkIndex(row + i, col + j);
            }
        }
        return count;
    }

    // check and wrap the out-of-bound indices
    private int checkIndex(int row, int col) {
        int MAX = this.size - 1;
        if (row < 0) row = MAX;
        if (row > MAX) row = 0;
        if (col < 0) col = MAX;
        if (col > MAX) col = 0;
        return this.map[row][col] == 1 ? 1 : 0;
    }

    // view method
    public void view() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                if (this.map[i][j] == 1)
                    System.out.print('O');
                else
                    System.out.print(' ');
            }
            System.out.println();
        }
    }
}
