package com.berre.rabbitmq.consumer;

import com.berre.rabbitmq.model.SaveAuthModel;
import com.berre.repository.entity.UserProfile;
import com.berre.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {

    private final UserProfileService userProfileService;


    @RabbitListener(queues = "queue-auth")
    public void CreateUserFromQueue(SaveAuthModel model){
        userProfileService.save(UserProfile.builder()
                        .authid(model.getAuthid())
                        .username(model.getUsername())
                        .email(model.getEmail())
                .build());
    }
}
