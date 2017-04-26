package com.sabha.bracket.dataaccess;

import com.sabha.bracket.entity.Bracket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Respository interface for 'brackets' table.
 *
 * @author Swaroop Gaddameedhi
 */
public interface BracketRepository extends JpaRepository<Bracket, Long> {
}
