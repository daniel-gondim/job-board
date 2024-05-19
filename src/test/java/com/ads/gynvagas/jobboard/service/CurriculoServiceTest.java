package com.ads.gynvagas.jobboard.service;

import com.ads.gynvagas.jobboard.model.Curriculo;
import com.ads.gynvagas.jobboard.repository.CurriculoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CurriculoServiceTest {

    @Test
    void testaAdicionarCurriculo() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);

        Curriculo curriculo = new Curriculo();
        curriculo.setNome("João Silva");
        curriculo.setEmail("joao.silva@example.com");
        curriculo.setTelefone("123456789");
        curriculo.setHabilidades(Arrays.asList("Java", "Spring Boot"));
        curriculo.setExperienciaProfissional(Arrays.asList());
        curriculo.setEducacao(Arrays.asList());

        Mockito.when(mockRepository.save(curriculo)).thenReturn(curriculo);

        Curriculo curriculoSalvo = curriculoService.adicionarCurriculo(curriculo);

        assertNotNull(curriculoSalvo);
        assertEquals(curriculo.getNome(), curriculoSalvo.getNome());
        assertEquals(curriculo.getEmail(), curriculoSalvo.getEmail());
        assertEquals(curriculo.getTelefone(), curriculoSalvo.getTelefone());
        assertEquals(curriculo.getHabilidades(), curriculoSalvo.getHabilidades());
    }

    @Test
    void testaObterTodosOsCurriculos() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);

        Curriculo curriculo1 = new Curriculo();
        curriculo1.setNome("Maria Souza");
        curriculo1.setEmail("maria.souza@example.com");
        curriculo1.setTelefone("987654321");
        curriculo1.setHabilidades(Arrays.asList("Python", "Django"));
        curriculo1.setExperienciaProfissional(Arrays.asList());
        curriculo1.setEducacao(Arrays.asList());

        Curriculo curriculo2 = new Curriculo();
        curriculo2.setNome("Carlos Pereira");
        curriculo2.setEmail("carlos.pereira@example.com");
        curriculo2.setTelefone("123456789");
        curriculo2.setHabilidades(Arrays.asList("JavaScript", "React"));
        curriculo2.setExperienciaProfissional(Arrays.asList());
        curriculo2.setEducacao(Arrays.asList());

        List<Curriculo> listaDeCurriculos = Arrays.asList(curriculo1, curriculo2);
        Mockito.when(mockRepository.findAll()).thenReturn(listaDeCurriculos);

        List<Curriculo> curriculos = curriculoService.obterTodosOsCurriculos();

        assertNotNull(curriculos);
        assertEquals(2, curriculos.size());
        assertTrue(curriculos.contains(curriculo1));
        assertTrue(curriculos.contains(curriculo2));
    }

    @Test
    void testaFalhaAoAdicionarCurriculo() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);
        Curriculo curriculo = new Curriculo();
        Mockito.when(mockRepository.save(curriculo)).thenThrow(RuntimeException.class);
        assertThrows(Exception.class, () -> curriculoService.adicionarCurriculo(curriculo));
    }

    @Test
    void testaObterListaVazia() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);

        Mockito.when(mockRepository.findAll()).thenReturn(Collections.emptyList());

        List<Curriculo> listaDeCurriculos = curriculoService.obterTodosOsCurriculos();

        assertTrue(listaDeCurriculos.isEmpty());
        assertNotNull(listaDeCurriculos);
    }

    @Test
    void testaAtualizarCurriculo() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);
        Curriculo curriculoExistente = new Curriculo();
        curriculoExistente.setId("1");
        curriculoExistente.setNome("João Silva");
        curriculoExistente.setEmail("joao.silva@example.com");
        curriculoExistente.setTelefone("123456789");
        curriculoExistente.setHabilidades(Arrays.asList("Java", "Spring Boot"));

        Curriculo curriculoAtualizado = new Curriculo();
        curriculoAtualizado.setId("1");
        curriculoAtualizado.setNome("João Silva Atualizado");
        curriculoAtualizado.setEmail("joao.silva.atualizado@example.com");
        curriculoAtualizado.setTelefone("987654321");
        curriculoAtualizado.setHabilidades(Arrays.asList("Java", "Spring Boot", "Kotlin"));

        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(curriculoExistente));
        Mockito.when(mockRepository.save(curriculoAtualizado)).thenReturn(curriculoAtualizado);

        Curriculo resultado = curriculoService.editarCurriculo("1", curriculoAtualizado);

        assertNotNull(resultado);
        assertEquals(curriculoAtualizado.getNome(), resultado.getNome());
        assertEquals(curriculoAtualizado.getEmail(), resultado.getEmail());
        assertEquals(curriculoAtualizado.getTelefone(), resultado.getTelefone());
        assertEquals(curriculoAtualizado.getHabilidades(), resultado.getHabilidades());
    }

    @Test
    void testaAtualizarCurriculoNaoExistente() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);

        Curriculo curriculoAtualizado = new Curriculo();
        curriculoAtualizado.setId("1");
        curriculoAtualizado.setNome("João Silva Atualizado");
        curriculoAtualizado.setEmail("joao.silva.atualizado@example.com");
        curriculoAtualizado.setTelefone("987654321");
        curriculoAtualizado.setHabilidades(Arrays.asList("Java", "Spring Boot", "Kotlin"));

        Mockito.when(mockRepository.findById("2")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> curriculoService.editarCurriculo("2", curriculoAtualizado));
    }

    @Test
    void testaExcluirCurriculo() {
        CurriculoRepository mockRepository = Mockito.mock(CurriculoRepository.class);
        CurriculoService curriculoService = new CurriculoService(mockRepository);
        Curriculo curriculoExistente = new Curriculo();
        curriculoExistente.setId("1");
        curriculoExistente.setNome("João Silva");
        curriculoExistente.setEmail("joao.silva@example.com");
        curriculoExistente.setTelefone("123456789");
        curriculoExistente.setHabilidades(Arrays.asList("Java", "Spring Boot"));

        Mockito.when(mockRepository.findById("1")).thenReturn(Optional.of(curriculoExistente));

        curriculoService.deletarCurriculo("1");
        Mockito.verify(mockRepository).deleteById("1");
    }
}
