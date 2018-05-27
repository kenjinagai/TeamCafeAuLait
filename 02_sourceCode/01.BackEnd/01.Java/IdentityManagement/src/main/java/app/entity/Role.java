package app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User role class.
 *
 * @author Kenji Nagai
 *
 */
@Data
@Entity
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue
    @ApiModelProperty(value = "Role id")
    @NumberFormat
    @NotNull
    private Integer roleId;

    @Column(nullable = false)
    @ApiModelProperty(value = "Role name")
    @NotEmpty
    private String roleName;

    @Column(nullable = false)
    @ApiModelProperty(value = "Role arias")
    @NotEmpty
    private String roleAlias;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_has_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<Permission> permissionList;
}
