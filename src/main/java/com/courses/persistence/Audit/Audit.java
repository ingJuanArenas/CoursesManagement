package com.courses.persistence.Audit;


import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;

public class Audit {
    

    @PostLoad
    public void postLoad(Object entity) {
        System.out.println("POST LOAD");
        System.out.println("Object loaded: " + entity.toString());
    }


    @PreRemove
    public void preRemove(Object entity) {
        System.out.println("PRE REMOVE");
        System.out.println("Object about to be removed: " + entity.toString());
    }

    @PostPersist
    public void postPersist(Object entity) {
       System.out.println("POST PERSIST");
       System.out.println("Object persisted" + entity.toString());
    }


    @PostUpdate
    public void postUpdate(Object entity) {
        System.out.println("POST UPDATE");
        System.out.println("Object updated: " + entity.toString());
    }



}
