import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    
}
