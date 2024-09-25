package com.jeremias.shortenerurl.repositories;

import com.jeremias.shortenerurl.models.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShorterUrl(String url);
}
