package org.suree.service;

import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Service;
import org.suree.constant.Yaku;
import org.suree.model.Round;
import org.suree.model.StaticsResultModel;
import org.suree.model.TaiKyoKu;

import java.util.*;

/**
 * Created by Sure on 8/14/16.
 */

@Service
public class StaticsService {


    private String userName;

    public StaticsResultModel analysis(List<TaiKyoKu> taiKyoKus, String userName) {
        StaticsResultModel model = initModel(userName);

        model.setUserName(userName);
        setUserName(userName);
        taiKyoKus = cleanTaikyokku(taiKyoKus);
        if (taiKyoKus.isEmpty()) {
            return model;
        }

        double roundCount = 0.0;
        double taiCount = 0.0;
        double rank = 0.0;
        double reach = 0.0;
        double agari = 0.0;
        double agariPoints = 0.0;

        double jyu = 0.0;
        double jyuPoints = 0.0;

        double ryukyoku = 0.0;

        for (TaiKyoKu t : taiKyoKus) {

            Integer position = getPosition(t);



            LinkedList<Round> rounds = t.getResults();
            for (Round round : rounds) {
                //立直
                if (round.getReach().contains(position)) {
                    reach++;
                }
                if (round.getAgari()) {
                    if (round.getWho().contains(position)) {
                        //主视角和了
                        agari++;
                        agariPoints += round.getTens().get(position).doubleValue();
                        List<Yaku> yakus = round.getYakus().get(position);
                        for (Yaku yaku : yakus) {
                            Double temp = model.getYakusRate().get(yaku.getCode()) + 1.0;
                            model.getYakusRate().put(yaku.getCode(), temp);
                        }

                    } else if (round.getFromWho().equals(position)) {
                        //主视角铳了
                        jyu++;
                        for (Map.Entry<Integer, Integer> entry : round.getTens().entrySet()) {
                            jyuPoints += entry.getValue().doubleValue();
                        }
                    }
                } else if (round.getRyukyoku()) {
                    ryukyoku++;
                }


                roundCount++;
            }



            //顺位
            Round lastRound = rounds.getLast();
            rank += getRank(lastRound, position);
            taiCount++;
        }



        model.setAvRank(rank/taiCount);
        model.setRiichiRate(reach/roundCount);
        model.setAgariRate(agari/roundCount);
        model.setAvAgariPoints(agariPoints/agari);
        model.setJyuRate(jyu/roundCount);
        model.setAvjyuPoints(jyuPoints/jyu);
        model.setRyukyokuRate(ryukyoku/roundCount);
        model.setTaikyokuCount(((Double)taiCount).intValue());
        model.setRoundCount(((Double)roundCount).intValue());


        for (int i = 0; i < 55; i++) {
            model.getYakusRate().put(i, model.getYakusRate().get(i)/agari);
        }
        return model;
    }



    private List<TaiKyoKu> cleanTaikyokku(List<TaiKyoKu> taiKyoKus) {
//        TaikyokuFilter filter = new TaikyokuFilter();
        //TODO 优化点
        List<TaiKyoKu> results = new ArrayList<TaiKyoKu>();
        for (TaiKyoKu taiKyoKu : taiKyoKus) {
            if (taiKyoKu.getPlayer0().equals(getUserName()) ||
                    taiKyoKu.getPlayer1().equals(getUserName()) ||
                    taiKyoKu.getPlayer2().equals(getUserName()) ||
                    taiKyoKu.getPlayer3().equals(getUserName())) {
                results.add(taiKyoKu);
            }
        }
        return results;
    }



    private class TaikyokuFilter implements Predicate{
        public boolean evaluate(Object o) {

            if (o instanceof TaiKyoKu) {

                if (((TaiKyoKu) o).getPlayer0().equals(getUserName()) ||
                        ((TaiKyoKu) o).getPlayer1().equals(getUserName()) ||
                        ((TaiKyoKu) o).getPlayer2().equals(getUserName()) ||
                        ((TaiKyoKu) o).getPlayer3().equals(getUserName())) {
                    return true;
                }
            }
            return false;
        }
    }

    private Integer getPosition(TaiKyoKu taiKyoKu) {
        if (taiKyoKu.getPlayer0().equals(getUserName())){
            return 0;
        } else if (taiKyoKu.getPlayer1().equals(getUserName())) {
            return 1;
        } else if (taiKyoKu.getPlayer2().equals(getUserName())) {
            return 2;
        } else {
            return 3;
        }
    }

    private Double getRank(Round lastRound, Integer position) {
        ArrayList<Double> tens = new ArrayList<Double>(4);

        tens.add(0, lastRound.getInitTens().get(0).doubleValue() + lastRound.getTens().get(0).doubleValue());
        tens.add(1, lastRound.getInitTens().get(1).doubleValue() + lastRound.getTens().get(1).doubleValue());
        tens.add(2, lastRound.getInitTens().get(2).doubleValue() + lastRound.getTens().get(2).doubleValue());
        tens.add(3, lastRound.getInitTens().get(3).doubleValue() + lastRound.getTens().get(3).doubleValue());
        Double lastTens = tens.get(position);
        Double rank = 0.0;
        for (Double ten : tens) {
            if (ten > lastTens) rank++;
        }
        return rank + 1;
    }

    private StaticsResultModel initModel(String userName) {
        StaticsResultModel model = new StaticsResultModel();
        model.setYakusRate(initYakusMap());
        model.setUserName(userName);
        model.setAgariRate(0.0);
        model.setAvAgariPoints(0.0);
        model.setAvjyuPoints(0.0);
        model.setFuuruRate(0.0);
        model.setJyuRate(0.0);
        model.setRiichiRate(0.0);
        model.setRyukyokuRate(0.0);
        model.setAvRank(0.0);
        return model;
    }

    private Map<Integer, Double> initYakusMap() {
        Map<Integer, Double> map = new HashMap<Integer, Double>();

        for (int i = 0; i < 55; i++) {
            map.put(i, 0.0);
        }
        return map;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
