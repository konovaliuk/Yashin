package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {
    private Long id;
    private Long route_id;

    private Long compartment_free;
    private Long deluxe_free;
    private Long berth_free;
}
