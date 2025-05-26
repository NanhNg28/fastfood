package com.nanhng.FastFood.service.cart;

import com.nanhng.FastFood.dto.request.cart.AddCartReq;
import com.nanhng.FastFood.dto.request.cart.CartDetailReq;
import com.nanhng.FastFood.entity.cart.Cart;
import com.nanhng.FastFood.entity.cart.CartItem;
import com.nanhng.FastFood.entity.user.User;
import com.nanhng.FastFood.exception.LovelyException;
import com.nanhng.FastFood.service.repository.cart.CartRepository;
import com.nanhng.FastFood.service.repository.cartItem.CartItemRepository;
import com.nanhng.FastFood.service.BaseService;
import com.nanhng.FastFood.service.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends BaseService implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    @Override
    public Cart addCart(AddCartReq request) {
        User user = getUser();

        if(cartRepository.existsCartByUserId(user.getId())) {
            throw new LovelyException("cart already exists", HttpStatus.BAD_REQUEST);
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        return cartRepository.save(cart);
    }

    @Override
    public void addCart(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user == null || user.isDeleted()) {
            throw new LovelyException("user not exists", HttpStatus.BAD_REQUEST);
        }
        if(cartRepository.existsCartByUserId(user.getId())) {
            throw new LovelyException("cart already exists", HttpStatus.BAD_REQUEST);
        }
        Cart cart = new Cart();
        cart.setUserId(user.getId());
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(CartDetailReq request) {
        User user = getUser();

        int userId = request.getUserId();
        Cart cart = cartRepository.findById(userId).orElseThrow(()->new LovelyException("Cart not found", HttpStatus.BAD_REQUEST));
        cart.setCartItems(cartItemRepository.findAllByCartId(userId));
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));
        return cart;
    }

    @Override
    public Cart getCart(Integer userId) {
        User user = getUser();

        Cart cart = cartRepository.findById(userId).orElse(null);
        if(cart == null) {
            throw new LovelyException("Cart not found", HttpStatus.BAD_REQUEST);
        }
        cart.setCartItems(cartItemRepository.findAllByCartId(userId));
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));
        return cart;
    }

    @Override
    public Cart getCart() {
        User user = getUser();

        Cart cart = cartRepository.findByUserId(user.getId());
        if(cart == null) {
            throw new LovelyException("Cart not found", HttpStatus.BAD_REQUEST);
        }
        cart.setCartItems(cartItemRepository.findAllByCartId(user.getId()));
        cart.setTotalPrice(calculateTotalPrice(cart.getCartItems()));
        return cart;
    }

    private Double calculateTotalPrice(List<CartItem> items) {
        double res = 0.0;
        for(CartItem item : items) {
            res += item.getPrice()*item.getQuantity();
        }
        return res;
    }
}
