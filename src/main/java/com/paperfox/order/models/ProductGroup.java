package com.paperfox.order.models;

import com.paperfox.order.models.types.ProductGroupType;
import com.paperfox.order.models.types.ProductType;

public class ProductGroup {
    ProductGroupType productGroupType;
    ProductType productType;

    public ProductGroup(ProductGroupType productGroupType, ProductType productType) {
        this.productGroupType = productGroupType;
        this.productType = productType;
    }

    public ProductGroupType getProductGroupType() {
        return productGroupType;
    }

    public void setProductGroupType(ProductGroupType productGroupType) {
        this.productGroupType = productGroupType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "productGroup=" + productGroupType +
                ", productType=" + productType +
                '}';
    }
}
