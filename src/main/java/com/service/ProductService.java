package com.service;

import com.dbc.InF.ProductDBCInF;
import com.dbc.ProductDBC;
import com.model.Product;
import com.service.InF.ProductInF;

import java.io.File;
import java.util.List;

public class ProductService implements ProductInF {
    ProductDBCInF productDBC = new ProductDBC();

    @Override
    public void insert(Product product) {
        productDBC.insert(product);
    }

    @Override
    public void edit(Product product) {
        Product oldProduct = productDBC.get(product.getId());

        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setCategory(product.getCategory());
        oldProduct.setDes(product.getDes());

        if (product.getImage() != null) {
            // XOA ANH CU DI
            String fileName = oldProduct.getImage();
            final String dir = "/upload";
            File file = new File(dir + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }

            oldProduct.setImage(product.getImage());
        }

        productDBC.edit(oldProduct);
    }

    @Override
    public void delete(int id) {
        productDBC.delete(id);
    }

    @Override
    public Product get(int id) {
        return productDBC.get(id);
    }

    @Override
    public List<Product> getAll() {
        return productDBC.getAll();
    }

    @Override
    public List<Product> search(String username) {
        return productDBC.search(username);
    }

    @Override
    public List<Product> seachByCategory(int cate_id) {
        return productDBC.seachByCategory(cate_id);
    }

    @Override
    public List<Product> seachByName(String productName) {
        return productDBC.seachByName(productName);
    }
}
