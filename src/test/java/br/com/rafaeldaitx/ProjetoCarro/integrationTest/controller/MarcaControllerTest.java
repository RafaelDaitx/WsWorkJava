package br.com.rafaeldaitx.ProjetoCarro.integrationTest.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import br.com.rafaeldaitx.ProjetoCarro.data.MarcaDTO;
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
public class MarcaControllerTest {

    private MarcaDTO marcaDTO;

    private RequestSpecification specification;

    private ObjectMapper objectMapper;

    @BeforeAll
    void setupTests(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        marcaDTO = new MarcaDTO();


        specification = new RequestSpecBuilder()
                .setBasePath("/api/carros/marca")
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
                .body(marcaDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            MarcaDTO marca = objectMapper.readValue(content, MarcaDTO.class);
            marcaDTO = marca;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(marcaDTO.getNomeMarca());
        assertNotNull(marcaDTO.getIdMarca());


        assertEquals(1l, marcaDTO.getIdMarca());
        assertEquals("Volkswagen", marcaDTO.getNomeMarca());

    }

    @Test
    @Order(1)
    void testUpdate(){

        specification.basePath("/api/carros/marca/1");

        marcaDTO.setNomeMarca("Chevrolet");

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .body(marcaDTO)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            MarcaDTO marca = objectMapper.readValue(content, MarcaDTO.class);
            marcaDTO = marca;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(marcaDTO.getIdMarca());
        assertNotNull(marcaDTO.getNomeMarca());

        assertEquals(1l, marcaDTO.getIdMarca());
        assertEquals("Chevrolet", marcaDTO.getNomeMarca());

    }

    @Test
    @Order(2)
    void testFindViewById(){
        Long id = marcaDTO.getIdMarca();
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
            MarcaDTO marca = objectMapper.readValue(content, MarcaDTO.class);
            marcaDTO = marca;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(marcaDTO.getIdMarca());
        assertNotNull(marcaDTO.getNomeMarca());

        assertEquals(1l, marcaDTO.getIdMarca());
        assertEquals("Chevrolet", marcaDTO.getNomeMarca());
    }

    @Test
    @Order(3)
    void testFindViewAll() {
        specification.basePath("/api/carros/marca");

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
            List<MarcaDTO> marcas = objectMapper.readValue(content, new TypeReference<List<MarcaDTO>>() {});

            assertNotNull(marcas);
            assertFalse(marcas.isEmpty());

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao deserializar JSON", e);
        }
    }

    private void mockBrand(){
        marcaDTO.setIdMarca(1l);
        marcaDTO.setNomeMarca("Volkswagen");
    }
}
