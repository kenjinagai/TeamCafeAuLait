package app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.entity.RemainingTicket;
import app.repository.TicketRepository;

/**
 *
 * TicketService manages tickets.
 *
 * @author Shoji Yamada.
 *
 */
@Service
public class TicketService {
    @Autowired
    private TicketRepository trepositry;
    /**
     *
     * getAllTicket is method to return each remaining tickets of user, using userID.
     *
     * @return resTicket.
     *
     */
    public List<RemainingTicket> getAllTicket()
    {
        final List<RemainingTicket> resTicket = trepositry.findAll();

        return resTicket;
    }

}
