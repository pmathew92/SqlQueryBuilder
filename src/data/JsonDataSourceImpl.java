package data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import helper.FileValidator;
import interfaces.JsonDataSource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonDataSourceImpl implements JsonDataSource {

    private FileValidator fileValidator;
    private Gson gson = new Gson();

    public JsonDataSourceImpl(FileValidator fileValidator) {
        this.fileValidator = fileValidator;
    }

    @Override
    public void fetchJsonData(String path) {
        try {
            FileReader reader = new FileReader(path);
            JsonElement jsonElement = JsonParser.parseReader(reader);
            System.out.println(jsonElement.toString());
            reader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
