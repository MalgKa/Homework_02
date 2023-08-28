package pl.coderslab.pojo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor

public class CartItem {
    private Product product;
    private Integer quantity;

}
