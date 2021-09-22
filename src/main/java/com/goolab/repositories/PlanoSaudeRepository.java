package com.goolab.repositories;

import com.goolab.models.PlanoSaude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoSaudeRepository extends JpaRepository<PlanoSaude, Long> {
}
