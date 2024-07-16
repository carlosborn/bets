package com.bets.App.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id_platform")
    private Long userIdPlatform;

    @Column(name = "id_str")
    private String idStr;

    @Column(name = "username")
    private String username;

    @Column(name = "rank")
    private String rank;

    @CreatedDate
    private Date createdAt;
}