package com.unithon.team15_server.domain.certificate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CertificateReq {

    @Schema(description = "이름", example = "엄복동")
    private String name;

    @Schema(description = "외국인등록번호", example = "123456-1234567")
    private String regNum;

    @Schema(description = "전공", example = "컴퓨터공학부")
    private String major;

    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNum;

    @Schema(description = "이메일", example = "asdf@gmail.com")
    private String email;

    @Schema(description = "학기", example = "1학기")
    private String semester;

    @Schema(description = "근무지 이름", example = "맘스터치 인천대점")
    private String companyName;

    @Schema(description = "사업자등록번호", example = "123-12-123456")
    private String bizRegNum;

    @Schema(description = "근무지 업종", example = "음식점업")
    private String industry;

    @Schema(description = "근무지 주소", example = "인천광역시 연수구 아카데미로 119")
    private String address;

    @Schema(description = "근무지 전화번호", example = "032-835-1111")
    private String companyPhoneNum;

    @Schema(description = "근무 시작 날짜", example = "2025-06-21")
    private LocalDate workingStartDate;

    @Schema(description = "근무 종료 날짜", example = "2025-06-30")
    private LocalDate workingEndDate;

    @Schema(description = "시급", example = "10,030원")
    private String hourlyWage;

    @Schema(description = "평일 근무시간 리스트")
    private List<WorkTimeReq> weekdayWorkTimes;

    @Schema(description = "주말 근무시간 리스트")
    private List<WorkTimeReq> weekendWorkTimes;
}
