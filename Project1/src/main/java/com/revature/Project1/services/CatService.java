package com.revature.Project1.services;


import com.revature.Project1.models.Cat;

import java.util.List;

public interface CatService
{
    public Cat addCat(int userId, Cat cat);
    public Cat getCat(Integer id);
    public List<Cat> getAllCats();
    public Integer updateCat(Integer catId);
    public Integer deleteCat(Integer catId);
    public List<Cat> getAllCatsFromOwner(Integer ownerId);
}
