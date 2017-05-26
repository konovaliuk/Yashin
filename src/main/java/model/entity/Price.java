package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Price {
    private Long id;


    private Double compartment_factor;
    private Double deluxe_factor;
    private Double berth_factor;

    public Price(Double compartment_factor, Double deluxe_factor, Double berth_factor) {
        this.compartment_factor = compartment_factor;
        this.deluxe_factor = deluxe_factor;
        this.berth_factor = berth_factor;
    }
}
