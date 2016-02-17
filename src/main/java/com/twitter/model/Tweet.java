package com.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String content;

    @NotNull
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User owner;

    @NotNull
    @DateTimeFormat(pattern = TwitterUtil.DATE_FORMAT)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = TwitterUtil.DATE_FORMAT)
    private Date tweetDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "tweetId"),
            inverseJoinColumns = @JoinColumn(name = "commentId"))
    private List<Tweet> comments;

    public Tweet() {
        this.comments = new ArrayList<>();
        this.tweetDate = Calendar.getInstance().getTime();
    }

    public Integer getId() {
        return id;
    }

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

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }

    public List<Tweet> getComments() {
        return comments;
    }

    public void setComments(List<Tweet> comments) {
        this.comments = comments;
    }


}
