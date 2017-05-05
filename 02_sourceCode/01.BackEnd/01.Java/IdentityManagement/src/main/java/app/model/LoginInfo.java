package app.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginInfo {
	@ApiModelProperty("User id")
	private String userId;

	@ApiModelProperty("User password")
	private String password;
}
