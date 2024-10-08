package tw.lai.util;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Jwt {

	private static long time = 1000 * 60 * 5;// 5分鐘
	private static String signature = "admin";// 需要簽名訊息進行解密

	/**
	 * 創造token
	 */
	public static String crateToken() {
		JwtBuilder jwtBuilder = Jwts.builder();// 建出jwt對象
		String jwtToken = jwtBuilder
				// header
				.setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")
				// payload
				.claim("username", "Lai").claim("row", "admin").setSubject("adminTest")
				.setExpiration(new Date(System.currentTimeMillis() + time))// 過期時間
				.setId(UUID.randomUUID().toString())
				// signature
				.signWith(SignatureAlgorithm.HS256, signature).compact();
		System.out.println(jwtToken);
		return jwtToken;
	}

	/**
	 * 分析token
	 */
	public static Boolean parse(String jwtToken) {

		try {
			String token = jwtToken;
			JwtParser jwtParser = Jwts.parser();
			Jws<Claims> claimsJws = jwtParser.setSigningKey(signature)// 通過加密時的key進行解密
					.parseClaimsJws(token);// 把token數據解析成很多Claims
			Claims claims = claimsJws.getBody();// 把Claims從集合當中取出
			System.out.println(claims.get("username"));
			System.out.println(claims.get("row"));
			System.out.println(claims.getSubject());
			System.out.println(claims.getExpiration());
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * 檢查當前有無token Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
	 * 
	 */
	public static boolean checkToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null) {
			System.err.println("token==null");
			return false;
		} else if (token.isEmpty()) {
			System.err.println("token is Empty)");
			return false;
		} else if (parse(token) == false) {
			return false;
		}
		return true;

	}

}
