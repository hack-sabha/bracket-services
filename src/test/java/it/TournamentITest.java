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
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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

    // TODO This works in local but fails on Travis :(
    Collection<Long> tournamentIds = new ArrayList<>(1);

    @Before
    public void setup() {
        createTournament("Cricket");
    }

    @After
    public void tearDown() {
        deleteTestTournaments();
    }

    @Test
    public void retrieveSingleTournament() {
        // end-to-end test
        ResponseEntity<String> response = template.getForEntity("/service/tournaments/search?term=TestTournament", String.class);
        LOGGER.info("------ RESPONSE ------\n" + response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody(), containsString("TestTournament"));
    }

    @Test
    public void retrieveMultipleTournaments() {
        createTournament("Tennis");
        // end-to-end test
        ResponseEntity<String> response = template.getForEntity("/service/tournaments/search?term=TestTournament", String.class);
        LOGGER.info("------ RESPONSE ------\n" + response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody(), containsString("TestTournamentCricket"));
        assertThat(response.getBody(), containsString("TestTournamentTennis"));
    }

    @Test
    public void retrieveAllTournaments() {
        // end-to-end test
        ResponseEntity<String> response = template.getForEntity("/service/tournaments/all", String.class);
        LOGGER.info("------ RESPONSE ------\n" + response);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    public void createTournament(String sportName) {
        // Get a Sport for testing.
        Collection<Sport> sports = refSportRepository.findByName(sportName);
        Sport sport = sports.iterator().next();

        // Creating a test Tournament object
        Tournament tournament = new Tournament("TestTournament" + sportName, 1, sport, 1);
        tournamentRepository.save(tournament);
        System.out.println(tournament.toString());
        tournamentIds.add(tournament.getId());
    }

    public void deleteTestTournaments() {
        LOGGER.info(" --- DELETING TOURNAMENTS ------ ");
        for (Long id : tournamentIds) {
            tournamentRepository.delete(id);
        }
        LOGGER.info("------ TOURNAMENTS in TOURNAMENT TABLE - " + tournamentRepository.findAll().size());
    }
}
