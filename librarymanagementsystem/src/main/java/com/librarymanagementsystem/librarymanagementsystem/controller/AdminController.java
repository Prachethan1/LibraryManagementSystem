package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.dto.AdminLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.Admin;
import com.librarymanagementsystem.librarymanagementsystem.exception.AdminException;
import com.librarymanagementsystem.librarymanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/addAdmin")
    public Admin addUser(@RequestBody Admin admin) throws AdminException {
        return adminService.addAdmin(admin);

    }

    @GetMapping("/adminId/{id}")
    public Admin getAdmin(@PathVariable int id) throws AdminException{
       return adminService.getAdmin(id);

    }

    @GetMapping("/admins")
    public List<Admin> getAdmins() throws AdminException{
        return adminService.getAdmins();

    }

    @PutMapping("/updateAdmin")
    public Admin updateAdmin(@RequestBody Admin updatedAdmin) throws AdminException {
        return adminService.updateAdmin(updatedAdmin);
    }

    @DeleteMapping("/deleteAdmin/{id}")
    public void deleteUser(@PathVariable int id) throws AdminException{
        adminService.deleteAdmin(id);
    }

    @PostMapping("/loginAdmin")
    public String  loginUser(@RequestBody AdminLoginDetails adminLoginDetails) throws AdminException{
        Admin loginAdmin = adminService.adminLogin(adminLoginDetails.getName(), adminLoginDetails.getPassword());
        if(loginAdmin!=null) {
            return "login successful";
        }
        else {
            return "login unsuccessful";
        }
    }

}
