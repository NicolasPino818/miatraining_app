package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String surname;

    //254 caracteres es el límite máximo establecido por el estándar de RFC para direcciones de correo electrónico
    //https://www.rfc-editor.org/rfc/rfc5321#section-4.5.3
    @Column(length = 254, unique = true, nullable = false)
    private String email;

    //Spring añade un length por defecto de 255 caracteres, por lo que es redundante añadirlo
    //No todos los usuarios pueden hacer login o tener contraseña, por lo tanto, queda nullable
    private String password;

    //Usuario con permiso de login activo
    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean firstLogin;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @OneToOne(mappedBy = "user")
    private ForgotPasswordModel forgotPassword;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserPlanModel> userPlan;

    @ManyToOne()
    @JoinColumn(name="roleID", nullable=false)
    private RoleModel role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return enabled;
    }
}
