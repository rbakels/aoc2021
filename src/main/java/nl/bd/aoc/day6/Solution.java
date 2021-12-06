package nl.bd.aoc.day6;

import nl.bd.aoc.utils.FileUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    private String[] input;

    public Solution() {
        input = FileUtils.fileContent("day6/input.txt").trim().split(",");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.goFishing(256));
    }

    /**
     * This method will create a long[] of size 9. Each index of the array [0-8]
     * represents the internal timer of a lanternfish. The value at each index will
     * be how many lanternfish in our population currently have an internal timer
     * equal to the index.
     *
     * example:
     *                    timer value = 0  1  2  3  4  5  6  7  8
     *  input = 3,4,3,1,2 --> long[] = {0, 1, 1, 2, 1, 0, 0, 0, 0}
     *
     * Each day, we will shift the elements of long[] to the left. In addition, we
     * will add the element at index 0 to the value at index 6.
     *
     * @param days The number of days we will allow our fish population to grow
     *
     * @return The number of fish in the population after 'days' days.
     */
    public long goFishing(int days) {
        long[] timers = new long[9];

        Arrays.stream(input).forEach(f -> timers[Integer.parseInt(f)]++);

        IntStream.rangeClosed(1, days).mapToLong(day -> timers[0]).forEach(f -> {
            System.arraycopy(timers, 1, timers, 0, timers.length - 2 + 1);
            timers[6] = timers[6] + f;
            timers[8] = f;
        });

        return Arrays.stream(timers).sum();
    }

}
