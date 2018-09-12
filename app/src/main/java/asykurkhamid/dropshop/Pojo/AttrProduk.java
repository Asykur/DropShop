package asykurkhamid.dropshop.Pojo;

public class AttrProduk {
    private String color;
    private String size;
    private String stock;

    public AttrProduk(String color, String size, String stock) {
        this.color = color;
        this.size = size;
        this.stock = stock;
    }

    public AttrProduk() {

    }

    @Override
    public String toString() {
        return "AttrProduk{" +
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
