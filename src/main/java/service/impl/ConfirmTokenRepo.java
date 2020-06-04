package service.impl;

import org.springframework.data.repository.CrudRepository;

import model.ConfirmToken;

public interface ConfirmTokenRepo extends CrudRepository<ConfirmToken, String> {
    ConfirmToken findByConfirmationToken(String confirmationToken);
}