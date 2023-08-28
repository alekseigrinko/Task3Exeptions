import java.util.Scanner;


public class Example4 {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("Введите любую строку, для отмены введите -1: ");
            String line = scanner.nextLine();
            try {
                if (line.equals("-1")) {
                    System.out.println("Выход из цикла!");
                    check = false;
                } else if (line.isBlank()) {
                    throw new Exception("Пустые строки вводить нельзя!");
                } else {
                    System.out.println("Вы ввели: " + line);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
