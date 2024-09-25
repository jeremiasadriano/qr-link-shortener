package com.jeremias.shortenerurl.repositories;

import com.jeremias.shortenerurl.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShorterUrl(String url);

    List<Url> findAllByExpirationTimeBefore(LocalDateTime now);
}
