package com.platform.agriwatch.domain.model;

import com.platform.agriwatch.application.dto.response.UserResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    private Integer age;
    private String gender;
    private String cellPhone;
    private Role role;

    private Integer numberPlants = 0;

    public UserResponse toUserResponse(){
        return UserResponse.builder()
                .id(this.id)
                .name(this.name)
                .lastName(this.lastName)
                .username(this.username)
                .age(this.age)
                .gender(this.gender)
                .cellPhone(this.cellPhone)
                .numberPlants(this.numberPlants)
                .build();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority((role.name()))
        );
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
