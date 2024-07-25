package org.boot.processor;

import lombok.extern.log4j.Log4j2;
import org.boot.model.Product;
import org.springframework.batch.item.ItemProcessor;

@Log4j2
public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product product) throws Exception {

        product.setProductId(null);
        log.info(product);
        return product;
    }
}
