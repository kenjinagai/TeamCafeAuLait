package app.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 認証結果を返すモデルクラス.
 * @author Kenji Nagai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResult {
	@ApiModelProperty("User name")
	private String userName;

	@ApiModelProperty("Permission list")
	private List<String> permissionList;

	@ApiModelProperty("Role list")
	private List<String> roleList;
}
