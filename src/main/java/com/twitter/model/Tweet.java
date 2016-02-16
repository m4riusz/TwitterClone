package com.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twitter.util.TwitterUtil;
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
@Component
public class Tweet {
    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Length(max = TwitterUtil.MAX_TWEET_LENGTH)
    private String content;

    @ManyToOne
    private User owner;

    @DateTimeFormat(pattern = TwitterUtil.DATE_FORMAT)
    @NotNull
    private Date tweetDate;

    public Tweet() {
    }

    public Tweet(String content, User owner) {
        this.comments = new ArrayList<>();
        this.tweetDate = Calendar.getInstance().getTime();
        this.content = content;
        this.owner = owner;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "tweetId"),
            inverseJoinColumns = @JoinColumn(name = "commentId"))
    private List<Tweet> comments;

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    @JsonIgnore
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonFormat(pattern = TwitterUtil.DATE_FORMAT)
    public Date getTweetDate() {
        return tweetDate;
    }

    @JsonIgnore
    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    public List<Tweet> getComments() {
        return comments;
    }

    @JsonIgnore
    public void setComments(List<Tweet> comments) {
        this.comments = comments;
    }
}
