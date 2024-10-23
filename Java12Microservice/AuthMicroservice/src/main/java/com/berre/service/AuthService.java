package com.berre.service;

import com.berre.dto.request.DoLoginRequestDto;
import com.berre.dto.request.RegisterRequestDto;
import com.berre.exception.AuthServiceException;
import com.berre.exception.ErrorType;
import com.berre.manager.IUserProfileManager;
import com.berre.mapper.AuthMapper;
import com.berre.rabbitmq.model.SaveAuthModel;
import com.berre.rabbitmq.producer.CreateUserProducer;
import com.berre.repository.AuthRepository;
import com.berre.repository.entity.Auth;
import com.berre.utility.JwtTokenManager;
import com.berre.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final AuthRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final IUserProfileManager iUserProfileManager;
    private final CreateUserProducer createUserProducer;

    public AuthService(AuthRepository repository, JwtTokenManager jwtTokenManager, IUserProfileManager iUserProfileManager, CreateUserProducer createUserProducer) {
        super(repository);
        this.repository=repository;
        this.jwtTokenManager = jwtTokenManager;
        this.iUserProfileManager = iUserProfileManager;
        this.createUserProducer = createUserProducer;
    }

    public Optional<Auth> findOptionalByEmailAndPassword(String email, String password){
        return repository.findOptionalByEmailAndPassword(email,password);

    }

    public Auth register(RegisterRequestDto dto) {
        if (repository.existsByEmail(dto.getEmail())){
            throw new AuthServiceException(ErrorType.REGISTER_EMAIL_ALREADY_EXISTS);
        }
        Auth auth=AuthMapper.INSTANCE.RegisterRequestDtotoAuth(dto);

        save(auth);

        //iUserProfileManager.save(AuthMapper.INSTANCE.fromAuth(auth));  // OpenFeign ile UserProfileService mesaj gönderme

        createUserProducer.convertAndSend(SaveAuthModel.builder()
                        .authid(auth.getId())
                        .email(auth.getEmail())
                        .username(auth.getUsername())
                .build());

        return auth;
    }


    //email ve password kullanılarak login işlemi yaptırılır
    //bu işlem başarılı ise ona gidip özel bir kimlik vereceğiz TOKEN
    // işlem başarısız za hata fırlat
    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth=repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (auth.isEmpty()) {
            throw new AuthServiceException(ErrorType.DOLOGIN_EMAILORPASSWORD_NOT_EXISTS);
        }
        return jwtTokenManager.createToken(auth.get().getId()).get();


    }

    public List<Auth> findAll(String token) {
        Optional<Long> idFromToken;
        try{
           idFromToken=jwtTokenManager.decodeToken(token);
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN_FORMAT);
        }
        if (!repository.existsById(idFromToken.get())){
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        return findAll();

    }
}
