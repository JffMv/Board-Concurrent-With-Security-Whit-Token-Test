package co.edu.ing.escuela.appWebSocket;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {

    @GetMapping("/getticket")
    public String getTicket() {

        TicketRepository ticketRepo = null;
        return "{\"ticket\":\"" +
                ticketRepo.getTicket() + "\"}";
    }
}
