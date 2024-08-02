package com.revature.Project1.controllers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Project1.models.Cat;
import com.revature.Project1.services.CatService;
import lombok.experimental.PackagePrivate;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cats")
@CrossOrigin
public class CatController {

    CatService cs;
    ObjectMapper mp;
    @Autowired //Constructor Injection
    public CatController(CatService cs, ObjectMapper mp) {
        this.cs = cs;
        this.mp = mp;
    }

    @GetMapping("/cats")
    public ResponseEntity<String> getAllCats() throws JsonProcessingException {
        List<Cat> catList = cs.getAllCats();
        String jsonResponse = mp.writeValueAsString(catList);
        return ResponseEntity.status(200).body(jsonResponse);
    }

    @GetMapping("cats/{id}")
    public ResponseEntity<String> getCat(@PathVariable int id) throws JsonProcessingException {
        Cat cat = cs.getCat(id);
        String jsonResponse = cat != null ? mp.writeValueAsString(cat) : "";
        return ResponseEntity.status(200).body(jsonResponse);
    }



    @PostMapping("/{userId}")
    public ResponseEntity<Cat> addCat(@PathVariable int userId, @RequestBody Cat a) {
        a = cs.addCat(userId, a);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }


}

