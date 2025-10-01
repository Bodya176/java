package com.example.productcatalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map products = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();
    public ProductController() {
// ПBGаF>BвV 4аAV 4?я 45@BAEFDаFVW
        products.put(counter.incrementAndGet(),
                new Product(1L, "Laptop", 1200.50));
        products.put(counter.incrementAndGet(),
                new Product(2L, "Smartphone", 800.00));
    }

    // GET /products - >B@8маB8 вAі ?@>дCкB8
    @GetMapping
    public List getAllProducts() {
        return new ArrayList<>(products.values());
    }
    // GET /products/{id} - >B@8маB8 ?@>дCкB 7а ID
    @GetMapping("/{id}")
    public ResponseEntity getProductById(@PathVariable long id) {
        Product product = (Product) products.get(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /products - ABв>@<B< =>в<= ?@>дC>B
    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        long newId = counter.incrementAndGet();
        product.setId(newId);
        products.put(newId, product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    // PUT /products/{id} - >=>в<B< іA=Cюч<= ?@>дC>B
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(
            @PathVariable long id,
            @RequestBody Product updatedProduct) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedProduct.setId(id);
        products.put(id, updatedProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    // DELETE /products/{id} - вида?иFи ?@>дG>F
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        if (products.remove(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}