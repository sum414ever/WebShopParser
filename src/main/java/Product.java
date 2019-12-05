public class Product {

  private String productName;
  private String brand;
  private String color;
  private String price;
  private String articleID;

  public Product(String productName, String brand, String color, String price, String articleID) {
    this.productName = productName;
    this.brand = brand;
    this.color = color;
    this.price = price;
    this.articleID = articleID;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getArticleID() {
    return articleID;
  }

  public void setArticleID(String articleID) {
    this.articleID = articleID;
  }

  @Override
  public String toString() {
    return "Item{" +
        "productName='" + productName + '\'' +
        ", brand='" + brand + '\'' +
        ", color=" + color + '\'' +
        ", price='" + price + '\'' +
        ", articleID='" + articleID + '\'' +
        '}';
  }
}
