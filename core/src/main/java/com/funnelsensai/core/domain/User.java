package com.funnelsensai.core.domain;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    // Remove username field since email will be used as the identifier.
    // private String username;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private Boolean isStripeAccountNonExpired;
    private Boolean isStripeAccountNonLocked;
    private Boolean isUserOwner;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // Adjusted constructor: only email is required.
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Now, getUsername() simply returns email.
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Other getters and setters for firstName, lastName, etc.
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getStripeAccountNonExpired() {
        return isStripeAccountNonExpired;
    }

    public void setStripeAccountNonExpired(Boolean stripeAccountNonExpired) {
        isStripeAccountNonExpired = stripeAccountNonExpired;
    }

    public Boolean getStripeAccountNonLocked() {
        return isStripeAccountNonLocked;
    }

    public void setStripeAccountNonLocked(Boolean stripeAccountNonLocked) {
        isStripeAccountNonLocked = stripeAccountNonLocked;
    }

    public Boolean getUserOwner() {
        return isUserOwner;
    }

    public void setUserOwner(Boolean userOwner) {
        isUserOwner = userOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

