package com.github.yash777.SpringcloudFeginclient.fegin.component;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
//import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
public class CustomFeignConfig {
//	@Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }
	
//	@Bean
//    public Contract feignContract() {
//        return new feign.Contract.Default();
//    }
	
//    @Bean
//    public Decoder feignDecoder() {
//        return new JacksonDecoder() {
//            @Override
//            public Object decode(Response response, Type type) throws IOException {
//                log.info("inside overridden feignDecoder.decoder()");
//                return super.decode(response, type);
//            }
//        };
//    }
//    @Bean
//    public Decoder myDecoder() {
//        return new JacksonDecoder() {
//            @Override
//            public Object decode(Response response, Type type) throws IOException {
//                log.info("inside myDecoder.decoder()");
//                return super.decode(response, type);
//            }
//        };
//    }
//    @Bean
//    public feign.okhttp.OkHttpClient client() {
//        return new feign.okhttp.OkHttpClient();
//    }
	
	@Bean
	public feign.auth.BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new feign.auth.BasicAuthRequestInterceptor("admin", "admin");
	}
}