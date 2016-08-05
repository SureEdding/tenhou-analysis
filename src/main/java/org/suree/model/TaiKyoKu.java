package org.suree.model;

import org.suree.constant.Mode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sure on 7/23/16.
 */
public class TaiKyoKu {

    private String yama;
    private Mode type;
    private Integer lobby;
    private String player0;
    private String player1;
    private String player2;
    private String player3;
    private LinkedList<Round> results = new LinkedList<Round>();


    public String getYama() {
        return yama;
    }

    public void setYama(String yama) {
        this.yama = yama;
    }

    public Mode getType() {
        return type;
    }

    public void setType(Mode type) {
        this.type = type;
    }

    public Integer getLobby() {
        return lobby;
    }

    public void setLobby(Integer lobby) {
        this.lobby = lobby;
    }

    public String getPlayer0() {
        return player0;
    }

    public void setPlayer0(String player0) {
        this.player0 = player0;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public LinkedList<Round> getResults() {
        return results;
    }

    public void setResults(LinkedList<Round> results) {
        this.results = results;
    }
}
