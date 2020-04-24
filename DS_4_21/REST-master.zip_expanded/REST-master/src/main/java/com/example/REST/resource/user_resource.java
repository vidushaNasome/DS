package com.example.REST.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.REST.model.sensor;
import com.example.REST.model.user;
import com.example.REST.repository.user_repositary;

@RestController
public class user_resource {
	
	@Autowired
	user_repositary u_m_r;
	
	@RequestMapping(value="/user")
	public List<user> getAll(){
		System.out.println("details");
		System.out.println(u_m_r.findAll());
		return u_m_r.findAll();
	}
	
	@GetMapping(value="/user/{name}")
    public ResponseEntity<?> getName(@PathVariable String name){
        user user=u_m_r.findBylogin(name);
        return new ResponseEntity<user>(user,HttpStatus.OK);
    }
	
	
}
