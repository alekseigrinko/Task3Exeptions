package Service;

import Exceptions.*;
import Model.Gender;
import Model.User;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    private List<User> users;

    public Service() {
        this.users = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean check = true;
        while (check) {
            System.out.println("\nВведите данные пользователя, разделенные пробелом, для отмены введите -1:\n" +
                    "Фамилия Имя Отчество датарождения номертелефона пол\n" +
                    "\n" +
                    "Форматы данных:\n" +
                    "фамилия, имя, отчество - строки\n" +
                    "датарождения - строка формата dd.mm.yyyy\n" +
                    "номертелефона - целое беззнаковое число без форматирования\n" +
                    "пол - символ латиницей f или m \n");
            String line = scanner.nextLine();
            if (line.equals("-1")) {
                System.out.println("Выход из цикла!");
                check = false;
            } else {
                addUserData(line);
            }
        }
    }

    private String[] parseLine(String line) throws DataUserSizeException {
        String[] dataUser = line.split(" ");
        if (dataUser.length < 6) {
            throw new DataUserSizeException("Ошибка полноты передачи данных, было введено данных меньше, чем требуется");
        } else if (dataUser.length > 6) {
            throw new DataUserSizeException("Ошибка полноты передачи данных, было введено данных больше, чем требуется");
        }
        return dataUser;
    }

    private LocalDate parseBirthday(String birthday) throws BirthdayException {
        LocalDate dataBirthday;
        try {
            dataBirthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            throw new BirthdayException("Ошибка формата ввода даты рождения");
        }
        return dataBirthday;
    }

    private Gender parseGender(String line) throws GenderException {
        if (line.length() != 1) {
            throw new GenderException("Ошибка ввода пола пользователя");
        }
        if (line.equals("m")) {
            return Gender.MALE;
        } else if (line.equals("f")) {
            return Gender.FEMALE;
        } else {
            throw new GenderException("Ошибка ввода пола пользователя");
        }
    }

    private int parseTelephone(String line) throws TelephoneException {
        int number;
        try {
            number = Integer.parseInt(line);
        } catch (Exception e) {
            throw new TelephoneException("Ошибка ввода данных телефона");
        }
        return number;
    }

    private String parseNames(String line) throws NameException {
        if (!line.matches("[а-яА-Яa-zA-Z]+")) {
            throw new NameException("Ошибка указания ФИО пользователя, в наименовании не должно быть символов и знаков");
        }
        return line;
    }

    private User addUser(String line) {
        try {
            User user = new User();
            String[] dataUser = parseLine(line);
            user.setName(parseNames(dataUser[1]));
            user.setSecondName(parseNames(dataUser[2]));
            user.setSurname(parseNames(dataUser[0]));
            user.setBirthday(parseBirthday(dataUser[3]));
            user.setTelephone(parseTelephone(dataUser[4]));
            user.setGender(parseGender(dataUser[5]));
            return user;
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return null;
    }

    private void addUserData(String line) {
        User user = addUser(line);
        if (user != null) {
            writeFile(user, users.stream().
                    anyMatch(u -> u.getSurname().equals(user.getSurname())));
            users.add(user);
        }
    }

    private void writeFile(User user, boolean append) {
        try (FileWriter fileWriter = new FileWriter(user.getSurname() + ".txt", append)) {
            fileWriter.write(user.toString() + "\n");
            System.out.println("Данные пользователя сохранены:\n" + user);
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
