package com.example.restfulwebservices.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(path = "/person/v1")
    public PersonV1 processPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/v2")
    public PersonV2 processPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
