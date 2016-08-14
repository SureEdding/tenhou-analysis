package org.suree.model;

import java.util.List;

/**
 * Created by Sure on 8/14/16.
 */
public class Request {

    private List<String> logcodes;
    private String username;


    public List<String> getLogcodes() {
        return logcodes;
    }

    public void setLogcodes(List<String> logcodes) {
        this.logcodes = logcodes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
