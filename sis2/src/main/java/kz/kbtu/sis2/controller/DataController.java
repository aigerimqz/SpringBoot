package kz.kbtu.sis2.controller;

import kz.kbtu.sis2.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DataController {
    @Autowired
    DataService dataService;

    @GetMapping("/api/data")
    public Map<String, Object> getData(){
        return dataService.getExternalData();
    }
}
