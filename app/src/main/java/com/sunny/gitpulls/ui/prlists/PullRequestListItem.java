package com.sunny.gitpulls.ui.prlists;

public class PullRequestListItem {
    public int id;
    public String description;
    public String url;
    public int number;

    public PullRequestListItem(int id, String description,String url,int number) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.number = number;
    }

    @Override
    public String toString() {
        return description;
    }
}
