package atula.product_service.controller;

import atula.product_service.dto.ProducrRequest;
import atula.product_service.dto.ProductRespone;
import atula.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductCotnroller {
    @Autowired
    private ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProducrRequest producrRequest){
        productService.createProduct(producrRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductRespone> getAllProduct(){
        return productService.getAllProduct();
    }

}
