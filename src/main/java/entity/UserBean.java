package entity;

import java.time.LocalDate;

public class UserBean {
    private String name;
    private LocalDate birthDate;

    public UserBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate.toString();
    }

    public void setBirthDate(String birthDateStr) {
        this.birthDate = LocalDate.parse(birthDateStr);
    }

    public long getLifeDays(){
        return LocalDate.now().toEpochDay() - birthDate.toEpochDay();
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
