import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.projeto.*;


public class HorarioTest {

    @Test
    void testSetDisponivel() {
        Horario horario = new Horario("10:00");
        horario.setDisponivel(false);
        
        assertTrue(!horario.isDisponivel());
    }
}
