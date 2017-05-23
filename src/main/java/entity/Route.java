package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private Long id;
    private Long price_id;

    private Long from_id;
    private Long to_id;

    private String from_time;
    private String to_time;

    private Double distance;


}
