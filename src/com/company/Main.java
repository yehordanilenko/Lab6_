package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class Main {

    public static final int MS_TIMEOUT = 50;
    public static final int TIMEOUT = 30000;

    public static void main(String[] args) throws IOException, InterruptedException {
        // https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json
        /*Metas metas = new Metas();
        String quotesStr = metas.loadByURL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        metas.restoreObject(quotesStr);
        System.out.println("kkk\n");
        System.out.println(metas + "\n");*/

        JSONGetter jsonGetter = new JSONGetter();
        JSONGetter.url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        jsonGetter.run();
        int msForWaiting = 0;
        do {
            msForWaiting += MS_TIMEOUT;
            Thread.sleep(MS_TIMEOUT); //milliseconds

        } while (msForWaiting <= TIMEOUT && jsonGetter.jsonIn == "");
        if (msForWaiting >= TIMEOUT) {
            System.out.println("Не удалось загрузить данные");

            return;
        }
        System.out.println("\nКол-во миллисекунд загрузки данных: \n" + msForWaiting);
        String jsonString = jsonGetter.jsonIn;

        Object tempObj = null;
        try {
            tempObj = new JSONParser().parse(jsonString);
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = (JSONArray) tempObj;
        System.out.println(jsonArray.toJSONString());

        Metas metas = new Metas();

        for (Object jsonObject : jsonArray) {
            JSONObject getMeta = (JSONObject) jsonObject;
            long r030 = (long) getMeta.get("r030");
            String txt = (String) getMeta.get("txt");
            double rate = Double.parseDouble(Double.toString((Double) getMeta.get("rate")));
            String cc = (String) getMeta.get("cc");
            String exchangedate = (String) getMeta.get("exchangedate");

            Meta newMeta = new Meta(r030, txt, rate, cc, exchangedate);
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
