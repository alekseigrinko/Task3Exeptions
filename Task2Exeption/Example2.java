import java.util.Random;

public class Example2 {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        int[] intArray = new int[new Random().nextInt(20)];
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException | IndexOutOfBoundsException e) {
            System.out.println("Catching exception: " + e);
        }
    }
}
