package app.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import app.constant.Constants;

@Service
public class AuthrizationService {
    public boolean isAdmin(final HttpServletRequest request) {
        return request.isUserInRole(Constants.ROLE_ADMIN);
    }
}
