package com.example.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping(path = "/person/v1")
    public PersonV1 urlVersioningPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/v2")
    public PersonV2 urlVersioningPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 requestParamPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 requestParamPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-Version=1")
    public PersonV1 headerPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-Version=2")
    public PersonV2 headerPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 acceptHeaderPersonV1() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 acceptHeaderPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
