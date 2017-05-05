package app.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import app.constant.TicketConstants;

@Service
public class AuthrizationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthrizationService.class);

    public boolean isAdmin(HttpServletRequest request) {
        return request.isUserInRole(TicketConstants.ROLE_ADMIN);
    }
}
