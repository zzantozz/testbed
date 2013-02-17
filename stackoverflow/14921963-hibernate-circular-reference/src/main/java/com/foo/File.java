package com.foo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: ryan
 * Date: 2/17/13
 * Time: 9:21 AM
 */
@Entity
public class File {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToOne
    private Folder rootFolder;

    public void setRootFolder(Folder rootFolder) {
        this.rootFolder = rootFolder;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "File{" +
                "name='" + name + '\'' +
                ", rootFolder=" + (rootFolder == null ? null : rootFolder.getName()) +
                '}';
    }
}