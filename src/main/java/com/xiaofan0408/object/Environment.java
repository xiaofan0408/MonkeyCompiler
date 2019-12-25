package com.xiaofan0408.object;

import java.util.Map;

public class Environment {

    private Map<String,MObject> store;

    private Environment outer;

    public Environment(Map<String, MObject> store) {
        this.store = store;
    }

    public Environment(Map<String, MObject> store, Environment outer) {
        this.store = store;
        this.outer = outer;
    }

    public MObject get(String name){
        MObject obj = store.get(name);
        if (obj == null) {
            if (outer != null) {
                return outer.get(name);
            }
            return null;
        } else {
            return obj;
        }
    }

    public MObject set(String name,MObject val){
        store.put(name,val);
        return val;
    }
}
