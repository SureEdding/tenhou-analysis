package org.suree.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Sure on 7/16/16.
 */

@Service
public class XmlReadService {


    public Document getXmlFile(String filePath) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        try {

            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

            Document doc = builder.parse(filePath);

            return doc;

        } catch (Exception o) {
            System.out.print(o.getMessage());
        }
        return null;
    }


}
