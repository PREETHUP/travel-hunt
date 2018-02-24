package com.ndchack.travelhunt.service;

import com.ndchack.travelhunt.Util.Configuration;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryDetail;
import com.ndchack.travelhunt.ui.domain.Ancilaries.UserAncillaryResponse;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by a-6281 on 2/24/18.
 */
@Service
public class UserAncillaryService {

    public UserAncillaryResponse retrieveUserAncillaryDetails(){
        UserAncillaryResponse userAncillaryResponse = new UserAncillaryResponse();
        userAncillaryResponse.setUserAncillaryDetails(retriveUpdatedUserAncillaryDetails());
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

    public void updateUserAncillary(Integer tasksFinished, Integer totalTasks) {
        Float percentageFraction = (Float.valueOf(tasksFinished.toString())/Float.valueOf(totalTasks.toString()));
        Float percentage = percentageFraction*Configuration.discount;

        for(String userAncillaryName :  Configuration.userSelectedAncillary.keySet()) {
            Float actualPrice = Configuration.airlineAncillary.get(userAncillaryName);
            Float discountAmount = actualPrice * (percentage/100);
            Configuration.userSelectedAncillary.put(userAncillaryName, actualPrice-discountAmount);
        }
    }

    private List<UserAncillaryDetail> retriveUpdatedUserAncillaryDetails() {
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
            userAncillaryDetails.add(userAncillaryDetail);
        }
        Collections.sort(userAncillaryDetails);
        return userAncillaryDetails;
    }
}
