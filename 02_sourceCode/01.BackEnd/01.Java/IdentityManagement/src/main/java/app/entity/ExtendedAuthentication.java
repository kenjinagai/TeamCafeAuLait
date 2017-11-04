package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Extended authentication.
 *
 * @author Kenji Nagai
 *
 */
@Data
@Entity
@Table(name = "extended_authentication")
public class ExtendedAuthentication implements Serializable {
    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue
    @Column(nullable = false)
    @ApiModelProperty(value = "extended id")
    private Integer id;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "User id")
    private String userId;

    @Column(nullable = true, columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "Extened authentication value. \n"
            + "e.g. IC Card number.")
    private String extendedAuthenticationValue;
}
