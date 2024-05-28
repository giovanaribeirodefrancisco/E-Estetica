import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class AgendaTest {

    @Test
    void AdicionarData(){
        Agenda agenda = new Agenda();
        Data data = new Data("22/05");

        agenda.addData(data);
        Data dia = agenda.getData(data);

        assertEquals(data, dia);

    }
    
}
