//Tested by Jimmy Morea
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    private UserList userList;
    private ArrayList<Student> studentList;
    private ArrayList<Employer> employerList;
    private ArrayList<Administrator> adminList;

    @BeforeEach
    public void setup() {
        userList = UserList.getUserList();
        userList.clear();
        studentList = userList.getStudents();
        employerList = userList.getEmployers();
        adminList = userList.getAdmin();
        studentList.add(new Student("firstName", "lastName", "sameemail", "password", "123-456-7890"));
        studentList.add(new Student("firstName2", "lastName2", "email2", "password2", "phoneNumber2"));
        DataWriter.saveStudents();
    }

    @Test
    public void testGetUserList() {
        UserList duplicate = UserList.getUserList();
        assertEquals(userList, duplicate);

    }

    @Test
    public void testGetStudents() {
        assertEquals(studentList.size(), 2);
    }

    @Test
    public void testAddStudent() {
        studentList.add(new Student("Bobby", "Land", "bland@email.sc.edu", "password", "842-494-2949"));
        assertEquals(studentList.size(), 3);
    }

    @Test
    public void testClear() {
        userList.clear();
        studentList = userList.getStudents();
        assertEquals(studentList.size(), 0);
    }
}
