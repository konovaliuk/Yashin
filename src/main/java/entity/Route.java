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

    private String from_station;
    private String to_station;

    private String from_time;
    private String to_time;


}
