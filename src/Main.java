import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<Integer, String> pairs = loadInput();
            System.out.println(pairs);
            int output = checkOverlap(pairs);
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static HashMap<Integer, String> loadInput() throws FileNotFoundException {
        HashMap<Integer, String> pairs = new HashMap<>();
        FileInputStream fis = new FileInputStream("Pairs.txt");
        Scanner scan = new Scanner(fis);
        int incrementer = 0;
        while (scan.hasNextLine()) {
            pairs.put(incrementer, scan.nextLine());
            incrementer++;
        }
        scan.close();
        return pairs;
    }

    public static int checkOverlap(HashMap<Integer, String> pairs) {
        int overlaps = 0;
        for (int i = 0; i < pairs.size(); i++) {
            int[] elf1 = new int[2];
            int[] elf2 = new int[2];
            String[] elvesNoPunctuation = pairs.get(i).split("[-,]");
            elf1[0] = Integer.parseInt(elvesNoPunctuation[0]);
            elf1[1] = Integer.parseInt(elvesNoPunctuation[1]);
            elf2[0] = Integer.parseInt(elvesNoPunctuation[2]);
            elf2[1] = Integer.parseInt(elvesNoPunctuation[3]);
            int elf1Size = 0;
            for (int j = elf1[0]; j < elf1[1]; j++) {
                elf1Size++;
            }
            int elf2Size = 0;
            for (int j = elf2[0]; j < elf2[1]; j++) {
                elf2Size++;
            }
            //take elfSizes 1 and 2 and arrange them from shortest to longest
            //from the smallest range
            //is the lowest number start in the range?
            //is the highest number ending in the range?
            boolean match = false;
            if (elf1Size < elf2Size || elf1Size == elf2Size) {
                if (elf1[0] >= elf2[0] && elf1[0] <= elf2[1]) {
                    match = true;
                } else if (elf1[1] >= elf2[0] && elf1[1] <= elf2[1]) {
                    match = true;
                }
            } else {
                if (elf2[0] >= elf1[0] && elf2[0] <= elf1[1]) {
                    match = true;
                } else if (elf2[1] >= elf1[0] && elf2[1] <= elf1[1]) {
                    match = true;
                }
            }
            if (match) {
                overlaps++;
            }
        }
        return overlaps;
    }
}