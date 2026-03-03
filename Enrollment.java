// File: Enrollment.java (similar to Order)
public class Enrollment {
    
    private String enrollmentId;
    private Student student;
    private Course course;
    private double grade;
    private IPerson createdBy;
    private boolean graded;

    public Enrollment(String enrollmentId, Student student, 
                     Course course, IPerson createdBy) {
        setEnrollmentId(enrollmentId);
        setStudent(student);
        setCourse(course);
        setCreatedBy(createdBy);
        this.grade = 0.0;
        this.graded = false;
    }

    // Getters
    public String getEnrollmentId() { return enrollmentId; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public double getGrade() { return grade; }
    public IPerson getCreatedBy() { return createdBy; }
    public boolean isGraded() { return graded; }

    // Setters
    public void setEnrollmentId(String enrollmentId) {
        if (enrollmentId == null || enrollmentId.trim().isEmpty()) 
            this.enrollmentId = "UNKNOWN";
        else this.enrollmentId = enrollmentId.trim();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCreatedBy(IPerson createdBy) {
        this.createdBy = createdBy;
    }

    public void setGrade(double grade) {
        if (grade >= 0 && grade <= 4.0) {
            this.grade = grade;
            this.graded = true;
            // Update student's GPA could be implemented here
        }
    }

    @Override
    public String toString() {
        String studentName = (student == null) ? "UNKNOWN" : student.getFullName();
        String courseName = (course == null) ? "UNKNOWN" : course.getCourseName();
        String staffId = (createdBy == null) ? "UNKNOWN" : createdBy.getId();

        return "Enrollment{" +
                "enrollmentId='" + enrollmentId + '\'' +
                ", student='" + studentName + '\'' +
                ", course='" + courseName + '\'' +
                ", grade=" + (graded ? grade : "Not graded") +
                ", createdBy='" + staffId + '\'' +
                '}';
    }
}