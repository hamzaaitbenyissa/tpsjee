package ma.enset.ecom.repositories;


import ma.enset.ecom.entities.Categroy;
import ma.enset.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Categroy,Long> {
}
