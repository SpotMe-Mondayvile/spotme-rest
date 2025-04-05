package com.mts.spotmerest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="MATCHES")
public class Match {
    @Id
    @SequenceGenerator(
            name="match_sequence",
            sequenceName="match_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "match_sequence"
    )
    private Long id;
    private Long authorId;
    private Long attendeeId;
    private String status;
    private String authorResponse;
    private String attendeeResponse;


    public Match() {
    }

    public Match(Long id, Long authorId, Long attendeeId, String status, String authorResponse, String attendeeResponse) {
        this.id = id;
        this.authorId = authorId;
        this.attendeeId = attendeeId;
        this.status = status;
        this.authorResponse = authorResponse;
        this.attendeeResponse = attendeeResponse;
    }
    public Match(Long id, Long authorId, Long attendeeId) {
        this.id = id;
        this.authorId = authorId;
        this.attendeeId = attendeeId;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(Long attendeeId) {
        this.attendeeId = attendeeId;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthorResponse() {
        return authorResponse;
    }

    public void setAuthorResponse(String authorResponse) {
        this.authorResponse = authorResponse;
    }

    public String getAttendeeResponse() {
        return attendeeResponse;
    }

    public void setAttendeeResponse(String attendeeResponse) {
        this.attendeeResponse = attendeeResponse;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name ="spot_id")
    private Spot spot;
}
