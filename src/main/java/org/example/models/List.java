package org.example.models;

public class List {
    public String id;
    public String name;
    public String content;

    public boolean deleted;

    @Override
    public String toString() {
        return "List{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
