
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.ing.escuela.appWebSocket.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DrawingServiceControllerTest  {

    private ControllerRest drawingServiceController;
    private TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() {
        ticketRepository = mock(TicketRepository.class);
        drawingServiceController = new ControllerRest();
        drawingServiceController.ticketRepo = ticketRepository;
    }



    @Test
    public void testGetTicket() {
        when(ticketRepository.getTicket()).thenReturn(123);
        String response = drawingServiceController.getTicket();
        assertEquals("{\"ticket\":\"123\"}", response);
    }
}