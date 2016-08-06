package org.suree.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.suree.model.TaiKyoKu;
import org.suree.service.RemotePaifuParseService;
import org.suree.service.XmlAnalysisService;
import org.suree.service.XmlReadService;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Resource
    private RemotePaifuParseService remotePaifuParseService;


    @RequestMapping("/analysis/test")
    public Map<String, Object> analysisTest(@RequestParam(value = "log", required = false, defaultValue = "2011020417gm-00a9-0000-b67fcaa3")String logCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        String path = "/Users/Sure/code/my_log/";
        Document paihouDoc = xmlReadService.getXmlFile(path + logCode + ".xml");
        TaiKyoKu taiKyoKu = xmlAnalysisService.analysisDocument(paihouDoc);
        result.put("data", taiKyoKu);
        return result;
    }


    @RequestMapping("/paifu/remoteGet/test")
    public Map<String, Object> paifuGetTest(){
        List<String> logCodes = new ArrayList<String>();
        logCodes.add("2016050720gm-0001-0000-f77a2431");
        logCodes.add("2016072020gm-00c1-0000-e0f49746");
        logCodes.add("2016072020gm-0089-0000-e83a8b43");
        logCodes.add("2016072101gm-0089-0000-0a870c91");
        logCodes.add("2016072223gm-0009-5770-d6ddf3d6");
        logCodes.add("2016072316gm-0089-0000-0b7b37ca");
        logCodes.add("2016072316gm-0089-0000-9bf292a3");
        logCodes.add("2016072323gm-0009-1022-3e77685a");
        logCodes.add("2016072400gm-0009-1022-85e8f4e4");
        logCodes.add("2016071022gm-0089-0000-ead7674a");

//        logCodes.add("2016050720gm-0001-0000-f77a2431");
//        logCodes.add("2016072020gm-00c1-0000-e0f49746");
//        logCodes.add("2016072020gm-0089-0000-e83a8b43");
//        logCodes.add("2016072101gm-0089-0000-0a870c91");
//        logCodes.add("2016072223gm-0009-5770-d6ddf3d6");
//        logCodes.add("2016072316gm-0089-0000-0b7b37ca");
//        logCodes.add("2016072316gm-0089-0000-9bf292a3");
//        logCodes.add("2016072323gm-0009-1022-3e77685a");
//        logCodes.add("2016072400gm-0009-1022-85e8f4e4");
//        logCodes.add("2016071022gm-0089-0000-ead7674a");

        long start = System.currentTimeMillis();
        List<String> xmls = remotePaifuParseService.parseRemotePaifuWithLogCodeList(logCodes);
        long elapsedTime = System.currentTimeMillis() - start;

        List<Document> documents = xmlReadService.convertToDocuments(xmls);
        List<TaiKyoKu> taiKyoKus = xmlAnalysisService.analysisDocuments(documents);
        Map results = new HashMap();
        results.put("time", elapsedTime/1000);
        results.put("data", taiKyoKus);
        return results;
    }




}
