package pl.coderslab.controller;


import org.springframework.stereotype.Component;
import pl.coderslab.pojo.Product;

import java.util.List;

@Component
public class ProductDao {
    public List<Product> getList() {
        List<Product> productsList = List.of(
                new Product(1L, "buty", 29.99),
                new Product(2L, "zabawka", 15.99),
                new Product(3L, "książka", 99.99),
                new Product(4L, "rower", 1999.99)

        );
        return productsList;
    }
}
