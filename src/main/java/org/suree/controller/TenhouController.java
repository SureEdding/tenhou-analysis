package org.suree.controller;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.suree.Utils.LogCodesValidator;
import org.suree.domain.TaiKyokuTable;
import org.suree.model.Request;
import org.suree.model.StaticsResultModel;
import org.suree.model.TaiKyoKu;
import org.suree.model.Xml;
import org.suree.service.*;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

    @Resource
    private TaiKyokuDao taiKyokuDao;

    @RequestMapping("/analysis/test")
    public Map<String, Object> analysisTest(@RequestParam(value = "log", required = false, defaultValue = "2011020417gm-00a9-0000-b67fcaa3") String logCode) {
        Map<String, Object> result = new HashMap<String, Object>();
        String path = "/Users/Sure/code/my_log/";
        Document paihouDoc = xmlReadService.getXmlFile(path + logCode + ".xml");
        TaiKyoKu taiKyoKu = xmlAnalysisService.analysisDocument(paihouDoc);
        result.put("data", taiKyoKu);
        return result;
    }

//
//    @RequestMapping("/paifu/remoteGet/test")
//    public Map<String, Object> paifuGetTest() {
//        List<String> logCodes = new ArrayList<String>();
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
//
//        long start = System.currentTimeMillis();
//        List<String> xmls = new ArrayList<String>();
//        List<String> results = new ArrayList<String>();
//
//
//        try {
//            xmls = remotePaifuParseService.parseRemotePaifuWithLogCodeList(logCodes);
//        } catch (Exception e) {
//            System.out.printf(e.getMessage());
//        }
//
//
//        long elapsedTime = System.currentTimeMillis() - start;
//
//        List<Document> documents = xmlReadService.convertToDocuments(xmls);
//        List<TaiKyoKu> taiKyoKus = xmlAnalysisService.analysisDocuments(documents);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("time", elapsedTime / 1000);
//        map.put("data", taiKyoKus);
//        return map;
//    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public Map<String, Object> submit(@RequestBody Request request) {


        Map<String, Object> results = new HashMap<String, Object>();
        List<String> codes = logCodesValidator.validate(request.getLogcodes());
        List<String> dbCodes = new ArrayList<String>();
        List<Xml> dbxmls = new ArrayList<Xml>();
        List<Xml> remoteXmls = new ArrayList<Xml>();
        List<TaiKyoKu> taiKyoKus = new ArrayList<TaiKyoKu>();

        String username = request.getUsername();
        try {
            if (!codes.isEmpty()) {
                List<TaiKyokuTable> taiKyokuTables = taiKyokuDao.getByLogs(codes);
                if (!CollectionUtils.isEmpty(taiKyokuTables)) {
                    for (TaiKyokuTable table : taiKyokuTables) {
                        dbCodes.add(table.getLogcode());
                        if (table.getUsername().equals(username)) {
                            Xml xml = new Xml(table.getContent(), table.getLogcode());
                            dbxmls.add(xml);
                        }
                    }
                    codes.removeAll(dbCodes);
                }

                if (!CollectionUtils.isEmpty(codes)) {
                    /**
                     * 需要写库的
                     */
                    remoteXmls = remotePaifuParseService.parseRemotePaifuWithLogCodeList(codes);
                    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    for (Xml xml : remoteXmls) {
                        Document doc = xmlReadService.convertToDocument(xml.getXml(), db);
                        TaiKyoKu taiKyoKu = xmlAnalysisService.analysisDocument(doc);

                        /**
                         * 写库
                         */
                        TaiKyokuTable taiKyokuTable = new TaiKyokuTable();
                        taiKyokuTable.setContent(xml.getXml());
                        taiKyokuTable.setLogcode(xml.getCode());
                        taiKyokuTable.setMode(taiKyoKu.getType().getCode());
                        taiKyokuTable.setUsername(username);
                        taiKyokuDao.insert(taiKyokuTable);
                        taiKyoKus.add(taiKyoKu);
                    }
                }

                List<Document> documents = xmlReadService.convertToDocuments(dbxmls);
                taiKyoKus.addAll(xmlAnalysisService.analysisDocuments(documents));
                StaticsResultModel model = staticsService.analysis(taiKyoKus, request.getUsername());
                results.put("data", model);
            }

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

        results.put("username", request.getUsername());
        return results;
    }


}
