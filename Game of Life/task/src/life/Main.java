package life;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        var input =
                Stream.of(scanner.nextLine()
                        .split(" "))
                        .map(String::new)
                        .collect(Collectors.toList());
        scanner.close();

        int size, seed, gens = 10;
        Life world;

        if (input.size() == 1) {
            world = new Life(Integer.parseInt(input.remove(0)));
        } else {
            size = Integer.parseInt(input.remove(0));
            seed = Integer.parseInt(input.remove(0));
            gens = Integer.parseInt(input.remove(0));
            world = new Life(size);
        }

        world.propagate(gens);
    }
}
