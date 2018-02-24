package com.ndchack.travelhunt.dataprovider.ndc.connector;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.*;

/**
 * Created by A-2174 on 2/24/18.
 */
public class HttpHeaderHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {


        Boolean outboundProperty = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);


        if (outboundProperty.booleanValue()) {

            @SuppressWarnings("unchecked")
            Map<String, List<String>> requestHeaders = (Map<String, List<String>>) context.get(MessageContext.HTTP_REQUEST_HEADERS);
            if (requestHeaders == null) {
                requestHeaders = new HashMap<String, List<String>>();
                context.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
            }
            requestHeaders.put("username", Collections.singletonList("HKTHONUSR"));
            requestHeaders.put("password", Collections.singletonList("12345"));
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) { return true; }

    @Override
    public void close(MessageContext context) { }

    @Override
    public Set<QName> getHeaders() { return Collections.emptySet(); }
}
