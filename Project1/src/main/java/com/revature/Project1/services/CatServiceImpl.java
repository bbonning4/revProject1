package com.revature.Project1.services;

import com.revature.Project1.repositories.CatRepo;
import com.revature.Project1.models.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService
{

    @Autowired
    CatRepo cr;

    public CatServiceImpl(CatRepo cr)
    {
        this.cr = cr;
    }

    @Override
    public Cat getCat(Integer id) {
        return cr.findById(id).orElseGet(Cat::new);
    }

    @Override
    public List<Cat> getAllCats() {
        return cr.findAll();
    }

    @Override
    public Integer updateCat(Integer catId, Cat newCat) {
        Optional<Cat> optionalCat = cr.findById(catId);
        if(optionalCat.isPresent()){
            Cat updatedCat = optionalCat.get();
            updatedCat.setColor(newCat.getColor());
            updatedCat.setName(newCat.getName());
            cr.save(updatedCat);
            return 1;
        }
        return 0;
    }

    @Override
    public Integer deleteCat(Integer catId)
    {
        if(getCat(catId) != null)
        {
            cr.deleteById(catId);
            return 1;
        }
        return 0;
    }

    @Override
    public Cat addCat(Cat c)
    {
        return cr.save(c);
    }


}