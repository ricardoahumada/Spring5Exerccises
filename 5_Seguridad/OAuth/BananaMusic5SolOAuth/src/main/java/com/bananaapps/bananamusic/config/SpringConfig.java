package com.bananaapps.bananamusic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SpringRepositoryConfig.class, SpringServicesConfig.class})
public class SpringConfig {

}
