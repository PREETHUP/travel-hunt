package com.ndchack.travelhunt.dataprovider.ndc.builder;

import org.iata.iata.edist.*;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.net.URL;

/**
 * Created by A-2174 on 2/24/18.
 */
public class CommonEntityBuilder {

    public static PointOfSaleType getPosType() {

        PointOfSaleType.Location loc = new PointOfSaleType.Location();
        loc.setCountryCode((new CountryCode()));
        loc.getCountryCode().setValue("DE");
        PointOfSaleType posType = new PointOfSaleType();
        posType.setLocation(loc);

        return  posType;
    }

    public static MsgDocumentType getDocument() {

        MsgDocumentType doc = new MsgDocumentType();
        doc.setReferenceVersion("15.2");
        doc.setName("NDC");
        return doc;
    }

    public static MsgPartiesType getParties() {

        AgentUserType.AgentUserID userID = new AgentUserType.AgentUserID();
        userID.setValue("HKTHONUSR");
        AgentUserSenderType agency = new AgentUserSenderType();
        agency.setAgentUserID(userID);

        MsgPartiesType.Sender sender = new MsgPartiesType.Sender();
        sender.setAgentUserSender(agency);
        MsgPartiesType party = new MsgPartiesType();
        party.setSender(sender);

        MsgPartiesType.Recipient recipient = new MsgPartiesType.Recipient();
        ORAAirlineRecipientType airlineRecipientType = new ORAAirlineRecipientType();
        AirlineID id = new AirlineID();
        id.setValue("XQ");
        airlineRecipientType.setAirlineID(id);
        recipient.setORARecipient(airlineRecipientType);
        JAXBElement<MsgPartiesType.Recipient> rec = new JAXBElement<MsgPartiesType.Recipient>(new QName("http://www.iata.org/IATA/EDIST","Recipient"), MsgPartiesType.Recipient.class, recipient);
        party.setRecipient(rec);

        return party;
    }

    public static URL getURL() throws Exception {

        return new URL("https://iflyrestest.ibsgen.com:6013/iRes_NdcRes_WS/services/NdcResServiceSOAPPort?wsdl");
    }
}
