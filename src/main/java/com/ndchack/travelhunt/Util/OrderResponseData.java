package com.ndchack.travelhunt.Util;

import com.ndchack.travelhunt.dataprovider.ndc.model.OrderView;

public final class OrderResponseData {

    public static OrderView getOrderDetails() {
        return orderDetails;
    }

    public static void setOrderDetails(OrderView orderDetails) {
        OrderResponseData.orderDetails = orderDetails;
    }

    public static OrderView orderDetails;

    private OrderResponseData() {

    }
}
