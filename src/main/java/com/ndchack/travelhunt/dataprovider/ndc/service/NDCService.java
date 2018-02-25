package com.ndchack.travelhunt.dataprovider.ndc.service;

import com.ibsplc.wsdl.ndc.NdcResServicePort;
import com.ndchack.travelhunt.dataprovider.ndc.builder.CommonEntityBuilder;
import com.ndchack.travelhunt.dataprovider.ndc.connector.NDCAccessServer;

/**
 * Created by A-2174 on 2/24/18.
 */
public abstract class NDCService {

    protected static NdcResServicePort NDC_SERVICE_PORT = null;

    public NDCService() {

        if (NDC_SERVICE_PORT == null) {
            try {
                NDCAccessServer server = new NDCAccessServer(CommonEntityBuilder.getURL());
                NDC_SERVICE_PORT = server.getNdcResServiceSOAPPort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
