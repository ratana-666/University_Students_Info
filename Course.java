// File: Course.java (similar to MenuItem)
public class Course {
    
  private String courseId;
  private String courseName;
  private int credits;
  private Professor professor;
  private int maxStudents;
  private int currentEnrollment;
  private boolean available;
  private IPerson createdBy;

  public Course(String courseId, String courseName, int credits,
                Professor professor, int maxStudents, IPerson createdBy) {
      setCreatedBy(createdBy);
      setCourseId(courseId);
      setCourseName(courseName);
      setCredits(credits);
      setProfessor(professor);
      setMaxStudents(maxStudents);
      this.currentEnrollment = 0;
      this.available = true;
  }

  // Getters
  public String getCourseId() { return courseId; }
  public String getCourseName() { return courseName; }
  public int getCredits() { return credits; }
  public Professor getProfessor() { return professor; }
  public int getMaxStudents() { return maxStudents; }
  public int getCurrentEnrollment() { return currentEnrollment; }
  public boolean isAvailable() { return available; }
  public IPerson getCreatedBy() { return createdBy; }

  // Setters
  public void setCreatedBy(IPerson createdBy) {
      if (createdBy == null) {
          System.out.println("Cannot create course: Person is required.");
      } else {
          this.createdBy = createdBy;
      }
  }

  public void setCourseId(String courseId) {
      if (isBlank(courseId)) this.courseId = "UNKNOWN";
      else this.courseId = courseId.trim();
  }

  public void setCourseName(String courseName) {
      if (isBlank(courseName)) this.courseName = "No Name";
      else this.courseName = courseName.trim();
  }

  public void setCredits(int credits) {
      if (credits < 1 || credits > 6) this.credits = 3;
      else this.credits = credits;
  }

  public void setProfessor(Professor professor) {
      this.professor = professor;
  }

  public void setMaxStudents(int maxStudents) {
      if (maxStudents < 1) this.maxStudents = 30;
      else this.maxStudents = maxStudents;
  }

  public void setAvailable(boolean available) {
      this.available = available;
  }

  public void incrementEnrollment() {
      if (currentEnrollment < maxStudents) {
          currentEnrollment++;
      }
  }

  private boolean isBlank(String s) {
      return s == null || s.trim().isEmpty();
  }

  @Override
  public String toString() {
      String professorName = (professor == null) ? "UNKNOWN" : professor.getFullName();
      return "Course [courseId=" + courseId + ", name=" + courseName + 
              ", credits=" + credits + ", professor=" + professorName +
              ", enrollment=" + currentEnrollment + "/" + maxStudents +
              ", available=" + available + "]";
  }
}