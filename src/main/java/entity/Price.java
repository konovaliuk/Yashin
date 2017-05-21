package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    private Long id;

    private Double compartment_factor;
    private Double deluxe_factor;
    private Double berth_factor;
}
