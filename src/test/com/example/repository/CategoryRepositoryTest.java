package com.example.repository;

import com.example.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void softDeleteTest() {
        Category category = createCategory("categoryName");
        assertTrue(categoryRepository.findById(category.getId()).isPresent());

        category.setDeleted(true);
        categoryRepository.save(category);
        entityManager.flush();
        entityManager.clear();

        assertFalse(categoryRepository.findById(category.getId()).isPresent());
    }

    @Test
    public void existsByNameTest() {
        String categoryName = "category unique name";
        assertFalse(categoryRepository.existsByNameOrRequestName(categoryName, "unique req_name"));
        Category category = createCategory(categoryName);
        assertTrue(categoryRepository.existsByNameOrRequestName(categoryName, "unique req_name"));
    }

    @Test
    public void existsByRequestNameTest() {
        String categoryName = "category unique name";
        Category category = createCategory(categoryName);

        assertFalse(categoryRepository.existsByNameOrRequestName("unique name", "unique req_name"));
        assertTrue(categoryRepository.existsByNameOrRequestName("unique name", category.getRequestName()));
    }

    private Category createCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        category.setRequestName("request " + categoryName);
        category.setDeleted(false);

        categoryRepository.save(category);

        assertNotNull(category);
        assertNotNull(category.getId());

        entityManager.flush();
        entityManager.clear();
        return category;
    }
}