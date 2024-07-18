import co.edu.ing.escuela.appWebSocket.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.BoundListOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketRepositoryTest {

    @Mock
    private StringRedisTemplate template;

    @Mock
    private ListOperations<String, String> listTickets;

    @Mock
    private BoundListOperations<String, String> boundListOps;

    @InjectMocks
    private TicketRepository ticketRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(template.opsForList()).thenReturn(listTickets);
        when(listTickets.getOperations()).thenReturn(template);
        when(template.boundListOps("ticketStore")).thenReturn(boundListOps);
    }

    @Test
    void testGetTicket() {
        when(listTickets.leftPush(eq("ticketStore"), anyString())).thenReturn(1L);

        Integer ticket1 = ticketRepository.getTicket();
        Integer ticket2 = ticketRepository.getTicket();

        assertNotNull(ticket1);
        assertNotNull(ticket2);
        assertEquals(1, ticket2 - ticket1);

        verify(listTickets, times(2)).leftPush(eq("ticketStore"), anyString());
    }

    @Test
    void testCheckTicket_ValidTicket() {
        when(boundListOps.remove(eq(0L), eq("123"))).thenReturn(1L);

        boolean result = ticketRepository.checkTicket("123");

        assertTrue(result);
        verify(boundListOps).remove(0L, "123");
    }

    @Test
    void testCheckTicket_InvalidTicket() {
        when(boundListOps.remove(eq(0L), eq("456"))).thenReturn(0L);

        boolean result = ticketRepository.checkTicket("456");

        assertFalse(result);
        verify(boundListOps).remove(0L, "456");
    }
}