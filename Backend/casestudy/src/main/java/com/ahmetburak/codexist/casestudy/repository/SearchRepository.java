package com.ahmetburak.codexist.casestudy.repository;
import com.ahmetburak.codexist.casestudy.model.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SearchRepository extends JpaRepository<Search, Long> {
    @Query("select s from Search s where s.latitude = :latitude and s.longitude = :longitude and s.radius = :radius  ")
    Optional<Search> findByLatitudeAndLongitudeAndRadius ( @Param("latitude") String latitude, @Param("longitude") String longitude,  @Param("radius") String radius);
}
