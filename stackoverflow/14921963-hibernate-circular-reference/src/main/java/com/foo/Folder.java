package com.foo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/17/13
 * Time: 9:21 AM
 */
@Entity
public class Folder{
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @OneToMany
    @JoinColumn(name = "folder_id")
    private List<Folder> folders = new ArrayList<Folder>();

    @OneToMany
    @JoinColumn(name = "file_id")
    private List<File> files = new ArrayList<File>();

    public List<File> getFiles() {
        return files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "name='" + name + '\'' +
                ", folders=" + folders +
                ", files=" + files +
                '}';
    }
}