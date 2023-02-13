package org.yezebi.billy.spring.security;

import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.yezebi.billy.spring.user.model.UserRole;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class FirebaseAuthProvider implements AuthenticationProvider {
    @Override
    public boolean supports(Class<?> authentication) {
        return FirebaseAuthToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        final var authToken = (FirebaseAuthToken) authentication;
        final var holder = (FirebaseToken) authToken.getCredentials();
        final var claims = holder.getClaims();
        UserRole role;

        try {
            role = UserRole.valueOf((String) claims.get("role"));
        } catch (Exception e) {
            role = UserRole.USER;
        }

        return new FirebaseAuthToken(
                authToken.getPrincipal(),
                authentication.getCredentials(),
                this.getAuthorities(role)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole role) {
        var authorities = new ArrayList<GrantedAuthority>();
        for (UserRole r : UserRole.values()) {
            if (r.getLevel() <= role.getLevel()) {
                authorities.add(new SimpleGrantedAuthority(r.name()));
            }
        }

        return authorities;
    }
}
