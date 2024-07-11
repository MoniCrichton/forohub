package com.alura.forohub;

import com.alura.forohub.model.Topico;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ForohubApplication implements CommandLineRunner {

	@Autowired
	private TopicoRepository topicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Crear un tópico al iniciar la aplicación
		Topico topico = new Topico();
		topico.setTitulo("Mi primer tópico");
		topico.setMensaje("Este es el mensaje de mi primer tópico");
		topicoRepository.save(topico);
	}
}
