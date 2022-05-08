package hcmute.edu.vn.foodyapp_04.models;

public class User {
    private  int id;
    private  String image;
    private  String Name;
    private  String phoneNumber;
    private  String password;
    private String type;
    private String address;

    public User() {
    }

    public User(int id, String image, String name, String phoneNumber, String password, String type, String address) {
        this.id = id;
        this.image = image;
        Name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.type = type;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
