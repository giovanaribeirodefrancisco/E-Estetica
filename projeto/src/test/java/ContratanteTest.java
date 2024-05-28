import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;




public class ContratanteTest {
    @Test
    void testAdicionarReserva() {
        Contratante contratante = new Contratante("João", 4.5f);
        Reserva reserva = new Reserva();
        contratante.addReserva(reserva);
        
        List<Reserva> reservas = contratante.getReservas();
        assertEquals(1, reservas.size());
        assertEquals(reserva, reservas.get(0));
    }

    @Test
    void testNotificar() {
        Contratante contratante = new Contratante("João", 4.5f);
        contratante.notificar("Nova mensagem");
        
        List<String> notificacoes = contratante.notificacoes;
        assertEquals(1, notificacoes.size());
        assertEquals("Nova mensagem", notificacoes.get(0));
    }
    
}
