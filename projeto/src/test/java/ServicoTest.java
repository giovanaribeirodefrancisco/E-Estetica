import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class ServicoTest {
    @Test
    void testAddData() {
        Servico servico = new Servico("Limpeza", 50);
        Data data = new Data("2024-06-01");
        servico.agendas.addData(data);
        
        List<Data> datas = servico.getAgendas().getDatas();
        assertEquals(1, datas.size());
        assertEquals("2024-06-01", datas.get(0).getData());
    }

    @Test
    void testBuscarData() {
        Servico servico = new Servico("Limpeza", 50);
        Data data = new Data("2024-06-01");
        servico.agendas.addData(data);
        
        Data foundData = servico.buscarData("2024-06-01");
        assertEquals(data, foundData);
    }
}
