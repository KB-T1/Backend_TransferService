package com.kbt1.ollilove.transferservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FamilyResponseDTO {
    Long familyId;
    List<FamilyMemberDTO> familyMember;
}
