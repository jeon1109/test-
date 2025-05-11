package com.example.jeon.mapper;

import com.example.jeon.dto.Member;
import com.example.jeon.dto.MemberDTO;
import com.example.jeon.dto.MemberRequestDTO;
import com.example.jeon.dto.Role;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-11T19:18:39+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.0.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO memberToMemberDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.userId( member.getUserId() );
        memberDTO.username( member.getUsername() );
        memberDTO.nickname( member.getNickname() );
        memberDTO.gender( member.getGender() );
        memberDTO.birth( member.getBirth() );
        memberDTO.email( member.getEmail() );
        memberDTO.phoneNo( member.getPhoneNo() );
        memberDTO.zipcode( member.getZipcode() );
        memberDTO.social( member.isSocial() );
        memberDTO.provider( member.getProvider() );
        memberDTO.street( member.getStreet() );
        memberDTO.addressDetail( member.getAddressDetail() );
        List<Role> list = member.getRoles();
        if ( list != null ) {
            memberDTO.roles( new ArrayList<Role>( list ) );
        }
        memberDTO.createdDate( member.getCreatedDate() );

        return memberDTO.build();
    }

    @Override
    public Member memberDTOToMember(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.userId( memberDTO.getUserId() );
        member.username( memberDTO.getUsername() );
        member.nickname( memberDTO.getNickname() );
        member.gender( memberDTO.getGender() );
        member.birth( memberDTO.getBirth() );
        member.email( memberDTO.getEmail() );
        member.phoneNo( memberDTO.getPhoneNo() );
        member.social( memberDTO.isSocial() );
        member.provider( memberDTO.getProvider() );
        member.zipcode( memberDTO.getZipcode() );
        member.street( memberDTO.getStreet() );
        member.addressDetail( memberDTO.getAddressDetail() );
        List<Role> list = memberDTO.getRoles();
        if ( list != null ) {
            member.roles( new ArrayList<Role>( list ) );
        }
        member.createdDate( memberDTO.getCreatedDate() );

        return member.build();
    }

    @Override
    public Member memberRequestDTOToMember(MemberRequestDTO memberRequestDTO) {
        if ( memberRequestDTO == null ) {
            return null;
        }

        Member.MemberBuilder member = Member.builder();

        member.userId( memberRequestDTO.getUserId() );
        member.password( memberRequestDTO.getPassword() );
        member.username( memberRequestDTO.getUsername() );
        member.nickname( memberRequestDTO.getNickname() );
        member.gender( memberRequestDTO.getGender() );
        member.birth( memberRequestDTO.getBirth() );
        member.email( memberRequestDTO.getEmail() );
        member.phoneNo( memberRequestDTO.getPhoneNo() );
        member.zipcode( String.valueOf( memberRequestDTO.getZipcode() ) );
        member.street( memberRequestDTO.getStreet() );
        member.addressDetail( memberRequestDTO.getAddressDetail() );
        List<Role> list = memberRequestDTO.getRoles();
        if ( list != null ) {
            member.roles( new ArrayList<Role>( list ) );
        }
        member.createdDate( memberRequestDTO.getCreatedDate() );

        return member.build();
    }

    @Override
    public MemberDTO requestDTOToMemberDTO(MemberRequestDTO memberRequestDTO) {
        if ( memberRequestDTO == null ) {
            return null;
        }

        MemberDTO.MemberDTOBuilder memberDTO = MemberDTO.builder();

        memberDTO.userId( memberRequestDTO.getUserId() );
        memberDTO.username( memberRequestDTO.getUsername() );
        memberDTO.nickname( memberRequestDTO.getNickname() );
        memberDTO.gender( memberRequestDTO.getGender() );
        memberDTO.birth( memberRequestDTO.getBirth() );
        memberDTO.email( memberRequestDTO.getEmail() );
        memberDTO.phoneNo( memberRequestDTO.getPhoneNo() );
        memberDTO.zipcode( String.valueOf( memberRequestDTO.getZipcode() ) );
        memberDTO.street( memberRequestDTO.getStreet() );
        memberDTO.addressDetail( memberRequestDTO.getAddressDetail() );
        List<Role> list = memberRequestDTO.getRoles();
        if ( list != null ) {
            memberDTO.roles( new ArrayList<Role>( list ) );
        }

        return memberDTO.build();
    }
}
