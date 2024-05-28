import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class PrestadorTest {
    @Test
    void testAddServico() {
        Prestador prestador = new Prestador("Maria", 4.8f);
        Servico servico = new Servico("Limpeza", 50);
        prestador.addServico(servico);
        
        List<Servico> servicos = prestador.getServicos();
        assertEquals(1, servicos.size());
        assertEquals("Limpeza", servicos.get(0).getNome());
    }

    @Test
    void testAddReservaPendente() {
        Prestador prestador = new Prestador("Maria", 4.8f);
        Reserva reserva = new Reserva();
        prestador.addReservaPendente(reserva);
        
        List<Reserva> reservasPendentes = prestador.getReservasPendentes();
        assertEquals(1, reservasPendentes.size());
        assertEquals(reserva, reservasPendentes.get(0));
    }

   
    @Test
    void testNotificar() {
        Prestador prestador = new Prestador("Maria", 4.8f);
        prestador.notificar("Nova mensagem");
        
        List<String> notificacoes = prestador.getNotificacoes();
        assertEquals(1, notificacoes.size());
        assertEquals("Nova mensagem", notificacoes.get(0));
    }
    
}
