package com.hungle.mbf.learnspring.controller;

import com.hungle.mbf.learnspring.entities.Products;
import com.hungle.mbf.learnspring.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/public/products")
//@CrossOrigin(origins = "**")
//localhost:1103/api/public/products
public class ProductController {

    @Autowired
    private ProductService productService;

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("")
    public List<Products> findAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{pid}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer pid) {
        try {
            logger.info("INFO: "+ pid);
            Products products = productService.findProductById(pid);
            return new ResponseEntity<Products>(products, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Products>(HttpStatus.valueOf(404));
        }
    }

    @PostMapping("")
    public void addProduct(@RequestBody Products product) {
        productService.saveProduct(product);
    }

    @PutMapping("/{pid}")
    public ResponseEntity<Products> updateProduct(@RequestBody Products products, @PathVariable Integer pid) {
        try {
            //Đánh dấu
            logger.info("BODY: "+ products.toString());
            Products existingProduct = productService.findProductById(pid);
            productService.saveProduct(products);
            return new ResponseEntity<Products>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Products>(HttpStatus.valueOf(404));
        }
    }

    @DeleteMapping("/{pid}")
    public ResponseEntity<Products> deleteExistingProduct(@PathVariable Integer pid) {
        try {
            logger.info("PARAM:"+pid);
            Products existingProduct = productService.findProductById(pid);
            productService.deleteProduct(pid);
            return new ResponseEntity<Products>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Products>(HttpStatus.valueOf(404));
        }
    }

}
