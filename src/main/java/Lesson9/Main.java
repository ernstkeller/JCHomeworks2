package Lesson9;

import Lesson9.student.Course;
import Lesson9.student.Student;
import Lesson9.studentsImpl.CourseImpl;
import Lesson9.studentsImpl.StudentImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Course courseComputerScience = new CourseImpl("Computer Science");
        Course courseBiology = new CourseImpl("Biology");
        Course courseMathematics = new CourseImpl("Mathematics");
        Course coursePhysics = new CourseImpl("Physics");
        Course courseChemistry = new CourseImpl("Chemistry");
        Course courseEnglish = new CourseImpl("English");

        Student student1 = new StudentImpl("Bill", Arrays.asList(courseMathematics, courseComputerScience, courseEnglish));
        Student student2 = new StudentImpl("Steve", Arrays.asList(courseComputerScience, courseEnglish));
        Student student3 = new StudentImpl("Helen", Arrays.asList(courseMathematics, coursePhysics, courseChemistry, courseEnglish));
        Student student4 = new StudentImpl("John", Arrays.asList(courseMathematics, coursePhysics, courseEnglish));
        Student student5 = new StudentImpl("Kate", Arrays.asList(courseComputerScience));



        courseStudent(Stream.of(student1, student2, student3, student4, student5), courseEnglish)
                .forEach(student -> System.out.println(student.getName() + " visits the following number of courses: " + student.getAllCourses().size()));

        smartestStudent(Stream.of(student1, student2, student3, student4, student5))
                .forEach(student -> System.out.println(student.getName() + " is one of 3 smartest students and visits the following number of courses: " + student.getAllCourses().size()));

        uniqueCourse(Stream.of(student1, student2, student3, student4, student5))
                .forEach(student -> System.out.println(student.courseName()));

    }

    public static List<Student> courseStudent(Stream<Student> studentStream, Course course){
        return studentStream
                .filter(student -> student.getAllCourses().contains(course))
                .collect(Collectors.toList());
    }

    public static List<Student> smartestStudent(Stream<Student> studentStream){

        Comparator<Student> studentComparator = Comparator.comparingInt(student -> student.getAllCourses().size());

        return studentStream
                .sorted(studentComparator.reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public static List<Course> uniqueCourse(Stream<Student> studentStream){
        return studentStream
                .map(student -> student.getAllCourses())
                .flatMap(courses -> courses.stream())
                .distinct()
                .collect(Collectors.toList());
    }

}
