package com.nanhng.FastFood.repository.product;

import com.nanhng.FastFood.entity.product.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>, ProductRepositoryCustom {

    boolean existsByName(@NotNull String name);

    Double getPriceById(int id);
}
