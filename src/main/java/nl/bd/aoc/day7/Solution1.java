package nl.bd.aoc.day7;

import nl.bd.aoc.utils.FileUtils;

import java.security.KeyStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.run();
    }

    public void run()
    {
        List<Integer> input = FileUtils.readFromFileAndSplitAsInteger("day7/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        int min = Collections.min(input);
        int max = Collections.max(input);

        System.out.println("Input: " + input);
        System.out.println("Minimal horizontal position: " + min);
        System.out.println("Maximal horizontal position: " + max);

        HashMap<Integer, Integer> result = new HashMap<>();

        for (int targetHorizontalPosition = min; targetHorizontalPosition <= max; targetHorizontalPosition++) {
            for (Integer currentPosition : input) {
                int fuel = 0;

                if (currentPosition > targetHorizontalPosition) {
                    fuel = currentPosition - targetHorizontalPosition;
                } else {
                    fuel = targetHorizontalPosition - currentPosition ;
                }

                if (result.containsKey(targetHorizontalPosition)) {
                    fuel += result.get(targetHorizontalPosition);
                }
                result.put(targetHorizontalPosition, fuel);
            }
        }

        System.out.println("Fuel result map: " + result);

        Map.Entry<Integer, Integer> minimalFuelEntry = null;
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {
            if (minimalFuelEntry == null || minimalFuelEntry.getValue() > entry.getValue()) {
                minimalFuelEntry = entry;
            }
        }

        System.out.println("Result: position = " + minimalFuelEntry.getKey() + ", fuel = " + minimalFuelEntry.getValue());
    }
}
