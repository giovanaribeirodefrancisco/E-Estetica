import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.projeto.*;

public class UsuarioTest {
    @Test
    void testAdicionaContratante() {
        Usuario usuario = new Usuario();
        Contratante contratante = new Contratante("João", 4.5f);
        usuario.adicionaContratante(contratante);
        
        List<Contratante> contratantes = usuario.getContratantes();
        assertEquals(1, contratantes.size());
        assertEquals("João", contratantes.get(0).getNome());
    }

    @Test
    void testAdicionaPrestador() {
        Usuario usuario = new Usuario();
        Prestador prestador = new Prestador("Maria", 4.8f);
        usuario.adicionaPrestador(prestador);
        
        List<Prestador> prestadores = usuario.getPrestadores();
        assertEquals(1, prestadores.size());
        assertEquals("Maria", prestadores.get(0).getNome());
    }
    
}
