package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataPackage.Lecturer;
import dataPackage.Positions;
import dataPackage.Rates;
import dataPackage.Student;
import mainPackage.MainAppletClass;

public class ProgrammTest3 {
    private Random random = new Random();
    private ProgrammClicker clicker = ProgrammClicker.INSTANCE;

    @BeforeClass
    public static void setUpOnce() throws Exception {
        MainAppletClass.main(null);
    }

    @Before
    public void init() throws Exception {
        MainAppletClass.panelsManager.dataCollector.removeAll();
    }

    @Test
    public void testCountStudentsOnLecturer() throws Exception {
        MainAppletClass.panelsManager.dataCollector.removeAll();
//        clicker.pressLectorerPanelButton();
        String surname = RandomValues.getSaltString();
        String name = RandomValues.getSaltString();
        String patronymic = RandomValues.getSaltString();
        String cathedra = RandomValues.getSaltString();
//        Positions position = Positions.values()[RandomString.randomNumberRange(0, Positions.values().length-1)];
//      Rates rate = Rates.values()[RandomString.randomNumberRange(1, Rates.values().length-1)];
        Positions position = Positions.PROFESSOR;
        Rates rate = Rates.THIRD_PART;
        Lecturer newLecturer = new Lecturer(surname, name, patronymic, cathedra, position, rate);
        MainAppletClass.panelsManager.dataCollector.addLecturer(newLecturer);

        for(int k = 0; k < 2; k++) {
            String surnameStudent = RandomValues.getSaltString();
            String nameStudent = RandomValues.getSaltString();
            String patronymicStudent = RandomValues.getSaltString();
            String facultyStudent = RandomValues.getSaltString();
            String groupNameStudent = RandomValues.getSaltString();
            Student newStudent = new Student(surnameStudent, nameStudent, patronymicStudent, facultyStudent, groupNameStudent);
            Lecturer lecturer = MainAppletClass.panelsManager.dataCollector.addStudent(newStudent);
        }

        ArrayList<Student> students = MainAppletClass.panelsManager.dataCollector.getStudentsByLecturer(newLecturer);
        assertEquals(newLecturer.maxStudens, students.size());
    }
}
