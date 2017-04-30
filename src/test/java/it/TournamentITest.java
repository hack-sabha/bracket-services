package it;

import com.google.common.collect.Maps;
import com.tpark.tournament.application.TournamentServicesApplication;
import com.tpark.tournament.dataaccess.RefSportRepository;
import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Sport;
import com.tpark.tournament.entity.Tournament;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TournamentServicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TournamentITest {
    private static Logger LOGGER = Logger.getLogger(TournamentITest.class);
    @Autowired
    RefSportRepository refSportRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    private TestRestTemplate template;

    Map<Long, Tournament> tournamentByIdMap;

    @Before
    public void setup() {
        tournamentByIdMap = Maps.newHashMap();
        createTournament("Cricket");
    }

    @After
    public void tearDown() {
        deleteTournament(tournamentByIdMap.keySet());
    }

    @Test
    public void retrieveSingleTournament() {
        // end-to-end test
        ResponseEntity<String> response = template.getForEntity("/tournaments/search?term=TestBracket", String.class);
        LOGGER.info("------ RESPONSE ------\n" + response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody(), containsString("TestBracket"));
    }

    @Test
    public void retrieveMultipleTournaments() {
        createTournament("Tennis");
        // end-to-end test
        ResponseEntity<String> response = template.getForEntity("/tournaments/search?term=TestBracket", String.class);
        LOGGER.info("------ RESPONSE ------\n" + response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody(), containsString("TestBracketCricket"));
        assertThat(response.getBody(), containsString("TestBracketTennis"));
    }

    public void createTournament(String sportName) {
        // Get a Sport for testing.
        Collection<Sport> sports = refSportRepository.findByName(sportName);
        Sport sport = sports.iterator().next();

        // Creating a test Bracket object
        Tournament tournament = new Tournament("TestBracket" + sportName, 1, sport, 1);
        tournamentRepository.save(tournament);
        tournamentByIdMap.put(tournament.getTournamentId(), tournament);
    }

    public void deleteTournament(Collection<Long> tournamentIds) {
        LOGGER.info(" --- DELETING TOURNAMENTS ------ " + tournamentIds.toString());
        for (Long id : tournamentIds) {
            tournamentRepository.delete(id);
        }
        LOGGER.info("------ TOURNAMENTS in TOURNAMENT TABLE - " + tournamentRepository.findAll().size());
    }
}
