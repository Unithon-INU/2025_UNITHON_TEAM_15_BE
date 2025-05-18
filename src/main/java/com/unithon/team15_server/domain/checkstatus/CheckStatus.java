package com.unithon.team15_server.domain.checkstatus;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "check_status_tb")
public class CheckStatus {
    @Id
    private Long id;

    private Long memberId;

    private Long taskId;

    private Boolean isChecked;

    @Enumerated(EnumType.STRING)
    private CheckStep checkStep;
}
