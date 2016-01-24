package com.wikisearch.android.wikiservice.pagesearch;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.wikisearch.android.entity.WikiBaseObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by veena.joshi on 1/24/2016.
 */
public class WikiPagesAdapter implements JsonDeserializer<WikiPageSearchResponse> {

    @Override
    public WikiPageSearchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        WikiPageSearchResponse response = new WikiPageSearchResponse();
        JsonObject jsonObject = json.getAsJsonObject();

        List<WikiBaseObject> wikiPageList = new ArrayList<WikiBaseObject>();

        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()){

            WikiPage wikiPage = context.deserialize(entry.getValue(), WikiPage.class);
            wikiPageList.add(wikiPage);
        }
        response.setWikiBaseObjectList(wikiPageList);
        return (response);
    }
}
