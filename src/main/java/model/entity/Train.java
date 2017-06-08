package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity to table <b>TRAIN</b>
 *
 * @author Andrii Yashin
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    private Long id;
    private Long routeId;

    private Long compartmentFree;
    private Long deluxeFree;
    private Long berthFree;
}
