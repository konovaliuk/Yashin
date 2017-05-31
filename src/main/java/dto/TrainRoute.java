package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainRoute {
    private Long train_id;
    private Long route_id;

    private Long compartment_free;
    private Long deluxe_free;
    private Long berth_free;

    private String fromDate;
    private String toDate;

    private String fromCity;
    private String toCity;

    private Double compartment_price;
    private Double deluxe_price;
    private Double berth_price;

    private Double distance;
}
