package com.walrus.serviceundererred.configuartion;

import com.walrus.serviceundererred.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//@EnableScheduling
@Configuration
public class BackendAPICall {

//    @Autowired
    SimpMessagingTemplate template;

    int responseCode=0;
    String responseMessage="";

    private static final Logger logger = LoggerFactory.getLogger(BackendAPICall.class);
    public BackendAPICall(SimpMessagingTemplate template)
    {
        this.template=template;
    }



    public ApiResponse makeAPICall()
    {
        ExponentialBackoff exponentialBackoff=new ExponentialBackoff();
        boolean successMessage=false;
        int count=0;
        String url="http://localhost:8085/service-walrus/hello";
        int statusCode=500;
        String response="";
        while(exponentialBackoff.shouldRetry())
        {


            try{

                statusCode=this.get(url);

            }
            catch (Exception e)
            {

                System.out.println(e);
                e.printStackTrace();
            }



            if(statusCode == 202)
            {
                successMessage=true;
                exponentialBackoff.stopRetry();
                break;
            }

            logger.info("Retrying");
            template.convertAndSend("/response/message",new ApiResponse("Error","504","Retrying"));
            exponentialBackoff.error();
        }

        if(successMessage)
        {
            logger.info("message generated");
            return new ApiResponse(responseMessage,"202","Stopping");

        }
        else
        {
            logger.warn("exceeded retried stopping backoff");
            return new ApiResponse("Error","504","Stopping");
        }
    }

    public int get(String uri) throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());


        int statusCode=response.statusCode();

        this.responseCode=response.statusCode();
        this.responseMessage=response.body();

        System.out.println(response.body());
        if(statusCode != 200)
            logger.warn("API Call responded with "+statusCode);
        else
            logger.warn("API Call responded with "+statusCode);
        System.out.println(statusCode);
        return statusCode;
    }


//    @Autowired
//    SimpMessagingTemplate template;

//    @Scheduled(fixedDelay = 3000)
//    public void sendMessage()
//    {
//        template.convertAndSend("/response/message",new apiResponse("Error","504","Retry"));
//    }
}
