package org.suree.controller;

import org.springframework.web.bind.annotation.*;
import org.suree.Utils.LogCodesValidator;
import org.suree.model.Request;
import org.suree.model.StaticsResultModel;
import org.suree.model.TaiKyoKu;
import org.suree.service.RemotePaifuParseService;
import org.suree.service.StaticsService;
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

    @Resource
    private LogCodesValidator logCodesValidator;

    @Resource
    private StaticsService staticsService;

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

        long start = System.currentTimeMillis();
        List<String> xmls = new ArrayList<String>();
        List<String > results = new ArrayList<String>();


        try {
        xmls = remotePaifuParseService.parseRemotePaifuWithLogCodeList(logCodes);
//            ExecutorService executorService = Executors.newCachedThreadPool();
//    for (int i = 0; i < logCodes.size(); i++) {
//        Future<String> result = executorService.submit(new AsyncRemotePaifuParser(logCodes.get(i)));
//        results.add(result.get());
//    }
//    executorService.awaitTermination(100, TimeUnit.SECONDS);


} catch (Exception e) {
            System.out.printf(e.getMessage());
}


        long elapsedTime = System.currentTimeMillis() - start;

        List<Document> documents = xmlReadService.convertToDocuments(xmls);
        List<TaiKyoKu> taiKyoKus = xmlAnalysisService.analysisDocuments(documents);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("time", elapsedTime/1000);
        map.put("data", taiKyoKus);
        return map;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map<String, Object> submit(@RequestBody Request request) {

        Map<String, Object> results = new HashMap<String, Object>();
        List<String> codes = logCodesValidator.validate(request.getLogcodes());
        if (!codes.isEmpty()) {
            List<String> xmls = remotePaifuParseService.parseRemotePaifuWithLogCodeList(codes);
            List<Document> documents = xmlReadService.convertToDocuments(xmls);
            List<TaiKyoKu> taiKyoKus = xmlAnalysisService.analysisDocuments(documents);
            StaticsResultModel model = staticsService.analysis(taiKyoKus, request.getUsername());
            results.put("data", model);
        }

        results.put("username", request.getUsername());
        return results;
    }




}
