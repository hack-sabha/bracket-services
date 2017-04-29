package com.sabha.bracket.dataaccess;

import com.sabha.bracket.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * JPA Respository interface for 'ref_round' table.
 *
 * @author Swaroop Gaddameedhi
 */
public interface RefRoundRepository extends JpaRepository<Round, Long> {
    public Collection<Round> findByRoundName(String roundName);
}
