package rest;

import java.util.List;
import data.entidades.Evento.Attributes;

public class ResponseData {
    private List<Attributes> data;

    // Getters and setters for data
    public List<Attributes> getData() {
        return data;
    }

    public void setData(List<Attributes> data) {
        this.data = data;
    }
}
