package com.example.REST.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.REST.model.sensor;
import com.example.REST.model.user;
import com.example.REST.repository.sensor_repositary;

@CrossOrigin 
@RestController
public class sensor_resource {

	@Autowired
	sensor_repositary rep;
	//These methods can used by any applications.In order to retrieve details
	@RequestMapping(value="/sensor")
	public List<sensor> getAll(){
		return rep.findAll();
	}
	
	@PostMapping(value="/sensor/add")
	public ResponseEntity<?> addSensorDetails(@RequestBody sensor s){
		
		rep.save(s);
		//When updating checking whether the exceeding of level and simulating sending emails and massages 
		if(s.getCo2()>=5 || s.getSmokelevel()>=5 ) {
			System.out.println("Smoke levels and or co2 levels are exceeding. Sending emails and massages.................");
		}
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@GetMapping(value="/sensor/{location}")
    public ResponseEntity<?> getLocation(@PathVariable String location){
        sensor sensor=rep.findBylocation(location);
        return new ResponseEntity<sensor>(sensor,HttpStatus.OK);
    }
}
