package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Order {
    private Integer id; // u
    private Long userId;
    private Long deliveryPrice;
    private Long actualPrice;
    private Integer addressId;
    private String desc;
}
