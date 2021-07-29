package productservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductFeignClient productFeignClient;

    @RequestMapping("/product/{productNumber}")
    public Product getProduct(@PathVariable String productNumber){
        Product product = productFeignClient.getProduct(productNumber);
        return product;
    }

    @FeignClient(name ="stock-service")
    interface ProductFeignClient{
        @RequestMapping("/stock/{productNumber}")
        public Product getProduct(@PathVariable String productNumber);
    }
}
