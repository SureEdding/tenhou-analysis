package org.suree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/monitor")
public class MonitorApiController {
    @RequestMapping("/alive")
    public Map<String, String> alive() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "ok");
        return map;
    }
}
