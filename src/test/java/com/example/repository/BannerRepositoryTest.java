package com.example.repository;

import com.example.entity.Banner;
import com.example.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BannerRepositoryTest {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void changeCategoryTest() {
        Banner banner = createBanner("banner change category");

        entityManager.flush();
        entityManager.clear();

        Category oldCategory = banner.getCategory();
        Category newCategory = createCategory();

        changeCategory(banner.getId(), newCategory);

        Banner updatedBanner = bannerRepository.findById(banner.getId()).orElseThrow(NullPointerException::new);
        assertEquals(newCategory.getId(), updatedBanner.getCategory().getId());
        assertNotEquals(oldCategory.getId(), updatedBanner.getCategory().getId());
    }

    @Test
    public void softDeleteTest() {
        Banner banner = createBanner("bannerTest");

        entityManager.flush();
        entityManager.clear();

        assertTrue(bannerRepository.findById(banner.getId()).isPresent());

        banner.setDeleted(true);
        bannerRepository.save(banner);
        entityManager.flush();
        entityManager.clear();

        assertFalse(bannerRepository.findById(banner.getId()).isPresent());
    }

    @Test
    public void existByNameTest() {
        String bannerName = "banner unique name";
        assertFalse(bannerRepository.existsByName(bannerName));
        Banner banner = createBanner(bannerName);

        entityManager.flush();
        entityManager.clear();

        assertTrue(bannerRepository.existsByName(bannerName));
    }

    private Banner createBanner(String bannerName) {
        return createBanner(bannerName, createCategory());
    }

    private Banner createBanner(String bannerName, Category category) {
        Banner banner = new Banner();
        banner.setName(bannerName);
        banner.setPrice(new BigDecimal("123.45"));
        banner.setCategory(category);
        banner.setContent(bannerName + "content");
        banner.setDeleted(false);

        bannerRepository.save(banner);

        assertNotNull(banner);
        assertNotNull(banner.getId());

        return banner;
    }

    private Category createCategory() {
        Category category = new Category();
        category.setName("testName");
        category.setRequestName("reqName test");
        category.setDeleted(false);

        categoryRepository.save(category);

        assertNotNull(category);
        assertNotNull(category.getId());

        return category;
    }

    private void changeCategory(Integer bannerId, Category category) {
        Banner persistedBanner = bannerRepository.findById(bannerId).orElseThrow(NullPointerException::new);
        persistedBanner.setCategory(category);

        entityManager.flush();
        entityManager.clear();
    }
}