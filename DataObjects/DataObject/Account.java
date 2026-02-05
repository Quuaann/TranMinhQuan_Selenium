package DataObject;

public class Account {
    // Chỉ giữ các thuộc tính cơ bản cần thiết cho test
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String pid;
    
    // Constructor mặc định
    public Account() {
    }
    
    // Constructor với thông tin cơ bản
    public Account(String email, String password, String pid) {
        this.email = email;
        this.password = password;
        this.confirmPassword = password;
        this.pid = pid;
    }
    
    // Constructor đầy đủ
    public Account(String username, String email, String password, 
                      String confirmPassword, String pid) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.pid = pid;
    }
    
    // GETTERS
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }
    
    public String getPid() {
        return pid;
    }
    
    // SETTERS
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public void setPid(String pid) {
        this.pid = pid;
    }
    
//    // Utility methods - chỉ giữ những cái thực sự cần
//    public boolean isPasswordMatch() {
//        return password != null && password.equals(confirmPassword);
//    }
//    
//    public boolean isValidEmail() {
//        return email != null && email.contains("@") && email.contains(".");
//    }
//    
//    // toString method
//    @Override
//    public String toString() {
//        return String.format(
//            "UserAccount{username='%s', email='%s', pid='%s'}",
//            username, email, pid
//        );
//    }
//    
//    // equals và hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAccount that = (UserAccount) o;
//        return Objects.equals(email, that.email);
//    }
//    
//    @Override
//    public int hashCode() {
//        return Objects.hash(email);
//    }
}