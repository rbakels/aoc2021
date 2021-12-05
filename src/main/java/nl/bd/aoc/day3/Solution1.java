package nl.bd.aoc.day3;

import nl.bd.aoc.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        int gammaRate = this.gammaRateDecimal(matrix);
        int epsilonRate = this.epsilonRate(matrix);

        System.out.println("Result:");
        System.out.println("Gamma Rate decimal: " + gammaRate);
        System.out.println("Epsilon Rate decimal: " + epsilonRate);
        System.out.println("Power consumption: " + (gammaRate * epsilonRate));

    }

    private int gammaRateDecimal(ArrayList<ArrayList<Boolean>> matrix)
    {
        String gammaRateCalculationResult = this.bitCalculation(matrix);

        return Integer.parseInt(gammaRateCalculationResult, 2);
    }

    private int epsilonRate(ArrayList<ArrayList<Boolean>> matrix)
    {
        String epsilonRateCalculationResult = this.bitCalculation(matrix);

        Character[] epsilonRateCalculationResultAsCharArray = epsilonRateCalculationResult.chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        Stream<Character> charStream = Arrays.stream(epsilonRateCalculationResultAsCharArray);
        List<Character> characterList = charStream.map(bit -> bit == '1' ? '0' : '1').collect(Collectors.toList());

        String epsilsonRateInversionResult = characterList.toString()
                .substring(1, 3 * characterList.size() - 1)
                .replaceAll(", ", "");

        return Integer.parseInt(epsilsonRateInversionResult, 2);
    }

    private String bitCalculation(ArrayList<ArrayList<Boolean>> matrix)
    {
        int length = matrix.get(0).size();
        String result = "";

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
                result += "1";
            } else {
                result += "0";
            }
        }

        return result;
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
