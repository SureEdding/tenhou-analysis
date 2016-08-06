package org.suree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suree.model.TaiKyoKu;
import org.suree.service.XmlAnalysisService;
import org.suree.service.XmlReadService;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sure on 8/4/16.
 */

@RestController
@RequestMapping("/tenhou/api")
public class TenhouController {


    @Resource
    private XmlReadService xmlReadService;

    @Resource
    private XmlAnalysisService xmlAnalysisService;


    @RequestMapping("/analysis/test")
    public Map<String, Object> analysisTest(@RequestParam(value = "log", required = false, defaultValue = "2011020417gm-00a9-0000-b67fcaa3")String logCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        String path = "/Users/Sure/code/my_log/";
        Document paihouDoc = xmlReadService.getXmlFile(path + logCode + ".xml");
        TaiKyoKu taiKyoKu = xmlAnalysisService.analysisDocument(paihouDoc);
        result.put("data", taiKyoKu);
        return result;
    }




}
