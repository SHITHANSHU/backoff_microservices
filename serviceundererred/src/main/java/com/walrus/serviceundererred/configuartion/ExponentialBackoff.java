package com.walrus.serviceundererred.configuartion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;



public class ExponentialBackoff {

    private static final Logger logger = LoggerFactory.getLogger(ExponentialBackoff.class);
    public static int defaultRetry=10;
    public static int defaultWaitMs=1000;
    private int retryCount;
    private int retryRemaining;
    private int waitTime;
    private int timeToWait;
    private Random random=new Random();

    public ExponentialBackoff()
    {
        this(defaultRetry,defaultWaitMs);
    }

    public ExponentialBackoff(int retryCount, int waitTime)
    {
        this.retryCount=retryCount;
        this.retryRemaining=retryCount;
        this.waitTime=waitTime;
        this.timeToWait=waitTime;
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
        timeToWait += random.nextInt(1000);

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
