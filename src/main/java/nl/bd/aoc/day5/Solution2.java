package nl.bd.aoc.day5;

import nl.bd.aoc.utils.FileUtils;
import org.ejml.simple.SimpleMatrix;

import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution1 = new Solution2();
        solution1.run();
    }

    public void run()
    {
        int maxSize = 1000;
        List<String> input = FileUtils.readFromFile("day5/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        SimpleMatrix diagram = new SimpleMatrix(maxSize, maxSize);

        for (String line : input) {
            InputLine inputLine = InputLine.instantiateFromLineString(line);

            List<Point> points = inputLine.getPointsInLine(inputLine.direction());

            System.out.println(inputLine + " (direction: " + inputLine.direction() + ", points in line: " + points + ")");

            for (Point point : points) {
                double result = diagram.get(point.getY(), point.getX());

                diagram.set(point.getY(), point.getX(), (result + 1));
            }
        }

        // Print board
        int cols = diagram.numCols();
        int rows = diagram.numRows();

        System.out.println("");
        System.out.println("Diagram:");

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double value = diagram.get(row, col);

                if (value == 0.0) {
                    System.out.print(". ");
                } else {
                    System.out.print((int) value + " ");
                }
            }

            System.out.println("");
        }

        // Calculate overlaps
        int overlaps = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double value = diagram.get(row, col);

                if (value > 1.0) {
                    overlaps++;
                }
            }
        }

        System.out.println("");
        System.out.println("Overlaps: " + overlaps);
    }
}
