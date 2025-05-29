/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author SCC-COLLEGE
 */
public class Job {
    private int id;
    private String title;

    public Job(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    // This is important! This text will show up in the JComboBox dropdown
    @Override
    public String toString() {
        return title;
    }
}

