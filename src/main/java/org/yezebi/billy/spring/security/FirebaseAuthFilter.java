package org.yezebi.billy.spring.security;

import com.google.common.base.Strings;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class FirebaseAuthFilter extends AbstractAuthenticationProcessingFilter {
    private static final String TOKEN_HEADER = "X-Auth-Token";

    public FirebaseAuthFilter() {
        super("/**");
        this.setAuthenticationSuccessHandler((request, response, authentication) -> {
            // nothing to do
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        var authToken = request.getHeader(TOKEN_HEADER);
        if (Strings.isNullOrEmpty(authToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing token");
        }

        try {
            final FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(authToken);
            if (token.isEmailVerified()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email not verified");
            }

            return new FirebaseAuthToken(token.getUid(), token);
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        if (!response.isCommitted()) {
            chain.doFilter(request, response);
        }
    }
}

