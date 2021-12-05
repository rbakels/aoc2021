package nl.bd.aoc.day4;

import nl.bd.aoc.utils.FileUtils;
import org.ejml.simple.SimpleMatrix;

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
        List<String> input = FileUtils.readFromFile("day4/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        // Eerste regel zijn alle getallen die getrokken worden.
        String numbersDrawn = input.get(0);
        List<Integer> numbersDrawnList = Arrays.stream(numbersDrawn.trim().split(",")).map(Integer::parseInt).toList();
        List<Bingo> bingoList = this.getBingoCardFromInput(input);

        for (Integer numberDrawn : numbersDrawnList) {
            System.out.println("Drawn number: " + numberDrawn);

            for (Bingo bingo : bingoList) {
                bingo.markNumberIfExists(numberDrawn);

                if (bingo.hasBingo()) {
                    int unmarkedSum = bingo.sumOfUnmarked();

                    System.out.println("Result: " + (unmarkedSum * numberDrawn));
                    System.exit(0);
                }
            }
        }

        System.out.println("Nothing found.");
    }

    private List<Bingo> getBingoCardFromInput(List<String> input) {
        List<Bingo> matrices = new ArrayList<>();

        input.remove(0);
        input.remove(0);

        List<String> matrixLines = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);

             if (line.isEmpty()) {
                 Bingo bingo = this.instantiateBingoObject(matrixLines);
                 matrices.add(bingo);
                 matrixLines = new ArrayList<>();
             } else {
                 matrixLines.add(line);
             }
        }

        Bingo bingo = this.instantiateBingoObject(matrixLines);
        matrices.add(bingo);

        return matrices;
    }

    private Bingo instantiateBingoObject(List<String> matrixLines) {
        Bingo bingo = new Bingo();
        SimpleMatrix matrix = new SimpleMatrix(5, 5);

        for (int j = 0, matrixLinesSize = matrixLines.size(); j < matrixLinesSize; j++) {
            String matrixLine = matrixLines.get(j);
            matrix.setRow(j, 0, this.lineToArray(matrixLine));
        }

        bingo.setBingoCard(matrix);

        return bingo;
    }

    private double[] lineToArray(String line) {
        String [] lineArray = line.trim().split("\\s+");

        return Arrays.stream(lineArray)
                .map(s -> s + ".0")
                .mapToDouble(Double::parseDouble)
                .toArray();
    }
}
