package com.xiaofan0408.object;

import java.util.Map;

public class Environment {

    private Map<String,MObject> store;

    public Environment(Map<String, MObject> store) {
        this.store = store;
    }

    public MObject get(String name){
        MObject obj = store.get(name);
        return obj;
    }

    public MObject set(String name,MObject val){
        store.put(name,val);
        return val;
    }
}
