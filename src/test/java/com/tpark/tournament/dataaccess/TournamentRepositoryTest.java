package com.tpark.tournament.dataaccess;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.tpark.tournament.entity.Sport;
import com.tpark.tournament.entity.Tournament;

/**
 * Test for {@link com.tpark.tournament.dataaccess.TournamentRepository}.
 *
 * @author Swaroop Gaddameedhi
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan("com.tpark.tournament.entity")
@ContextConfiguration(classes = { TournamentRepository.class, RefRoundRepository.class })
public class TournamentRepositoryTest {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Ignore
    @Test
    public void testFindByName() {
        Sport expectedSport = new Sport("TestSport");
        Tournament expectedTournament = new Tournament("TestTournament", 0, expectedSport, 0);
        tournamentRepository.save(expectedTournament);
        assertThat(expectedTournament.getId(), notNullValue());

        Collection<Tournament> searchResults = tournamentRepository.findByNameIgnoreCaseContaining("TestTournament");
        assertEquals(1, searchResults.size());
    }
}
