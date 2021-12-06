package nl.bd.aoc.day5;

import java.util.Arrays;

public class Point {
    private String pointString;

    private int x;

    private int y;

    public static Point instantiate(String pointFormat)
    {
        return new Point(pointFormat);
    }

    private Point(String pointString) {
        this.pointString = pointString;
        this.parsePoint();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Point point = (Point) obj;

        return this.x == point.x &&
                this.y == point.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }

    private void parsePoint()
    {
        String[] x1y1 = Arrays.stream(this.pointString.split(",")).map(String::trim).toArray(String[]::new);

        this.x = Integer.parseInt(x1y1[0]);
        this.y = Integer.parseInt(x1y1[1]);
    }
}
