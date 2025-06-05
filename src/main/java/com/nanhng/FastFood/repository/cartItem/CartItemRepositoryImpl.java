package com.nanhng.FastFood.repository.cartItem;

import com.nanhng.FastFood.entity.cart.CartItem;
import com.nanhng.FastFood.entity.cart.QCartItem;
import com.nanhng.FastFood.entity.product.Product;
import com.nanhng.FastFood.entity.product.QProduct;
import com.nanhng.FastFood.entity.upload_file.QUploadFile;
import com.nanhng.FastFood.repository.BaseRepository;
import com.querydsl.core.types.Projections;

import java.util.List;

public class CartItemRepositoryImpl extends BaseRepository implements CartItemRepositoryCustom {
    @Override
    public List<CartItem> getAllByCartId(Integer cartId) {
        QCartItem qCartItem = QCartItem.cartItem;
        QProduct qProduct = QProduct.product;
        QUploadFile qUploadFile = QUploadFile.uploadFile;

        return query().from(qCartItem)
                .innerJoin(qProduct).on(qProduct.id.eq(qCartItem.productId))
                .innerJoin(qUploadFile).on(qProduct.imageId.eq(qUploadFile.id))
                .select(Projections.fields(CartItem.class, qCartItem.id, qProduct.id,
                        qCartItem.cartId, qCartItem.price, qCartItem.quantity,
                        qCartItem.productId, Projections.fields(Product.class, qProduct.id,
                                qProduct.name, qProduct.shortDescription, qProduct.imageId, qProduct.createdAt,
                                qProduct.quantity, qProduct.price, qProduct.categoryId, qProduct.status,
                                qProduct.updatedAt, qProduct.deleted,
                                qUploadFile.as("image")).as("product")))
                .fetch();
    }
}
