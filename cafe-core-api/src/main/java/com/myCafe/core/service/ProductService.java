package com.myCafe.core.service;

import com.myCafe.core.dto.CafeProduct;
import com.myCafe.core.dto.ProductInOrder;

import java.util.Collection;
import java.util.List;

public interface ProductService {


    /**
     * Get all products.
     *
     * @return
     */
    public List<CafeProduct> getAll();

    /**
     * Create product.
     * @param product
     * @return
     */
    CafeProduct createProduct(CafeProduct product);

    /**
     * Get product by provided id.
     *
     * @param productId
     * @return Product
     */
    CafeProduct getProductById(final Integer productId);


    void deleteProduct(Integer productId);

}
