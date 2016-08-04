package org.suree.service;

import org.springframework.stereotype.Service;
import org.suree.constant.Mode;
import org.suree.model.TaiKyoKu;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sure on 7/23/16.
 */

@Service
public class XmlAnalysisService {



    public TaiKyoKu analysisDocument(Document doc) {
        NodeList element = doc.getElementsByTagName("mjloggm");
        Node node = element.item(0);
        NodeList list = node.getChildNodes();
        int length = list.getLength();
        TaiKyoKu taiKyoKu = new TaiKyoKu();
        for (int i = 0; i < length; i++) {
            Node temp = list.item(i);
            String nodeName = temp.getNodeName();
            NamedNodeMap map = temp.getAttributes();
            processNode(map, nodeName, taiKyoKu);
        }
        return taiKyoKu;
    }

    private void processNode(NamedNodeMap map, String nodeName, TaiKyoKu taiKyoKu) {

        try {
            if (nodeName.equals("SHUFFLE")) {
                /**
                 * 牌山
                 */
                Node ref = map.item(0);
                Node seed = map.item(1);
                taiKyoKu.setYama(seed.getNodeValue());
            } else if (nodeName.equals("GO")) {
                /**
                 * 对局类型与房间
                 */
                for (int i = 0; i < map.getLength(); i++) {
                    Node node = map.item(i);
                    if (node.getNodeName().equals("type")) {
                        Integer typeValue = Integer.parseInt(node.getNodeValue());
                        Mode mode = Mode.getByCode(typeValue);
                        taiKyoKu.setType(mode);
                    } else if (node.getNodeName().equals("lobby")){
                        taiKyoKu.setLobby(Integer.parseInt(node.getNodeValue()));
                    } else {

                    }
                }
            } else if (nodeName.equals("UN")) {
                /**
                 * 对局者信息
                 */
                for (int i = 0; i < map.getLength(); i++) {
                    Node node = map.item(i);
                    if (node.getNodeName().equals("n0")) {
                        taiKyoKu.setPlayer0(getName(node.getNodeValue()));
                    } else if (node.getNodeName().equals("n1")) {
                        taiKyoKu.setPlayer1(getName(node.getNodeValue()));
                    } else if (node.getNodeName().equals("n2")) {
                        taiKyoKu.setPlayer2(getName(node.getNodeValue()));
                    } else if (node.getNodeName().equals("n3")) {
                        taiKyoKu.setPlayer3(getName(node.getNodeValue()));
                    } else {

                    }
                }

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
        } catch (Exception e) {

        }
    }
    private String getName(String encodedString) {
        try {
            return URLDecoder.decode(encodedString, "UTF-8");
        } catch (Exception e) {
            return "Unknow";
        }
    }
}
