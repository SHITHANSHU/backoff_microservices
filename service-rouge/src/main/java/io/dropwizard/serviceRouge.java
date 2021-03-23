package io.dropwizard;

import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;
import java.util.logging.Logger;

public class serviceRouge extends Application<serviceRougeConfiguration> {


    private static Logger logger=Logger.getLogger((serviceRouge.class.getName()));

    public static void main(String[] args) throws Exception {
        new serviceRouge().run(args);
    }

    @Override
    public String getName()
    {
        return "Sample Service Application";
    }

    @Override
    public void initialize(Bootstrap<serviceRougeConfiguration> bootstrap)
    {

    }


    public void run(serviceRougeConfiguration serviceRougeConfiguration, Environment environment) throws Exception {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");



        System.out.println("Entering env");
        environment.jersey().register(new serviceRougeResourceImpl());
        System.out.println("exit env");
    }
}
