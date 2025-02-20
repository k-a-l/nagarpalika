package com.nagarpalika.repository;

import com.nagarpalika.model.ApplicationStatus;
import com.nagarpalika.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

}
