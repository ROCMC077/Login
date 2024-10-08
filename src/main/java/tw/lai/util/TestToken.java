package tw.lai.util;

import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TestToken {
	private static long time = 1000*60*60*24;//毫秒數
	private static String signature = "admin";//需要簽名訊息進行解密
	public static void main(String[] args) {
		JwtBuilder jwtBuilder = Jwts.builder();//建出jwt對象
		String jwtToken = jwtBuilder
				//header
				.setHeaderParam("typ","JWT")
				.setHeaderParam("alg", "HS256")
				//payload
				.claim("username","Lai")
				.claim("row","admin")
				.setSubject("adminTest")
				.setExpiration(new Date( System.currentTimeMillis()+time))//獲取時間
				.setId(UUID.randomUUID().toString())
				//signature
				.signWith(SignatureAlgorithm.HS256,signature)
				.compact();
		System.out.println(jwtToken);
		parse(jwtToken);

	}
	
	public static void parse(String jwtToken)
	{
		String token = jwtToken;
		JwtParser jwtParser = Jwts.parser();
		Jws<Claims> claimsJws = jwtParser.setSigningKey(signature)//通過加密時的key進行解密
										 .parseClaimsJws(token);//把token數據解析成很多Claims
		Claims claims = claimsJws.getBody();//把Claims從集合當中取出
		System.out.println(claims.get("username"));
		System.out.println(claims.get("row"));
		System.out.println(claims.getSubject());
		System.out.println(claims.getExpiration());
	}
	
	

}
