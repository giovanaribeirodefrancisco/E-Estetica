import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class PublicarServicoCtrTest {

    @Test
    void testAdicionarServico() {
        Prestador prestador = new Prestador("Maria", 4.8f);
        PublicarServicoCtr publicarServicoCtr = new PublicarServicoCtr();
        
        publicarServicoCtr.adicionarServico("Limpeza", 50, prestador);
        
        List<Servico> servicos = prestador.getServicos();
        assertEquals(1, servicos.size());
        assertEquals("Limpeza", servicos.get(0).getNome());
    }

    @Test
    void testAdicionarData() {
        PublicarServicoCtr publicarServicoCtr = new PublicarServicoCtr();
        Prestador prestador = new Prestador("Maria", 4.8f);

        String dia = "2024-05-28";
        String inicio = "09:00";
        String fim = "12:00";
        String nomeServico = "Serviço Teste";
        float valorServico = 100.0f;

        // Primeiro, adicionamos o serviço
        publicarServicoCtr.adicionarServico(nomeServico, valorServico, prestador);

        // Então, adicionamos a data com horários
        publicarServicoCtr.adicionarData(dia, inicio, fim, nomeServico, prestador);

        // Verificar se a data foi adicionada corretamente ao serviço
        Servico servico = prestador.buscar(nomeServico);
        assertNotNull(servico);
        
        Data data = servico.buscarData(dia);
        assertNotNull(data);
        assertEquals(dia, data.getData());

        // Verificar se os horários foram adicionados corretamente
        List<Horario> horariosDisponiveis = data.getHorariosDisponiveis();
        assertEquals(4, horariosDisponiveis.size()); // 09:00, 10:00, 11:00, 12:00

        assertEquals("09:00", horariosDisponiveis.get(0).getHora());
        assertEquals("10:00", horariosDisponiveis.get(1).getHora());
        assertEquals("11:00", horariosDisponiveis.get(2).getHora());
        assertEquals("12:00", horariosDisponiveis.get(3).getHora());
    }
    
}
