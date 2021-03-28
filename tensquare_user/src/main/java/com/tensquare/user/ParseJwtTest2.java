package com.tensquare.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseJwtTest2 {
    public static void main(String[] args) {
        String compactJws="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MTY5NDI1ODIsImV4cCI6MTYxNjk0MjYxMX0.11rgkKM-5kv0aiQfwkA9BrQ_muRHUzv8xWiQtgbWFXY";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(compactJws).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
    }
}
