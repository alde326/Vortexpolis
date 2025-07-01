package com.vortexpolis.config;

import com.cloudinary.Cloudinary;
// import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dfxjp8kfj");
        config.put("api_key", "845587325476931");
        config.put("api_secret", "_h-V0c__Ew3u600QGsgurWLzl8w");

        return new Cloudinary(config);
    }
}
