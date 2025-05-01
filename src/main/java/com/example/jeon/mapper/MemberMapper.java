package com.example.jeon.mapper;

import com.example.jeon.dto.Member;
import com.example.jeon.dto.MemberDTO;
import com.example.jeon.dto.MemberRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberDTO memberToMemberDTO(Member member);

    @Mapping(target = "password", ignore = true)
    Member memberDTOToMember(MemberDTO memberDTO);

    @Mapping(target = "social", ignore = true)
    @Mapping(target = "provider", ignore = true)
    Member memberRequestDTOToMember(MemberRequestDTO memberRequestDTO);

    @Mapping(target = "social", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    MemberDTO requestDTOToMemberDTO(MemberRequestDTO memberRequestDTO);

}
