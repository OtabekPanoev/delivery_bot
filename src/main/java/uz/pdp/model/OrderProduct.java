package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderProduct {
    private Integer id;
    private Integer productId;
    private Integer amount;
    private Integer orderId;
}
