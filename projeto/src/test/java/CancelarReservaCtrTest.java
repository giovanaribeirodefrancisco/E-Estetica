import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.projeto.*;

public class CancelarReservaCtrTest {

    @Test
    void testCancelarReservaCo() {
        Contratante contratante = new Contratante("João", 4.5f);
        Prestador prestador = new Prestador("Maria", 4.8f);
        Servico servico = new Servico("Limpeza", 50);
        Data data = new Data("2024-06-01");
        Horario horario = new Horario("10:00");
        Reserva reserva = new Reserva(contratante, prestador, horario, 50, data, servico);
        contratante.addReserva(reserva);
        
        CancelarReservaCtr cancelarReservaCtr = new CancelarReservaCtr();
        cancelarReservaCtr.cancelarReservaCo(contratante, 0);
        
        assertEquals("Cancelada", reserva.getStatus());
    }

    @Test
    void testCancelarReservaPre() {
        Contratante contratante = new Contratante("João", 4.5f);
        Prestador prestador = new Prestador("Maria", 4.8f);
        Servico servico = new Servico("Limpeza", 50);
        Data data = new Data("2024-06-01");
        Horario horario = new Horario("10:00");
        Reserva reserva = new Reserva(contratante, prestador, horario, 50, data, servico);
        prestador.addReservaPendente(reserva);
        
        CancelarReservaCtr cancelarReservaCtr = new CancelarReservaCtr();
        cancelarReservaCtr.cancelarReservaPre(prestador, 0);
        
        assertEquals("Cancelada", reserva.getStatus());
    }
    
}
