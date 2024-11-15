package org.example.dtoOutgoing;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PreviousOrdersData {
    //@Embedded
    //private List<Integer> oderCodesList; // meekath OrderDtoSendingToFE eke field ekakata
                                                // daala yavanna try karamu

    @Embedded
    private List<OrderDtoSendingToFE> orderDtoListSendingToFE;


}
