package com.nanhng.FastFood.service.cartItem;

import com.nanhng.FastFood.dto.request.cartItem.AddCartItemReq;
import com.nanhng.FastFood.dto.request.cartItem.UpdateCartItemReq;
import com.nanhng.FastFood.dto.request.ids.IdsRequest;
import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.entity.cart.CartItem;
import com.nanhng.FastFood.entity.product.Product;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.repository.cart.CartRepository;
import com.nanhng.FastFood.repository.cartItem.CartItemRepository;
import com.nanhng.FastFood.repository.product.ProductRepository;
import com.nanhng.FastFood.service.BaseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartItemServiceImpl extends BaseService implements CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Transactional
    @Override
    public CartItem addCartItem(AddCartItemReq request) {
        User user = getUser();

        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new LovelyException("Product not found", HttpStatus.BAD_REQUEST));

        Cart cart = cartRepository.findByUserId(user.getId());

        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUserId(user.getId());
            cart = cartRepository.save(newCart);
        }

        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndProductId(cart.getId(), request.getProductId());

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            return cartItemRepository.save(cartItem);
        }

        cartItem = new CartItem();
        cartItem.setCartId(cart.getId());
        cartItem.setProductId(request.getProductId());
        cartItem.setQuantity(request.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Transactional
    @Override
    public CartItem updateCartItem(UpdateCartItemReq request) {
        User user = getUser();

        log.info("Updating cart item");

        CartItem cartItem = cartItemRepository.findById(request.getCartItemId()).orElseThrow(() -> new LovelyException("product not found", HttpStatus.BAD_REQUEST));
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new LovelyException("Product not found", HttpStatus.BAD_REQUEST));
            cartItem.setProductId(product.getId());
        }
        if (cartItem.getQuantity() != null || cartItem.getQuantity() != 0) {
            cartItem.setQuantity(request.getQuantity());
        }
        return cartItemRepository.save(cartItem);
    }

    @Override
    public List<Integer> deleteCartItemByIds(IdsRequest request) {
        User user = getUser();

        List<Integer> ids = request.getIds();
        List<Integer> existIds = cartItemRepository.findAllById(ids).stream().map(CartItem::getId).toList();
        Integer notExistId = ids.stream().filter(id -> !existIds.contains(id)).findFirst().orElse(null);
        if (notExistId != null) {
            throw new LovelyException("cant found cart item", HttpStatus.BAD_REQUEST);
        }
        cartItemRepository.deleteAllByIdInBatch(request.getIds());
        return existIds;
    }
}
