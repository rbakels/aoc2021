package nl.bd.aoc.day4;

import org.ejml.simple.SimpleMatrix;

public class Bingo {
    private SimpleMatrix bingoCard;

    private SimpleMatrix markedNumbers;

    private boolean markAsWinning;

    public Bingo() {
        this.bingoCard = new SimpleMatrix(5, 5);
        this.markedNumbers = new SimpleMatrix(5, 5);
    }

    public void print() {
        int cols = this.bingoCard.numCols();
        int rows = this.bingoCard.numRows();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double value = this.bingoCard.get(row, col);

                if (isMarked(row, col)) {
                    System.out.print(String.format("\033[1m%.2d\033[0m", (int) value));
                } else {
                    System.out.print(String.format("%d", (int) value));
                }

                System.out.println("");
            }
        }

    }
    public void markNumberIfExists(int number) {
        int cols = this.bingoCard.numCols();
        int rows = this.bingoCard.numRows();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double value = this.bingoCard.get(row, col);

                if ((int) value == number) {
                    this.markedNumbers.set(row, col, 1.0);
                }
            }
        }
    }

    public void mark(int row, int col, boolean mark) {
        if (mark) {
            this.markedNumbers.set(row, col, 1.0);
        } else {
            this.markedNumbers.set(row, col, 0.0);
        }
    }

    public boolean isMarked(int row, int col) {
        double val = this.markedNumbers.get(row, col);

        if (val == 0.0) {
            return false;
        }

        return true;
    }

    public boolean hasBingo() {
        int cols = this.bingoCard.numCols();
        int rows = this.bingoCard.numRows();

        // Horizontaal eerst.
        for (int row = 0; row < rows; row++) {
            int teller = 0;

            for (int col = 0; col < cols; col++) {
                if (this.isMarked(row, col)) {
                    teller++;
                }
            }

            if (teller == 5) {
                return true;
            }
        }

        // Verticaal daarna
        for (int col = 0; col < cols; col++) {
            int teller = 0;

            for (int row = 0; row < rows; row++) {
                if (this.isMarked(row, col)) {
                    teller++;
                }
            }

            if (teller == 5) {
                return true;
            }
        }

        return false;
    }

    public int sumOfUnmarked()
    {
        int result = 0;

        int cols = this.bingoCard.numCols();
        int rows = this.bingoCard.numRows();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double value = this.bingoCard.get(row, col);

                if (!this.isMarked(row, col)) {
                    result += (int) value;
                }
            }
        }

        return result;
    }


    public SimpleMatrix getBingoCard() {
        return bingoCard;
    }

    public void setBingoCard(SimpleMatrix bingoCard) {
        this.bingoCard = bingoCard;
    }

    public SimpleMatrix getMarkedNumbers() {
        return markedNumbers;
    }

    public boolean isMarkAsWinning() {
        return markAsWinning;
    }

    public void setMarkAsWinning(boolean markAsWinning) {
        this.markAsWinning = markAsWinning;
    }
}
