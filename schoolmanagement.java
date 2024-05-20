import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Student class to manage student information
class Student {
    private String id;
    private String name;
    private List<Course> courses;
    private Map<Course, String> grades;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void enrollCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
        }
    }

    public void assignGrade(Course course, String grade) {
        if (courses.contains(course)) {
            grades.put(course, grade);
        }
    }

    public void displayCourses() {
        System.out.println("Courses enrolled by " + name + ":");
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }

    public void displayGrades() {
        System.out.println("Grades for " + name + ":");
        for (Map.Entry<Course, String> entry : grades.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}

// Course class to manage course information
class Course {
    private String code;
    private String name;
    private Teacher teacher;

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void assignTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}

// Teacher class to manage teacher information
class Teacher {
    private String id;
    private String name;
    private List<Course> courses;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void assignCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.assignTeacher(this);
        }
    }

    public void displayCourses() {
        System.out.println("Courses taught by " + name + ":");
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }
}

// Main class to demonstrate the functionality
public class schoolmanagement {
    public static void main(String[] args) {
        // Create students
        Student student1 = new Student("S001", "Ajay");
        Student student2 = new Student("S002", "Vijay");

        // Create teachers
        Teacher teacher1 = new Teacher("T001", "Ramesh");
        Teacher teacher2 = new Teacher("T002", "Rajesh");

        // Create courses
        Course course1 = new Course("C101", "Python");
        Course course2 = new Course("C102", "Java");

        // Assign teachers to courses
        teacher1.assignCourse(course1);
        teacher2.assignCourse(course2);

        // Enroll students in courses
        student1.enrollCourse(course1);
        student1.enrollCourse(course2);
        student2.enrollCourse(course1);

        // Assign grades to students
        student1.assignGrade(course1, "A");
        student1.assignGrade(course2, "B");
        student2.assignGrade(course1, "A+");

        // Display information
        student1.displayCourses();
        student1.displayGrades();
        
        student2.displayCourses();
        student2.displayGrades();

        teacher1.displayCourses();
        teacher2.displayCourses();
    }
}
