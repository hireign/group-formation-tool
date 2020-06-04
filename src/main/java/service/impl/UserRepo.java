package service.impl;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import dao.User;

@Repository("userRepository")
public interface UserRepo extends CrudRepository<User, String> {
    User findByEmailIdIgnoreCase(String emailId);
}