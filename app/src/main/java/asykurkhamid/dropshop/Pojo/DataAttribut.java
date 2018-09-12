package asykurkhamid.dropshop.Pojo;

public class DataAttribut {
    private String color;
    private String size;
    private String stock;

    public DataAttribut() {
    }

    @Override
    public String toString() {
        return "DataAttribut{" +
                "color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", stock='" + stock + '\'' +
                '}';
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
