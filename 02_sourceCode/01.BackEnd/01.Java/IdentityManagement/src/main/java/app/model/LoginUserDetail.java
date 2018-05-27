package app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import app.entity.User;
import lombok.Data;

/**
 * LoginUserDetail.
 *
 * @author Kenji Nagai
 *
 */
@Data
public class LoginUserDetail extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 5L;

    private String name;
    private String password;
    private List<String> permissionList;
    private Collection<GrantedAuthority> authorities;

    /**
     * Constructor.
     *
     * @param User user
     */
    public LoginUserDetail(User user) {
        super(user.getUserName(), user.getEncodedPassword(), new ArrayList<GrantedAuthority>());
        name = user.getUserName();
        password = user.getEncodedPassword();
        //User -> list<Role> -> list<Permission> -> list<String>
        permissionList = user.getRoleList()
                .stream()
                .flatMap(role -> role.getPermissionList()
                        .stream()
                        .map(permission -> permission.getPermissionName()))
                .collect(Collectors.toList());

        //User -> list<Role> -> list<GrantedAuthority>
        authorities = user.getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

}
