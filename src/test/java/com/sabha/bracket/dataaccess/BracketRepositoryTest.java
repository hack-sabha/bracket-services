package com.sabha.bracket.dataaccess;

import com.sabha.bracket.entity.Bracket;
import com.sabha.bracket.entity.Sport;
import org.junit.Ignore;
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
 * Test for {@link BracketRepository}.
 *
 * @author Swaroop Gaddameedhi
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan("com.sabha.bracket.entity")
@ContextConfiguration(classes = {BracketRepository.class, RefRoundRepository.class})
public class BracketRepositoryTest {
    @Autowired
    private BracketRepository bracketRepository;

    @Ignore
    @Test
    public void testFindByName() {
        Sport expectedSport = new Sport("TestSport");
        Bracket expectedBracket = new Bracket("TestBracket", 0, expectedSport, 0);
        bracketRepository.save(expectedBracket);
        assertThat(expectedBracket.getBracketId(), notNullValue());

        Collection<Bracket> searchResults = bracketRepository.findByName("TestBracket");
        assertEquals(1, searchResults.size());
    }
}
