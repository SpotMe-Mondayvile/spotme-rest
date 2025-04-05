package com.mts.spotmerest.auth;

import com.mts.spotmerest.models.User;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Authority {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.username")
    private User user;

    @NotNull
    private String authority;
}