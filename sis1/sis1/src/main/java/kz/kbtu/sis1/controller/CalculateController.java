package kz.kbtu.sis1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CalculateController {
    @PostMapping("/api/calculate")
    public Map<String, Integer> calculate(
            @RequestBody Map<String, Integer> body
    ){
        Integer a = body.get("a");
        Integer b= body.get("b");
        return Map.of("result", a + b );
    }
}
