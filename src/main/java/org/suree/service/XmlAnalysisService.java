package org.suree.service;

import org.springframework.stereotype.Service;
import org.suree.model.TaiKyoKu;
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



    public Map<String, Object> analysisDocument(Document doc) {
        NodeList element = doc.getElementsByTagName("mjloggm");
        Node node = element.item(0);
        NodeList list = node.getChildNodes();
        int length = list.getLength();
        Map<String, Object> result = new HashMap<String, Object>();

        TaiKyoKu taiKyoKu = new TaiKyoKu();
        for (int i = 0; i < length; i++) {
            Node temp = list.item(i);
            String nodeName = temp.getNodeName();
            NamedNodeMap map = temp.getAttributes();

            processNode(map, nodeName, taiKyoKu);

        }

        return result;
    }

    private void processNode(NamedNodeMap map, String nodeName, TaiKyoKu taiKyoKu) {

        if (nodeName.equals("SHUFFLE")) {
            /**
             * 牌山
             */
        } else if (nodeName.equals("GO")) {
            /**
             * 对局类型与房间
             */
        } else if (nodeName.equals("UN")) {
            /**
             * 对局者信息
             */
        } else if (nodeName.equals("TAIKYOKU")) {
            /**
             * 庄
             */
        } else if (nodeName.equals("INIT")) {
            /**
             * 每一局的配牌信息
             */
        } else if (nodeName.equals("REACH")) {
            /**
             * 立直
             */
        } else if (nodeName.equals("N")) {
            /**
             * 副露
             */
        } else if (nodeName.equals("RYUUKYOKU")) {
            /**
             * 流局
             */
        } else if (nodeName.equals("AGARI")) {
            /**
             * 和牌
             */
        } else {
            /**
             * 摸牌与切牌
             */
        }
    }


}
