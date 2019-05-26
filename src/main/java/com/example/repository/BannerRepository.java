package com.example.repository;

import com.example.entity.Banner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BannerRepository extends CrudRepository<Banner, Integer> {

    @Override
    Collection<Banner> findAll();
}
