package com.example.motorcycle.Model;

import java.util.List;

public class ModelResponse {
    private  String kode, pesan;

    private List<ModelMotor> data;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<ModelMotor> getData() {
        return data;
    }
}
