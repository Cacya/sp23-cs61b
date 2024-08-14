package bomb;

import common.IntList;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            b.phase1(IntList.of(0, 9, 3, 0, 8)); // Figure this out too
        }
        if (phase >= 2) {
            // Can't use StdRandom because I really do want a random int
            Random r = new Random(1337);
            String numbers = "";
            int i = 0;
            while (i < 100000) {
                if(i == 1337) numbers+="-81201430"+" ";
                else numbers += r.nextInt()+" ";
                i ++;
            }
            b.phase2(numbers);
        }
    }
}
