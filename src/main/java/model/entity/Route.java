package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity to table <b>ROUTE</b>
 *
 * @author Andrii Yashin
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    private Long id;
    private Long priceId;

    private Long fromId;
    private Long toId;

    private String fromTime;
    private String toTime;

    private Double distance;


}
