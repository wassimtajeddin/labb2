import java.util.Objects;

public class  Product {
 private final String category;
 private final int barcode;
 private final String name;
 private final double price;


 public Product(String category, int barcode, String name, double price) {
  this.category = category;
  this.barcode = barcode;
  this.name = name;
  this.price = price;
 }

 public String getCategory() {
  return category;
 }


 public int getBarcode() {
  return barcode;
 }


 public String getName() {
  return name;
 }


 public double getPrice() {
  return price;
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