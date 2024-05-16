package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "docente")
@SuperBuilder()
public class DocenteEntity extends Persona implements Serializable, UserDetails {
    @Column(name = "especialidad_docente", length = 40)
    private String especialidad;
    @Column(name = "review", length = 500)
    private String review;
    private boolean activo;
    /*
    Relación con la entidad Sesión
     */
    @OneToMany(mappedBy = "docente",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnore
    private List<SessionEntity> sessionEntityList;

    /*relation with roles*/
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinTable(name = "docentsRoles",
            joinColumns =@JoinColumn(name="docenteId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"docenteId","roleId"})})
    private List<RoleEntity> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
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
