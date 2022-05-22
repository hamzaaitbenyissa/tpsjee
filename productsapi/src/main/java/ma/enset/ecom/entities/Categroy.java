package ma.enset.ecom.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categroy{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;

    @OneToMany(mappedBy = "category")
    @JsonProperty
    private List<Product> products = new ArrayList<>();


}
