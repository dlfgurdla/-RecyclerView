package com.cos.viewtest.provider;

import com.cos.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonProvider {

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i=1; i<21; i++) {
            persons.add(new Person("이름"+i,"010222222"));
        }
       return persons;
    }
}
