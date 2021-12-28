package fact.it.apt_attractionservice;

import fact.it.apt_attractionservice.model.Attraction;
import fact.it.apt_attractionservice.repository.AttractionRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AttractionRepositoryTests {
    @Autowired
    private AttractionRepository attractionRepository;

    private Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");
    private Attraction attractionType2Themepark1 = new Attraction(2, "Attractie 2", "omschrijving attractie 2", 100, 2, "TP1", "A002");
    private Attraction attractionType1Themepark2 = new Attraction(3, "Attractie 3", "omschrijving attractie 3", 120, 1, "TP2", "A003");
    private Attraction attraction2Type1Themepark1 = new Attraction(4, "Attractie 4", "omschrijving attractie 4", 125, 1, "TP1", "A004");

    @BeforeEach
    public void beforeAllTests() {
        attractionRepository.deleteAll();
        attractionRepository.save(attractionType1Themepark1);
        attractionRepository.save(attractionType2Themepark1);
        attractionRepository.save(attractionType1Themepark2);
        attractionRepository.save(attraction2Type1Themepark1);
    }

    @AfterEach
    public void afterAllTests() {
        attractionRepository.deleteAll();
    }

    @Test
    void findAttractionByAttractionCode_happy() {
        final Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        Attraction result = attractionRepository.findAttractionByAttractionCode("A001");

        assertThat(result).isNotNull();
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(attractionType1Themepark1);
    }

    @Test
    void findAttractionsByThemeparkCodeEqualsAndNameContaining_happy() {
        final Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");
        final Attraction attractionType2Themepark1 = new Attraction(2, "Attractie 2", "omschrijving attractie 2", 100, 2, "TP1", "A002");
        final Attraction attraction2Type1Themepark1 = new Attraction(4, "Attractie 4", "omschrijving attractie 4", 125, 1, "TP1", "A004");

        final List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attractionType1Themepark1);
        attractionList.add(attractionType2Themepark1);
        attractionList.add(attraction2Type1Themepark1);

        List<Attraction> result = attractionRepository.findAttractionsByThemeparkCodeEqualsAndNameContaining("Attractie", "TP1");

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(attractionList);
    }

    @Test
    void findAttractionByTypeId_happy() {
        final Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");
        final Attraction attractionType1Themepark2 = new Attraction(3, "Attractie 3", "omschrijving attractie 3", 120, 1, "TP2", "A003");
        final Attraction attraction2Type1Themepark1 = new Attraction(4, "Attractie 4", "omschrijving attractie 4", 125, 1, "TP1", "A004");

        final List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attractionType1Themepark1);
        attractionList.add(attractionType1Themepark2);
        attractionList.add(attraction2Type1Themepark1);

        List<Attraction> result = attractionRepository.findAttractionByTypeId(1);

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(attractionList);
    }

    @Test
    void findAttractionsByThemeparkCode_happy() {
        final Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");
        final Attraction attractionType2Themepark1 = new Attraction(2, "Attractie 2", "omschrijving attractie 2", 100, 2, "TP1", "A002");
        final Attraction attraction2Type1Themepark1 = new Attraction(4, "Attractie 4", "omschrijving attractie 4", 125, 1, "TP1", "A004");

        final List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attractionType1Themepark1);
        attractionList.add(attractionType2Themepark1);
        attractionList.add(attraction2Type1Themepark1);

        List<Attraction> result = attractionRepository.findAttractionsByThemeparkCode("TP1");

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(attractionList);
    }

    @Test
    void findAttractionsByTypeIdAndThemeparkCode_happy() {
        final Attraction attractionType1Themepark1 = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");
        final Attraction attraction2Type1Themepark1 = new Attraction(4, "Attractie 4", "omschrijving attractie 4", 125, 1, "TP1", "A004");

        final List<Attraction> attractionList = new ArrayList<>();
        attractionList.add(attractionType1Themepark1);
        attractionList.add(attraction2Type1Themepark1);

        List<Attraction> result = attractionRepository.findAttractionsByTypeIdAndThemeparkCode(1, "TP1");

        assertThat(result).isNotNull().isNotEmpty();
        assertThat(result).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(attractionList);
    }
}
