package app.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, columnDefinition="VARCHAR(255)")
	@ApiModelProperty(value="User id")
	private String userId;

	@Column(nullable=false, columnDefinition="VARCHAR(255)")
	@JsonIgnore
	@ApiModelProperty(value="User password")
	private String encodedPassword;

	@Column(nullable=false, columnDefinition="VARCHAR(50)")
	@ApiModelProperty("User name")
	private String name;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="user_has_role",
			joinColumns=@JoinColumn(name="user_name",referencedColumnName="name"),
			inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id")
			)
	private List<Role> roleList;
}
