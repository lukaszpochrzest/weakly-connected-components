package org.wcc.ui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.wcc.ui.exception.InvalidGraphFileFormatException;

public class GraphConfigParser {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static GraphConfig toObject(String json) throws InvalidGraphFileFormatException {
        try{
            return gson.fromJson(json, GraphConfig.class);
        } catch(Exception e) {
            throw new InvalidGraphFileFormatException();
        }
    }

    public static String toJson(GraphConfig src) {
        return gson.toJson(src);
    }

}
