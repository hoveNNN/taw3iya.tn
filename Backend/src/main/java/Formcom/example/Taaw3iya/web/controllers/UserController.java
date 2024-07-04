package Formcom.example.Taaw3iya.web.controllers;

import Formcom.example.Taaw3iya.business.serviceslmpl.UserServiceImpl;
import Formcom.example.Taaw3iya.dao.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserbyId(@PathVariable("id") Long id) {
        return userService.getUserbyId(id);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        Optional<User> user=userService.getUserbyId(id);

        if(user.isPresent()){
            userService.deleteUser(id);

            return new ResponseEntity<>("User deleted successfully",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("failed: User not found",HttpStatus.NOT_FOUND);
        }
    }
}
