package tn.esprit.gestiondesformations.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    FORMATEUR,
    PARTICIPANT,
    EQUIPE_DSI;

    @Override
    public String getAuthority() {
        return name();
    }
}
