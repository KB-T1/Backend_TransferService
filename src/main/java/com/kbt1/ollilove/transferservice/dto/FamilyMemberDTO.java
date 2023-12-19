package com.kbt1.ollilove.transferservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FamilyMemberDTO {
    private Long id;
    private String name;
    private String nickname;
    private int familyType;
}
