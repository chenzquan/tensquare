package com.tensquare.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwtTest {
    public static void main(String[] args) {
        String token="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE2MTY5NDE5MDl9.Wx5wlbQeCN9O5Kb2RywCzodwE7MXKfdjNkF1leHUSTA";
        Claims claims =
                Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("IssuedAt:"+claims.getIssuedAt());
    }
}
