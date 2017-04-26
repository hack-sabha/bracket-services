package com.sabha.bracket.dataaccess;

import com.sabha.bracket.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Respository interface for 'ref_round' table.
 *
 * @author Swaroop Gaddameedhi
 */
public interface RefRoundRepository extends JpaRepository<Round, Long> {
}
