package app.model.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Response Model for GET /tickets
 *
 * @author Shoji Yamada
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTicketResModel {
    private Tickets tickets;

    private TicketSets ticketSet;

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public class Tickets {
       private TicketModel barista;
       private TicketModel dolce;
   }

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   public class TicketSets {
       private TicketSetModel barista;
       private TicketSetModel dolce;
   }

}
