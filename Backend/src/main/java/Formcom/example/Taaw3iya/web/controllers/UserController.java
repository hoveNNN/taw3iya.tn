package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.serviceslmpl.UserServiceImpl;
import Formcom.example.Taaw3iya.dao.entities.User;
import Formcom.example.Taaw3iya.web.dto.AdminAddUserDTO;
import Formcom.example.Taaw3iya.web.models.requests.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static Formcom.example.Taaw3iya.dao.enums.Role.USER;
import static Formcom.example.Taaw3iya.dao.enums.Role.ADMIN;
import java.util.Optional;

@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/allUsers")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getAllUserr(), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/{id}")
    public Optional<User> getUserbyId(@PathVariable("id") Long id) {
        return userService.getUserr(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value="delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id")Long id) {
        Optional<User> user=userService.getUserr(id);

        if(user.isPresent()){
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed: User not found",HttpStatus.NOT_FOUND);
        }
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value="changeRole/{id}")
    public ResponseEntity<?> changeRole(@PathVariable("id")Long id) {
        Boolean test=userService.chnageRole(id);

        if(test=true){
            return new ResponseEntity<>("User Role has been changed successfully",HttpStatus.OK);

        }else{
            return new ResponseEntity<>("failed: User not found",HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value="/AdminAddUser")
    public ResponseEntity<?> AdminaddUser(@RequestBody AdminAddUserDTO data) {


        Boolean test=userService.AdminAddUser2(data);
        if(test){
            return new ResponseEntity<>("User added successfully",HttpStatus.OK);
        }
        else return new ResponseEntity<>("failed: User not found",HttpStatus.NOT_FOUND);
    }
}
