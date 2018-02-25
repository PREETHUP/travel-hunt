package com.ndchack.travelhunt.service;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.Util.OrderResponseData;
import com.ndchack.travelhunt.Util.Util;
import com.ndchack.travelhunt.dataprovider.ndc.model.Ancillary;
import com.ndchack.travelhunt.dataprovider.ndc.model.ServiceList;
import com.ndchack.travelhunt.dataprovider.ndc.model.ServiceListRequest;
import com.ndchack.travelhunt.dataprovider.ndc.service.GetServiceListService;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryDetail;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryResponse;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@Service
public class UserAncillaryService {

    public UserAncillaryResponse retrieveUserAncillaryDetails(){
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();
        userAncillaryResponse.setUserAncillaryDetails(populateUserAncillaryDetailsList());
        userAncillaryResponse.setStage(Configuration.gameStage);
        return userAncillaryResponse;
    }

    public UserAncillaryResponse retrieveAirlineAncillaryDetails(){
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();
        userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        userAncillaryResponse.setStage(Configuration.gameStage);
        return userAncillaryResponse;
    }

    public void updateUserAncillaryAmount(Integer tasksFinished, Integer totalTasks) {
        Float percentageFraction = (Float.valueOf(tasksFinished.toString())/Float.valueOf(totalTasks.toString()));
        Float percentage = percentageFraction*Configuration.discount;

        for(String userAncillaryName :  Configuration.userSelectedAncillary.keySet()) {
            Float actualPrice = Configuration.airlineAncillary.get(userAncillaryName);
            Float discountAmount = actualPrice * (percentage/100);
            Configuration.userSelectedAncillary.put(userAncillaryName, actualPrice-discountAmount);
        }
    }

    public void setUserAncillary(List<String> ids) {
        for(String id :  ids) {
            Configuration.userSelectedAncillary.put(id, Configuration.airlineAncillary.get(id));
        }
        Configuration.gameStage = "2";
    }

    private List<UserAncillaryDetail> populateUserAncillaryDetailsList() {
        List<UserAncillaryDetail> userAncillaryDetails= new ArrayList<UserAncillaryDetail>();
        for(String userAncillaryName :  Configuration.userSelectedAncillary.keySet()) {
            UserAncillaryDetail userAncillaryDetail = new UserAncillaryDetail();
            userAncillaryDetail.setAncillaryName(userAncillaryName);
            Float totalAmount = Configuration.airlineAncillary.get(userAncillaryName);
            Float discountedAmount = Configuration.userSelectedAncillary.get(userAncillaryName);
            userAncillaryDetail.setDiscountedAmount(Util.round(discountedAmount,2));
            userAncillaryDetail.setTotalAmount(Util.round(totalAmount,2));
            userAncillaryDetail.setSavedAmount(Util.round(totalAmount,2)-Util.round(discountedAmount,2));
            userAncillaryDetails.add(userAncillaryDetail);
        }
        Collections.sort(userAncillaryDetails);
        return userAncillaryDetails;
    }

    private List<UserAncillaryDetail> getAllAirAncillaryDetailsFromDb() {
        List<UserAncillaryDetail> userAncillaryDetails= new ArrayList<UserAncillaryDetail>();
        for(String userAncillaryName :  Configuration.airlineAncillary.keySet()) {
            UserAncillaryDetail userAncillaryDetail = new UserAncillaryDetail();
            userAncillaryDetail.setAncillaryName(userAncillaryName);

            Float totalAmount = Configuration.airlineAncillary.get(userAncillaryName);
            Float discountedAmount = totalAmount * (Integer.valueOf(Configuration.discount).floatValue()/100);

            userAncillaryDetail.setTotalAmount(Util.round(totalAmount,2));
            userAncillaryDetail.setDiscountedAmount(Util.round(totalAmount,2)-Util.round(discountedAmount,2));
            userAncillaryDetail.setSavedAmount(Float.valueOf("0"));
            userAncillaryDetails.add(userAncillaryDetail);
        }
        Collections.sort(userAncillaryDetails);
        return userAncillaryDetails;
    }

    public UserAncillaryResponse getAncillaryResponse() {
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();

        if (new DateTime().isAfter(Configuration.firstLegDepartureTime)  && Configuration.gameStage.equals("0") ) {
            Configuration.gameStage = "1";
        }
        String stage = Configuration.gameStage;
        System.out.println("Current Stage is + " + stage);
        userAncillaryResponse.setStage(stage);

        if( (stage.equals("0") && !Configuration.airlineAncillary.isEmpty()) ) {
            userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        } else if ( stage.equals("3") || stage.equals("2") ){
            userAncillaryResponse.setUserAncillaryDetails(populateUserAncillaryDetailsList());
        } else {
            GetServiceListService listService = new GetServiceListService();
            ServiceListRequest req = new ServiceListRequest();
            req.setOdId(OrderResponseData.getOrderDetails().getOds().get(0).getReferenceKey());
            req.setFlight(OrderResponseData.getOrderDetails().getOds().get(0).getFlights().get(0));
            ServiceList list = listService.getServices(req);
            for (Ancillary ancillary : list.getAncillaries()) {
                Configuration.airlineAncillary.put(ancillary.getAncillaryName(), ancillary.getPrice());
            }
            userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        }
        return userAncillaryResponse;
    }

}
