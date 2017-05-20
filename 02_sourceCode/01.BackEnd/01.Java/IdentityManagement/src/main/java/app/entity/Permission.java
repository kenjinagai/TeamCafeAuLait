package app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable=false)
	private String name;

	@Column(nullable=false)
	private String alias;
}
