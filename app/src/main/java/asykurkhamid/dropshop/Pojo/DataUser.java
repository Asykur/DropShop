package asykurkhamid.dropshop.Pojo;

public class DataUser {

    private String name;
    private String ShopName;
    private String Address;
    private String phone;
    private String email;
    private String key;

    public DataUser() {
    }

    public DataUser(String name, String shopName, String address, String phone, String email) {
        this.name = name;
        ShopName = shopName;
        Address = address;
        this.phone = phone;
        this.email = email;
//        this.key = key;
    }

    @Override
    public String toString() {
        return "DataUser{" +
                "name='" + name + '\'' +
                ", ShopName='" + ShopName + '\'' +
                ", Address='" + Address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
