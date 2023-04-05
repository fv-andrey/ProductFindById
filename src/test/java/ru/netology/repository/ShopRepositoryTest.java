package ru.netology.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.exception.AlreadyExistsException;
import ru.netology.exception.NotFoundException;
import ru.netology.product.Product;

public class ShopRepositoryTest {

    Product prod1 = new Product(11, "хлеб", 50);
    Product prod2 = new Product(17, "молоко", 80);
    Product prod3 = new Product(25, "халва", 140);
    Product prod4 = new Product(11, "сыр", 250);


    @Test
    public void shouldRemoveById() {
        ShopRepository repo = new ShopRepository();

        repo.add(prod1);
        repo.add(prod2);
        repo.add(prod3);
        repo.remove(17);

        Product[] expected = {prod1, prod3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateNotFoundExceptionByNonExistentId() {
        ShopRepository repo = new ShopRepository();

        repo.add(prod1);
        repo.add(prod2);
        repo.add(prod3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(15);
        });
    }

    @Test
    public void shouldAddProductsInRepository() {
        ShopRepository repo = new ShopRepository();

        repo.add(prod1);
        repo.add(prod2);
        repo.add(prod3);

        Product[] expected = {prod1, prod2, prod3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateAlreadyExistsExceptionByExistId() {
        ShopRepository repo = new ShopRepository();

        repo.add(prod1);
        repo.add(prod2);
        repo.add(prod3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(prod4);
        });
    }
}
