package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class Metas {
    @JsonProperty("metas")
    List<Meta> metas;

    public Metas() {
        metas = new ArrayList<>();
    }
    public List<Meta> getMetas() {
        return metas;
    }

    public void add(Meta meta) {
        this.metas.add(meta);
    }

    public Metas filterByCCY(String ccy) {
        Metas tempRats = new Metas();
        for (Meta meta : this.metas) {
            if (meta.getTxt().toLowerCase().contains(ccy.toLowerCase()))
                tempRats.add(meta);
        }
        return tempRats;
    }



    @Override
    public String toString() {
        return "Metas{" +
                "metas=" + metas +
                '}';
    }
}
