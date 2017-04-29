package com.sabha.bracket.controller;


import com.sabha.bracket.dataaccess.BracketRepository;
import com.sabha.bracket.entity.Bracket;
import com.sabha.bracket.entity.Sport;
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

import static org.hamcrest.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(BracketSearchController.class)
@ContextConfiguration(classes = BracketSearchController.class)
public class BracketSearchControllerTest {

    @InjectMocks
    BracketSearchController bracketSearchController;

    @Mock
    BracketRepository bracketRepository;

    @Mock
    Sport sport;

    @Ignore
    @Test
    public void searchBracketByTerm() throws Exception {
        when(sport.getName()).thenReturn("Cricket");
        Bracket mockBracket = new Bracket("CrickBrack", 0, sport, 0);

        MockMvc mockMvc = standaloneSetup(bracketSearchController).build();
        when(bracketRepository.findAll()).thenReturn(Lists.newArrayList(mockBracket));

        RestAssuredMockMvc.given().
            mockMvc(mockMvc).
            param("term", "CrickBrack").
            contentType(MediaType.TEXT_PLAIN_VALUE).
            when().
            get("/brackets/search").
            then().
            body("name", contains("CrickBrack"));
    }


}