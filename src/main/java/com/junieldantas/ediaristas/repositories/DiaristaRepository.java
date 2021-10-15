package com.junieldantas.ediaristas.repositories;

import com.junieldantas.ediaristas.models.Diarista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaristaRepository extends JpaRepository<Diarista, Long> {

    Page<Diarista> findByCodigoIbge(String codigoIbge, Pageable pageable);
}
