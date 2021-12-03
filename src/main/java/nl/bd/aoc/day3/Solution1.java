package nl.bd.aoc.day3;

import nl.bd.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.run();
    }

    public void run()
    {
        List<String> input = FileUtils.readFromFile("day3/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        ArrayList<ArrayList<Boolean>> matrix = this.convertToMatrix(input);

        if (matrix.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        int length = matrix.get(0).size();
        String gammaRateBits = "";
        String episolonRateBits = "";

        for (int column = 0; column < length; column++) {
            int noOfZero = 0;
            int noOfOne = 0;

            for (int row = 0; row < matrix.size(); row++) {
                Boolean entry = matrix.get(row).get(column);

                if (entry) {
                    noOfOne++;
                } else {
                    noOfZero++;
                }
            }

            if (noOfOne >= noOfZero) {
                gammaRateBits += "1";
                episolonRateBits += "0";
            } else {
                gammaRateBits += "0";
                episolonRateBits += "1";
            }
        }

        int gammaRate = Integer.parseInt(gammaRateBits, 2);
        int epsilonRate = Integer.parseInt(episolonRateBits, 2);

        System.out.println("Gamma Rate (bit pattern): " + gammaRateBits);
        System.out.println("Epsilon Rate (bit pattern): " + episolonRateBits);

        System.out.println("Result:");
        System.out.println("Gamma Rate decimal: " + gammaRate);
        System.out.println("Epsilon Rate decimal: " + epsilonRate);
        System.out.println("Power consumption: " + (gammaRate * epsilonRate));

    }


    private ArrayList<ArrayList<Boolean>> convertToMatrix(List<String> input)
    {
        ArrayList<ArrayList<Boolean>> matrix = new ArrayList<ArrayList<Boolean>>();

        for (int row = 0; row < input.size(); row++) {
            String entry = input.get(row);
            List<Boolean> entryParts = new ArrayList<String>(Arrays.asList(entry.split(""))).stream().map(this::convertToBoolean).toList();

            matrix.add(new ArrayList<Boolean>(entryParts));
        }

        return matrix;
    }


    private boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) ||
                "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }
}
