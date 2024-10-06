package com.tienpv.petcare.infrastructure.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tienpv.petcare.application.dto.request.PetRequest;
import com.tienpv.petcare.application.dto.response.PetResponse;
import com.tienpv.petcare.domain.service.IPetSerivce;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc // nhan request
public class PetControllerTest {

    @Autowired
    private MockMvc mockMvc; // gọi tới API

    @MockBean
    private IPetSerivce petSerivce;
    private PetRequest request;
    private PetResponse response;

    @BeforeEach
    void initData(){
        request = PetRequest.builder()
                .name("Phu")
                .gender("nam")
                .breed("canh")
                .species("cho")
                .birthdate("16-4-2005")
                .wellness("bad")
                .build();

        response = PetResponse.builder()
                .id(4L)
                .name("Phu")
                .gender("nam")
                .breed("canh")
                .species("cho")
                .birthdate("16-4-2005")
                .wellness("bad")
                .build();
    }
    @Test
    void createPet_validRequest_success() throws Exception {
        //Given : Dữ liệudđược biết trước, biết no sẽ xảy ra như vậy
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);
        Mockito.when(petSerivce.createPet(ArgumentMatchers.any())).thenReturn(response);
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJkZXYuY29tIiwic3ViIjoicGh1bmd1eWVuIiwiZXhwIjoxNzI4MTk4NzU4LCJpYXQiOjE3MjgxOTUxNTgsImp0aSI6IjIyZjMxY2UxLTI4NmYtNGQ0ZS05NjFkLThhYWYyY2ZjYmMzNCIsInNjb3BlIjoiUk9MRV9VU0VSIn0.tm15bC0rcWmaZqKb7vwkyQyzQNgqK2fyukLA4A6nsG4BYn0ySmpWcouVGLGgcs4tXa_G85UFpqPdnT7f6cQR3g";
        //When: test cái gì
         mockMvc.perform(MockMvcRequestBuilders
                 .post("/api/pets")
                         .header("Authorization", "Bearer " + jwtToken)
                 .contentType(MediaType.APPLICATION_JSON_VALUE)
                 .content(content))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("code").value(200)
         );
    }
}
