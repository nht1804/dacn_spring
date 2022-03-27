package com.nttu.dacnsv;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Test {
    public Map<String, String> message(String message) {
        Map<String, String> map = new HashMap<>();
        map.put("Message", message);
        return map;
    }
}
