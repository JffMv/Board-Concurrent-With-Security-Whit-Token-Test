package co.edu.ing.escuela.appWebSocket;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerRest {
    @Autowired
    TicketRepository ticketRepo;

    @GetMapping("/getticket")
    public String getTicket() {
        return "{\"ticket\":\"" + ticketRepo.getTicket() + "\"}";
    }
}
