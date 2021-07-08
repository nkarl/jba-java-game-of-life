package life;

import java.util.Random;

public class Life extends Model {

    public Life(int size, int seed) {
        super(size);

        Random random = new Random(seed);
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                this.map[i][j] = random.nextBoolean() ? 1 : 0;
            }
        }
    }

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
