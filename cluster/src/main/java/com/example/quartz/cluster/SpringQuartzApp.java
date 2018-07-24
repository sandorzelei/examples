package com.example.quartz.cluster;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class SpringQuartzApp {

    public static void main(final String[] args) {
        new SpringApplicationBuilder(SpringQuartzApp.class).bannerMode(Mode.OFF).run(args);
    }


}
