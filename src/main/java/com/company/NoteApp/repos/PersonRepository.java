package com.company.NoteApp.repos;

import com.company.NoteApp.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Note, Integer> {
}
