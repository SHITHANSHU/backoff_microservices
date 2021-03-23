package com.walrus.serviceundererred.configuartion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Random;


public class ExponentialBackoff {


    private static final Logger logger = LoggerFactory.getLogger(ExponentialBackoff.class);

    public static int defaultRetry=5;

    public static int defaultWaitMs=1000;

    public static float defaultFactor=2;
    private float retryFactor;
    private int retryCount;
    private int retryRemaining;
    private int waitTime;
    private int timeToWait;
    private Random random=new Random();

    public ExponentialBackoff()
    {
        this(defaultRetry,defaultWaitMs,defaultFactor);
    }

    public ExponentialBackoff(int retryCount, int waitTime,float retryFactor)
    {
        System.out.println(defaultRetry);
        System.out.println(defaultWaitMs);
        System.out.println(defaultFactor);

        this.retryCount=retryCount;
        this.retryRemaining=retryCount;
        this.waitTime=waitTime;
        this.timeToWait=waitTime;
        this.retryFactor=retryFactor;
    }

    public boolean shouldRetry()
    {
        return retryRemaining>0;
    }

    public void error()
    {
        --retryRemaining;

        if(!shouldRetry())
        {
            logger.info("API Call Failed");
        }

        waitTillNextTry();
        int count=retryCount-retryRemaining;
        double exponetialTime=Math.pow(retryFactor,count);
        timeToWait = (int) (waitTime*exponetialTime +random.nextInt(1000));
        logger.info("new time to wait "+timeToWait);

    }

    private void waitTillNextTry()
    {
        try
        {
            logger.info("waiting for "+timeToWait+"");
            Thread.sleep(timeToWait);

        }
        catch (InterruptedException err)
        {
            err.printStackTrace();
        }
    }

    public int getWaitTime()
    {
        return  this.timeToWait;
    }

    public void stopRetry()
    {
        logger.info("terminating retries");
        retryRemaining=0;
    }

    public void resetSystem()
    {
        this.retryRemaining=defaultRetry;
        this.waitTime=defaultWaitMs;
    }
}
