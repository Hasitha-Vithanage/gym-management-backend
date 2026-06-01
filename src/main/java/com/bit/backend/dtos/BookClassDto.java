package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class BookClassDto {

    private long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private long classId;
    private String bookedBy;
    private String status;
    private LocalDate bookedDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private byte[] image;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String imageType;

    public BookClassDto() {
        // Required for deserialization
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public long getClassId() { return classId; }
    public void setClassId(long classId) { this.classId = classId; }

    public String getBookedBy() { return bookedBy; }
    public void setBookedBy(String bookedBy) { this.bookedBy = bookedBy; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getBookedDate() { return bookedDate; }
    public void setBookedDate(LocalDate bookedDate) { this.bookedDate = bookedDate; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImageType() { return imageType; }
    public void setImageType(String imageType) { this.imageType = imageType; }
}
