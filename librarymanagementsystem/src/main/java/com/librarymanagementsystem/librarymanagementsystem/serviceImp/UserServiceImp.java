package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;
import com.librarymanagementsystem.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.librarymanagementsystem.repository.UserRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public User addUser(User user) throws UserException {
        if(userRepository.findByPhone(user.getPhone())!= null) {
            throw new UserException("user details already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(int id) throws UserException {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) throws UserException{
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);

        existingUser.setName(user.getName());
        existingUser.setPhone(user.getPhone());
        existingUser.setBranch(user.getBranch());
        existingUser.setAddress(user.getAddress());
        existingUser.setNoOfBooksIssued(user.getNoOfBooksIssued());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) throws UserException{
         userRepository.deleteById(id);
    }

    @Override
    public User userLogin(String name, String password){
        User user = userRepository.findByName(name);
        if(user!=null && user.getPassword().equals(password)){
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public List<User> getUsers() throws UserException{
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void updateTotalFine(User user, double fine) throws UserException {
        user.setTotalFine(user.getTotalFine() + fine);
        userRepository.save(user);
    }

    @Override
    public void payFine(User user, double amount) throws UserException {
        double currentFine = user.getTotalFine();
        if (currentFine == amount) {
            user.setTotalFine(0.0);
        } else if (currentFine > amount) {
            user.setTotalFine(currentFine - amount);
        } else{
            user.setTotalFine(currentFine-amount);
        }
        userRepository.save(user);
    }
}
