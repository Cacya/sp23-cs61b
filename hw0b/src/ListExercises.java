import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (int elem: L) {
            sum += elem;
        }
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> even_l = new ArrayList<>();
        for (int elem: L) {
            if (elem % 2 == 0) {
                even_l.add(elem);
            }
        }
        return even_l;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> common_l = new ArrayList<>();
        for (int elem: L1) {
            if (L2.contains(elem)) {
                common_l.add(elem);
            }
        }
        return common_l;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int sum = 0;
        for (String elem: words) {
            for (int i = 0; i < elem.length(); i ++) {
                if(elem.charAt(i) == c) {
                    sum ++;
                }
            }
        }
        return sum;
    }
}
