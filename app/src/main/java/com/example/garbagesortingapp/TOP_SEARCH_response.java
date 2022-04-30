package com.example.garbagesortingapp;

import java.util.List;

public class TOP_SEARCH_response {
    private int code;
    private String msg;
    private List<newslist> newslists;
    /*private String name;
    private int type;
    private int index;*/

    /*public TOP_SEARCH_response(){
        this.name = name;
        this.type = type;
        this.index = index;
    }*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public List<newslist> getTop_sear_list(){
        return  newslists;
    }

    public void setTop_sear_list(List<newslist> top_sear_list){
        this.newslists= top_sear_list;
    }

    private static class newslist {
        private String name;
        private int type;
        private int index;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "newslist{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", index=" + index +
                    '}';
        }
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "TOP_SEARCH_response{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", index=" + index +
                '}';
    }*/
}
