package com.itgt.pos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
}
