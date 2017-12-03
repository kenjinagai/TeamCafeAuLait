package app.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Login Info.
 *
 * @author Kenji Nagai
 *
 */
@Setter
@Getter
@ToString
public class LoginInfo {
    @ApiModelProperty(value = "User id", required = true)
    private String userId;

    @ApiModelProperty(value = "User password", required = true)
    private String password;
}
