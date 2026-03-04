public class Professor extends Staff{
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
                        String username, String password,
                        String department) {
      super(professorId, fullName, phone, username, password, "Professor");
      setDepartment(department);
  }

  public String getDepartment() { return department; }

  public void setDepartment(String department) {
      if (department == null || department.trim().isEmpty())
        this.department = "Unknown";
      else
        this.department = department.trim();
  }
}
