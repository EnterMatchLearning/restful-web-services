package com.example.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilteringController {

    @GetMapping(path = "/filtering")
    public MappingJacksonValue retrieveSomeBean() {

        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        return getMappingJacksonValue(someBean, "field1", "field3");
    }

    @GetMapping(path = "/filtering-list")
    public MappingJacksonValue retrieveSomeBeanList() {

        List<SomeBean> someBeans = List.of(
                new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6")
        );

        return getMappingJacksonValue(someBeans, "field3");
    }

    private static MappingJacksonValue getMappingJacksonValue(Object objectToSerialize, String... fields) {
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(objectToSerialize);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
