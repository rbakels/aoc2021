package nl.bd.aoc.day3;

import nl.bd.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
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

        int oxygenGeneratorRating = this.oxygenGeneratorRating(matrix);
        int co2ScrubberRating = this.co2ScrubberRating(matrix);

        System.out.println("Result:");
        System.out.println("Oxygen generator rating: " + oxygenGeneratorRating);
        System.out.println("CO2 Scrubber rating: " + co2ScrubberRating);
        System.out.println("Life support rating: " + (oxygenGeneratorRating * co2ScrubberRating));
    }

    private int oxygenGeneratorRating(ArrayList<ArrayList<Boolean>> matrix)
    {
        int columnLength = matrix.get(0).size();
        ArrayList<ArrayList<Boolean>> filteredMatrix = (ArrayList<ArrayList<Boolean>>) matrix.clone();

        for (int column = 0; column < columnLength; column++) {
            BitCriteria bitCriteria = this.bitCriteria(filteredMatrix, column);

            if (bitCriteria.numberOfOnes == bitCriteria.numberOfZeros) {
                // Keep 1s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, true);
            } else if (bitCriteria.numberOfOnes > bitCriteria.numberOfZeros) {
                // Keep 1s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, true);
            } else {
                // Keep 0s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, false);
            }

            if (filteredMatrix.size() == 1) {
                break;
            }
        }

        String result = this.listBooleanToString(filteredMatrix.get(0));

        return Integer.parseInt(result, 2);
    }

    private int co2ScrubberRating(ArrayList<ArrayList<Boolean>> matrix)
    {
        int columnLength = matrix.get(0).size();
        ArrayList<ArrayList<Boolean>> filteredMatrix = (ArrayList<ArrayList<Boolean>>) matrix.clone();

        for (int column = 0; column < columnLength; column++) {
            BitCriteria bitCriteria = this.bitCriteria(filteredMatrix, column);

            if (bitCriteria.numberOfOnes == bitCriteria.numberOfZeros) {
                // Keep 1s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, false);
            } else if (bitCriteria.numberOfOnes > bitCriteria.numberOfZeros) {
                // Keep 1s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, false);
            } else {
                // Keep 0s
                filteredMatrix = this.rebuildMatrix(filteredMatrix, column, true);
            }

            if (filteredMatrix.size() == 1) {
                break;
            }
        }

        String result = this.listBooleanToString(filteredMatrix.get(0));

        return Integer.parseInt(result, 2);
    }

    private ArrayList<ArrayList<Boolean>> rebuildMatrix(ArrayList<ArrayList<Boolean>> matrix, int column, boolean match)
    {
        ArrayList<ArrayList<Boolean>> filteredMatrix = new ArrayList<ArrayList<Boolean>>();

        for (int row = 0; row < matrix.size(); row++) {
            Boolean entry = matrix.get(row).get(column);

            if (entry == match) {
                filteredMatrix.add(matrix.get(row));
            }
        }

        return filteredMatrix;
    }

    private String listBooleanToString(ArrayList<Boolean> list) {
        String result = "";

        for (Boolean b : list) {
            if (b) {
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
    }

    private BitCriteria bitCriteria(ArrayList<ArrayList<Boolean>> matrix, int column)
    {
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

        return new BitCriteria(noOfOne, noOfZero);
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
