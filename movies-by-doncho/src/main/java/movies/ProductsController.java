package movies;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ProductsController {

    @RequestMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>(2);
        products.add(new Product("Milk"));
        products.add(new Product("Eggs"));
        products.add(new Product("Beer"));

        return products;
    }

    @RequestMapping()
    public Product getById(@RequestParam(value = "id") int id) {
        return new Product("");
    }
}
