// File: AdminPerson.java (similar to ManagerStaff)
public class Admin implements IPerson {
    
    private String adminId;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String role;
    private boolean active;

    @Override
    public boolean can(String action) {
        // Admin can do everything
        return true;
    }

    public Admin(String adminId, String fullName, String phone,
                      String username, String password, String role) {
        setAdminId(adminId);
        setFullName(fullName);
        setPhone(phone);
        setUsername(username);
        setPassword(password);
        setRole(role);
        this.active = true;
    }

    // Getters
    public String getId() { return adminId; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }

    @Override
    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    // Setters
    public void setAdminId(String adminId) {
        if (isBlank(adminId)) this.adminId = "UNKNOWN";
        else this.adminId = adminId.trim();
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
        if (isBlank(username)) this.username = "admin_" + this.adminId;
        else this.username = username.trim();
    }

    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        if (pw.length() < 4) this.password = "0000";
        else this.password = pw;
    }

    public void setRole(String role) {
        if (isBlank(role)) this.role = "Administrator";
        else this.role = role.trim();
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
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}