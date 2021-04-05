package com.bestapp.ordersapp.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.bestapp.ordersapp.security.SecurityConstants.JWT;

@Service
public class JWTRedisService {

    private HashOperations<String, String, String> hashOperations;

    @Autowired
    public JWTRedisService(RedisTemplate<String , String > redisTemplate){
        this.hashOperations = redisTemplate.opsForHash();

    }
    public void invalidateJwt(String jwt, String userEmail){
        this.hashOperations.put(JWT,jwt, userEmail);

    }
    public boolean isJwtBlackListed(String jwt){
        if(this.hashOperations.get(JWT, jwt) != null){
            return true;
        }
        return false;
    }


}
