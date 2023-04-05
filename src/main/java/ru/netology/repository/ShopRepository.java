package ru.netology.repository;

import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;
import ru.netology.product.Product;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        findByIdForAdd(product);
        if (findByIdForAdd(product) == null) {
            throw new AlreadyExistsException(
                    "Element with id: " + product.getId() + " exists"
            );
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public void remove(int id) {
        findById(id);
        if (findById(id) == null) {
            throw new NotFoundException(
                    "Element with id: " + id + " not found"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product findByIdForAdd(Product product) {
        for (Product productAll : products) {
            if (productAll.getId() == product.getId()) {
                return null;
            }
        }
        return product;
    }
}
