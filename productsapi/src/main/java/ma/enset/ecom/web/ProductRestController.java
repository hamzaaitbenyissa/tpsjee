package ma.enset.ecom.web;

import ma.enset.ecom.entities.Product;
import ma.enset.ecom.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class ProductRestController {

    @Autowired
    private ProductRepository productRepository;

    //urls must be
    @GetMapping(path ="products")
    public List<Product> productList(){
        return  productRepository.findAll();
    }

    //path param
    @GetMapping(path ="products/{id}")
    public Product getProduct(@PathVariable (name ="id") String id){

        return  productRepository.findById(id).get();
    }

//    PUT is a method of modifying resource where the client sends data that updates the entire resource . PATCH is a method of modifying resources where the client sends partial data that is to be updated without modifying the entire data./**/
@PostMapping (path = "/products")
    public  Product saveProduct(@RequestBody Product product){
        return  productRepository.save(product);
}

    //    PUT is a method of modifying resource where the client sends data that updates the entire resource . PATCH is a method of modifying resources where the client sends partial data that is to be updated without modifying the entire data./**/
    @PutMapping(path = "/products/{id}")
    public  Product updateProduct(@RequestBody Product product,@PathVariable String id){
        product.setId(id);
        return  productRepository.save(product);
    }

    @DeleteMapping(path = "/products/{id}")
    public void deleteProduct(@RequestBody Product product,@PathVariable String id){
        productRepository.deleteById(id);

    }


}
