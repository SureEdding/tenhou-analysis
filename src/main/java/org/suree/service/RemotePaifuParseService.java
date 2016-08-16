package org.suree.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;
import org.suree.model.Xml;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Sure on 8/6/16.
 */

@Service
public class RemotePaifuParseService {


    public List<Xml> parseRemotePaifuWithLogCodeList(List<String> logCodes) {
        if (logCodes == null || logCodes.isEmpty()) {
            return new ArrayList<Xml>();
        }
        String path = "http://e.mjv.jp/0/log/?";
        Iterator ic = logCodes.iterator();
        List<Xml> xmls = new ArrayList<Xml>();
        try {

            HttpClient httpclient = new DefaultHttpClient();
            while (ic.hasNext()) {
                String code = (String) ic.next();
                String tempPath = path + code;
                URL url = new URL(tempPath);
                HttpGet request = new HttpGet(tempPath);
                HttpResponse response = httpclient.execute(request);

                //TODO 404 handle
                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                if (!result.toString().equals("")) {
                    Xml xml = new Xml(result.toString(), code);
                    xmls.add(xml);
                }
            }
        } catch (Exception e) {
            //todo error handle
            System.out.printf(e.getMessage());
        }
        return xmls;
    }
}
