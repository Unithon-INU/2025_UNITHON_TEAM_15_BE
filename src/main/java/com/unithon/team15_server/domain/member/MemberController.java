package com.unithon.team15_server.domain.member;

import com.unithon.team15_server.domain.member.dto.*;
import com.unithon.team15_server.global.jwt.MemberDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원 API")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "- 이메일 중복 여부를 체크합니다.\n")
    @ApiResponse(responseCode = "204", description = "회원 초기 가입 성공 (respones == 토큰)")
    @PostMapping("/sign-up")
    public ResponseEntity<Map<String, String>> signup(@RequestBody MemberSignupReq memberSignupReq) {
        String token = memberService.signup(memberSignupReq);
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "닉네임 설정", description =
            "- 회원가입 이후 닉네임을 설정합니다.\n" +
                    "- 닉네임 중복을 체크합니다.\n")
    @ApiResponse(responseCode = "204", description = "닉네임 설정 성공")
    @PostMapping("/me/nickname")
    public ResponseEntity<Void> registerNickname(@AuthenticationPrincipal MemberDetail memberDetail, @RequestBody MemberNicknameReq memberNicknameReq) {
        memberService.registerNickname(memberDetail.getId(), memberNicknameReq);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "회원 정보 설정", description = "- 회원의 정보를 받습니다. (언어 ~ 업무 직종)\n" +
            "- topikLevel값은 디자인 그대로 넣어주세요. (없음, TOPIK 3급, TOPIK 4급 이상) \n")
    @ApiResponse(responseCode = "200", description = "회원 정보 설정 성공 (respones == 토큰)")
    @PostMapping("/me/profile")
    public ResponseEntity<Map<String, String>> registerProfile(@AuthenticationPrincipal MemberDetail memberDetail, @RequestBody MemberProfileSetReq memberProfileSetReq) {
        String token = memberService.registerProfile(memberDetail.getId(), memberProfileSetReq);
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "로그인")
    @ApiResponse(responseCode = "200", description = "로그인 성공 (respones == 토큰)")
    @PostMapping("/sign-in")
    public ResponseEntity<Map<String, String>> signin(@RequestBody MemberSignInReq memberSignInReq) {
        String token = memberService.signin(memberSignInReq);
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "회원의 정보 조회", description = "- 회원의 온보딩 정보 조회\n" +
            "- 설정이 되지 않은 값은 빈 문자열(\"\") 로 응답")
    @ApiResponse(responseCode = "200", description = "로그인 성공 (respones == 토큰)")
    @GetMapping("/me/profile")
    public ResponseEntity<MemberProfileGetRes> getMemberProfile(@AuthenticationPrincipal MemberDetail memberDetail) {
        return ResponseEntity.ok(memberService.getMemberProfile(memberDetail.getId()));
    }
}
