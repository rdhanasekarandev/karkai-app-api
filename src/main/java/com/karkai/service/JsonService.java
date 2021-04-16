package com.karkai.service;

import com.amazonaws.util.IOUtils;

import net.minidev.json.parser.ParseException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.IOException;
import java.net.URL;

public class JsonService {
    public JSONObject jsonParse(String url) throws IOException, ParseException {
        JSONObject jsonObject = (JSONObject) new JSONTokener(IOUtils.toString(new URL(url).openStream())).nextValue();
        return jsonObject;
    }
}
