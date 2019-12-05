import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppRunner {

  public void run() throws IOException, InterruptedException {

    Parser parser = new Parser();
    parser.getLinks();
    List<Product> products = parser.parseItem();

    FileWriter writer = new FileWriter("Products");

    CSVUtils.writeLine(writer, Arrays.asList("Name", "Brand", "Color", "Price", "Article"));

    for (Product d : products) {

      List<String> list = new ArrayList<>();
      list.add(d.getProductName());
      list.add(d.getBrand());
      list.add(d.getColor());
      list.add(d.getPrice());
      list.add(d.getArticleID());

      CSVUtils.writeLine(writer, list);
    }

    writer.flush();
    writer.close();

    System.out.println("Amount of triggered HTTP requests is " + Parser.requestCounter);
    System.out.println("Amount of extracted products is " + Parser.productCounter);

  }

}
