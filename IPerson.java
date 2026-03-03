public interface IPerson {
    String getId();
    String getUsername();
    String getRole();
    boolean isActive();
    boolean checkPassword(String input);
    String getFullName();
    
    boolean can(String action);
}
