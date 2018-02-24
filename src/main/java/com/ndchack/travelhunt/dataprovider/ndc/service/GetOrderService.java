package com.ndchack.travelhunt.dataprovider.ndc.service;

import com.ndchack.travelhunt.dataprovider.ndc.builder.CommonEntityBuilder;
import com.ndchack.travelhunt.dataprovider.ndc.builder.OrderViewBuilder;
import com.ndchack.travelhunt.dataprovider.ndc.connector.NDCAccessServer;
import com.ndchack.travelhunt.dataprovider.ndc.model.OrderView;
import org.iata.iata.edist.OrderIDType;
import org.iata.iata.edist.OrderRetrieveRQ;
import org.iata.iata.edist.OrderViewRS;

/**
 * Created by A-2174 on 2/24/18.
 */
public class GetOrderService {

    public static OrderView getOrder(String locatorCode) {

        OrderRetrieveRQ rq = createRequest(locatorCode);
        try {
            NDCAccessServer server = new NDCAccessServer(CommonEntityBuilder.getURL());
            OrderViewRS rs = server.getNdcResServiceSOAPPort().retrieveOrder(rq);

            return OrderViewBuilder.buildOrder(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new OrderView();
    }

    private static OrderRetrieveRQ createRequest(String locatorCode) {

        OrderRetrieveRQ rq = new OrderRetrieveRQ();
        rq.setPointOfSale(CommonEntityBuilder.getPosType());
        rq.setDocument(CommonEntityBuilder.getDocument());
        rq.setParty(CommonEntityBuilder.getParties());


        OrderRetrieveRQ.Query query = new OrderRetrieveRQ.Query();
        OrderRetrieveRQ.Query.Filters filters = new OrderRetrieveRQ.Query.Filters();
        query.setFilters(filters);
        OrderIDType orderIDType = new OrderIDType();
        filters.setOrderID(orderIDType);
        orderIDType.setValue(locatorCode);
        orderIDType.setOwner("XQ");
        rq.setQuery(query);

        return rq;
    }
}
