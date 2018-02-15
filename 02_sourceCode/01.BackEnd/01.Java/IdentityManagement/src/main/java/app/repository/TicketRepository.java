package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entity.RemainingTicket;

/**
 * TicketRepository for JPA.
 *
 * @author Shoji Yamada.
 *
 */
@Repository
public interface TicketRepository extends JpaRepository<RemainingTicket, String> {

}
