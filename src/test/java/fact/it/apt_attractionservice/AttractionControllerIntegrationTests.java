package fact.it.apt_attractionservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.apt_attractionservice.model.Attraction;
import fact.it.apt_attractionservice.repository.AttractionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AttractionControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AttractionRepository attractionRepository;

    private Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?", 125, 1, "156545", "A001");
    private Attraction attractionType2Themepark1 = new Attraction(2, "Attractie 2", "Neem met de hele familie plaats in een boomstammetje en ontdek de jungle in Indiana River. Trotseer de vele bergen en dalen en bereid je voor om nat te worden!", 100, 2, "156545", "A002");
    private Attraction attractionType1Themepark2 = new Attraction(3, "Attractie 3", "Nietsvermoedend 77 m kaarsrecht omhoog, genietend van de frisse lucht, van steil gesproken... Alles rondom jou wordt steeds kleiner", 120, 1, "156554", "A003");
    private Attraction attractionToBeDeleted = new Attraction(4, "Verwijder mij", "Deze attractie wordt gebruikt om het verwijderen van een attractie te testen", 120, 1, "156545", "A004");

    @BeforeEach
    public void beforeAllTests() {
        attractionRepository.deleteAll();
        attractionRepository.save(attractionType1Themepark1);
        attractionRepository.save(attractionType2Themepark1);
        attractionRepository.save(attractionType1Themepark2);
        attractionRepository.save(attractionToBeDeleted);
    }

    @AfterEach
    public void afterAllTests() {
        attractionRepository.deleteAll();
    }

    // Used to convert objects to JSON strings
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenAttraction_whenGetAttractionByAttractionCode_thenReturnJsonAttraction() throws Exception {
        mockMvc.perform(get("/api/attractions/{attractionCode}", "A001"))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // Result matching the values of attraction A001
                .andExpect(jsonPath("$.name", is("Attractie 1")))
                .andExpect(jsonPath("$.description", is("Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?")))
                .andExpect(jsonPath("$.minHeight", is(125)))
                .andExpect(jsonPath("$.typeId", is(1)))
                .andExpect(jsonPath("$.themeparkCode", is("156545")))
                .andExpect(jsonPath("$.attractionCode", is("A001")));
    }

    @Test
    public void givenAttraction_whenGetAttractionsByNameAndThemeparkCode_thenReturnJsonAttraction() throws Exception {
        mockMvc.perform(get("/api/attractions/name/{name}/themepark/{themeparkCode}", "Attractie", "156545"))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // 2 Attractions
                .andExpect(jsonPath("$", hasSize(2)))
                // Results matching the values of attraction A001 and A002
                .andExpect(jsonPath("$[0].name", is("Attractie 1")))
                .andExpect(jsonPath("$[0].description", is("Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?")))
                .andExpect(jsonPath("$[0].minHeight", is(125)))
                .andExpect(jsonPath("$[0].typeId", is(1)))
                .andExpect(jsonPath("$[0].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[0].attractionCode", is("A001")))
                .andExpect(jsonPath("$[1].name", is("Attractie 2")))
                .andExpect(jsonPath("$[1].description", is("Neem met de hele familie plaats in een boomstammetje en ontdek de jungle in Indiana River. Trotseer de vele bergen en dalen en bereid je voor om nat te worden!")))
                .andExpect(jsonPath("$[1].minHeight", is(100)))
                .andExpect(jsonPath("$[1].typeId", is(2)))
                .andExpect(jsonPath("$[1].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[1].attractionCode", is("A002")));
    }

    @Test
    public void givenAttraction_whenGetAttractionsByTypeId_thenReturnJsonAttraction() throws Exception {
        mockMvc.perform(get("/api/attractions/type/{typeId}", 1))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // 3 Attractions
                .andExpect(jsonPath("$", hasSize(3)))
                // Results matching the values of attraction A001, A003 and A004
                .andExpect(jsonPath("$[0].name", is("Attractie 1")))
                .andExpect(jsonPath("$[0].description", is("Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?")))
                .andExpect(jsonPath("$[0].minHeight", is(125)))
                .andExpect(jsonPath("$[0].typeId", is(1)))
                .andExpect(jsonPath("$[0].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[0].attractionCode", is("A001")))
                .andExpect(jsonPath("$[1].name", is("Attractie 3")))
                .andExpect(jsonPath("$[1].description", is("Nietsvermoedend 77 m kaarsrecht omhoog, genietend van de frisse lucht, van steil gesproken... Alles rondom jou wordt steeds kleiner")))
                .andExpect(jsonPath("$[1].minHeight", is(120)))
                .andExpect(jsonPath("$[1].typeId", is(1)))
                .andExpect(jsonPath("$[1].themeparkCode", is("156554")))
                .andExpect(jsonPath("$[1].attractionCode", is("A003")))
                .andExpect(jsonPath("$[2].name", is("Verwijder mij")))
                .andExpect(jsonPath("$[2].description", is("Deze attractie wordt gebruikt om het verwijderen van een attractie te testen")))
                .andExpect(jsonPath("$[2].minHeight", is(120)))
                .andExpect(jsonPath("$[2].typeId", is(1)))
                .andExpect(jsonPath("$[2].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[2].attractionCode", is("A004")));
    }

    @Test
    public void givenAttraction_whenGetAttractionsByTypeIdAndThemeparkCode_thenReturnJsonAttraction() throws Exception {
        mockMvc.perform(get("/api/attractions/type/{typeId}/themepark/{themeparkCode}", 1, "156545"))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // 2 Attractions
                .andExpect(jsonPath("$", hasSize(2)))
                // Results matching the values of attraction A001 and A004
                .andExpect(jsonPath("$[0].name", is("Attractie 1")))
                .andExpect(jsonPath("$[0].description", is("Duistere krachten in Land of Legends wekten een eeuwige Typhoon op. Spring in de koets van de Skyriders, vlieg met hen mee en duik 25 meter het diepe in. Help jij heks Wayra in te tomen?")))
                .andExpect(jsonPath("$[0].minHeight", is(125)))
                .andExpect(jsonPath("$[0].typeId", is(1)))
                .andExpect(jsonPath("$[0].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[0].attractionCode", is("A001")))
                .andExpect(jsonPath("$[1].name", is("Verwijder mij")))
                .andExpect(jsonPath("$[1].description", is("Deze attractie wordt gebruikt om het verwijderen van een attractie te testen")))
                .andExpect(jsonPath("$[1].minHeight", is(120)))
                .andExpect(jsonPath("$[1].typeId", is(1)))
                .andExpect(jsonPath("$[1].themeparkCode", is("156545")))
                .andExpect(jsonPath("$[1].attractionCode", is("A004")));
    }

    @Test
    public void whenPostAttraction_thenReturnJsonAttraction() throws Exception {
        Attraction attractionType3Themepark1 = new Attraction(5, "Attractie 5", "Even uitblazen? Het Reuzenrad is d?? eyecatcher van 't plezantste land en brengt je meteen in hogere sferen.", 140, 3, "156545", "A005");

        mockMvc.perform(post("/api/attractions")
                .content(mapper.writeValueAsString(attractionType3Themepark1))
                .contentType(MediaType.APPLICATION_JSON))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // Results matching the values of attractionType3Themepark1
                .andExpect(jsonPath("$.name", is("Attractie 5")))
                .andExpect(jsonPath("$.description", is("Even uitblazen? Het Reuzenrad is d?? eyecatcher van 't plezantste land en brengt je meteen in hogere sferen.")))
                .andExpect(jsonPath("$.minHeight", is(140)))
                .andExpect(jsonPath("$.typeId", is(3)))
                .andExpect(jsonPath("$.themeparkCode", is("156545")))
                .andExpect(jsonPath("$.attractionCode", is("A005")));
    }

    @Test
    public void givenAttraction_whenPutAttraction_thenReturnJsonAttraction() throws Exception {
        Attraction updatedAttraction = new Attraction(1, "Attractie 1", "Aangepaste beschrijving", 125, 1, "156545", "A001");

        mockMvc.perform(put("/api/attractions")
                .content(mapper.writeValueAsString(updatedAttraction))
                .contentType(MediaType.APPLICATION_JSON))
                // Expect:
                // JSON format
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // HTTP 200
                .andExpect(status().isOk())
                // Results matching the values of updatedAttraction
                .andExpect(jsonPath("$.name", is("Attractie 1")))
                .andExpect(jsonPath("$.description", is("Aangepaste beschrijving")))
                .andExpect(jsonPath("$.minHeight", is(125)))
                .andExpect(jsonPath("$.typeId", is(1)))
                .andExpect(jsonPath("$.themeparkCode", is("156545")))
                .andExpect(jsonPath("$.attractionCode", is("A001")));
    }

    @Test
    public void givenAttraction_whenDeleteAttraction_thenStatusOk() throws Exception {
        mockMvc.perform(delete("/api/attractions/{attractionCode}", "A004")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoAttraction_whenDeleteAttraction_thenStatusNotFound() throws Exception {
        mockMvc.perform(delete("/api/attractions/{attractionCode}", "A999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
