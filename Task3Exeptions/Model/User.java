package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {
    private String name;
    private String secondName;
    private String surname;
    private LocalDate birthday;
    private int telephone;
    private Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("<%s><%s><%s><%s><%s><%s>", surname, name, secondName,
                DateTimeFormatter.ofPattern("dd.MM.yyyy").format(birthday), telephone, gender);
    }
}
