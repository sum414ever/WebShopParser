import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {

  public static int requestCounter;
  public static int productCounter;
  private final String DOMAIN = "https://www.aboutyou.de";
  private List<String> listOfLinks = new ArrayList<>();
  private List<Product> listOfProducts = new ArrayList<>();


  public List<String> getLinks() throws IOException {

    Document document = Jsoup.connect("https://www.aboutyou.de/maenner/bekleidung").get();
    Elements elements = document.getElementsByAttributeValue("data-test-id", "ProductTile");
    requestCounter++;

    for (Element e : elements) {

      if (e.attr("data-test-id", "ColorContainer").getElementsByTag("li").size() > 1) {

        Document doc = Jsoup.connect(DOMAIN + e.attr("href")).get();
        Elements colorElements = doc.getElementsByAttributeValue("data-test-id", "ThumbnailsList");

        List<String> color = new ArrayList<>();
        for (Element element : colorElements) {

          color = element.getElementsByAttributeValue("class", "oib7dg-0 jCKpPJ")
              .eachAttr("href");

          for (String url : color) {
            listOfLinks.add(DOMAIN + url);
          }
        }
      } else {
        listOfLinks.add(DOMAIN + e.attr("href"));
      }

    }
    return listOfLinks;
  }

  public List<Product> parseItem() throws IOException {

    for (String link : listOfLinks) {

      Document document;
      document = Jsoup.connect(link).get();
      requestCounter++;
      Element element = document.body();

      String productName = element.getElementsByAttributeValue("class", "iay39c-1 faQObc").text();
      String brand = element.getElementsByAttributeValue("class", "iay39c-0 gyEZgA").attr("alt");
      String color = element.getElementsByAttributeValue("class", "sc-12rq7nw-3 ivQbqZ").text();
      String price = element.getElementsByAttributeValue("class", "sc-137x7zs-0 x3voc9-0 clDOsP")
          .text();
      if (price.isEmpty()) {
        price = element.getElementsByAttributeValue("class", "sc-137x7zs-0 x3voc9-0 iunCxA")
            .text();
      }
      String article = element.getElementsByAttributeValue("class", "d5kk8t-5 gHXVER").first()
          .text();

      listOfProducts.add(new Product(productName, brand, color, price, article));
      productCounter++;
    }
    return listOfProducts;
  }
}


