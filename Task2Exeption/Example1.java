import java.util.Scanner;


public class Example1 {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("Введите дробное значение, для отмены введите -1: ");
            String line = scanner.nextLine();
            try {
                float num = Float.parseFloat(line);
                if (num == -1) {
                    System.out.println("Выход из цикла!");
                    check = false;
                } else {
                    System.out.println("Было введено значение: " + num);
                }
            } catch (RuntimeException e) {
                System.out.println("Было введено некорректное значение, введите число повторно!");
            }
        }
    }
}
