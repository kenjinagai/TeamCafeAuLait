package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue
	@ApiModelProperty("Permission id. e.g. create, read, manageUser")
	private Integer id;

	@Column(nullable=false)
	@ApiModelProperty("Permission name. e.g. guest, user, admin")
	private String name;

	@Column(nullable=false)
	@ApiModelProperty("Permission alias.")
	private String alias;
}
