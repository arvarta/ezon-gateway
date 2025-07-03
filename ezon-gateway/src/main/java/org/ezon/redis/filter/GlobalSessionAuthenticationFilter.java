package org.ezon.redis.filter;

import java.net.URI;
import java.util.Base64;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class GlobalSessionAuthenticationFilter implements GlobalFilter, Ordered {

	private static final Set<String> WHITELIST = Set.of(
        "/api/products", "/api/search/products", "/api/search/products/result",
        "/api/auth", "/auth", "/api/profile", "/auth/checkSession","/css","/js","/images","/favicon.ico"
    );

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    	String path = exchange.getRequest().getPath().value();
        // ✅ 화이트리스트 경로는 통과
        if (WHITELIST.stream().anyMatch(path::startsWith)) {
        	System.out.println("통과");
            return chain.filter(exchange);
        }

        String userId = redisTemplate.opsForValue().get("userId");
        if (userId == null || userId.isEmpty()) {
        	System.out.println("no exist user-id");
        	return notPass(exchange);
        }
        
        String typeRole = null;
        if(path.contains("seller")) {
        	typeRole = "SELLER";
        }else if(path.contains("admin")) {
        	typeRole = "ADMIN";
        }else {
        	typeRole = "BUYER";
        }
        String userRole = redisTemplate.opsForValue().get("userRole");
        if(userRole == null) {
        	System.out.println("no exist user-role");
        	return notPass(exchange);
    	}else if (!userRole.equals(typeRole) && !userRole.equals("ADMIN")) {
			System.out.println("no match service role");
			return notPass(exchange);
        }

        // ✅ 세션이 유효하므로 다음 필터로 진행
        System.out.println("마지막으로 통과");
        return chain.filter(exchange);
    }
    
    
    private Mono<Void> notPass(ServerWebExchange exchange){
    	 exchange.getResponse().setStatusCode(HttpStatus.FOUND);
    	 exchange.getResponse().getHeaders().setLocation(URI.create("/buyer/login"));
         return exchange.getResponse().setComplete();
    }
    public static String generateHmac(String secretKey, String message) {
        try {
	    // algorithm은 HmacSHA256로 할경우	
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(keySpec);

            byte[] hmacBytes = mac.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(hmacBytes);  // 또는 hex로 반환해도 됨
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate HMAC", e);
        }
    }
    @Override
    public int getOrder() {
        return -1; // 필터 우선순위 높게 설정
    }
}
