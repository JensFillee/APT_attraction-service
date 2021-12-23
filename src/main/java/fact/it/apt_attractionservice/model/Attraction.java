package fact.it.apt_attractionservice.model;

import javax.persistence.*;

@Entity
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int minHeight;
    private int typeId;

    private String themeparkCode;

    @Column(unique = true)
    private String attractionCode;

    public Attraction(int id, String name, int minHeight, int typeId, String themeparkCode, String attractionCode) {
        this.id = id;
        this.name = name;
        this.minHeight = minHeight;
        this.typeId = typeId;
        this.themeparkCode = themeparkCode;
        this.attractionCode = attractionCode;
    }

    public Attraction() {

    }
}
