package org.suree.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Service;


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


    public List<String> parseRemotePaifuWithLogCodeList(List<String> logCodes) {
        if (logCodes == null || logCodes.isEmpty()) {
            return new ArrayList<String>();
        }
        String path = "http://e.mjv.jp/0/log/?";
        Iterator ic = logCodes.iterator();
        List<String> xmls = new ArrayList<String>();
        try {

            HttpClient httpclient = new DefaultHttpClient();
            while (ic.hasNext()) {
                String tempPath = path + ic.next();
                URL url = new URL(tempPath);
                HttpGet request = new HttpGet(tempPath);
                HttpResponse response = httpclient.execute(request);

                BufferedReader rd = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                xmls.add(result.toString());
            }
        } catch (Exception e) {
            //todo error handle
            System.out.printf(e.getMessage());
        }
        return xmls;
    }
}
