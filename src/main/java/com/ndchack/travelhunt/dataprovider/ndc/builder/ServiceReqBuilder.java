package com.ndchack.travelhunt.dataprovider.ndc.builder;

import com.ibsplc.iflyres.simpletypes.FareDetailAugPoint;
import com.ndchack.travelhunt.dataprovider.ndc.model.Flight;
import com.ndchack.travelhunt.dataprovider.ndc.model.ServiceListRequest;
import com.sun.org.apache.xerces.internal.jaxp.datatype.DatatypeFactoryImpl;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.iata.iata.edist.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import java.math.BigInteger;

/**
 * Created by A-2174 on 2/25/18.
 */
public class ServiceReqBuilder {

    public static ServiceListRQ createRequest(ServiceListRequest req) {

        ServiceListRQ rq = new ServiceListRQ();
        rq.setPointOfSale(CommonEntityBuilder.getPosType());
        rq.setDocument(CommonEntityBuilder.getDocument());
        rq.setParty(CommonEntityBuilder.getParties());

        ServiceListRQ.Query query = new ServiceListRQ.Query();
        rq.setQuery(query);

        /*OrderIDType orderIDType = new OrderIDType();
        orderIDType.setValue("R4254R");
        query.setOrderID(orderIDType);*/

        ServiceListRQ.Query.OriginDestination od = new ServiceListRQ.Query.OriginDestination();
        query.getOriginDestination().add(od);
        od.setOriginDestinationKey(req.getOdId());
        //od.setOriginDestinationKey("XQ_OD_1494289486264");

        FlightType.Flight flight = new FlightType.Flight();
        od.getFlight().add(flight);

        Flight reqFlight = req.getFlight();
        flight.setSegmentKey(reqFlight.getSegmentKey());
        //flight.setSegmentKey("XQ_SEG_1494289486269");


        Departure departure = new Departure();
        flight.setDeparture(departure);

        FlightDepartureType.AirportCode airport = new FlightDepartureType.AirportCode();
        airport.setValue(reqFlight.getDepartureAirport());
        //airport.setValue("FRA");
        JAXBElement<FlightDepartureType.AirportCode> airportCode = new JAXBElement<FlightDepartureType.AirportCode>(new QName("http://www.iata.org/IATA/EDIST","AirportCode"), FlightDepartureType.AirportCode.class, airport);
        departure.getRest().add(airportCode);

        XMLGregorianCalendarImpl cal = new XMLGregorianCalendarImpl();
        cal.setYear(reqFlight.getDepartureTime().getYear());
        cal.setMonth(reqFlight.getDepartureTime().getMonthOfYear());
        cal.setDay(reqFlight.getDepartureTime().getDayOfMonth());
        //cal.setYear(2018);
        //cal.setMonth(3);
        //cal.setDay(18);
        JAXBElement<XMLGregorianCalendar> departureDateJaxb = new JAXBElement<XMLGregorianCalendar>(new QName("http://www.iata.org/IATA/EDIST","Date"), XMLGregorianCalendar.class, cal);
        departure.getRest().add(departureDateJaxb);

        JAXBElement<String> timeJaxb = new JAXBElement<String>(new QName("http://www.iata.org/IATA/EDIST","Time"), String.class, reqFlight.getDepartureTime().getHourOfDay() + ":" + reqFlight.getDepartureTime().getMinuteOfHour());
        //JAXBElement<String> timeJaxb = new JAXBElement<String>(new QName("http://www.iata.org/IATA/EDIST","Time"), String.class, "14:30");
        departure.getRest().add(timeJaxb);


        FlightArrivalType arrival = new FlightArrivalType();
        flight.setArrival(arrival);

        FlightArrivalType.AirportCode aairport = new FlightArrivalType.AirportCode();
        aairport.setValue(reqFlight.getArrivalAirport());
        //aairport.setValue("AYT");
        JAXBElement<FlightArrivalType.AirportCode> aairportCode = new JAXBElement<FlightArrivalType.AirportCode>(new QName("http://www.iata.org/IATA/EDIST","AirportCode"), FlightArrivalType.AirportCode.class, aairport);
        arrival.getRest().add(aairportCode);

        cal = new XMLGregorianCalendarImpl();
        cal.setYear(reqFlight.getArrivalTime().getYear());
        cal.setMonth(reqFlight.getArrivalTime().getMonthOfYear());
        cal.setDay(reqFlight.getArrivalTime().getDayOfMonth());
        //cal.setYear(2018);
        //cal.setMonth(3);
        //cal.setDay(18);
        JAXBElement<XMLGregorianCalendar> arrivalDateJaxb = new JAXBElement<XMLGregorianCalendar>(new QName("http://www.iata.org/IATA/EDIST","Date"), XMLGregorianCalendar.class, cal);
        arrival.getRest().add(departureDateJaxb);

        timeJaxb = new JAXBElement<String>(new QName("http://www.iata.org/IATA/EDIST","Time"), String.class, reqFlight.getArrivalTime().getHourOfDay() + ":" + reqFlight.getArrivalTime().getMinuteOfHour());
        //timeJaxb = new JAXBElement<String>(new QName("http://www.iata.org/IATA/EDIST","Time"), String.class, "19:55");
        arrival.getRest().add(timeJaxb);

        MarketingCarrierFlightType markCarrier = new MarketingCarrierFlightType();
        flight.setMarketingCarrier(markCarrier);
        AirlineID id = new AirlineID();
        id.setValue(reqFlight.getMarketingCarrier());
        markCarrier.setAirlineID(id);
        FlightNumber no =  new FlightNumber();
        markCarrier.setFlightNumber(no);
        no.setValue(reqFlight.getFlightNumber());
        //no.setValue("141");

        AircraftSummaryType summary = new AircraftSummaryType();
        flight.setEquipment(summary);
        summary.setAirlineEquipCode("800");
        AircraftCode craftCode = new AircraftCode();
        summary.setAircraftCode(craftCode);
        craftCode.setValue("738");

        CabinType cabinType = new CabinType();
        flight.setCabinType(cabinType);
        //cabinType.setCode("L");
        cabinType.setName("ECONOMY");
        cabinType.setCode(reqFlight.getBookingCode());





        FlightDetailType detailsType = new FlightDetailType();
        flight.setDetails(detailsType);
        FlightDetailType.Stops stops = new FlightDetailType.Stops();
        detailsType.setStops(stops);
        stops.setStopQuantity(BigInteger.ZERO);
        FlightDurationType durationType = new FlightDurationType();
        DatatypeFactoryImpl impl = new DatatypeFactoryImpl();
        int hour = reqFlight.getFlightDuration()/60;
        int minute = reqFlight.getFlightDuration()%60;
        //int hour = 1;
        //int minute = 10;
        Duration duration = impl.newDuration(true, 0, 0, 0, hour, minute, 0);
        durationType.setValue(duration);
        detailsType.setFlightDuration(durationType);

        ServiceListRQ.Metadata metadata = new ServiceListRQ.Metadata();
        rq.setMetadata(metadata);
        SrvListReqMetadataType.Shopping shopping = new SrvListReqMetadataType.Shopping();
        metadata.setShopping(shopping);
        ShopMetadataGroup dataGrp = new ShopMetadataGroup();
        shopping.setShopMetadataGroup(dataGrp);
        Offer offer = new Offer();
        dataGrp.setOffer(offer);
        OfferMetadatas metadatas = new OfferMetadatas();
        offer.getDisclosureMetadatasOrOfferMetadatasOrOfferInstructionMetadatas().add(metadatas);
        OfferItemMetadataType metadataType = new OfferItemMetadataType();
        metadatas.getOfferMetadata().add(metadataType);
        AugPointInfoType augPointInfoType = new AugPointInfoType();
        metadataType.setAugmentationPoint(augPointInfoType);
        AugPointType augPointType = new AugPointType();
        augPointInfoType.getAugPoint().add(augPointType);
        augPointType.setKey(reqFlight.getFareAugKey());
        //augPointType.setKey("XQ_FMD_1494289486271");
        FareDetailAugPoint augPoint = new FareDetailAugPoint();
        augPointType.setAny(augPoint);
        augPoint.setFareBasis(reqFlight.getFareBasis());
        augPoint.setFareLevel(reqFlight.getFareLevel());
        //augPoint.setFareBasis("ST");
        //augPoint.setFareLevel("SUNECO");

        AugPointType flightAugType = new AugPointType();
        flightAugType.setKey(reqFlight.getFareAugKey());
        //flightAugType.setKey("XQ_FMD_1494289486271");
        flight.getRefs().add(flightAugType);


        Travelers.Traveler traveler = new Travelers.Traveler();
        Travelers travelers = new Travelers();
        rq.setTravelers(travelers);
        rq.getTravelers().getTraveler().add(traveler);
        AnonymousTravelerType anoType = new AnonymousTravelerType();
        anoType.setObjectKey("XQ_PAX_1");
        traveler.setAnonymousTraveler(anoType);
        TravelerCoreType.PTC ptc = new TravelerCoreType.PTC();
        anoType.setPTC(ptc);
        ptc.setQuantity(BigInteger.ONE);
        ptc.setValue("ADT");


        return rq;
    }
}
