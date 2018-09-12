package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Timestamp;

public class DataProduct implements Parcelable{
    private String pID;
    private String name;
    private String price;
    private String weight;
    private String size;
    private String color;
    private String stock;
    private String def_image;
    private String description;
    private String dateTime;

    public DataProduct(String pID, String name, String price, String weight, String size, String color, String stock, String def_image, String description, String dateTime) {
        this.pID = pID;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.stock = stock;
        this.def_image = def_image;
        this.description = description;
        this.dateTime = dateTime;
    }

    public DataProduct() {
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDef_image() {
        return def_image;
    }

    public void setDef_image(String def_image) {
        this.def_image = def_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    protected DataProduct(Parcel in) {
        pID = in.readString();
        name = in.readString();
        price = in.readString();
        weight = in.readString();
        size = in.readString();
        color = in.readString();
        stock = in.readString();
        def_image = in.readString();
        description = in.readString();
        dateTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pID);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(weight);
        dest.writeString(size);
        dest.writeString(color);
        dest.writeString(stock);
        dest.writeString(def_image);
        dest.writeString(description);
        dest.writeString(dateTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataProduct> CREATOR = new Creator<DataProduct>() {
        @Override
        public DataProduct createFromParcel(Parcel in) {
            return new DataProduct(in);
        }

        @Override
        public DataProduct[] newArray(int size) {
            return new DataProduct[size];
        }
    };
}
