package pl.nieckarz.librarymanager.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.nieckarz.librarymanager.appuser.AppUser;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        AppUser appUser = (AppUser) authentication.getPrincipal();

        String redirectURL = "/";

        if (appUser.getAppUserRole().toString() == "ROLE_ADMIN") {
            redirectURL = "/admin";
        }

        response.sendRedirect(redirectURL);

    }

}