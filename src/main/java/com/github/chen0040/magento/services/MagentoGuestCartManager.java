package com.github.chen0040.magento.services;


import com.alibaba.fastjson.JSON;
import com.github.chen0040.magento.MagentoClient;
import com.github.chen0040.magento.models.Cart;
import com.github.chen0040.magento.models.CartTotal;
import com.github.chen0040.magento.utils.StringUtils;


/**
 * Created by xschen on 10/7/2017.
 */
public class MagentoGuestCartManager extends MagentoHttpComponent {

   private static final String relativePath = "rest/V1/guest-carts";
   private final MagentoClient client;

   public MagentoGuestCartManager(MagentoClient client){
      super(client.getHttpComponent());
      this.client = client;
   }

   @Override public String token() {
      return client.token();
   }


   @Override public String baseUri() {
      return client.baseUri();
   }

   public String newCart() {
      String json = postSecure(baseUri() + "/" + relativePath, "");

      if(!validate(json)){
         return null;
      }

      return StringUtils.stripQuotation(json);
   }

   public Cart getCart(String cartId) {

      String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId);

      if(!validate(json)){
         return null;
      }

      System.out.println(json);

      Cart cart = JSON.parseObject(json, Cart.class);
      return cart;
   }

   public CartTotal getCartTotal(String cartId) {
      String json = getSecured(baseUri() + "/" + relativePath + "/" + cartId + "/totals");

      if(!validate(json)){
         return null;
      }

      System.out.println(json);

      CartTotal cartTotal = JSON.parseObject(json, CartTotal.class);
      return cartTotal;
   }

}
