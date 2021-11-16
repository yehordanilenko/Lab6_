package com.company;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Main {

    public static void main(String[] args) throws IOException {
	// https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json
        /*Metas metas = new Metas();
        String quotesStr = metas.loadByURL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        metas.restoreObject(quotesStr);
        System.out.println("kkk\n");
        System.out.println(metas + "\n");*/

        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        jsonGetter.run();

        String jsonString = jsonGetter.jsonIn;

        Object tempObj = null;
        try
        {
            tempObj = new JSONParser().parse(jsonString);
        }
        catch (org.json.simple.parser.ParseException e)
        {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) tempObj;
        System.out.println(jsonArray.toJSONString());

        Metas metas = new Metas();

        for (Object jsonObject : jsonArray)
        {
            JSONObject getMeta = (JSONObject) jsonObject;
            long r030 = (long) getMeta.get("r030");
            String txt = (String) getMeta.get("txt");
            double rate = Double.parseDouble(Double.toString((Double) getMeta.get("rate")));
            String cc = (String) getMeta.get("cc");
            String exchangedate = (String) getMeta.get("exchangedate");

            Meta newMeta = new Meta(r030, txt, rate , cc, exchangedate);
            metas.add(newMeta);
        }

        System.out.println(metas);

        // Сортировка по возрастанию курса валюты
        metas.getMetas().sort(Meta.byRateAsc);
        System.out.println("After sorting by rate ascending:\n" + metas + "\n");

        // Сортировка в алфавитном порядке по наименованию валюты
        metas.getMetas().sort(Meta.byNameAsc);
        System.out.println("After sorting by name ascending:\n" + metas + "\n");

        // Фильтрация по валютам, имеющие букву "р" в своём наименовании

        Metas withR = metas.filterByCCY("р");
        System.out.println("Filtered txt with 'р' in title:\n" + withR + "\n");

    }
}
