package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class CatalogProduk implements Parcelable{
    String id;
    String name;
    String price;
    String def_image;
    String desc;
    String weight;
    String path1;
    String path2;
    String path3;

    public CatalogProduk(String id, String name, String price, String def_image, String desc, String weight, String path1, String path2, String path3) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.def_image = def_image;
        this.desc = desc;
        this.weight = weight;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
    }

    public CatalogProduk() {
    }


    protected CatalogProduk(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        def_image = in.readString();
        desc = in.readString();
        weight = in.readString();
        path1 = in.readString();
        path2 = in.readString();
        path3 = in.readString();
    }

    public static final Creator<CatalogProduk> CREATOR = new Creator<CatalogProduk>() {
        @Override
        public CatalogProduk createFromParcel(Parcel in) {
            return new CatalogProduk(in);
        }

        @Override
        public CatalogProduk[] newArray(int size) {
            return new CatalogProduk[size];
        }
    };



    @Override
    public String toString() {
        return "CatalogProduk{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", def_image='" + def_image + '\'' +
                ", desc='" + desc + '\'' +
                ", weight='" + weight + '\'' +
                ", path1='" + path1 + '\'' +
                ", path2='" + path2 + '\'' +
                ", path3='" + path3 + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDef_image() {
        return def_image;
    }

    public void setDef_image(String def_image) {
        this.def_image = def_image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPath1() {
        return path1;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getPath3() {
        return path3;
    }

    public void setPath3(String path3) {
        this.path3 = path3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(def_image);
        parcel.writeString(desc);
        parcel.writeString(weight);
        parcel.writeString(path1);
        parcel.writeString(path2);
        parcel.writeString(path3);
    }
}
