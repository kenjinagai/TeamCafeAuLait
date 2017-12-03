package app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import app.constant.Constants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * User authetication and role.
 *
 * @author Kenji Nagai
 *
 */
@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "User id\n Only alphabet or number.")
    @Size(min = 1, max = 15)
    @Pattern(regexp = Constants.REGEX_ALPHABET_OR_NUMBER)
    private String userId;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    @ApiModelProperty(value = "User password\n Only alphabet or number.", name = "password")
    @JsonProperty(value = "password")
    @Size(min = 1, max = 15)
    @Pattern(regexp = Constants.REGEX_ALPHABET_OR_NUMBER)
    private String encodedPassword;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    @ApiModelProperty("User name")
    @Size(min = 1, max = 20)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_has_role", joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "name"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roleList;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="extended_authentication")
//    private ExtendedAuthentication extendedAuthentication;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="remaining_ticket")
    private RemainingTicket remainingTicket;

}
