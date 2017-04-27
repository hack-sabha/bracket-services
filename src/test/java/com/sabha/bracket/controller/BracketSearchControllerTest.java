package com.sabha.bracket.controller;

import com.google.common.collect.Lists;
import com.sabha.bracket.entity.Bracket;
import com.sabha.bracket.entity.Sport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BracketSearchController.class)
@ContextConfiguration(classes = BracketSearchController.class)
public class BracketSearchControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BracketSearchController bracketSearchController;

    @Mock
    Sport sport;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bracketSearchController).build();
    }

    //@Test
    public void searchBracketByTerm() throws Exception {
        given(this.bracketSearchController.search("Foo"))
            .willReturn(Lists.newArrayList(new Bracket("Foo", 0, sport, 0)));

        this.mockMvc.perform(get("/brackets/search?term=Foo").accept(MediaType.ALL))
            .andExpect(status().is5xxServerError())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
}