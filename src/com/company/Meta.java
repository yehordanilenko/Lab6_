
package com.company;

import java.io.Serializable;
import java.util.Comparator;

public class Meta implements Serializable
{

    long r030;
    String txt;
    double rate;
    String cc;
    String exchangedate;
    private final static long serialVersionUID = 4976987307641598477L;

    public Meta(long r030, String txt, double rate, String cc, String exchangedate) {
        this.setR030(r030);
        this.setTxt(txt);
        this.setRate(rate);
        this.setCc(cc);
        this.setExchangedate(exchangedate);
    }

    public Meta() {

    }
    public long getR030() {
        return r030;
    }

    public void setR030(long r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    public static Comparator<Meta> byr030Asc =Comparator.comparing(o -> o.r030);
    public static Comparator<Meta> byRateAsc = (o1, o2) -> o1.rate > o2.rate ? 1 : o1.rate < o2.rate ? -1 : 0;
    public static Comparator<Meta> byRateDesc = (o1, o2) -> o1.rate < o2.rate ? 1 : o1.rate > o2.rate ? -1 : 0;
    public static Comparator<Meta> byNameAsc = Comparator.comparing(o -> o.txt);

    @Override
    public String toString() {
        return "Meta{" +
                "r030=" + r030 +
                ", txt='" + txt + '\'' +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                '}';
    }
}
