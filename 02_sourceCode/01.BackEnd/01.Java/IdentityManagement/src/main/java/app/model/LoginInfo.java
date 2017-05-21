package app.model;


import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

import app.constant.TicketConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginInfo {
    @ApiModelProperty("User id")
    private String userId;

    @ApiModelProperty("User password")
    private String password;

    public Boolean validParam(){
        return validBlank() && validTextLength() && validNumOrAlpha();
    }

    /**
     * Validate whether this class fields aren't blank.
     *
     * @return Boolean whether this class fields aren't blank.
     */
    private Boolean validBlank() {
        return StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(password);
    }

    /**
     * Validate text lenght of this class string fields.
     *
     * @return Boolean Whether this class string fields counts under max text length.
     */
    private Boolean validTextLength() {
            return (StringUtils.length(userId) < TicketConstants.MAX_TEXT_LENGHT || StringUtils.length(userId) == TicketConstants.MAX_TEXT_LENGHT)
                    && (StringUtils.length(password) < TicketConstants.MAX_TEXT_LENGHT || StringUtils.length(password) == TicketConstants.MAX_TEXT_LENGHT);
    }

    /**
     * Validate alphabet or number of this class string fields.
     *
     * @return Boolean Whether this class string fields aren't alphabet or number.
     */
    private Boolean validNumOrAlpha() {
        if(userId != null && password != null) {
            String alphanumericPattern = "^[a-zA-Z0-9]*$";
            return userId.matches(alphanumericPattern) && password.matches(alphanumericPattern);
        } else {
            return false;
        }
    }
}
