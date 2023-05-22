package com.example.ioc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @PostMapping("/save")
    public void saveWord(@RequestBody Map<String, Object> map) {
        System.out.println(map.get("words"));
        ServiceDispatcher.createService(map.get("type").toString(), (List<String>) map.get("words"));
    }
}
