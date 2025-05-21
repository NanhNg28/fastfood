//package com.nanhng.FastFood.dto.constant;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonValue;
//
//public enum ProductCategory implements BaseEnum<Integer>{
//    FOOD(0),
//    DRINK(1),
//    SNACK(2);
//
//    private final int value;
//
//    ProductCategory(int value) {
//        this.value = value;
//    }
//
//    @JsonCreator
//    public static ProductCategory fromValue(int value) {
//        for(ProductCategory productCategory : ProductCategory.values()) {
//            if(productCategory.getValue() == value){
//                return productCategory;
//            }
//        }
//        return null;
//    }
//
//    @JsonValue
//    @Override
//    public Integer getValue() {
//        return value;
//    }
//}
