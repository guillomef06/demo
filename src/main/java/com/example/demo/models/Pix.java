package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "Pix")
@Table(name = "pix")
public class Pix {

    /* Using a sequence for postgresql, if we leave the generationType on AUTO Hibernate
    will guess the generationType for us depending on the database we use */
    @Id
    @SequenceGenerator(
            name = "pix_sequence",
            sequenceName = "pix_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pix_sequence"
    )
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "caption",
            columnDefinition = "TEXT"
    )
    private String caption;

    @Column(
            name = "location",
            columnDefinition = "TEXT"
    )
    private String location;

    @Column(
            name = "image_url",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String imageUrl;

    private Date creationDate;
    private Integer likes;

    private Pix() {}

    public Pix(Client client, String title, String caption, String location, String imageUrl) {
        this.client = client;
        this.title = title;
        this.caption = caption;
        this.location = location;
        this.imageUrl = imageUrl;
        this.likes = 0;
        this.creationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
