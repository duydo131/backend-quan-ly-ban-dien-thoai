package spring_boot.utils;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import spring_boot.entity.BaseEntity;

public class Utils {
	public static <T extends BaseEntity> void addCreate(T object) {
		Date now = new Date();
		object.setCreatedDate(now);
		object.setModifiedDate(now);
	}
	
	public static <T extends BaseEntity> void addUpdate(T object) {
		object.setModifiedDate(new Date());
	}
	
	public static String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if(StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		return null;
	}
}
