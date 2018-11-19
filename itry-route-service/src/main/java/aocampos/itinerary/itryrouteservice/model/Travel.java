package aocampos.itinerary.itryrouteservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity(label = "TravelDTO")
public class Travel {

    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String departureTime;

    private String arrivalTime;

    private Long duration;

    @Getter
    @Relationship(type = "TRAVELING_TO")
    private Set<City> city = new HashSet<>();

    public void setCity(City city) {
        this.city.add(city);
    }
}
