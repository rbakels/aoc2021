package nl.bd.aoc.day1;

import nl.bd.aoc.utils.FileUtils;

import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.run();
    }

    public void run()
    {
        List<Integer> input = FileUtils.readFromFileAsInteger("day1/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        int increased = 0;
        int decreased = 0;
        int same = 0;

        for (int currentIndex = 0; currentIndex < input.size(); currentIndex++) {
            Integer currentEntry = input.get(currentIndex);

            if (currentIndex == 0) {
                System.out.println(currentEntry + " (N/A - no previous measurement)");
            } else {
                int previousIndex = currentIndex - 1;
                Integer previousEntry = input.get(previousIndex);

                if (currentEntry > previousEntry) {
                    System.out.println(currentEntry + " (increased)");
                    increased++;
                } else if (currentEntry < previousEntry) {
                    System.out.println(currentEntry + " (decreased)");
                    decreased++;
                } else {
                    System.out.println(currentEntry + " (same)");
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
