package com.twitter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.twitter.util.TwitterUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
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

    @JsonIgnore
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonFormat(pattern = TwitterUtil.DATE_FORMAT)
    public Date getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Date tweetDate) {
        this.tweetDate = tweetDate;
    }
}
