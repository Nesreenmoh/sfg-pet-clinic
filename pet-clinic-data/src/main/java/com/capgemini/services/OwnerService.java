package com.capgemini.services;

import com.capgemini.models.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameContaining(String lastName) ;


}
