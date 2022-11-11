import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Product> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println();
            menuChoices();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> writer();
                case 2 -> search();
                case 3 -> sortMenu();
                case 4 -> System.out.println("Ha det gött!");
                default -> System.out.println("Var god välj ett alternativ!");
            }

        }
    }


    public static void reader() throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Product List.csv"));
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


    public static void writer() throws IOException {


        CSVWriter writer = new CSVWriter(new FileWriter(("src/Product List.csv"), true), ',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);

        System.out.println("lägg till ett nytt kategori!");
        String category = scanner.next();
        System.out.println("lägg till en streckkod!");
        int barcode = scanner.nextInt();
        System.out.println("lägg till varansnamn!");
        String name = scanner.next();
        System.out.println("lägg priset!");
        int price = scanner.nextInt();
        products.add(new Product(category, barcode, name, price));
        writer.writeNext(new String[]{category, String.valueOf(barcode), name, String.valueOf(price)});

        writer.flush();
        writer.close();


    }

    private static void sortByCategory() throws IOException {
        reader();
        products.stream()
                .sorted(Comparator.comparing(Product::getCategory))
                .toList()
                .forEach(System.out::println);
    }

    private static void sortByBarcode() throws IOException {
        reader();
        products.stream()
                .sorted(Comparator.comparing(Product::getBarcode))
                .toList()
                .forEach(System.out::println);
    }

    private static void sortByName() throws IOException {
        reader();
        products.stream()
                .sorted(Comparator.comparing(Product::getName))
                .toList()
                .forEach(System.out::println);
    }

    private static void sortByPrice() throws IOException {
        reader();
        products.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .toList()
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


    private static void sortMenu() throws IOException {
        System.out.println("""
                Sortera efter:
                ======
                1. Kategori
                2. Streckkod
                3. Namn
                4. Pris
                5.Avsluta""");
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

    private static void search() throws IOException {
        System.out.println("Skriv nedan kategoriet:");
        String scannerCategory = scanner.next();
        String line;
        FileInputStream inPut = new FileInputStream("src/Product List.csv");
        Scanner scanner = new Scanner(inPut);
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.startsWith(scannerCategory))
                System.out.println(line);
        }
    }
}



