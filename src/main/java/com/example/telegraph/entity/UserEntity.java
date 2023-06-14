package com.example.telegraph.entity;

import com.example.telegraph.entity.enums.Role;
import com.example.telegraph.entity.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseEntity implements UserDetails {
    private String name;

    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private List<Role>userRoles;
    @Enumerated(EnumType.STRING)
    private UserStatus hasBlocked;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_";
        List<SimpleGrantedAuthority>authorities=new ArrayList<>();
        for (Role roles:userRoles){
            authorities.add(new SimpleGrantedAuthority(role+roles.name()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
