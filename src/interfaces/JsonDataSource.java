package interfaces;

import model.Query;

public interface JsonDataSource {

    public Query fetchJsonData(String path);
}
