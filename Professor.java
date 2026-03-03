public class Professor implements IPerson{
  private String professorId;
  private String fullName;
  private String phone;
  private String username;
  private String password;
  private String role;
  private boolean active;
  private String department;

  @Override
  public boolean can(String action) {
      if (action.equals(University.CREATE_COURSE) || 
          action.equals(University.VIEW_COURSES) ||
          action.equals(University.VIEW_ENROLLMENTS) ||
          action.equals(University.GRADE_STUDENT) ||
          action.equals(University.SET_COURSE_AVAILABILITY)) {
          return true;
      }
      return false;
  }

  public Professor(String professorId, String fullName, String phone,
                        String username, String password, String role,
                        String department) {
      setProfessorId(professorId);
      setFullName(fullName);
      setPhone(phone);
      setUsername(username);
      setPassword(password);
      setRole(role);
      setDepartment(department);
      this.active = true;
  }

  // Getters
  public String getId() { return professorId; }
  public String getFullName() { return fullName; }
  public String getPhone() { return phone; }
  public String getUsername() { return username; }
  public String getRole() { return role; }
  public boolean isActive() { return active; }
  public String getDepartment() { return department; }

  @Override
  public boolean checkPassword(String input) {
      return password != null && password.equals(input);
  }

  // Setters
  public void setProfessorId(String professorId) {
      if (isBlank(professorId)) this.professorId = "UNKNOWN";
      else this.professorId = professorId.trim();
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
      if (isBlank(username)) this.username = "prof_" + this.professorId;
      else this.username = username.trim();
  }

  public void setPassword(String password) {
      String pw = (password == null) ? "" : password;
      if (pw.length() < 4) this.password = "0000";
      else this.password = pw;
  }

  public void setRole(String role) {
      if (isBlank(role)) this.role = "Professor";
      else this.role = role.trim();
  }

  public void setDepartment(String department) {
      if (isBlank(department)) this.department = "Unknown";
      else this.department = department.trim();
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
      return "Professor{" +
              "professorId='" + professorId + '\'' +
              ", fullName='" + fullName + '\'' +
              ", department='" + department + '\'' +
              ", active=" + active +
              '}';
  }
}
