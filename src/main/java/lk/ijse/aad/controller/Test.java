package lk.ijse.aad.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "v1/api")
public class Test {

    @GetMapping(value = "/ping")
    public String ping(){
        log.info(".........Request is hear.......");
        log.info("Response Processing.......");
        return "Application is running...." ;
    }
}
