package com.danscafe.siteapi.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Getter
@Setter
@Document
public class User {

    @Id
    private String id;

    User(){
    }

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String username;
    private String password;
    private boolean enabled;
    private String token;
    @DBRef
    private Set<Role> roles;

}
