package com.walrus.serviceundererred.api;

import com.walrus.serviceundererred.model.ApiResponse;
import com.walrus.serviceundererred.configuartion.BackendAPICall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RequestResponse {

    @Autowired
    SimpMessagingTemplate template;
    private static final Logger logger = LoggerFactory.getLogger(RequestResponse.class);

    @MessageMapping("/message")
    @SendTo("/response/message")
    public ApiResponse getRequest()
    {
        logger.info("Recieved request to start API Call");
        System.out.println("api call");

        BackendAPICall backendAPICall=new BackendAPICall(template);


        return backendAPICall.makeAPICall();
    }

}
