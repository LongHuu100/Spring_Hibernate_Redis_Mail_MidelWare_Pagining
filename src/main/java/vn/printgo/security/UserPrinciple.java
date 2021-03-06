package vn.printgo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.printgo.model.User;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String name;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;
    
    public UserPrinciple(Integer id, String firstName, String username) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
    }
    
    public UserPrinciple(Integer id, String firstName, String name,
            String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {
        
        List<GrantedAuthority> authorities = user.getUserProfiles().stream().map(role
                -> new SimpleGrantedAuthority(role.getType())
        ).collect(Collectors.toList());

        return new UserPrinciple(
        		user.getId(),
        		user.getFirstName(),
        		user.getLastName(),
        		user.getEmail(),
        		user.getPassword(),
                authorities
        );
    }
    
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        
        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
