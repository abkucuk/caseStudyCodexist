package com.ahmetburak.codexist.casestudy.controller;
import com.ahmetburak.codexist.casestudy.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {
    Optional<Search> findByLatitudeAndLongitudeAndRadius (String latitude, String longitude, String radius);
}
