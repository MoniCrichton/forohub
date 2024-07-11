package com.alura.forohub.service;

import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    public Optional<Topico> obtenerPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico crear(Topico topico) {
        return topicoRepository.save(topico);
    }

    public Optional<Topico> actualizar(Long id, Topico topico) {
        return topicoRepository.findById(id).map(existingTopico -> {
            existingTopico.setTitulo(topico.getTitulo());
            existingTopico.setMensaje(topico.getMensaje());
            return topicoRepository.save(existingTopico);
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
