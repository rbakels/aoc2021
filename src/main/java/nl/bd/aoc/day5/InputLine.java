package nl.bd.aoc.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputLine {
    private String line;
    private Point[] points = new Point[2];

    private InputLine(String line) {
        this.line = line;
        this.parseLine();
    }

    public static InputLine instantiateFromLineString(String line) {
        return new InputLine(line);
    }

    public int distance()
    {
        Direction direction = this.direction();

        return this.distance(direction);
    }

    public int distance(Direction direction)
    {
        Point start = this.start();
        Point end = this.end();

        if (direction.equals(Direction.HORIZONTAL)) {
            int distance = end.getX() - start.getX();

            if (distance < 0) {
                distance *= -1;
            }

            return distance;
        }

        if (direction.equals(Direction.VERTICAL)) {
            int distance = end.getY() - start.getY();

            if (distance < 0) {
                distance *= -1;
            }

            return distance;
        }

        if (direction.equals(Direction.DIAGONAL)) {
            int distance = end.getY() - start.getY();

            if (distance < 0) {
                distance *= -1;
            }

            return distance;
        }

        return 0;
    }

    public Direction direction()
    {
        Point start = this.start();
        Point end = this.end();

        int sX = start.getX();
        int eX = end.getX();

        int sY = start.getY();
        int eY = end.getY();

//   |--      X      --|
//   0 1 2 3 4 5 6 7 8 9 -
// 0 . . . . . . . . . . |
// 1 . . . . . . . . . . |
// 2 . . . . . . . . . .
// 3 . . . . . . . . . .
// 4 . . . . . . . . . . Y
// 5 . . . . . . . . . .
// 6 . . . . . . . . . .
// 7 . . . . . . . . . . |
// 8 . . . . . . . . . . |
// 9 . . . . . . . . . . -
//   0 1 2 3 4 5 6 7 8 9
//
// Point notation = x,y
// sX,sY -> eX,eY
// 0 ,0  -> 9 ,9
//

        if ( (sX - eX != 0) && ((sY - eY) == 0) ) {
            return Direction.HORIZONTAL;
        }

        if ( ((sY - eY) != 0) && (sX - eX == 0) ) {
            return Direction.VERTICAL;
        }

        if ( ((sY - eY) != 0) && (sX - eX != 0) ) {
            return Direction.DIAGONAL;
        }

        return Direction.UNKNOWN;
    }

    public Point end()
    {
        return this.points[1];
    }

    public Point start()
    {
        return this.points[0];
    }

    public List<Point> getPointsInLine() {
        Direction direction = this.direction();
        return this.getPointsInLine(direction);
    }

    public List<Point> getPointsInLine(Direction direction) {
        List<Point> points = new ArrayList<>();

        int distance = this.distance(direction);
        Point start = this.start();
        Point end = this.end();

        for (int i = 0; i < distance; i++) {
            if (direction.equals(Direction.HORIZONTAL)) {
                points.add(Point.instantiate((start.getX() + i) + "," + start.getY()));
            }

            if (direction.equals(Direction.VERTICAL)) {
                points.add(Point.instantiate(start.getX() + "," + (start.getY() + i)));
            }

            if (direction.equals(Direction.DIAGONAL)) {
                int newX = 0;
                int newY = 0;

                if (start.getX() > end.getX()) {
                    newX = start.getX() - i;
                }

                if (start.getY() > end.getY()) {
                    newY = start.getY() - i;
                }

                if (start.getX() < end.getX()) {
                    newX = start.getX() + i;
                }

                if (start.getY() < end.getY()) {
                    newY = start.getY() + i;
                }

                points.add(Point.instantiate(newX + "," + newY));
            }
        }

        points.add(Point.instantiate(end().getX() + "," + (end().getY())));

        return points;
    }

    private void parseLine()
    {
        // x1,y1 -> x2,y2
        String[] fromPointToPoint = Arrays.stream(this.line.split("->")).map(String::trim).toArray(String[]::new);

        Point start = Point.instantiate(fromPointToPoint[0]);
        Point end = Point.instantiate(fromPointToPoint[1]);

        if ((start.getX() > end.getX())) {
            this.points[0] = end;
            this.points[1] = start;
        } else if ((start.getX() < end.getX())) {
            this.points[0] = start;
            this.points[1] = end;
        } else if ((start.getX() == end.getX()) && (start.getY() > end.getY())) {
            this.points[0] = end;
            this.points[1] = start;
        } else if ((start.getX() == end.getX()) && (start.getY() < end.getY())) {
            this.points[0] = start;
            this.points[1] = end;
        } else {
            this.points[0] = start;
            this.points[1] = end;
        }
    }

    @Override
    public String toString() {
        return this.points[0] + " -> " + this.points[1];
    }
}
