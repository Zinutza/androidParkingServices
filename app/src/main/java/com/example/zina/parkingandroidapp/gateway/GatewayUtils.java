package com.example.zina.parkingandroidapp.gateway;

import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.CONTEXT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.HOST;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PORT;
import static com.example.zina.parkingandroidapp.gateway.GatewayProperties.PROTOCOL;

/**
 * Created by Zina on 29/06/2017.
 */

public class GatewayUtils {

    private GatewayUtils(){
        // Should never be created
    }

    public static String buildServiceURL(String protocol, String host, String port, String context, String service) {
        //http://localhost:8080/parking/login
        StringBuffer buffy = new StringBuffer();
        buffy.append(protocol);
        buffy.append("://");
        buffy.append(host);
        buffy.append(":");
        buffy.append(port);
        buffy.append("/");
        buffy.append(context);
        buffy.append("/");
        buffy.append(service);
        return buffy.toString();
    }
}
