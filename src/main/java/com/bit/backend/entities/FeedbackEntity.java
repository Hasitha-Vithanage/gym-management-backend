package com.bit.backend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rating-feedback")
public class FeedbackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category")
    private String category;

    @Column(name = "trainer")
    private String trainer;

    @Column(name = "rating")
    private int rating;

    @Column(name = "anonymous")
    private Boolean anonymous;

    @Column(name = "feedback")
    private String feedback;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "username")
    private String username;

    public FeedbackEntity() {
    }

    public FeedbackEntity(long id, String category, String trainer, int rating, Boolean anonymous, String feedback, LocalDate date, String username) {
        this.id = id;
        this.category = category;
        this.trainer = trainer;
        this.rating = rating;
        this.anonymous = anonymous;
        this.feedback = feedback;
        this.date = date;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
