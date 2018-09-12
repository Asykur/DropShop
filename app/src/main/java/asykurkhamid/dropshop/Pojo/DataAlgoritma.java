package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class DataAlgoritma implements Parcelable{
    private String productID;
    private int jmlOrder;
    private String color;
    private String size;
    private int sisaStock;

    public DataAlgoritma() {
    }

    protected DataAlgoritma(Parcel in) {
        productID = in.readString();
        jmlOrder = in.readInt();
        color = in.readString();
        size = in.readString();
        sisaStock = in.readInt();
    }

    public static final Creator<DataAlgoritma> CREATOR = new Creator<DataAlgoritma>() {
        @Override
        public DataAlgoritma createFromParcel(Parcel in) {
            return new DataAlgoritma(in);
        }

        @Override
        public DataAlgoritma[] newArray(int size) {
            return new DataAlgoritma[size];
        }
    };

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getJmlOrder() {
        return jmlOrder;
    }

    public void setJmlOrder(int jmlOrder) {
        this.jmlOrder = jmlOrder;
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

    public int getSisaStock() {
        return sisaStock;
    }

    public void setSisaStock(int sisaStock) {
        this.sisaStock = sisaStock;
    }

    @Override
    public String toString() {
        return "DataAlgoritma{" +
                "productID='" + productID + '\'' +
                ", jmlOrder=" + jmlOrder +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", sisaStock=" + sisaStock +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productID);
        dest.writeInt(jmlOrder);
        dest.writeString(color);
        dest.writeString(size);
        dest.writeInt(sisaStock);
    }
}
