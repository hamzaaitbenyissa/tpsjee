package ma.enset.ecom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    private String id;
    private String name;
    private Double price;
    private Double quantity;
    @ManyToOne()
    Categroy categroy;

}
