// File: AdminPerson.java (similar to ManagerStaff)
public class Admin extends Staff {

    @Override
    public boolean can(String action) {
        // Admin can do everything
        return true;
    }

    public Admin(String adminId, String fullName, String phone,
                      String username, String password) {
        super(adminId, fullName, phone, username, password, "Admin");
    }
}