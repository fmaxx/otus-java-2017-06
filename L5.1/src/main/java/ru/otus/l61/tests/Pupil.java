package ru.otus.l61.tests;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by maximfirsov on 08/07/2017.
 */
public class Pupil {
    private int age = 36;
    private Set<String> skills = new HashSet<>();

    public Pupil() {

    }

    public boolean addSkill(String skill){
        return skills.add(skill);
    }

    public String[] getAllSkills(){
        return (String[]) skills.toArray();
    }

}
