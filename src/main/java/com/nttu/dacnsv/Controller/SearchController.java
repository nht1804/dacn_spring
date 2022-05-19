package com.nttu.dacnsv.Controller;

import com.mongodb.lang.Nullable;
import com.nttu.dacnsv.Request.ServiceResult;
import com.nttu.dacnsv.Service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/Search")
@AllArgsConstructor
public class SearchController {
    private final SearchService service;

    @GetMapping("/Car")
    public ResponseEntity<ServiceResult> carSearch(@RequestParam(required = false) String name, @RequestParam(required = true) int price, @RequestParam(required = false, defaultValue = "0") int seat, @RequestParam(required = false) Boolean driver, @RequestParam(required = false) String manufacturer, @RequestParam(required = false) String transmission) {
        return ResponseEntity.ok().body(service.searchCar(name, price, seat, driver, manufacturer, transmission));
    }
}
