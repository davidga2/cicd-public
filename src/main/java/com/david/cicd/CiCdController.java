package com.david.cicd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CiCdControlle {

	@GetMapping("/get/{name}")
	public String getName(@PathVariable String name) {
		return name;
	}
	@GetMapping("/return/{name}")
	public String getName1(@PathVariable String name) {
		return "david";
	}
}
