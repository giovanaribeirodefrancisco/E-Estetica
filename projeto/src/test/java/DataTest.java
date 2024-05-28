import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class DataTest {
    @Test
    void testAddHorario() {
        Data data = new Data("2024-06-01");
        Horario horario = new Horario("10:00");
        data.addHorario(horario);
        
        List<Horario> horariosDisponiveis = data.getHorariosDisponiveis();
        assertEquals(1, horariosDisponiveis.size());
        assertEquals("10:00", horariosDisponiveis.get(0).getHora());
    }

    @Test
    void testBuscarHorarioDisponivel() {
        Data data = new Data("2024-06-01");
        Horario horario = new Horario("10:00");
        data.addHorario(horario);
        
        Horario foundHorario = data.buscarHorarioDisponivel("10:00");
        assertEquals(horario, foundHorario);
    }
}
