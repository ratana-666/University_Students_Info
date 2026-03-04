// File: University.java
import java.util.ArrayList;

public class University {
    // Action permissions
    public static final String CREATE_STUDENT = "CREATE_STUDENT";
    public static final String CREATE_PROFESSOR = "CREATE_PROFESSOR";
    public static final String CREATE_COURSE = "CREATE_COURSE";
    public static final String ENROLL_STUDENT = "ENROLL_STUDENT";
    public static final String VIEW_STUDENTS = "VIEW_STUDENTS";
    public static final String VIEW_PROFESSORS = "VIEW_PROFESSORS";
    public static final String VIEW_COURSES = "VIEW_COURSES";
    public static final String VIEW_ENROLLMENTS = "VIEW_ENROLLMENTS";
    public static final String GRADE_STUDENT = "GRADE_STUDENT";
    public static final String SET_COURSE_AVAILABILITY = "SET_COURSE_AVAILABILITY";

    // Basic Info
    private String universityName;
    private String address;

    // ArrayLists
    private ArrayList<IPerson> persons;
    private ArrayList<Course> courses;
    private ArrayList<Enrollment> enrollments;

    // Login Dependency
    private IPerson loggedInPerson;

    // Feedback Message
    private String lastMessage;

    // Constructor
    public University(String universityName, String address) {
        setUniversityName(universityName);
        setAddress(address);

        persons = new ArrayList<>();
        courses = new ArrayList<>();
        enrollments = new ArrayList<>();

        loggedInPerson = null;

        // Default admin
        seedDefaultAdmin();

        lastMessage = "University created. Default admin: admin / 1234";
    }

    // Getters/Setters
    public String getUniversityName() { return universityName; }
    public String getAddress() { return address; }
    public String getLastMessage() { return lastMessage; }
    public boolean isPersonLoggedIn() { return loggedInPerson != null; }
    public IPerson getLoggedInPerson() { return loggedInPerson; }

    public void setUniversityName(String universityName) {
        if (isBlank(universityName)) this.universityName = "University";
        else this.universityName = universityName.trim();
    }

    public void setAddress(String address) {
        if (isBlank(address)) this.address = "Unknown";
        else this.address = address.trim();
    }

    private void setLastMessage(String msg) {
        lastMessage = msg;
    }

    // Default Admin
    private void seedDefaultAdmin() {
        Admin admin = new Admin("A001", "Admin User", "012345678", 
                                           "admin", "1234");
        persons.add(admin);
    }

    // Login Check
    private boolean requireLogin() {
        if (loggedInPerson == null) {
            setLastMessage("Action denied: must login first.");
            return false;
        }

        if (!loggedInPerson.isActive()) {
            loggedInPerson = null;
            setLastMessage("Action denied: account is inactive (auto logout).");
            return false;
        }

        return true;
    }

    // Login/Logout
    public void login(String username, String password) {
        if (isBlank(username) || password == null) {
            setLastMessage("Login failed: missing username/password.");
            return;
        }

        for (int i = 0; i < persons.size(); i++) {
            IPerson p = persons.get(i);

            if (p.getUsername().equalsIgnoreCase(username.trim())) {

                if (!p.isActive()) {
                    setLastMessage("Login failed: account is inactive.");
                    return;
                }

                if (!p.checkPassword(password)) {
                    setLastMessage("Login failed: wrong password.");
                    return;
                }

                loggedInPerson = p;
                setLastMessage("Login success. Welcome " + p.getFullName() + "!");
                return;
            }
        }

        setLastMessage("Login failed: username not found.");
    }

    public void logout() {
        loggedInPerson = null;
        setLastMessage("Logged out successfully.");
    }

    // Require Permission
    private boolean requirePermission(String action) {
        if (loggedInPerson == null) {
            setLastMessage("Please login first");
            return false;
        }
        if (!loggedInPerson.can(action)) {
            setLastMessage("Permission denied: " + loggedInPerson.getRole() + 
                          " cannot perform " + action);
            return false;
        }
        return true;
    }

