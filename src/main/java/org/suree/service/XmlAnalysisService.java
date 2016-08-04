package org.suree.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sure on 7/23/16.
 */

@Service
public class XmlAnalysisService {

    //牌山字符串
    private String yama;




    public Map<String, Object> analysisDocument(Document doc) {

        NodeList element = doc.getElementsByTagName("mjloggm");
        Node node = element.item(0);
        NodeList list = node.getChildNodes();
        int length = list.getLength();
        Map<String, Object> result = new HashMap<String, Object>();


        for (int i = 0; i < length; i++) {
            Node temp = list.item(i);
            String nodeName = temp.getNodeName();
            NamedNodeMap map = temp.getAttributes();

            processNode(map, nodeName);

        }

        return result;
    }

    private void processNode(NamedNodeMap map, String nodeName) {
        if (nodeName.equals("SHUFFLE")) {

        } else if (nodeName.equals("GO")) {

        } else if (nodeName.equals("UN")) {
        } else if (nodeName.equals("TAIKYOKU")) {
        } else if (nodeName.equals("INIT")) {
        } else if (nodeName.equals("REACH")) {
        } else if (nodeName.equals("N")) {
        } else if (nodeName.equals("RYUUKYOKU")) {
        } else if (nodeName.equals("AGARI")) {
        } else {
        }
    }


}
