package com.mveril.quiz.repository;
import com.mveril.quiz.business.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
