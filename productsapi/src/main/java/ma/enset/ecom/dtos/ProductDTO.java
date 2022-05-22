package ma.enset.ecom.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {
    private String id;
    private String name;
    private Double price;
    private Double quantity;
    private CategroyDTO categroyDTO;

}
