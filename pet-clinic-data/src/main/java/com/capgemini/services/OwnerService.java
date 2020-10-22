package com.capgemini.services;

import com.capgemini.models.Owner;


public interface OwnerService extends CrudService<Owner,Long>{

    Owner findByLastName(String lastName);

}
