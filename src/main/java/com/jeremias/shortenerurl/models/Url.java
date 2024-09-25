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
    private String baseUrl;
    private String shorterUrl;
    private String qrCode;
    private LocalDateTime expirationTime;

    @PrePersist
    public void setCreateTime() {
        this.expirationTime = LocalDateTime.now().plusMinutes(10L);
    }
}
