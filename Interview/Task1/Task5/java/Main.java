import models.Student;
import service.StudentDaoImpl;

public class Main {
    public static void main(String[] args) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        for (int i = 0; i < 1000; i++) {
            Student student = new Student("Student " + i, (int) (Math.random() * 5 + 1));
            sdi.save(student);
        }

        System.out.println(sdi.findById(66));

        for (Student st: sdi.findAll()) {
            sdi.delete(st);
        }

        System.out.println(sdi.findAll());
    }
}
