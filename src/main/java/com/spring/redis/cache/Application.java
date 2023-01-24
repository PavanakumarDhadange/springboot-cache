package com.spring.redis.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Level;
import java.util.logging.Logger;

@EnableCaching
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Controller
class RestTest {

	@GetMapping("/data/{id}")
	@ResponseBody
	@Cacheable(value = "user", key = "#id")
	public String getData(@PathVariable String id) {
		return "Hello....";
	}

}

@Configuration
class CachingConfiguration implements CachingConfigurer {

	@Override
	public CacheErrorHandler errorHandler() {
		return new CustomCacheErrorHandler();
	}
}

class CustomCacheErrorHandler implements CacheErrorHandler {

	Logger log = Logger.getLogger(CustomCacheErrorHandler.class.getName());

	@Override
	public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
		log.log(Level.SEVERE, e.getMessage());
	}

	@Override
	public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
		log.log(Level.SEVERE, e.getMessage());
	}

	@Override
	public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
		log.log(Level.SEVERE, e.getMessage());
	}

	@Override
	public void handleCacheClearError(RuntimeException e, Cache cache) {
		log.log(Level.SEVERE, e.getMessage());
	}
}