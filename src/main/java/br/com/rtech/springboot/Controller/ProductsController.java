package br.com.rtech.springboot.Controller;

import br.com.rtech.springboot.Repository.ProductsRepository;
import br.com.rtech.springboot.dto.ProductsDTO;
import br.com.rtech.springboot.model.ProductModel;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductsController {

    @Autowired
    ProductsRepository productsRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductsDTO productsDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productsDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productsRepository.findAll());
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> product0 = productsRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());

    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductsDTO productsDTO){
        Optional<ProductModel> product0 = productsRepository.findById(id);
        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produtct not Found.");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productsDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product0 = productsRepository.findById(id);
        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productsRepository.delete(product0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }



}
