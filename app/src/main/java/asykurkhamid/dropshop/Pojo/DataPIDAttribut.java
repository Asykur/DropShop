package asykurkhamid.dropshop.Pojo;

public class DataPIDAttribut {
    private String productID;

    public DataPIDAttribut() {
    }

    public DataPIDAttribut(String productID) {
        this.productID = productID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "DataPIDAttribut{" +
                "productID='" + productID + '\'' +
                '}';
    }
}
