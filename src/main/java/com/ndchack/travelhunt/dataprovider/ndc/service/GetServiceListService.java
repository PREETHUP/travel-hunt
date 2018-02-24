package com.ndchack.travelhunt.dataprovider.ndc.service;

import com.ndchack.travelhunt.dataprovider.ndc.builder.ServiceReqBuilder;
import com.ndchack.travelhunt.dataprovider.ndc.model.Ancillary;
import com.ndchack.travelhunt.dataprovider.ndc.model.OrderView;
import com.ndchack.travelhunt.dataprovider.ndc.model.ServiceList;
import com.ndchack.travelhunt.dataprovider.ndc.model.ServiceListRequest;
import org.iata.iata.edist.ServiceListRQ;
import org.iata.iata.edist.ServiceListRS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A-2174 on 2/25/18.
 */
public class GetServiceListService extends NDCService {

    private final List<String> excludedAncillaries = new ArrayList<>();

    public GetServiceListService() {
        super();
        excludedAncillaries.add("AVIH");
        excludedAncillaries.add("AVIH1");
        excludedAncillaries.add("BIKE");
        excludedAncillaries.add("BIKP");
        excludedAncillaries.add("BORD");
        excludedAncillaries.add("BORP");
        excludedAncillaries.add("BOXB");
        excludedAncillaries.add("BOXC");
        excludedAncillaries.add("CANO");
        excludedAncillaries.add("CANP");
        excludedAncillaries.add("CATH");
        excludedAncillaries.add("DBML");
        excludedAncillaries.add("DING");
        excludedAncillaries.add("DINP");
        excludedAncillaries.add("DIVE");
        excludedAncillaries.add("DIVP");
        excludedAncillaries.add("DOGH");
        excludedAncillaries.add("EDOG");
        excludedAncillaries.add("GDOG");
        excludedAncillaries.add("GFML");
        excludedAncillaries.add("GOLF");
        excludedAncillaries.add("GOLFIT");
        excludedAncillaries.add("GOLFTO");
        excludedAncillaries.add("GOLFP");
        excludedAncillaries.add("GOLP");
        excludedAncillaries.add("HANG");
        excludedAncillaries.add("HANP");
        excludedAncillaries.add("KAYK");
        excludedAncillaries.add("KAYP");
        excludedAncillaries.add("MDOG");
        excludedAncillaries.add("NLML");
        excludedAncillaries.add("PARA");
        excludedAncillaries.add("PARP");
        excludedAncillaries.add("PET1");
        excludedAncillaries.add("PET2");
        excludedAncillaries.add("PET3");
        excludedAncillaries.add("PET4");
        excludedAncillaries.add("PETC");
        excludedAncillaries.add("PMIB");
        excludedAncillaries.add("PMIC");
        excludedAncillaries.add("PMID");
        excludedAncillaries.add("PMIE");
        excludedAncillaries.add("RDOG");
        excludedAncillaries.add("SKEQ");
        excludedAncillaries.add("SPWE");
        excludedAncillaries.add("TAML");
        excludedAncillaries.add("TBML");
        excludedAncillaries.add("TCML");
        excludedAncillaries.add("TDML");
        excludedAncillaries.add("TEML");
        excludedAncillaries.add("TFML");
        excludedAncillaries.add("TGML");
        excludedAncillaries.add("THML");
        excludedAncillaries.add("TIML");

        excludedAncillaries.add("TMML");
        excludedAncillaries.add("TPML");
        excludedAncillaries.add("TSML");
        excludedAncillaries.add("TYML");
        excludedAncillaries.add("UMNR");
        excludedAncillaries.add("VGML");
        excludedAncillaries.add("VLML");
    }

    /*public static void main(String[] args) {

        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
        System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");

        GetOrderService service = new GetOrderService();
        OrderView view = service.getOrder("R4254R");

        GetServiceListService listService = new GetServiceListService();
        ServiceListRequest req = new ServiceListRequest();
        req.setOdId(view.getOds().get(0).getReferenceKey());
        req.setFlight(view.getOds().get(0).getFlights().get(0));
        ServiceList list = listService.getServices(req);
        System.out.println("done");
    }*/

    public ServiceList getServices(ServiceListRequest req) {


        ServiceListRQ rq = ServiceReqBuilder.createRequest(req);
        try {

            ServiceListRS rs = ndcServicePort.listServices(rq);

            ServiceList list = collectPayableServices(rs);

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ServiceList();
    }

    private ServiceList collectPayableServices(ServiceListRS rs) {

        ServiceList list = new ServiceList();
        for (ServiceListRS.Services.Service service : rs.getServices().getService()) {

            if(service.getPrice().isEmpty() || service.getPrice().get(0).getTotal().getValue().toString().equals("0.00") || service.getDescriptions() == null || service.getDescriptions().getDescription() == null || service.getDescriptions().getDescription().isEmpty()) {
                continue;
            }
            if (excludedAncillaries.contains(service.getEncoding().getCode().getValue())) {
                continue;
            }
            Ancillary ancillary = new Ancillary();
            ancillary.setPrice(service.getPrice().get(0).getTotal().getValue().floatValue());
            ancillary.setAncillaryName(service.getDescriptions().getDescription().get(0).getText().getValue());
            ancillary.setAncillaryCode(service.getEncoding().getCode().getValue());
            list.getAncillaries().add(ancillary);
        }
        return list;
    }
}
