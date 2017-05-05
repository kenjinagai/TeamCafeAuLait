package app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import app.entity.User;
import lombok.Data;

@Data
public class LoginUserDetail extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 5L;

    private String name;
    private String password;
    private List<String> permissionList;
    private Collection<GrantedAuthority> authorities;

    public LoginUserDetail(User user) {
        super(user.getName(), user.getEncodedPassword(), new ArrayList<GrantedAuthority>());
        name = user.getName();
        password = user.getEncodedPassword();
        //User -> list<Role> -> list<Permission> -> list<String>
        permissionList = user.getRoleList()
                .stream()
                .flatMap(role -> role.getPermissionList()
                        .stream()
                        .map(permission -> permission.getName()))
                .collect(Collectors.toList());

        //User -> list<Role> -> list<GrantedAuthority>
        authorities = user.getRoleList()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

}
