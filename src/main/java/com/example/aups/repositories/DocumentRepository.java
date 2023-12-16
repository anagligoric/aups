package com.example.aups.repositories;

import com.example.aups.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByJob_User_Email(String email);
}
