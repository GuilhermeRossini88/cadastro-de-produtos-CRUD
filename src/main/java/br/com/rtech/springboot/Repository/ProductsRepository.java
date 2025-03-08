package br.com.rtech.springboot.Repository;

import br.com.rtech.springboot.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<ProductModel, UUID> {
}
