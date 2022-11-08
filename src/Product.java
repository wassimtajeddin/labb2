import java.util.Objects;

public class  Product {
 private String category;
 private int barcode;
 private String name;
 private double price;


 public Product(String category, int barcode, String name, double price) {
  this.category = category;
  this.barcode = barcode;
  this.name = name;
  this.price = price;
 }

 public String getCategory() {
  return category;
 }

 public void setCategory(String category) {
  this.category = category;
 }

 public int getBarcode() {
  return barcode;
 }

 public void setBarcode(int barcode) {
  this.barcode = barcode;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public double getPrice() {
  return price;
 }

 public void setPrice(int price) {
  this.price = price;
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (!(o instanceof Product product)) return false;

  if (barcode != product.barcode) return false;
  if (Double.compare(product.price, price) != 0) return false;
  if (!Objects.equals(category, product.category)) return false;
  return Objects.equals(name, product.name);
 }

 @Override
 public int hashCode() {
  int result;
  long temp;
  result = category != null ? category.hashCode() : 0;
  result = 31 * result + barcode;
  result = 31 * result + (name != null ? name.hashCode() : 0);
  temp = Double.doubleToLongBits(price);
  result = 31 * result + (int) (temp ^ (temp >>> 32));
  return result;
 }

 @Override
 public String toString() {
  return "Product{" +
          "category='" + category + '\'' +
          ", barcode=" + barcode +
          ", name='" + name + '\'' +
          ", price=" + price +
          '}';
 }
}