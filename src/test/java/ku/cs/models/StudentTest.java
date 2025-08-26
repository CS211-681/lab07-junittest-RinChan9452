package ku.cs.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 45.15 คะแนน")
    void testAddScore(){
        Student s = new Student("6xxxxxxxx", "StudentTest");
        s.addScore(45.15);
        assertEquals(45.15, s.getScore());

    }

    @Test
    @DisplayName("ทดสอบการเพิ่มคะแนน 85 คะแนน และให้ Object คำนวนเกรดออกมา")
    void testCalculateGrade(){
        Student s = new Student("6xxxxxxxxx", "StudentTest");
        s.addScore(85);
        assertEquals("A", s.grade());
    }

    @Test
    void testGetScore(){
        Student s = new Student("6xxxxxxxx", "StudentTest");
        s.addScore(45.15);
        assertEquals(45.15, s.getScore());
    }

  @Test
  @DisplayName("ทดสอบการเทียบID")
  void testIsId(){
        Student s = new Student("6xxxxxxxx", "StudentTest");
        assertTrue(s.isId("6xxxxxxxx"));
  }

  @Test
  @DisplayName("ทดสอบการเทียบชื่อ")
  void testIsNameContain(){
        Student s = new Student("6xxxxxxxx", "King Joe");
        assertTrue(s.isNameContains("King Joe"));
  }

}