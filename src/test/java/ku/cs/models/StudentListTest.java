package ku.cs.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ku.cs.services.StudentListFileDatasource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudentListTest {
    private StudentList list;

    private static final String dir  = "data";
    private static final String file = "student-list.csv";

    @BeforeEach
    void setup() {
        StudentListFileDatasource ds = new StudentListFileDatasource(dir, file);
        list = ds.readData();
    }

    @Test
    @DisplayName("ทดสอบการเพิ่มนักศึกษาใหม่")
    void testAddNewStudent() {
        StudentList l = new StudentList();
        l.addNewStudent("100", "Mari");
        Student s = l.findStudentById("100");
        assertNotNull(s);
        assertEquals("Mari", s.getName());
        assertEquals(0.0, s.getScore(), 1e-9);
    }

    @Test
    @DisplayName("ทดสอบการค้นหาด้วยID")
    void testFindStudentById() {
        Student s = list.findStudentById("6410450002"); // Barbara Liskov
        assertNotNull(s);
        assertEquals("Barbara Liskov", s.getName());
        assertNull(list.findStudentById("999"));
    }

    @Test
    @DisplayName("ทดสอบการกรองนักศึกษาด้วยชื่อ")
    void testFilterByName() {
        StudentList alans = list.filterByName("Alan"); // Alan Turing + Alan Kay
        ArrayList<Student> got = alans.getStudents();
        assertEquals(2, got.size());
        assertTrue(got.stream().anyMatch(st -> st.getName().equals("Alan Turing")));
        assertTrue(got.stream().anyMatch(st -> st.getName().equals("Alan Kay")));

        StudentList sub2 = list.filterByName("cArThy");
        assertEquals(1, sub2.getStudents().size());
        assertEquals("John McCarthy", sub2.getStudents().get(0).getName());
    }

    @Test
    @DisplayName("ทดสอบการให้คะแนนตามID")
    void testGiveScoreToId() {
        Student barbara = list.findStudentById("6410450002"); // 60.7
        assertEquals(60.7, barbara.getScore(), 1e-9);

        list.giveScoreToId("6410450002", 10);
        assertEquals(70.7, barbara.getScore(), 1e-9);

        list.giveScoreToId("6410450002", -5); // ignored
        assertEquals(70.7, barbara.getScore(), 1e-9);

        list.giveScoreToId("999", 10); // no effect
        assertNull(list.findStudentById("999"));
    }

    @Test
    @DisplayName("ทดสอบการดูเกรดจากID")
    void testViewGradeOfId() {
        assertEquals("A", list.viewGradeOfId("6410450001")); // 145.5
        assertEquals("C", list.viewGradeOfId("6410450002")); // 60.7
        assertEquals("A", list.viewGradeOfId("6410450003")); // 346.2
        assertEquals("C", list.viewGradeOfId("6410450004")); // 63.8
        assertEquals("C", list.viewGradeOfId("6410450005")); // 68.0
        assertNull(list.viewGradeOfId("999"));
    }
}