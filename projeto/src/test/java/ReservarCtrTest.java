import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class ReservarCtrTest {
    @Test
    void testCriarReserva() {
        Contratante contratante = new Contratante("João", 4.5f);
        Prestador prestador = new Prestador("Maria", 4.8f);
        Servico servico = new Servico("Limpeza", 50);
        Data data = new Data("2024-06-01");
        Horario horario = new Horario("10:00");
        String forma = "Pix";
        
        ReservarCtr reservarCtr = new ReservarCtr();
        reservarCtr.criarReserva(contratante, prestador, servico, data, horario, forma);
        
        List<Reserva> reservas = reservarCtr.getReservas();
        assertEquals(1, reservas.size());
        assertEquals("João", reservas.get(0).getContratante().getNome());
        assertEquals("Maria", reservas.get(0).getPrestador().getNome());
    }
}
