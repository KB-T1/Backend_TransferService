package com.kbt1.ollilove.transferservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FamilyMemberDTO {
    private Long userId;
    private String userName;
    private String nickName;
    private String profile;
}
