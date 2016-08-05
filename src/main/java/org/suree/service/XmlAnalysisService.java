package org.suree.service;

import org.springframework.stereotype.Service;
import org.suree.constant.Mode;
import org.suree.model.Round;
import org.suree.model.TaiKyoKu;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

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
                 * 每一局的配牌信息,每出现一次INIT,代表一局的开始
                 */
                Round newRound = new Round();
                for (int i = 0; i < map.getLength(); i++) {
                    Node node = map.item(i);
                    if (node.getNodeName().equals("seed")) {

                    } else if (node.getNodeName().equals("ten")) {
                        String[] tens = node.getNodeValue().split(",");
                        newRound.getInitTens().put(0, Integer.parseInt(tens[0]));
                        newRound.getInitTens().put(1, Integer.parseInt(tens[1]));
                        newRound.getInitTens().put(2, Integer.parseInt(tens[2]));
                        newRound.getInitTens().put(3, Integer.parseInt(tens[3]));
                    } else if (node.getNodeName().equals("oya")) {
                        newRound.setOya(Integer.parseInt(node.getNodeValue()));
                    } else {
                        //TODO 每一局配牌信息
                    }
                }
                taiKyoKu.getResults().addLast(newRound);
            } else if (nodeName.equals("REACH")) {
                /**
                 * 立直
                 */
                Round round = taiKyoKu.getResults().getLast();
                Node node = map.getNamedItem("ten");
                if (node != null) {
                    round.getReach().add(Integer.parseInt(map.getNamedItem("who").getNodeValue()));
                } else {

                }
            } else if (nodeName.equals("N")) {
                /**
                 * 副露
                 */
            } else if (nodeName.equals("RYUUKYOKU")) {
                /**
                 * 流局
                 */
                Round round = taiKyoKu.getResults().getLast();
                Node ba = map.getNamedItem("ba");
                Node sc = map.getNamedItem("sc");
                Node type = map.getNamedItem("type");
                round.setRyukyoku(true);
                round.setAgari(false);
                String[] bas = ba.getNodeValue().split(",");

                round.setCombo(Integer.parseInt(bas[0]));
                round.setRiichi(Integer.parseInt(bas[1]));

                String[] scs = sc.getNodeValue().split(",");
                round.getTens().put(0, Integer.parseInt(scs[1]));
                round.getTens().put(1, Integer.parseInt(scs[3]));
                round.getTens().put(2, Integer.parseInt(scs[5]));
                round.getTens().put(3, Integer.parseInt(scs[7]));

                if (type != null) {

                }

            } else if (nodeName.equals("AGARI")) {
                /**
                 * 和牌
                 */
                Round round = taiKyoKu.getResults().getLast();
                round.setAgari(true);
                round.setRyukyoku(false);

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
