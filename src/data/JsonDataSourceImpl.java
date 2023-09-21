package data;

import com.google.gson.*;
import helper.ValidatorUtil;
import interfaces.JsonDataSource;
import model.Query;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implementation of [JsonDataSource]  interface that fetches json from a file
 */
public class JsonDataSourceImpl implements JsonDataSource {

    @Override
    public Query fetchJsonData(String path) {
        if (!ValidatorUtil.isValidJsonFile(path)) {
            throw new RuntimeException("Exception: Not a json file");
        }
        Query query;
        try {
            FileReader reader = new FileReader(path);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            query = new Gson().fromJson(jsonElement, Query.class);
            reader.close();
            return query;
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (JsonIOException exception) {
            System.out.println("Error parsing json from reader");
            exception.printStackTrace();
        } catch (JsonSyntaxException exception) {
            System.out.println("Error in json syntax");
            exception.printStackTrace();
        }
        return null;
    }
}
