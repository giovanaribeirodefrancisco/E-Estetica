package com.estetica.es;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.estetica.es.models.UsuarioModel;
import com.estetica.es.models.UsuarioModel.TipoUsuario;
import com.estetica.es.repositores.UusarioRepository;
import com.estetica.es.services.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UsuarioTest {

    @Mock
    private UusarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarUsuario_Sucesso() {
        // Arrange
        String nomeUsuario = "João Silva";
        TipoUsuario tipoUsuario = TipoUsuario.CONTRATANTE;

        // Criando usuário mock para retorno
        UsuarioModel usuarioMock = new UsuarioModel(nomeUsuario, tipoUsuario);
        usuarioMock.setId(1);

        // Configurando o mock para salvar o usuário
        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(usuarioMock);

        // Act
        UsuarioModel usuarioCriado = usuarioService.criarUsuario(nomeUsuario, tipoUsuario);

        // Assert
        assertNotNull(usuarioCriado);
        assertEquals(nomeUsuario, usuarioCriado.getNome());
        assertEquals(tipoUsuario, usuarioCriado.getTipo());
        assertNotNull(usuarioCriado.getId());

        // Verificando chamada do método save
        verify(usuarioRepository).save(any(UsuarioModel.class));
    }

    @Test
    void testListarUsuarios() {
        // Arrange
        UsuarioModel usuario1 = new UsuarioModel("João Silva", TipoUsuario.CONTRATANTE);
        usuario1.setId(1);

        UsuarioModel usuario2 = new UsuarioModel("Maria Souza", TipoUsuario.PRESTADOR);
        usuario2.setId(2);

        List<UsuarioModel> usuariosEsperados = Arrays.asList(usuario1, usuario2);

        // Configurando o mock para retornar a lista de usuários
        when(usuarioRepository.findAll()).thenReturn(usuariosEsperados);

        // Act
        List<UsuarioModel> usuariosEncontrados = usuarioService.listarUsuarios();

        // Assert
        assertEquals(2, usuariosEncontrados.size());
        assertEquals(usuariosEsperados, usuariosEncontrados);
        
        // Verificando chamada do método findAll
        verify(usuarioRepository).findAll();
    }

    @Test
    void testBuscarUsuarioPorId_Encontrado() {
        // Arrange
        Integer id = 1;
        UsuarioModel usuario = new UsuarioModel("João Silva", TipoUsuario.CONTRATANTE);
        usuario.setId(id);

        // Configurando o mock para retornar o usuário
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        // Act
        Optional<UsuarioModel> usuarioEncontrado = usuarioService.buscarUsuarioPorId(id);

        // Assert
        assertTrue(usuarioEncontrado.isPresent());
        assertEquals(usuario, usuarioEncontrado.get());
        
        // Verificando chamada do método findById
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testBuscarUsuarioPorId_NaoEncontrado() {
        // Arrange
        Integer id = 999;

        // Configurando o mock para retornar um Optional vazio
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<UsuarioModel> usuarioEncontrado = usuarioService.buscarUsuarioPorId(id);

        // Assert
        assertFalse(usuarioEncontrado.isPresent());
        
        // Verificando chamada do método findById
        verify(usuarioRepository).findById(id);
    }

    @Test
    void testAtualizarUsuario() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel("João Silva", TipoUsuario.CONTRATANTE);
        usuario.setId(1);
        usuario.setNome("João Silva Atualizado");

        // Configurando o mock para retornar o usuário atualizado
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        // Act
        UsuarioModel usuarioAtualizado = usuarioService.atualizarUsuario(usuario);

        // Assert
        assertNotNull(usuarioAtualizado);
        assertEquals(usuario, usuarioAtualizado);
        assertEquals("João Silva Atualizado", usuarioAtualizado.getNome());
        
        // Verificando chamada do método save
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testExcluirUsuario() {
        // Arrange
        UsuarioModel usuario = new UsuarioModel("João Silva", TipoUsuario.CONTRATANTE);
        usuario.setId(1);

        // Act
        usuarioService.excluirUsuario(usuario);

        // Assert
        // Verificando chamada do método delete
        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void testConstrutorComRepositorio() {
        // Arrange
        UusarioRepository repositorioTeste = mock(UusarioRepository.class);

        // Act
        UsuarioService servicoTeste = new UsuarioService(repositorioTeste);

        // Assert
        assertNotNull(servicoTeste);
        // Verificando se o repositório foi corretamente injetado
        assertDoesNotThrow(() -> servicoTeste.listarUsuarios());
    }

}
