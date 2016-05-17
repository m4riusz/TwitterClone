package com.twitter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twitter.util.TwitterUtil;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "users")
@Component
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean enable;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<Tweet> tweets;

    @NotNull
    @DateTimeFormat(pattern = TwitterUtil.DATE_FORMAT)
    @JsonFormat(pattern = TwitterUtil.DATE_FORMAT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createDate;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinTable(joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "followerId"))
    private List<User> followers;

    public User() {
        tweets = new ArrayList<>();
        followers = new ArrayList<>();
        role = Role.USER;
        enable = true;
        createDate = Calendar.getInstance().getTime();
    }

    public User(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(role.getRoleName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
