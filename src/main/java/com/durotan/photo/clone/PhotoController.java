package com.durotan.photo.clone;


//marker anotation do something special



import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


//photo raw bytes.

@RestController
public class PhotoController {

    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "hello.jpg"));

    }};

    @GetMapping("/")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/photos")
    public Collection<Photo> get() {
        return db.values();

    }

    //this anotation will tell springboot if you have in mapping some parametr s curly braces called id, put whatever to je do metody
    @GetMapping("/photos/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = db.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        return photo;
    }

    @DeleteMapping("/photos/{id}")
    public void delete(@PathVariable String id) {
        Photo photo = db.remove(id);
        if(photo==null) throw new ResponseStatusException(HttpStatusCode.valueOf(404));


    }
}

