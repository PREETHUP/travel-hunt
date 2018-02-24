package com.ndchack.travelhunt.dataprovider.ndc.service;

import com.ibsplc.wsdl.ndc.NdcResServicePort;
import com.ndchack.travelhunt.dataprovider.ndc.builder.CommonEntityBuilder;
import com.ndchack.travelhunt.dataprovider.ndc.connector.NDCAccessServer;

/**
 * Created by A-2174 on 2/24/18.
 */
public abstract class NDCService {

    protected NdcResServicePort ndcServicePort = null;

    public NDCService() {

        if (ndcServicePort == null) {
            try {
                NDCAccessServer server = new NDCAccessServer(CommonEntityBuilder.getURL());
                ndcServicePort = server.getNdcResServiceSOAPPort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
