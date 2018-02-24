package com.ndchack.travelhunt.dataprovider.ndc.builder;

import com.ibsplc.iflyres.simpletypes.FareDetailAugPoint;
import com.ndchack.travelhunt.dataprovider.ndc.model.Fare;
import com.ndchack.travelhunt.dataprovider.ndc.model.OD;
import com.ndchack.travelhunt.dataprovider.ndc.model.OrderView;
import com.ndchack.travelhunt.dataprovider.ndc.model.Passenger;
import org.iata.iata.edist.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import javax.xml.bind.JAXBElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A-2174 on 2/24/18.
 */
public class OrderViewBuilder {

    public static OrderView buildOrder(OrderViewRS rs) {

        OrderView response = new OrderView();
        response.getPax().addAll(buildPassengers(rs.getResponse().getPassengers()));

        response.getOds().addAll(buildOD(rs.getResponse()));
        response.setFare(getFare(rs.getResponse().getOrder().get(0).getOrderItems().getOrderItem().get(0).getFlightItem().getPrice()));


        return response;
    }

    private static Fare getFare(FlightItemType.Price orderPrice) {

        Fare fare = new Fare();
        fare.setBaseFare(orderPrice.getBaseAmount().getValue().floatValue());
        return fare;

    }

    private static List<OD> buildOD(OrderViewRS.Response rs) {

        List<OD> ods = new ArrayList<>();
        for (FlightType flightType : rs.getOrder().get(0).getOrderItems().getOrderItem().get(0).getFlightItem().getOriginDestination()) {

            OD od = new OD();
            od.setReferenceKey(flightType.getOriginDestinationKey());


            for (FlightType.Flight orderFlight : flightType.getFlight()) {

                com.ndchack.travelhunt.dataprovider.ndc.model.Flight flight = new com.ndchack.travelhunt.dataprovider.ndc.model.Flight();
                od.getFlights().add(flight);
                flight.setSegmentKey(orderFlight.getSegmentKey());
                String date = null;
                String time = null;
                for (JAXBElement<?> obj : orderFlight.getDeparture().getRest()) {

                    String key = obj.getName().getLocalPart();

                    switch (key) {
                        case "AirportCode" : flight.setDepartureAirport((String) obj.getValue()); break;
                        case "Date" : date = (String) obj.getValue(); break;
                        case "Time" : time = (String) obj.getValue(); break;
                    }

                }
                if (date != null && time != null) {

                    flight.setDepartureTime(
                            DateTime.parse(date.substring(0, date.length() - 1) + " " + time, DateTimeFormat.forPattern("yyyy-MM-dd hh:mm")));
                }

                for (JAXBElement<?> obj : orderFlight.getArrival().getRest()) {

                    String key = obj.getName().getLocalPart();

                    switch (key) {
                        case "AirportCode" : flight.setArrivalAirport((String) obj.getValue()); break;
                        case "Date" : date = (String) obj.getValue(); break;
                        case "Time" : time = (String) obj.getValue(); break;
                    }
                }
                if (date != null && time != null) {

                    flight.setArrivalTime(
                            DateTime.parse(date.substring(0, date.length() - 1) + " " + time, DateTimeFormat.forPattern("yyyy-MM-dd hh:mm")));
                }

                flight.setMarketingCarrier(orderFlight.getMarketingCarrier().getName());
                flight.setFlightNumber(orderFlight.getMarketingCarrier().getFlightNumber().getValue());
                flight.setBookingCode(orderFlight.getClassOfService().getCode().getValue());
                flight.setCabinClass(orderFlight.getCabinType().getName());
                javax.xml.datatype.Duration timeField = orderFlight.getDetails().getFlightDuration().getValue();
                flight.setFlightDuration(timeField.getHours() + timeField.getMinutes() + timeField.getSeconds());
                flight.setNumberOfStops(0);




                for(FareComponentType orderComponentType : rs.getOrder().get(0).getOrderItems().getOrderItem().get(0).getFlightItem().getFareDetail().getFareComponent()) {

                    if(orderComponentType.getSegmentReference().getValue().equals(flight.getSegmentKey())) {

                        for(Object ref : orderComponentType.getRefs()) {
                            flight.getOtherAugKeys().add((String)ref);
                        }
                        break;
                    }
                }

                for (OrdViewMetadataType.Other.OtherMetadata other : rs.getMetadata().getOther().getOtherMetadata()) {

                    if (other.getPriceMetadatas() != null) {

                        for (PriceMetadataType priceMetadataType : other.getPriceMetadatas().getPriceMetadata()) {

                            for (AugPointType fareAugPoint : priceMetadataType.getAugmentationPoint().getAugPoint()) {
                                if (fareAugPoint.getOwner().equals("FareDetailAugPoint")) {
                                    flight.setFareAugKey(fareAugPoint.getKey());
                                    flight.setFareBasis(((FareDetailAugPoint)fareAugPoint.getAny()).getFareBasis());
                                    flight.setFareLevel(((FareDetailAugPoint)fareAugPoint.getAny()).getFareLevel());
                                    break;
                                }
                            }
                        }
                    }
                }


            }
        }
        return ods;
    }

    private static List<Passenger> buildPassengers(OrderViewRS.Response.Passengers orderPaxs) {

        List<Passenger> paxs = new ArrayList<>();
        for (org.iata.iata.edist.Passenger orderPax : orderPaxs.getPassenger()) {

            Passenger pax = new Passenger();
            pax.setPassengerName(orderPax.getName().getGiven() + " " + orderPax.getName().getSurname());
            pax.setPassengerType(orderPax.getPTC().getValue());
            paxs.add(pax);
        }
        return paxs;
    }
}
