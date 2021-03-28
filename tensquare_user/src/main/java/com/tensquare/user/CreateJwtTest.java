package com.tensquare.user;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwtTest {

    public static void main(String[] args) {
        JwtBuilder builder= Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println( builder.compact() );
    }
}
