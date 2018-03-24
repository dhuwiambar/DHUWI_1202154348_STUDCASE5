package com.example.lenovo.dhuwi_1202154348_studycase5;

/**
 * Created by LENOVO on 23/03/2018.
 */

public class itemtodo {
    String name, description, priority;

    public itemtodo(String name, String description, String priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}


