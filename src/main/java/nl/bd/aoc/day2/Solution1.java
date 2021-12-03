package nl.bd.aoc.day2;

import nl.bd.aoc.utils.FileUtils;

import java.util.List;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        solution1.run();
    }

    public void run()
    {
        List<String> input = FileUtils.readFromFile("day2/input.txt");

        if (input.isEmpty()) {
            System.out.println("Empty input given. Not executing.");

            System.exit(1);
        }

        int horizontal = 0;
        int depth = 0;

        for (String command : input) {
            String[] parsedCommand = command.split("\\s+");
            String direction = parsedCommand[0];
            int unit = Integer.parseInt(parsedCommand[1]);

            System.out.println("Command received: Direction = " + direction + ", unit = " + unit);

            switch (direction) {
                case "forward":
                    horizontal = horizontal + unit;
                    break;
                case "down":
                    depth = depth + unit;
                    break;
                case "up":
                    depth = depth - unit;
                    break;
                default:
                    System.out.println("Invallid direction received: " + direction);
            }
        }

        System.out.println("");
        System.out.println("Result:");
        System.out.println("Horizontal position = " + horizontal);
        System.out.println("Final depth = " + depth);
        System.out.println("Multiplication = " + (horizontal * depth));
    }
}
