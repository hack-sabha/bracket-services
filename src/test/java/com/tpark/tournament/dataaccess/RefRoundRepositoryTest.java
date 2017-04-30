package com.tpark.tournament.dataaccess;

import com.tpark.tournament.entity.Round;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


/**
 * Test for {@link TournamentRepository}.
 *
 * @author Swaroop Gaddameedhi
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan("com.sabha.bracket.entity")
@ContextConfiguration(classes = RefRoundRepository.class)
public class RefRoundRepositoryTest {
    @Autowired
    private RefRoundRepository refRoundRepository;

    @Test
    public void testFindByName() {
        Round expectedRound = new Round("Final");
        refRoundRepository.save(expectedRound);
        assertThat(expectedRound.getRoundId(), notNullValue());

        Collection<Round> results = refRoundRepository.findByRoundName("Final");
        assertEquals(1, results.size());
    }
}
