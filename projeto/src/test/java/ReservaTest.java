import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class ReservaTest {

    @Test
    void testSetStatus() {
        Reserva reserva = new Reserva();
        reserva.setStatus("Confirmada");
        
        assertEquals("Confirmada", reserva.getStatus());
    }
    
}
