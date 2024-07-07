package br.com.rafaeldaitx.ProjetoCarro.integrationTest.controller;

import br.com.rafaeldaitx.ProjetoCarro.data.CarroDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarroControllerTest {

    private CarroDTO carroDTO;

    private RequestSpecification specification;

    private ObjectMapper objectMapper;

    @BeforeAll
    void setupTests(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        carroDTO = new CarroDTO();


        specification = new RequestSpecBuilder()
                .setBasePath("/api/carros")
                .setPort(8080)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();
    }

    @Test
    @Order(0)
    void testCreate(){
        mockCar();

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .body(carroDTO)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            CarroDTO carro = objectMapper.readValue(content, CarroDTO.class);
            carroDTO = carro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(carroDTO.getId() > 0);
        assertNotNull(carroDTO.getTimestamp_cadastro());
        assertNotNull(carroDTO.getModelo_id());
        assertNotNull(carroDTO.getCombustivel());
        assertNotNull(carroDTO.getAno());
        assertNotNull(carroDTO.getNum_portas());
        assertNotNull(carroDTO.getCor());
        assertNotNull(carroDTO.getNome_modelo());
        assertNotNull(carroDTO.getValor());

        assertEquals(1696539488, carroDTO.getTimestamp_cadastro());
        assertEquals(1l, carroDTO.getModelo_id());
        assertEquals("FLEX", carroDTO.getCombustivel());
        assertEquals(2015, carroDTO.getAno());
        assertEquals(4, carroDTO.getNum_portas());
        assertEquals("BEGE", carroDTO.getCor());
        assertEquals("ONIX PLUS", carroDTO.getNome_modelo());
        assertEquals(50.00, carroDTO.getValor());
    }

    @Test
    @Order(1)
    void testUpdate(){

        specification.basePath("/api/carros/1");

        carroDTO.setId(1l);
        carroDTO.setTimestamp_cadastro(1696539488L);
        carroDTO.setModelo_id(2L);
        carroDTO.setAno(2016);
        carroDTO.setCombustivel("FLEXX");
        carroDTO.setNum_portas(2);
        carroDTO.setCor("BEGEE");
        carroDTO.setNome_modelo("ONIX PLUSS");
        carroDTO.setValor(500.00);

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .body(carroDTO)
                .when()
                .put()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            CarroDTO carro = objectMapper.readValue(content, CarroDTO.class);
            carroDTO = carro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(carroDTO.getId() > 0);
        assertNotNull(carroDTO.getTimestamp_cadastro());
        assertNotNull(carroDTO.getModelo_id());
        assertNotNull(carroDTO.getCombustivel());
        assertNotNull(carroDTO.getAno());
        assertNotNull(carroDTO.getNum_portas());
        assertNotNull(carroDTO.getCor());
        assertNotNull(carroDTO.getNome_modelo());
        assertNotNull(carroDTO.getValor());


        assertEquals(2l, carroDTO.getModelo_id());
        assertEquals("FLEXX", carroDTO.getCombustivel());
        assertEquals(2016, carroDTO.getAno());
        assertEquals(2, carroDTO.getNum_portas());
        assertEquals("BEGEE", carroDTO.getCor());
        assertEquals("ONIX PLUSS", carroDTO.getNome_modelo());
        assertEquals(500.00, carroDTO.getValor());
        assertEquals(1696539488, carroDTO.getTimestamp_cadastro());

    }

    @Test
    @Order(2)
    void testFindViewById(){

        String content = given()
                .spec(specification)
                .contentType("application/json")
                .pathParam("id", carroDTO.getId())
                .when()
                .get("{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        try {
            CarroDTO carro = objectMapper.readValue(content, CarroDTO.class);
            carroDTO = carro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(carroDTO.getId() > 0);
        assertNotNull(carroDTO.getTimestamp_cadastro());
        assertNotNull(carroDTO.getModelo_id());
        assertNotNull(carroDTO.getCombustivel());
        assertNotNull(carroDTO.getAno());
        assertNotNull(carroDTO.getNum_portas());
        assertNotNull(carroDTO.getCor());
        assertNotNull(carroDTO.getNome_modelo());
        assertNotNull(carroDTO.getValor());

        assertEquals(1696539488, carroDTO.getTimestamp_cadastro());
        assertEquals(2l, carroDTO.getModelo_id());
        assertEquals("FLEXX", carroDTO.getCombustivel());
        assertEquals(2016, carroDTO.getAno());
        assertEquals(2, carroDTO.getNum_portas());
        assertEquals("BEGEE", carroDTO.getCor());
        assertEquals("ONIX PLUSS", carroDTO.getNome_modelo());
        assertEquals(500.00, carroDTO.getValor());
    }

    @Test
    @Order(3)
    void testFindViewAll(){
        specification.basePath("/api/carros");
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
            CarroDTO carro = objectMapper.readValue(content, CarroDTO.class);
            carroDTO = carro;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        assertNotNull(carroDTO.getId());
        assertNotNull(carroDTO.getTimestamp_cadastro());
        assertNotNull(carroDTO.getModelo_id());
        assertNotNull(carroDTO.getCombustivel());
        assertNotNull(carroDTO.getAno());
        assertNotNull(carroDTO.getNum_portas());
        assertNotNull(carroDTO.getCor());
        assertNotNull(carroDTO.getNome_modelo());
        assertNotNull(carroDTO.getValor());
    }

    private void mockCar(){
        carroDTO.setId(1l);
        carroDTO.setTimestamp_cadastro(1696539488L);
        carroDTO.setModelo_id(1L);
        carroDTO.setAno(2015);
        carroDTO.setCombustivel("FLEX");
        carroDTO.setNum_portas(4);
        carroDTO.setCor("BEGE");
        carroDTO.setNome_modelo("ONIX PLUS");
        carroDTO.setValor(50.00);
    }
}
