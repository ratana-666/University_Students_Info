// File: UniversityMain.java
import java.util.Scanner;

public class UniversityMain {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        University uni = new University("CADT University", "Phnom Penh");

        int choice;

        do {
            if (!uni.isPersonLoggedIn()) {
                printMainMenu();

                System.out.print("Choose: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: {
                        System.out.print("Username: ");
                        String username = sc.nextLine();

                        System.out.print("Password: ");
                        String password = sc.nextLine();

                        uni.login(username, password);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 2: {
                        uni.printCourses();
                        break;
                    }

                    case 0: {
                        System.out.println("Goodbye!");
                        break;
                    }

                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                printPersonMenu(uni);

                System.out.print("Choose: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: { // Create Student
                        System.out.print("Student ID: ");
                        String studentId = sc.nextLine();

                        System.out.print("Full Name: ");
                        String fullName = sc.nextLine();

                        System.out.print("Phone: ");
                        String phone = sc.nextLine();

                        System.out.print("Username: ");
                        String username = sc.nextLine();

                        System.out.print("Password: ");
                        String password = sc.nextLine();

                        System.out.print("Major: ");
                        String major = sc.nextLine();

                        System.out.print("Year (1-6): ");
                        int year = sc.nextInt();
                        sc.nextLine();

                        uni.createStudent(studentId, fullName, phone, username, 
                                        password, major, year);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 2: { // Create Professor
                        System.out.print("Professor ID: ");
                        String professorId = sc.nextLine();

                        System.out.print("Full Name: ");
                        String fullName = sc.nextLine();

                        System.out.print("Phone: ");
                        String phone = sc.nextLine();

                        System.out.print("Username: ");
                        String username = sc.nextLine();

                        System.out.print("Password: ");
                        String password = sc.nextLine();

                        System.out.print("Department: ");
                        String department = sc.nextLine();

                        uni.createProfessor(professorId, fullName, phone, username, 
                                          password, department);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 3: { // Create Course
                        System.out.print("Course ID: ");
                        String courseId = sc.nextLine();

                        System.out.print("Course Name: ");
                        String courseName = sc.nextLine();

                        System.out.print("Credits: ");
                        int credits = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Professor ID: ");
                        String professorId = sc.nextLine();

                        System.out.print("Max Students: ");
                        int maxStudents = sc.nextInt();
                        sc.nextLine();

                        uni.createCourse(courseId, courseName, credits, 
                                       professorId, maxStudents);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 4: { // Enroll Student
                        System.out.print("Student ID: ");
                        String studentId = sc.nextLine();

                        System.out.print("Course ID: ");
                        String courseId = sc.nextLine();

                        uni.enrollStudent(studentId, courseId);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 5: { // Grade Student
                        System.out.print("Enrollment ID: ");
                        String enrollmentId = sc.nextLine();

                        System.out.print("Grade (0.0 - 4.0): ");
                        double grade = sc.nextDouble();
                        sc.nextLine();

                        uni.gradeStudent(enrollmentId, grade);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 6: { // Set Course Availability
                        System.out.print("Course ID: ");
                        String courseId = sc.nextLine();

                        System.out.print("Available? (1=Yes, 0=No): ");
                        int a = sc.nextInt();
                        sc.nextLine();

                        boolean available = (a == 1);

                        uni.setCourseAvailability(courseId, available);
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 7: { // List Students
                        uni.printStudents();
                        break;
                    }

                    case 8: { // List Professors
                        uni.printProfessors();
                        break;
                    }

                    case 9: { // List Courses
                        uni.printCourses();
                        break;
                    }

                    case 10: { // List Enrollments
                        uni.printEnrollments();
                        break;
                    }

                    case 11: { // Logout
                        uni.logout();
                        System.out.println(uni.getLastMessage());
                        break;
                    }

                    case 0: {
                        System.out.println("Goodbye!");
                        break;
                    }

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } while (choice != 0);

        sc.close();
    }

    private static void printMainMenu() {
        System.out.println("\n=== MAIN MENU (Not Logged In) ===");
        System.out.println("1) Login");
        System.out.println("2) View Courses");
        System.out.println("0) Exit");
    }

    private static void printPersonMenu(University uni) {
        System.out.println("\n=== UNIVERSITY MENU (Logged In) ===");
        System.out.println("Logged in: " + uni.getLoggedInPerson());
        System.out.println("1) Create Student");
        System.out.println("2) Create Professor");
        System.out.println("3) Create Course");
        System.out.println("4) Enroll Student");
        System.out.println("5) Grade Student");
        System.out.println("6) Set Course Availability");
        System.out.println("7) List Students");
        System.out.println("8) List Professors");
        System.out.println("9) List Courses");
        System.out.println("10) List Enrollments");
        System.out.println("11) Logout");
        System.out.println("0) Exit");
    }
}