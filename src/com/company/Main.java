package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json
        Metas metas = new Metas();
        String quotesStr = metas.loadByURL("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json");
        metas.restoreObject(quotesStr);
        System.out.println("kkk\n");
        System.out.println(metas + "\n");

        metas.getMetas().sort(Meta.byRateAsc);
        System.out.println("After sorting by rate ascending:\n" + metas + "\n");

        metas.getMetas().sort(Meta.byRateeDesc);
        System.out.println("After sorting by rate descending:\n" + metas + "\n");

        metas.getMetas().sort(Meta.byr030Asc);
        System.out.println("After sorting by r030 ascending:\n" + metas + "\n");

        metas.getMetas().sort(Meta.byNameAsc);
        System.out.println("After sorting by name ascending:\n" + metas + "\n");

        Metas withUR = metas.filterByCCY("р");
        System.out.println("Filtered data with 'Євро' in title:" + withUR);

    }
}
