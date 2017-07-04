package com.tpark.tournament.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.tpark.tournament.dataaccess.RefRoundRepository;
import com.tpark.tournament.entity.Round;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(RefRoundController.class)
public class RefRoundControllerTest {
    @InjectMocks
    RefRoundController refRoundController;

    @Mock
    RefRoundRepository refRoundRepository;

    @Test
    public void getRoundInfo() throws Exception {
        MockMvc mockMvc = standaloneSetup(refRoundController).build();
        when(refRoundRepository.findAll()).thenReturn(Lists.newArrayList(new Round("Final")));

        MockMvcResponse actualResponse = RestAssuredMockMvc.given().contentType(ContentType.JSON).mockMvc(mockMvc).when().get("/service/rounds").then().statusCode(200).extract().response();

        Assert.assertTrue(actualResponse.asString().contains("Final"));
    }
}