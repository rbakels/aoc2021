package nl.bd.aoc.day3;

public class BitCriteria {
    public int numberOfOnes = 0;
    public int numberOfZeros = 0;

    public BitCriteria(int numberOfOnes, int numberOfZeros) {
        this.numberOfOnes = numberOfOnes;
        this.numberOfZeros = numberOfZeros;
    }

    public int getNumberOfOnes() {
        return numberOfOnes;
    }

    public void setNumberOfOnes(int numberOfOnes) {
        this.numberOfOnes = numberOfOnes;
    }

    public int getNumberOfZeros() {
        return numberOfZeros;
    }

    public void setNumberOfZeros(int numberOfZeros) {
        this.numberOfZeros = numberOfZeros;
    }
}
