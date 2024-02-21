package com.bananaapps.bananamusic.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(1)
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    @Email
    private String email;

    @Column(nullable = false, length = 64)
    @Size(min = 8, max = 12)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotBlank
    private ERole role;

    public User(Integer id) {
        this.id = id;
    }


}