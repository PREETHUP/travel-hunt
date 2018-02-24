package com.ndchack.travelhunt.dataprovider.ndc.connector;

import com.ibsplc.wsdl.ndc.NdcResService;

import javax.jws.HandlerChain;
import java.net.URL;

/**
 * Created by A-2174 on 2/24/18.
 */
@HandlerChain(file="HandlerChain.xml")
public class NDCAccessServer  extends NdcResService {

    public NDCAccessServer(URL url) {
        super(url);
    }
}
