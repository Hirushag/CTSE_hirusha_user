package com.ctse.userservice.service;

import com.ctse.userservice.dto.Login;
import com.ctse.userservice.dto.UserDetails;
import com.ctse.userservice.model.User;
import com.ctse.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInt {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    //save user
    @Override
    public void createUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("error getting the save user " + e);
        }
    }

    // find all users
    @Override
    public List<User> users() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("error getting list of users " + e);
        }
    }

    //find single user
    @Override
    public Optional<User> getSingleUser(String id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("error getting find a single user " + e);
        }

    }

    //update user
    @Override
    public boolean editUser(User user, String id) {
        try {
            Optional<User> user2 = userRepository.findById(id);
            if (user2 == null) {
                return false;
            } else {
                User u1 = user2.get();
                u1.setUsername(user.getUsername());
                u1.setContactNumber(user.getContactNumber());
                u1.setEmail(user.getEmail());
                u1.setName(user.getName());
                u1.setRole(user.getRole());
                u1.setPassword(user.getPassword());
                u1.setIsActive(user.getIsActive());
                ;

                userRepository.save(u1);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("error getting update user " + e);
        }

    }

    //delete user
    @Override
    public boolean deleteUser(String id) {
        try {
            if (id == null) {
                return false;
            } else {
                userRepository.deleteById(id);
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException("error getting delete user " + e);
        }
    }
    @Override
    public List<User> userWithStatus(String status[]) {
        return( userRepository.findByUserRole(status));

    }
    //update user Role


    public UserDetails authenticateUser(Login login) {

        String username = login.getUsername();
        String password = login.getPassword();

        UserDetails userDetails = new UserDetails();
        User user = new User();

        try {

            user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(username)), User.class);

            String email = user.getEmail();
            String key = user.getPassword();

            if(email.equals(username) && password.equals(key)) {

                userDetails.setEmail(email);
                userDetails.setPassword(password);
                userDetails.setContactNumber(user.getContactNumber());
                userDetails.setIsActive(user.getIsActive());
                userDetails.setRole(user.getRole());
                userDetails.setId(user.getId());

                return userDetails;
            }else {


                return null;
            }

        }catch (Exception e) {

            return null;
        }

    }

}










