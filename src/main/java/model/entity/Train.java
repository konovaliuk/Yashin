package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
