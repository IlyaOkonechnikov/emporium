package com.emporium.account.model.mapper;

import com.emporium.account.model.Account;
import com.emporium.lib.auth.RegistrationDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-10-25T23:08:28+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 13.0.1 (AdoptOpenJDK)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toEntity(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setId( registrationDTO.getId() );
        account.setUsername( registrationDTO.getUsername() );
        account.setEmail( registrationDTO.getEmail() );
        account.setPassword( registrationDTO.getPassword() );

        return account;
    }
}
