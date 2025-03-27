import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity, enrolled;
    
    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public boolean enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }
}

class Student {
    String id, name;
    List<Course> registeredCourses;
    
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println(name + " successfully registered for " + course.title);
        } else {
            System.out.println("Course is full.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println(name + " dropped " + course.title);
        } else {
            System.out.println("You are not registered in this course.");
        }
    }

    public void displayStudentDetails() {
        System.out.println("\nStudent Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Registered Courses:");
        if (registeredCourses.isEmpty()) {
            System.out.println("None");
        } else {
            for (Course course : registeredCourses) {
                System.out.println("- " + course.title + " (" + course.code + ")");
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        
        courses.add(new Course("CS101", "Intro to CS", "Basics of programming", 3, "Mon-Wed 10 AM"));
        courses.add(new Course("MA102", "Calculus", "Advanced Math", 2, "Tue-Thu 2 PM"));

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Student");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Student Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Student ID: ");
                String studentId = scanner.nextLine();
                System.out.print("Enter Student Name: ");
                String studentName = scanner.nextLine();
                students.add(new Student(studentId, studentName));
                System.out.println("Student added successfully!");
            } else if (choice == 2) {
                if (students.isEmpty()) {
                    System.out.println("No students available. Add a student first.");
                    continue;
                }
                System.out.println("Select a student:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println((i + 1) + ". " + students.get(i).name);
                }
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                
                if (studentIndex >= 0 && studentIndex < students.size()) {
                    Student student = students.get(studentIndex);
                    System.out.println("\nAvailable Courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println((i + 1) + ". " + course.code + " - " + course.title + " (" + course.enrolled + "/" + course.capacity + ")");
                    }
                    System.out.print("Enter the course number to register: ");
                    int courseIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (courseIndex >= 0 && courseIndex < courses.size()) {
                        student.registerCourse(courses.get(courseIndex));
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                } else {
                    System.out.println("Invalid student selection.");
                }
            } else if (choice == 3) {
                System.out.println("Select a student:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println((i + 1) + ". " + students.get(i).name);
                }
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                
                if (studentIndex >= 0 && studentIndex < students.size()) {
                    Student student = students.get(studentIndex);
                    System.out.println("Your registered courses:");
                    for (int i = 0; i < student.registeredCourses.size(); i++) {
                        System.out.println((i + 1) + ". " + student.registeredCourses.get(i).title);
                    }
                    System.out.print("Enter the course number to drop: ");
                    int dropIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (dropIndex >= 0 && dropIndex < student.registeredCourses.size()) {
                        student.dropCourse(student.registeredCourses.get(dropIndex));
                    } else {
                        System.out.println("Invalid selection.");
                    }
                } else {
                    System.out.println("Invalid student selection.");
                }
            } else if (choice == 4) {
                System.out.println("Select a student:");
                for (int i = 0; i < students.size(); i++) {
                    System.out.println((i + 1) + ". " + students.get(i).name);
                }
                int studentIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                
                if (studentIndex >= 0 && studentIndex < students.size()) {
                    students.get(studentIndex).displayStudentDetails();
                } else {
                    System.out.println("Invalid student selection.");
                }
            } else if (choice == 5) {
                System.out.println("Exiting system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}
