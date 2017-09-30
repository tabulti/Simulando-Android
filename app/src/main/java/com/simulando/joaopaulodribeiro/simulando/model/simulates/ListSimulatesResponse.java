package com.simulando.joaopaulodribeiro.simulando.model.simulates;

import java.util.List;

/**
 * Created by joao.paulo.d.ribeiro on 30/09/2017.
 */

public class ListSimulatesResponse {
    String status;
    List<Test> data;

    public String getStatus() {
        return status;
    }

    public List<Test> getData() {
        return data;
    }
}
