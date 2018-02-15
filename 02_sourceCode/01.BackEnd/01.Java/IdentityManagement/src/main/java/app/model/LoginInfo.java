package app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @NotNull
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^\\p{Alnum}+")
    private String userId;

    @ApiModelProperty(value = "User password", required = true)
    @NotNull
    @Size(min = 1, max = 15)
    @Pattern(regexp = "^\\p{Alnum}+")
    private String password;
}
