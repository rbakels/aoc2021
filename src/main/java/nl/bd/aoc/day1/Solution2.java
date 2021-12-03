package nl.bd.aoc.day1;

import nl.bd.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution_2 = new Solution2();
        solution_2.run();
    }

    public void run()
    {
        List<Integer> input = FileUtils.readFromFileAsInteger("day1/input.txt");
        List<Integer> windowInput = new ArrayList<>();

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        int increased = 0;
        int decreased = 0;
        int same = 0;
        String windowName = "W";

        System.out.println("Window sum list:");
        for (int endIndex = 2; endIndex < input.size(); endIndex++) {
            int firstIndex = endIndex - 2;
            int middleIndex = endIndex - 1;

            String label = windowName + (firstIndex + 1);


            Integer firstEntry = input.get(firstIndex);
            Integer middleEntry = input.get(middleIndex);
            Integer lastEntry = input.get(endIndex);

            Integer sum = firstEntry + middleEntry + lastEntry;

            System.out.println(label + ": " + sum);

            windowInput.add(sum);
        }

        System.out.println("");
        System.out.println("Resulting window input list: " + windowInput);
        System.out.println("");
        System.out.println("Results:");

        windowName = "W";

        for (int currentIndex = 0; currentIndex < windowInput.size(); currentIndex++) {
            Integer currentEntry = windowInput.get(currentIndex);
            String label = windowName + (currentIndex + 1);

            if (currentIndex == 0) {
                System.out.println(label + ": " + currentEntry + " (N/A - no previous measurement)");
            } else {
                int previousIndex = currentIndex - 1;
                Integer previousEntry = windowInput.get(previousIndex);

                if (currentEntry > previousEntry) {
                    System.out.println(label + ": " + currentEntry + " (increased)");
                    increased++;
                } else if (currentEntry < previousEntry) {
                    System.out.println(label + ": " + currentEntry + " (decreased)");
                    decreased++;
                } else {
                    System.out.println(label + ": " + currentEntry + " (no change)");
                    same++;
                }
            }
        }

        System.out.println("");
        System.out.println("Statistics: ");
        System.out.println("Increased: " + increased);
        System.out.println("Decreased: " + decreased);
        System.out.println("Same: " + same);
    }
}
