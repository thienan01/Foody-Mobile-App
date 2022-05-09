package hcmute.edu.vn.foodyapp_04.models;

public class CartLine {
    private int id;
    private String image;
    private String name;
    private String price;
    private String quantity;
    private  int userId;

    public CartLine() {
    }

    public CartLine(int id,String image, String name, String price, String quantity, int userId) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
    }

    public CartLine(String image, String name, String price, String quantity, int userId) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
