package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User permission class.
 *
 * @author Kenji Nagai
 */
@Data
@Entity
@Table(name = "permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue
    @ApiModelProperty("Permission id. e.g. create, read, manageUser")
    @NumberFormat
    @NotNull
    private Integer permissionId;

    @Column(nullable = false)
    @ApiModelProperty("Permission name. e.g. guest, user, admin")
    @NotEmpty
    private String permissionName;

    @Column(nullable = false)
    @ApiModelProperty("Permission alias.")
    private String permissionAlias;
}
