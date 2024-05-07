package com.devs.demoCours.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder()
@Entity(name = "estudiante")
public class EstudianteEntity extends Persona implements Serializable, UserDetails {
    @Column(name = "fecha_inscripcion", nullable = false, length = 10)
    private LocalDate fechaIncripcion;
    @Column(name = "resenia", length = 100)
    private String resenia;

    /*
    relacion con la entidad Inscripcion
     */
    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<InscripcionEntity> inscripcionEntityList;

    /*relation with roles*/
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "estudentRoles",
            joinColumns = @JoinColumn(name = "estudentId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"estudentId", "roleId"})})
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
