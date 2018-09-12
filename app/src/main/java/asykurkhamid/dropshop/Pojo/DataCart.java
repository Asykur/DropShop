package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class DataCart implements Parcelable{

    private String address;
    private String color;
    private String kurir;
    private String notes;
    private String paketPengiriman;
    private String productID;
    private String productName;
    private int qtyOrder;
    private String size;
    private int stock;
    private int totalBayar;
    private String unitPrice;
    private String userID;

    public DataCart() {
    }


    protected DataCart(Parcel in) {
        address = in.readString();
        color = in.readString();
        kurir = in.readString();
        notes = in.readString();
        paketPengiriman = in.readString();
        productID = in.readString();
        productName = in.readString();
        qtyOrder = in.readInt();
        size = in.readString();
        stock = in.readInt();
        totalBayar = in.readInt();
        unitPrice = in.readString();
        userID = in.readString();
    }

    public static final Creator<DataCart> CREATOR = new Creator<DataCart>() {
        @Override
        public DataCart createFromParcel(Parcel in) {
            return new DataCart(in);
        }

        @Override
        public DataCart[] newArray(int size) {
            return new DataCart[size];
        }
    };

    @Override
    public String toString() {
        return "DataCart{" +
                "address='" + address + '\'' +
                ", color='" + color + '\'' +
                ", kurir='" + kurir + '\'' +
                ", notes='" + notes + '\'' +
                ", paketPengiriman='" + paketPengiriman + '\'' +
                ", productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", qtyOrder=" + qtyOrder +
                ", size=" + size +
                ", stock=" + stock +
                ", totalBayar='" + totalBayar + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaketPengiriman() {
        return paketPengiriman;
    }

    public void setPaketPengiriman(String paketPengiriman) {
        this.paketPengiriman = paketPengiriman;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQtyOrder() {
        return qtyOrder;
    }

    public void setQtyOrder(int qtyOrder) {
        this.qtyOrder = qtyOrder;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(int totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(color);
        parcel.writeString(kurir);
        parcel.writeString(notes);
        parcel.writeString(paketPengiriman);
        parcel.writeString(productID);
        parcel.writeString(productName);
        parcel.writeInt(qtyOrder);
        parcel.writeString(size);
        parcel.writeInt(stock);
        parcel.writeInt(totalBayar);
        parcel.writeString(unitPrice);
        parcel.writeString(userID);
    }
}
