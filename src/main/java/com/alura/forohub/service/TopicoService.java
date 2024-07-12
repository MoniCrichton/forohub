package com.alura.forohub.service;

import com.alura.forohub.exception.TopicoNotFoundException;
import com.alura.forohub.model.Topico;
import com.alura.forohub.model.Usuario;
import com.alura.forohub.model.Curso;
import com.alura.forohub.repository.TopicoRepository;
import com.alura.forohub.repository.UsuarioRepository;
import com.alura.forohub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Page<Topico> listarTodos(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }

    public Page<Topico> listarPorCurso(String cursoNombre, Pageable pageable) {
        return topicoRepository.findByCursoNombre(cursoNombre, pageable);
    }

    public Page<Topico> listarPorAnio(Integer anio, Pageable pageable) {
        return topicoRepository.findByAnio(anio, pageable);
    }

    public Page<Topico> listarPorCursoYAnio(String cursoNombre, Integer anio, Pageable pageable) {
        return topicoRepository.findByCursoNombreAndAnio(cursoNombre, anio, pageable);
    }

    public Optional<Topico> obtenerPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico crear(Topico topico) {
        Optional<Usuario> autor = usuarioRepository.findById(topico.getAutor().getId());
        Optional<Curso> curso = cursoRepository.findById(topico.getCurso().getId());
        if (autor.isPresent() && curso.isPresent()) {
            topico.setAutor(autor.get());
            topico.setCurso(curso.get());
            return topicoRepository.save(topico);
        } else {
            throw new TopicoNotFoundException("Autor o Curso no encontrado");
        }
    }

    public Optional<Topico> actualizar(Long id, Topico topico) {
        return topicoRepository.findById(id).map(existingTopico -> {
            Optional<Usuario> autor = usuarioRepository.findById(topico.getAutor().getId());
            Optional<Curso> curso = cursoRepository.findById(topico.getCurso().getId());
            if (autor.isPresent() && curso.isPresent()) {
                existingTopico.setTitulo(topico.getTitulo());
                existingTopico.setMensaje(topico.getMensaje());
                existingTopico.setFechaCreacion(topico.getFechaCreacion());
                existingTopico.setStatus(topico.getStatus());
                existingTopico.setAutor(autor.get());
                existingTopico.setCurso(curso.get());
                existingTopico.setAnio(topico.getAnio());
                return topicoRepository.save(existingTopico);
            } else {
                throw new TopicoNotFoundException("Autor o Curso no encontrado");
            }
        });
    }

    public boolean eliminar(Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
