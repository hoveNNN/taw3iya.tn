package Formcom.example.Taaw3iya.business.services;

import java.util.List;
import java.util.Optional;

import Formcom.example.Taaw3iya.dao.entities.User;

public interface IUserService {

    public Optional<User> getUserbyId(Long id);

    public void deleteUser(Long id);

    public List<User> getUsers();


}
