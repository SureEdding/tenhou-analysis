package org.suree.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public List<Document> convertToDocuments(List<String> xmls) {
        List<Document> documents = new ArrayList<Document>();
        if (xmls == null || xmls.isEmpty()) {
            return documents;
        }
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Iterator<String> i = xmls.iterator();
            while (i.hasNext()) {
                String xml = i.next();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xml));
                Document doc = db.parse(is);
                documents.add(doc);
            }
        } catch (Exception e) {
            //TODO error handle
            return documents;
        }
        return documents;
    }


}
