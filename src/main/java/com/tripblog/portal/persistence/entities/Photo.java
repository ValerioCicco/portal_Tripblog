package com.tripblog.portal.persistence.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "photo", columnDefinition = "MEDIUMBLOB")
    @Lob
    private byte[] photo;

    @ManyToOne()
    @JoinColumn(name = "id_trip", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trip trip;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
