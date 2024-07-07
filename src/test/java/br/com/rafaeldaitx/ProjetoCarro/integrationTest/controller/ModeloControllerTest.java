package br.com.rafaeldaitx.ProjetoCarro.integrationTest.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.ModeloDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModeloControllerTest {

    private ModeloDTO modeloDTO;

    private RequestSpecification specification;

    private ObjectMapper objectMapper;

    @BeforeAll
    void setupTests(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        modeloDTO = new ModeloDTO();


        specification = new RequestSpecBuilder()
                .setBasePath("/api/carros/modelo")
                .setPort(8080)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(0)
    void testCreate(){
        mockBrand();

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .body(modeloDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            ModeloDTO modelo = objectMapper.readValue(content, ModeloDTO.class);
            modeloDTO = modelo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(modeloDTO.getId());
        assertNotNull(modeloDTO.getMarca_id());
        assertNotNull(modeloDTO.getNome());
        assertNotNull(modeloDTO.getValor_fipe());

        assertEquals(1l, modeloDTO.getId());
        assertEquals(1l, modeloDTO.getMarca_id());
        assertEquals("JETTA", modeloDTO.getNome());
        assertEquals(40.000, modeloDTO.getValor_fipe());

    }

    @Test
    @Order(1)
    void testUpdate(){

        specification.basePath("/api/carros/modelo/1");

        modeloDTO.setNome("Chevrolet");

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .body(modeloDTO)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            ModeloDTO modelo = objectMapper.readValue(content, ModeloDTO.class);
            modeloDTO = modelo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(modeloDTO.getId());
        assertNotNull(modeloDTO.getMarca_id());
        assertNotNull(modeloDTO.getNome());
        assertNotNull(modeloDTO.getValor_fipe());

        assertEquals(1l, modeloDTO.getId());
        assertEquals(1l, modeloDTO.getMarca_id());
        assertEquals("Chevrolet", modeloDTO.getNome());
        assertEquals(40.000, modeloDTO.getValor_fipe());

    }

    @Test
    @Order(2)
    void testFindViewById(){
        Long id = modeloDTO.getId();
        specification.basePath("/api/carros/marca/{id}");

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .pathParam("id", 1l)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            ModeloDTO modelo = objectMapper.readValue(content, ModeloDTO.class);
            modeloDTO = modelo;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(modeloDTO.getId());
        assertNotNull(modeloDTO.getMarca_id());
        assertNotNull(modeloDTO.getNome());
        assertNotNull(modeloDTO.getValor_fipe());

        assertEquals(1l, modeloDTO.getId());
        assertEquals(1l, modeloDTO.getMarca_id());
        assertEquals("Chevrolet", modeloDTO.getNome());
        assertEquals(40.000, modeloDTO.getValor_fipe());
    }

    @Test
    @Order(3)
    void testFindViewAll() {
        specification.basePath("/api/carros/modelo");

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            List<ModeloDTO> modelos = objectMapper.readValue(content, new TypeReference<List<ModeloDTO>>() {});

            assertNotNull(modelos);
            assertFalse(modelos.isEmpty());

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar JSON", e);
        }
    }

    private void mockBrand(){
        modeloDTO.setId(1l);
        modeloDTO.setMarca_id(1l);
        modeloDTO.setNome("JETTA");
        modeloDTO.setValor_fipe(40.000);
    }
}
