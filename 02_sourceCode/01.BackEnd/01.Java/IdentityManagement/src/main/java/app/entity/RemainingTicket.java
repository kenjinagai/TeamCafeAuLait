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
@Table(name = "remaining_ticket")
public class RemainingTicket implements Serializable {
    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Integer remainingTicketId ;

    @Column(nullable = false, columnDefinition="INT(10)")
    @ApiModelProperty(value = "Remaining dolce ticket number")
    private Integer remainingDolce;

    @Column(nullable = false, columnDefinition="INT(10)")
    @ApiModelProperty(value = "Remaining barista ticket number")
    private Integer remainingBarista;
}
