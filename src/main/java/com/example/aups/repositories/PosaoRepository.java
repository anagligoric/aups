package com.example.aups.repositories;

import com.example.aups.models.Posao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosaoRepository extends JpaRepository<Posao, Long> {
}
