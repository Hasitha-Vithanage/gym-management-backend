package com.bit.backend.dtos;

import java.time.LocalDate;

public class FeedbackDto {

    private long id;
    private String category;
    private String trainer;
    private int rating;
    private boolean anonymous;
    private String feedback;
    private LocalDate date;
    private String username;

    public FeedbackDto() {
    }

    public FeedbackDto(long id, String category, String trainer, int rating, boolean anonymous, String feedback, LocalDate date, String username) {
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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
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
