package com.myCafe.core.service.impl;

import com.myCafe.core.converter.Converter;
import com.myCafe.core.dto.CafeProduct;
import com.myCafe.core.service.helper.ServiceHelper;
import com.myCafe.core.service.ProductService;
import com.myCafe.dal.entities.ProductEntity;
import com.myCafe.dal.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends ServiceHelper implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private Converter converter;


    @Override
    public List<CafeProduct> getAll() {
        return productRepository.findAll().stream().map(e -> converter.toModel(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CafeProduct createProduct(CafeProduct product) {
        ProductEntity cafeProduct = productRepository.saveAndFlush(converter.toEntity(product));
        return converter.toModel(cafeProduct);
    }

    @Override
    public CafeProduct getProductById(Integer productId) {
        Assert.notNull(productId, "Product id should not be null");
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(() -> {
            String message = String.format("No product found with id " +
                    "[%s]", productId);
            LOGGER.error(message);
            return new EntityNotFoundException(message);
        });
        return converter.toModel(productEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer productId) {
        Assert.notNull(productId, "Product id should not be null");
        productRepository.deleteById(productId);
    }
}
