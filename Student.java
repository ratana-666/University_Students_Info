public class Student implements IPerson{
  private String studentId;
  private String fullName;
  private String phone;
  private String username;
  private String password;
  private String role;
  private boolean active;
  private String major;
  private int year;
  private double gpa;

  @Override
  public boolean can(String action) {
      // Students can view their courses and grades
      if (action.equals(University.VIEW_COURSES) || 
          action.equals(University.VIEW_ENROLLMENTS)) {
          return true;
      }
      return false;
  }

  public Student(String studentId, String fullName, String phone,
                      String username, String password, String role,
                      String major, int year) {
      setStudentId(studentId);
      setFullName(fullName);
      setPhone(phone);
      setUsername(username);
      setPassword(password);
      setRole(role);
      setMajor(major);
      setYear(year);
      this.gpa = 0.0;
      this.active = true;
  }

  // Getters
   public String getId() { return studentId; }
   public String getFullName() { return fullName; }
   public String getPhone() { return phone; }
   public String getUsername() { return username; }
   public String getRole() { return role; }
   public boolean isActive() { return active; }
  public String getMajor() { return major; }
  public int getYear() { return year; }
  public double getGpa() { return gpa; }

  @Override
  public boolean checkPassword(String input) {
      return password != null && password.equals(input);
  }

  // Setters
  public void setStudentId(String studentId) {
      if (isBlank(studentId)) this.studentId = "UNKNOWN";
      else this.studentId = studentId.trim();
  }

  public void setFullName(String fullName) {
      if (isBlank(fullName)) this.fullName = "No Name";
      else this.fullName = fullName.trim();
  }

  public void setPhone(String phone) {
      String p = (phone == null) ? "" : phone.trim();
      if (!isDigits(p) || p.length() < 8 || p.length() > 15) this.phone = "00000000";
      else this.phone = p;
  }

  public void setUsername(String username) {
      if (isBlank(username)) this.username = "student_" + this.studentId;
      else this.username = username.trim();
  }

  public void setPassword(String password) {
      String pw = (password == null) ? "" : password;
      if (pw.length() < 4) this.password = "0000";
      else this.password = pw;
  }

  public void setRole(String role) {
      if (isBlank(role)) this.role = "Student";
      else this.role = role.trim();
  }

  public void setMajor(String major) {
      if (isBlank(major)) this.major = "Undeclared";
      else this.major = major.trim();
  }

  public void setYear(int year) {
      if (year < 1 || year > 6) this.year = 1;
      else this.year = year;
  }

  public void setGpa(double gpa) {
      if (gpa < 0 || gpa > 4.0) this.gpa = 0.0;
      else this.gpa = gpa;
  }

  public void setActive(boolean active) {
      this.active = active;
  }

  private boolean isBlank(String s) {
      return s == null || s.trim().isEmpty();
  }

  private boolean isDigits(String s) {
      if (isBlank(s)) return false;
      for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if (c < '0' || c > '9') return false;
      }
      return true;
  }

  @Override
  public String toString() {
      return "Student{" +
              "studentId='" + studentId + '\'' +
              ", fullName='" + fullName + '\'' +
              ", major='" + major + '\'' +
              ", year=" + year +
              ", gpa=" + gpa +
              ", active=" + active +
              '}';
  }
}
