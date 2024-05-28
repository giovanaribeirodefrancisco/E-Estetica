package com.projeto;

import java.util.ArrayList;
import java.util.List;

public class Agenda{
    public List<Data> datas;

    public Agenda() {
        this.datas = new ArrayList<Data>();
    }

    public List<Data> getDatas() {
        return datas;
    }

    public void addData(Data data) {
        datas.add(data);
    }

    public Data getData(Data dataStr) {
        for (Data data : datas) {
            if (data.equals(dataStr)) {
                return data;
            }
        }
        return null;
    }

}
