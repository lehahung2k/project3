package com.hungle.mbf.learnspring.service;

import com.hungle.mbf.learnspring.entities.Users;

import java.util.List;

public interface UserService {
    public List<Users> getListUsers();
    public void saveUser(Users user);
    public Users getUserById(Integer uid);
    public Users deleteUser(Integer uid);
}
