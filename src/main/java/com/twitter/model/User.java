package com.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.twitter.util.TwitterUtil;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Length(min = TwitterUtil.MinLoginLength, max = TwitterUtil.MaxLoginLength)
    @Column(unique = true)
    private String username;

    @NotNull
    @Length(min = TwitterUtil.MinPasswordLength, max = TwitterUtil.MaxPasswordLength)
    @JsonIgnore
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private Role role;

    @NotNull
    private Boolean enable;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Tweet> tweets;

    @NotNull
    @DateTimeFormat(pattern = TwitterUtil.DATE_FORMAT)
    private Date createDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
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

    @JsonFormat(pattern = TwitterUtil.DATE_FORMAT)
    public Date getCreateDate() {
        return createDate;
    }

    @JsonIgnore
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Boolean getEnable() {
        return enable;
    }

    @JsonIgnore
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<User> getFollowers() {
        return followers;
    }

    @JsonIgnore
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    @JsonIgnore
    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
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

    @JsonIgnore
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!username.equals(user.username)) return false;
        return email.equals(user.email);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }


}
