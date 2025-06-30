package com.nanhng.FastFood.other_service.schedule;

import com.nanhng.FastFood.entity.product.Product;
import com.nanhng.FastFood.repository.product.ProductRepository;
import com.nanhng.FastFood.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductDiscountSchedule {
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void cleanupExpiredDiscounts() {
        List<Product> expiredProducts = productRepository.findByDiscountExpiryDateBefore((LocalDate.now()));
        for (Product product : expiredProducts) {
            product.setDiscountPercentage(null);
            product.setDiscountExpiryDate(null);
            productRepository.save(product);
        }
    }
}
