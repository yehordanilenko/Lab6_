package com.company;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
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
    public String loadByURL(String url) throws IOException {
        StringBuilder jsonIn = new StringBuilder();
        InputStream is = null;
        is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            int cp;
            while ((cp = rd.read()) != -1) {
                jsonIn.append((char) cp);
            }
            //System.out.println(jsonIn);
        } finally {
            is.close();
        }

        return jsonIn.toString();
    }

    public void restoreObject(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Meta [] newMeta = objectMapper.readValue(json.strip(), Meta[].class);
        this.metas.addAll(Arrays.stream(newMeta).toList());
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
