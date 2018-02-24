package com.ndchack.travelhunt.service;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.ui.domain.Ancilaries.AncillaryIdWrapper;
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
        return userAncillaryResponse;
    }

    public UserAncillaryResponse retrieveAirlineAncillaryDetails(){
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();
        userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        return userAncillaryResponse;
    }

    public UserAncillaryResponse retrieveAirAncillaryDetailsWithApiCall(){
        //do Service call
        //update the Air Ancillary list
        // generate ancillary list
        return new UserAncillaryResponse();
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
            userAncillaryDetail.setDiscountedAmount(discountedAmount);
            userAncillaryDetail.setTotalAmount(totalAmount);
            userAncillaryDetail.setSavedAmount(totalAmount-discountedAmount);
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
            userAncillaryDetail.setTotalAmount(Configuration.airlineAncillary.get(userAncillaryName));
            userAncillaryDetail.setDiscountedAmount(Float.valueOf("0"));
            userAncillaryDetail.setSavedAmount(Float.valueOf("0"));
            userAncillaryDetails.add(userAncillaryDetail);
        }
        Collections.sort(userAncillaryDetails);
        return userAncillaryDetails;
    }

    public UserAncillaryResponse getAncillaryResponse() {
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();

        String stage = Configuration.gameStage;
        userAncillaryResponse.setStage(stage);



        if( (stage.equals("0") && !Configuration.airlineAncillary.isEmpty()) ) {
            userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        } else if ( stage.equals("3") || stage.equals("2") ){
            userAncillaryResponse.setUserAncillaryDetails(populateUserAncillaryDetailsList());
        } else {
            //doservice Call
            Configuration.gameStage = "1";
            userAncillaryResponse.setUserAncillaryDetails(getAllAirAncillaryDetailsFromDb());
        }
        return userAncillaryResponse;
    }
}
