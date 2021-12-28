package fact.it.apt_attractionservice;

import fact.it.apt_attractionservice.model.Attraction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AttractionModelTests {
    @Test
    void testConstructor() {
        final Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertEquals("Attractie 1", attraction.getName());
        assertEquals("omschrijving attractie 1", attraction.getDescription());
        assertEquals(125, attraction.getMinHeight());
        assertEquals(1, attraction.getTypeId());
        assertEquals("TP1", attraction.getThemeparkCode());
        assertEquals("A001", attraction.getAttractionCode());
    }

    @Test
    public void getName_happy() {
        final Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getName()).isEqualTo("Attractie 1");
    }

    @Test
    public void getDescription_happy() {
        final Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getDescription()).isEqualTo("omschrijving attractie 1");
    }

    @Test
    public void getMinHeight_happy() {
        final Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getMinHeight()).isEqualTo(125);
    }

    @Test
    public void getTypeId_happy() {
        final Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getTypeId()).isEqualTo(1);
    }

    @Test
    public void getThemeparkCode_happy() {
        Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getThemeparkCode()).isEqualTo("TP1");
    }

    @Test
    public void getAttractionCode_happy() {
        Attraction attraction = new Attraction(1, "Attractie 1", "omschrijving attractie 1", 125, 1, "TP1", "A001");

        assertThat(attraction).isNotNull();
        assertThat(attraction.getAttractionCode()).isEqualTo("A001");
    }
}
