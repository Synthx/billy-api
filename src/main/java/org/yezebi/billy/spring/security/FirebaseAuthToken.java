package org.yezebi.billy.spring.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class FirebaseAuthToken extends AbstractAuthenticationToken {
    private Object principal;
    private Object credentials;

    public FirebaseAuthToken(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(false);
    }

    public FirebaseAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public Object getCredentials() {
        return this.credentials;
    }
}
