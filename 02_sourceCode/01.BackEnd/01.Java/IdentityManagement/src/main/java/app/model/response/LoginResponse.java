package app.model.response;

import app.model.AuthResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Login response.
 * @author Kenji Nagai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private AuthResult loginedUserInfo;
    private String token;
}
