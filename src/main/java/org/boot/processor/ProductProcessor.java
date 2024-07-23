package org.boot.processor;

import org.boot.model.Product;
import org.springframework.batch.item.ItemProcessor;

public class ProductProcessor implements ItemProcessor<Product, Product> {
    @Override
    public Product process(Product product) throws Exception {
        product.setProductId(null);
        return product;
    }
}
