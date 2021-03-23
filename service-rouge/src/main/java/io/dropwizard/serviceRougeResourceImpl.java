package io.dropwizard;
import javax.ws.rs.core.Response;
import io.dropwizard.api.serviceRougeResource;
import org.joda.time.DateTime;

import java.util.logging.Level;
import java.util.logging.Logger;


public class serviceRougeResourceImpl implements serviceRougeResource  {

    static int pastMinute=-1;
    private static Logger logger=Logger.getLogger((serviceRougeResourceImpl.class.getName()));

    public serviceRougeResourceImpl() {
        System.out.println("test");

        logger.log(Level.INFO,"server started");
    }

    private String getServer() {
        return "server";
    }

    @Override
    public Response getHelloWorld() {

        Response timeoutResponse=Response.status(Response.Status.GATEWAY_TIMEOUT).build();
        Response accepted= Response.accepted().entity("Hello from server 2").build();
        Response serviceUnavailable=Response.status(Response.Status.SERVICE_UNAVAILABLE).build();

        System.out.println(pastMinute);
        int minute= new DateTime().getMinuteOfHour();

        if(pastMinute==-1)
        {
            pastMinute=minute;

        }

        if(pastMinute-minute >= 1 || pastMinute-minute <= -1) {
            pastMinute=-1;

            logger.log(Level.FINEST,"accepted returning 200");
            return (accepted);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.log(Level.SEVERE,"rejected returning 504");
        return (timeoutResponse);



    }


//    public void updateNumberData(String numberplate, String license, int mobile, String name, int toll) {
//        numberplate np=new numberplate(numberplate,license,mobile,name,toll);
//        System.out.println("update data");
//        this.numberPlateDAO.update(np);
//
//    }


}
