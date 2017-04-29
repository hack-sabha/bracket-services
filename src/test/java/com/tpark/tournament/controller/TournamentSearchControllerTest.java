package com.tpark.tournament.controller;

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.assertj.core.util.Lists;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.tpark.tournament.dataaccess.TournamentRepository;
import com.tpark.tournament.entity.Sport;
import com.tpark.tournament.entity.Tournament;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(TournamentSearchController.class)
@ContextConfiguration(classes = TournamentSearchController.class)
public class TournamentSearchControllerTest {

    @InjectMocks
    TournamentSearchController tournamentSearchController;

    @Mock
    TournamentRepository tournamentRepository;

    @Mock
    Sport sport;

    @Ignore
    @Test
    public void searchBracketByTerm() throws Exception {
        when(sport.getName()).thenReturn("Cricket");
        Tournament mockBracket = new Tournament("CrickBrack", 0, sport, 0);

        MockMvc mockMvc = standaloneSetup(tournamentSearchController).build();
        when(tournamentRepository.findAll()).thenReturn(Lists.newArrayList(mockBracket));

        RestAssuredMockMvc.given().mockMvc(mockMvc).param("term", "CrickBrack").contentType(MediaType.TEXT_PLAIN_VALUE).when().get("/brackets/search").then().body("name", contains("CrickBrack"));
    }

}