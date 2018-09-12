package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class DataTransaction implements Parcelable{
    private String image;
    private String name;
    private String unitPrice;
    private int subTotal;
    private int stock;
    private String color;
    private String size;
    private String productID;
    private String userID;
    private String time;
    private int qty;

    public DataTransaction() {
    }

    protected DataTransaction(Parcel in) {
        image = in.readString();
        name = in.readString();
        unitPrice = in.readString();
        subTotal = in.readInt();
        stock = in.readInt();
        color = in.readString();
        size = in.readString();
        productID = in.readString();
        userID = in.readString();
        time = in.readString();
        qty = in.readInt();
    }

    public static final Creator<DataTransaction> CREATOR = new Creator<DataTransaction>() {
        @Override
        public DataTransaction createFromParcel(Parcel in) {
            return new DataTransaction(in);
        }

        @Override
        public DataTransaction[] newArray(int size) {
            return new DataTransaction[size];
        }
    };

    @Override
    public String toString() {
        return "DataTransaction{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", subTotal=" + subTotal +
                ", stock=" + stock +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", productID='" + productID + '\'' +
                ", userID='" + userID + '\'' +
                ", time='" + time + '\'' +
                ", qty=" + qty +
                '}';
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(unitPrice);
        dest.writeInt(subTotal);
        dest.writeInt(stock);
        dest.writeString(color);
        dest.writeString(size);
        dest.writeString(productID);
        dest.writeString(userID);
        dest.writeString(time);
        dest.writeInt(qty);
    }
}