    // Create Student
    public void createStudent(String studentId, String fullName, String phone,
                             String username, String password, String major, int year) {
        if (!requireLogin() || !requirePermission(CREATE_STUDENT)) return;

        if (isBlank(studentId) || isBlank(username)) {
            setLastMessage("Cannot create student: studentId/username is empty.");
            return;
        }

        // duplicate username check
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getUsername().equalsIgnoreCase(username.trim())) {
                setLastMessage("Cannot create student: username already exists.");
                return;
            }
        }

        persons.add(new Student(studentId, fullName, phone, username, 
                                     password, "Student", major, year));
        setLastMessage("Student created successfully.");
    }

    // Create Professor
    public void createProfessor(String professorId, String fullName, String phone,
                               String username, String password, String department) {
        if (!requireLogin() || !requirePermission(CREATE_PROFESSOR)) return;

        if (isBlank(professorId) || isBlank(username)) {
            setLastMessage("Cannot create professor: professorId/username is empty.");
            return;
        }

        // duplicate username check
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getUsername().equalsIgnoreCase(username.trim())) {
                setLastMessage("Cannot create professor: username already exists.");
                return;
            }
        }

        persons.add(new Professor(professorId, fullName, phone, username, 
                                       password, department));
        setLastMessage("Professor created successfully.");
    }

    // Create Course
    public void createCourse(String courseId, String courseName, int credits,
                            String professorId, int maxStudents) {
        if (!requireLogin() || !requirePermission(CREATE_COURSE)) return;

        if (isBlank(courseId)) {
            setLastMessage("Cannot create course: courseId is empty.");
            return;
        }

        // duplicate courseId check
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equalsIgnoreCase(courseId.trim())) {
                setLastMessage("Cannot create course: courseId already exists.");
                return;
            }
        }

        // Find professor
        Professor professor = null;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i) instanceof Professor && 
                persons.get(i).getId().equalsIgnoreCase(professorId)) {
                professor = (Professor) persons.get(i);
                break;
            }
        }

        if (professor == null) {
            setLastMessage("Cannot create course: professor not found.");
            return;
        }

        courses.add(new Course(courseId, courseName, credits, professor, maxStudents, loggedInPerson));
        setLastMessage("Course created successfully.");
    }

    // Enroll Student
    public void enrollStudent(String studentId, String courseId) {
        if (!requireLogin() || !requirePermission(ENROLL_STUDENT)) return;

        // Find student
        Student student = null;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i) instanceof Student && 
                persons.get(i).getId().equalsIgnoreCase(studentId)) {
                student = (Student) persons.get(i);
                break;
            }
        }

        if (student == null) {
            setLastMessage("Cannot enroll: student not found.");
            return;
        }

        if (!student.isActive()) {
            setLastMessage("Cannot enroll: student is inactive.");
            return;
        }

        // Find course
        Course course = findCourseById(courseId);
        if (course == null) {
            setLastMessage("Cannot enroll: course not found.");
            return;
        }

        if (!course.isAvailable()) {
            setLastMessage("Cannot enroll: course is not available.");
            return;
        }

        if (course.getCurrentEnrollment() >= course.getMaxStudents()) {
            setLastMessage("Cannot enroll: course is full.");
            return;
        }

        // Check if already enrolled
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            if (e.getStudent().getId().equalsIgnoreCase(studentId) && 
                e.getCourse().getCourseId().equalsIgnoreCase(courseId)) {
                setLastMessage("Cannot enroll: student already enrolled in this course.");
                return;
            }
        }

        String enrollmentId = "ENR" + (enrollments.size() + 1);
        enrollments.add(new Enrollment(enrollmentId, student, course, loggedInPerson));
        course.incrementEnrollment();
        setLastMessage("Student enrolled successfully: " + enrollmentId);
    }

    // Grade Student
    public void gradeStudent(String enrollmentId, double grade) {
        if (!requireLogin() || !requirePermission(GRADE_STUDENT)) return;

        Enrollment enrollment = findEnrollmentById(enrollmentId);
        if (enrollment == null) {
            setLastMessage("Cannot grade: enrollment not found.");
            return;
        }

        // Check if logged in person is the professor of this course
        if (!enrollment.getCourse().getProfessor().getId().equals(loggedInPerson.getId())) {
            setLastMessage("Permission denied: Only the course professor can assign grades.");
            return;
        }

        enrollment.setGrade(grade);
        setLastMessage("Grade assigned successfully.");
    }

    // Set Course Availability
    public void setCourseAvailability(String courseId, boolean available) {
        if (!requireLogin() || !requirePermission(SET_COURSE_AVAILABILITY)) return;

        Course course = findCourseById(courseId);
        if (course == null) {
            setLastMessage("Course not found.");
            return;
        }

        course.setAvailable(available);
        setLastMessage("Course availability updated.");
    }

    // Print Methods
    public void printStudents() {
        System.out.println("\n--- Students ---");
        int count = 0;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i) instanceof Student) {
                System.out.println((count + 1) + ") " + persons.get(i));
                count++;
            }
        }
        if (count == 0) System.out.println("No students.");
    }

    public void printProfessors() {
        System.out.println("\n--- Professors ---");
        int count = 0;
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i) instanceof Professor) {
                System.out.println((count + 1) + ") " + persons.get(i));
                count++;
            }
        }
        if (count == 0) System.out.println("No professors.");
    }

    public void printCourses() {
        System.out.println("\n--- Courses (" + courses.size() + ") ---");
        if (courses.size() == 0) System.out.println("No courses.");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ") " + courses.get(i));
        }
    }

    public void printEnrollments() {
        System.out.println("\n--- Enrollments (" + enrollments.size() + ") ---");
        if (enrollments.size() == 0) System.out.println("No enrollments.");
        for (int i = 0; i < enrollments.size(); i++) {
            System.out.println((i + 1) + ") " + enrollments.get(i));
        }
    }

    // Find Helpers
    private Course findCourseById(String courseId) {
        if (isBlank(courseId)) return null;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equalsIgnoreCase(courseId.trim())) {
                return courses.get(i);
            }
        }
        return null;
    }

    private Enrollment findEnrollmentById(String enrollmentId) {
        if (isBlank(enrollmentId)) return null;
        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getEnrollmentId().equalsIgnoreCase(enrollmentId.trim())) {
                return enrollments.get(i);
            }
        }
        return null;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}