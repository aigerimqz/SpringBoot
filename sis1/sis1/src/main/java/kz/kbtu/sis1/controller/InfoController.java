package kz.kbtu.sis1.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class InfoController {
    @GetMapping("/api/info")
    public Map<String, String> getInfo(){
        return Map.of(
                "student", "Aigerim Manat",
                "course", "Spring Framework",
                "week", "1");
    }


}
