package com.pjwstk.sakila.diagnostics;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SakilaDiagnosticsConfiguration {

    @Value("${sakila.diagnostics.service.name}")String serviceName;
    @Value("${sakila.diagnostics.service.host}")String host;

    @Bean
    public ServiceInfo getDiagnosticsStatus(@Value("${sakila.diagnostics.service.name}")String serviceName,
                                            @Value("${sakila.diagnostics.service.host}")String host){
        ServiceInfo serviceInfo = new ServiceInfo(serviceName, host);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8083/monitoring/register", serviceInfo, ServiceInfo.class);
        return serviceInfo;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class ServiceInfo{
        String serviceName;
        String host;
    }
}


