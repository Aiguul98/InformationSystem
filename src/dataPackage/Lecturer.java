package dataPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Lecturer implements Serializable {
    public String surname;
    public String name;
    public String patronymic;
    public String cathedra;
    public Positions position;
    public Rates rate;
    public int maxStudens;
    public ArrayList<Student> students;

    public Lecturer(String surname, String name, String patronymic, String cathedra, Positions position, Rates rate) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.cathedra = cathedra;
        this.position = position;
        this.rate = rate;
        float fRate = Float.parseFloat(rate.toString());
        if(position.equals(Positions.PROFESSOR)) {
            maxStudens = (int)(8*fRate);
        } else if(position.equals(Positions.DOCENT)) {
            maxStudens = (int)(6*fRate);
        } else if(position.equals(Positions.SENIOR_LECTURER)) {
            maxStudens = (int)(4*fRate);
        } else if(position.equals(Positions.LECTURER)) {
            maxStudens = (int)(2*fRate);
        }
        students = new ArrayList<Student>(maxStudens);
        System.out.println("Lecturer::Lecturer(); -- fRate:" + fRate + " maxStudens:" + maxStudens);
    }

    public String getFIO() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(surname);
        stringBuilder.append(" " + name);
        stringBuilder.append(" " + patronymic);
        return stringBuilder.toString();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lecturer:[");
        stringBuilder.append("surname:" + surname);
        stringBuilder.append(", " + "name:" + name);
        stringBuilder.append(", " + "patronymic:" + patronymic);
        stringBuilder.append(", " + "cathedra:" + cathedra);
        stringBuilder.append(", " + "position:" + position);
        stringBuilder.append(", " + "rate:" + rate);
        stringBuilder.append(", " + "maxStudens:" + maxStudens);
        stringBuilder.append(", " + "students:" + students);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
