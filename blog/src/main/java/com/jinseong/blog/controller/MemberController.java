package com.jinseong.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinseong.blog.auth.PrincipalDetail;
import com.jinseong.blog.model.KakaoProfile;
import com.jinseong.blog.model.OAuthToken;


//인증이 안된 사용자들이 출입 할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/**, /css/**, /image/**
@Controller
public class MemberController {

	@GetMapping("/auth/joinForm")
	public String JoinForm() {
		return "member/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String LoginForm() {
		return "member/loginForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoLoginCallback(String code) { //Data를 리턴해주는 컨트롤러 함수
		
		/*kakao token req*/
		
		//POST방식으로 key=value 데이터를 요청 (to 카카오)
		RestTemplate rt = new RestTemplate();
		
		//Headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//Body
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "5e74706108bffe6ea2bef14ddb178495");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//Hedaer + Body
		HttpEntity<MultiValueMap<String, String>> kakaoTokenReq = new HttpEntity<>(params,headers);
		
		//Http 요청하기 - Post방식 response 변수의 응답을 받음
		ResponseEntity response = rt.exchange(
				"https://kauth.kakao.com/oauth/token", 
				HttpMethod.POST, 
				kakaoTokenReq,
				String.class
			);
		
		//Gson, Json Simple, ObejctMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = null;
        try {
        	oAuthToken = objectMapper.readValue(response.getBody().toString(),OAuthToken.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*kakao profile req*/
        
		RestTemplate rt2 = new RestTemplate();
		
		//Headers
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization","Bearer " + oAuthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

		//Hedaer
		HttpEntity<MultiValueMap<String, String>> kakaoProfileReq2 = new HttpEntity<>(headers2);
		
		//Http 요청하기 - Post방식 response 변수의 응답을 받음
		ResponseEntity response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST, 
				kakaoProfileReq2,
				String.class
			);
		
		//Gson, Json Simple, ObejctMapper
        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
        	kakaoProfile = objectMapper.readValue(response2.getBody().toString(),KakaoProfile.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println(kakaoProfile.getId() + kakaoProfile.getKakaoAccount().getEmail());
        
		return "카카오 인증 완료 : " + response2.getBody();
	}
	
	
	@GetMapping("/member/updateForm")
	public String updateForm(@AuthenticationPrincipal PrincipalDetail principal) {
		return "member/updateForm";
	}
}
