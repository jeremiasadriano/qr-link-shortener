package com.jeremias.shortenerurl.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url-shortener")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "original_url", nullable = false)
    private String baseUrl;
    @Column(name = "short_url", unique = true, nullable = false)
    private String shorterUrl;
    @Column(unique = true, nullable = false)
    private String qrCode;
    private LocalDateTime expirationTime;

    @PrePersist
    public void setCreateTime() {
        this.expirationTime = LocalDateTime.now().plusMinutes(10L);
    }
}
