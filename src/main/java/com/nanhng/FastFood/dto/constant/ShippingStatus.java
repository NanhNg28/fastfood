//package com.nanhng.FastFood.dto.constant;
//
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonValue;
//import io.swagger.v3.oas.annotations.media.Schema;
//
//@Schema(type = "integer")
//public enum ShippingStatus implements BaseEnum<Integer> {
//    NotShipped(0),
//    Shipped(1),
//    Delivered(2),
//    Returned(3);
//
//    private final int value;
//
//    ShippingStatus(int value) {
//        this.value = value;
//    }
//
//    @JsonCreator
//    public static ShippingStatus forValue(int value) {
//        for(ShippingStatus item : ShippingStatus.values()) {
//            if (item.toValue() == value) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    @JsonValue
//    public Integer toValue() {
//        return value;
//    }
//}
