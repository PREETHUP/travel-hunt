package com.ndchack.travelhunt.ui.domain.Ancilaries;

import java.util.List;

/**
 * Created by a-6281 on 2/24/18.
 */
public class UserAncillaryResponse {

    private List<UserAncillaryDetail> userAncillaryDetails;
    private String stage;


    public List<UserAncillaryDetail> getUserAncillaryDetails() {
        return userAncillaryDetails;
    }

    public void setUserAncillaryDetails(List<UserAncillaryDetail> userAncillaryDetails) {
        this.userAncillaryDetails = userAncillaryDetails;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
