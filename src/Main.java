import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        reader();

        while (true) {
            System.out.println();
            menuChoices();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> writer();
                case 2 -> search();
                case 3 -> sortMenu();
                case 4 -> {
                    System.out.println("Ha det gött!");
                    return;
                }
                default -> System.out.println("Var god välj ett alternativ!");
            }

        }
    }

    public static void reader() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Product List.csv"))) {
            bufferedReader.readLine();
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] detailed = currentLine.split(",");
                String category = detailed[0];
                int barcode = Integer.parseInt(detailed[1]);
                String name = detailed[2];
                double price = Double.parseDouble(detailed[3]);
                products.add(new Product(category, barcode, name, price));
            }
        }
    }

    public static void writer() throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter("src/Product List.csv", true))) {
            System.out.println("Lägg till en ny kategori:");
            String category = scanner.next();
            System.out.println("Lägg till en streckkod:");
            int barcode = scanner.nextInt();
            System.out.println("Lägg till varans namn:");
            String name = scanner.next();
            System.out.println("Lägg priset:");
            double price = scanner.nextDouble();

            products.add(new Product(category, barcode, name, price));
            writer.writeNext(new String[]{category, String.valueOf(barcode), name, String.valueOf(price)});
        }
    }

    private static void sortByCategory() {
        products.stream()
                .sorted(Comparator.comparing(Product::getCategory))
                .forEach(System.out::println);
    }

    private static void sortByBarcode() {
        products.stream()
                .sorted(Comparator.comparing(Product::getBarcode))
                .forEach(System.out::println);
    }

    private static void sortByName() {
        products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .forEach(System.out::println);
    }

    private static void sortByPrice() {
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .forEach(System.out::println);
    }

    private static void menuChoices() {
        System.out.println("""
                Products
                ======
                1. Lägg till en ny vara
                2. Sök
                3. Sortera
                4. Avsluta""");
    }

    private static void sortMenu() {
        System.out.println("""
                Sortera efter:
                ======
                1. Kategori
                2. Streckkod
                3. Namn
                4. Pris
                5. Avsluta""");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> sortByCategory();
            case 2 -> sortByBarcode();
            case 3 -> sortByName();
            case 4 -> sortByPrice();
            case 5 -> System.out.println("Ha det gött!");
            default -> System.out.println("Var god välj ett alternativ!");
        }
    }

    private static void search() {
        System.out.println("Skriv nedan kategori:");
        String scannerCategory = scanner.next();
        products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(scannerCategory))
                .forEach(System.out::println);
    }
}
