package com.bestapp.ordersapp.security.model;

import com.bestapp.ordersapp.authentication.model.persitance.Role;
import com.bestapp.ordersapp.authentication.model.persitance.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CurrentUser implements UserDetails {
    private Long id;

    private String email;
    private String password;
    private Set<SimpleGrantedAuthority> authorities;

    public CurrentUser(Long id, String email, String password, Role role){
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = new HashSet<SimpleGrantedAuthority>();
        this.authorities.add(new SimpleGrantedAuthority(role.name()));
    }

    public static CurrentUser create(UserEntity user){
        return new CurrentUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentUser that = (CurrentUser) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword());
    }


}
