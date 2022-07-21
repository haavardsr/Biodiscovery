package models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String tlf;
    private boolean is_superuser;
    private String salt;

    public String getVerificationKey() {
        return verificationKey;
    }

    public void setVerificationKey(String verificationKey) {
        this.verificationKey = verificationKey;
    }

    private String verificationKey;

    public boolean isIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getId() {return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public boolean is_superuser() {
        return is_superuser;
    }

    public void set_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    @Override
    public String toString() {
        return
                "id:" + id +
                        ", firstName:" + firstName +
                        ", lastName:" + lastName +
                        ", email:" + email +
                        ", password:" + password +
                        ", tlf:" + tlf +
                        ", is_superuser:" + is_superuser
                ;
    }
}
