package atula.product_service.service;

import atula.product_service.dto.ProducrRequest;
import atula.product_service.dto.ProductRespone;
import atula.product_service.model.Product;
import atula.product_service.respository.ProductRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRespository productRespository;
    public void createProduct(ProducrRequest producrRequest){
        Product product = Product.builder()
                .name(producrRequest.getName())
                .description(producrRequest.getDescription())
                .price(producrRequest.getPrice())
                .build();


        productRespository.save(product);
        log.info("Product saved");
    }

    public List<ProductRespone> getAllProduct(){
        List<Product> products = productRespository.findAll();
        return products.stream().map(this::mapToProduct).toList();
    }

    private ProductRespone mapToProduct(Product product) {

    return ProductRespone.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .build();

    }
}
