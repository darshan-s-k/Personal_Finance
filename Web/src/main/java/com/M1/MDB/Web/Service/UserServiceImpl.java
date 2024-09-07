package com.M1.MDB.Web.Service;


import com.M1.MDB.Web.Model.User;
import com.M1.MDB.Web.Repository.UserRepository;
import com.M1.MDB.Web.Util.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // Hash the password before saving
        user.setPassword(HashingUtil.hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);

    }
}


