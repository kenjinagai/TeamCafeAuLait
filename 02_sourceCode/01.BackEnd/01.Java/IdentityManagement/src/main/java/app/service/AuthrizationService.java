package app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import app.constant.TicketConstants;

@Service
public class AuthrizationService {
    public boolean isAdmin(final HttpServletRequest request) {
        return request.isUserInRole(TicketConstants.ROLE_ADMIN);
    }
}
