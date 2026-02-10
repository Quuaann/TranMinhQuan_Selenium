package DataObject;

public class Account {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    private String pid;
    
    public Account() {
    }
    
    public Account(String email, String password, String pid) {
        this.email = email;
        this.password = password;
        this.confirmPassword = password;
        this.pid = pid;
    }
    
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
        return this.username;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public String getPid() {
        return this.pid;
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
    
}