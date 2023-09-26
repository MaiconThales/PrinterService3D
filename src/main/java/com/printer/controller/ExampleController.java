package com.printer.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

	@GetMapping("/mensagem")
	public String obterMensagem() {
		return "Olá, este é um exemplo de serviço Web!";
	}

	@PostMapping("/enviar")
	public String enviarMensagem(@RequestBody String mensagem) {
		return "Mensagem recebida: " + mensagem;
	}

	@PutMapping("/atualizar/{id}")
	public String atualizarMensagem(@PathVariable Long id, @RequestBody String novaMensagem) {
		return "Mensagem com ID " + id + " atualizada com: " + novaMensagem;
	}

	@DeleteMapping("/excluir/{id}")
	public String excluirMensagem(@PathVariable Long id) {
		return "Mensagem com ID " + id + " excluída com sucesso.";
	}

}