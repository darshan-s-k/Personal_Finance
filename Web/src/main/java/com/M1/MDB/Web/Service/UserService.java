package com.M1.MDB.Web.Service;


import com.M1.MDB.Web.Model.User;

public interface UserService {
    User createUser(User user);
    User findByEmail(String email);
}
