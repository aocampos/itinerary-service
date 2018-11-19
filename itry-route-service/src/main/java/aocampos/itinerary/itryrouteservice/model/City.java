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
@NodeEntity(label = "CityDTO")
public class City {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Getter
    @Relationship(type = "HAS_TRAVEL")
    private Set<Travel> travel = new HashSet<>();

    public void setTravel(Travel travel) {
        this.travel.add(travel);
    }
}
