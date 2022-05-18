package com.nttu.dacnsv.Request;

public class GroupResponse {
    private String id;
    private int count;
    private int total;

    public GroupResponse(String id, int count, int total) {
        this.id = id;
        this.count = count;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
