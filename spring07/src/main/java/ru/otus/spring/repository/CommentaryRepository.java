package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
}
