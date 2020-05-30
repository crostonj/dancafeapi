package com.danscafe.siteapi.repository;

import com.danscafe.siteapi.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository  extends MongoRepository<Role, String> {
    Role findByRole(String role);
}
