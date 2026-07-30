package com.bit.backend.services.impl;

import com.bit.backend.dtos.EmployeeDto;
import com.bit.backend.dtos.MemberDto;
import com.bit.backend.entities.EmployeeEntity;
import com.bit.backend.entities.MemberEntity;
import com.bit.backend.entities.MemberLoginEntity;
import com.bit.backend.entities.User;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MemberMapper;
import com.bit.backend.repositories.AssignTrainerRepository;
import com.bit.backend.repositories.MemberLoginRepository;
import com.bit.backend.repositories.MemberRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.MemberServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements MemberServiceI {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final MemberLoginRepository memberLoginRepository;
    private final UserRepository userRepository;
    private final AssignTrainerRepository assignTrainerRepository;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper,
                         MemberLoginRepository memberLoginRepository, UserRepository userRepository,
                         AssignTrainerRepository assignTrainerRepository) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.memberLoginRepository = memberLoginRepository;
        this.userRepository = userRepository;
        this.assignTrainerRepository = assignTrainerRepository;
    }

    @Override
    public MemberDto addMemberEntity(MemberDto memberDto) {
        System.out.println("In the addMemberEntity method");

        try {
            System.out.println("************ In Service *************");

            if (memberDto.getNic() != null && !memberDto.getNic().isBlank()
                    && memberRepository.existsByNic(memberDto.getNic())) {
                throw new AppException("This NIC is already registered to another member.", HttpStatus.CONFLICT);
            }

            MemberEntity memberEntity = memberMapper.toMemberEntity(memberDto);
            MemberEntity savedItem = memberRepository.save(memberEntity);
            MemberDto savedDto = memberMapper.toMemberDto(savedItem);
            return savedDto;
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MemberDto> getMember() {
        List<MemberEntity> memberEntities = memberRepository.findAll();
        List<MemberDto> memberDtoList = memberMapper.toMemberDto(memberEntities);
        return memberDtoList;
    }

    @Override
    public MemberDto updateMember(long id, MemberDto memberDto) {
        System.out.println("In the updateMemberEntity method");

        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);

        if (!optionalMemberEntity.isPresent()) {
            throw new AppException("Member Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        if (memberDto.getNic() != null && !memberDto.getNic().isBlank()
                && memberRepository.existsByNicAndIdNot(memberDto.getNic(), id)) {
            throw new AppException("This NIC is already registered to another member.", HttpStatus.CONFLICT);
        }

        MemberEntity newMemberEntity = memberMapper.toMemberEntity(memberDto);
        newMemberEntity.setId(id);
        MemberEntity savedItem = memberRepository.save(newMemberEntity);
        MemberDto savedDto = memberMapper.toMemberDto(savedItem);
        return savedDto;
    }

//    @Override
//    public MemberDto deleteMember(long id) {
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
//        if (!optionalMemberEntity.isPresent()) {
//            throw new AppException("Member Does Not Exist", HttpStatus.BAD_REQUEST);
//        }
//
//        memberRepository.deleteById(id);
//        MemberDto deletedDto = memberMapper.toMemberDto(optionalMemberEntity.get());
//        return deletedDto;
//    }

    @Override
    public MemberDto deleteMember(long id) {
        try {
            MemberEntity existingMember = memberRepository.findById(id)
                    .orElseThrow(() -> new AppException("Member does not exist", HttpStatus.BAD_REQUEST));

            MemberLoginEntity login = memberLoginRepository.findByMember(id);
            if (login != null && login.isActive()) {
                throw new AppException(
                    "This member has an active login account. Please deactivate the login before deleting.",
                    HttpStatus.CONFLICT);
            }

            if (assignTrainerRepository.existsByMemberId(id)) {
                throw new AppException(
                    "This member is assigned to a trainer. Please remove the trainer assignment before deleting.",
                    HttpStatus.CONFLICT);
            }

            if (login != null) {
                memberLoginRepository.delete(login);
            }

            existingMember.setDeleted(true);
            MemberEntity updatedMember = memberRepository.save(existingMember);
            return memberMapper.toMemberDto(updatedMember);

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MemberDto getMemberById(long id) {
        MemberEntity memberEntity = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + id));
        return memberMapper.toMemberDto(memberEntity);
    }

    @Override
    public MemberDto getMemberProfileByUserId(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        if (user.getCustomerLoginId() == null) {
            throw new AppException("No member profile linked to this account", HttpStatus.NOT_FOUND);
        }
        MemberEntity memberEntity = memberRepository.findById(user.getCustomerLoginId())
                .orElseThrow(() -> new AppException("Member record not found", HttpStatus.NOT_FOUND));
        return memberMapper.toMemberDto(memberEntity);
    }

    @Override
    public MemberDto updateMemberProfile(long userId, MemberDto memberDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        if (user.getCustomerLoginId() == null) {
            throw new AppException("No member profile linked to this account", HttpStatus.NOT_FOUND);
        }
        MemberEntity memberEntity = memberRepository.findById(user.getCustomerLoginId())
                .orElseThrow(() -> new AppException("Member record not found", HttpStatus.NOT_FOUND));

        applyMemberUpdates(memberEntity, memberDto);
        MemberEntity saved = memberRepository.save(memberEntity);

        applyUserUpdates(user, memberDto);
        userRepository.save(user);

        syncMemberLogin(userId, memberDto);

        return memberMapper.toMemberDto(saved);
    }

    private void applyMemberUpdates(MemberEntity entity, MemberDto dto) {
        if (hasValue(dto.getFirstName())) entity.setFirstName(dto.getFirstName());
        if (hasValue(dto.getLastName()))  entity.setLastName(dto.getLastName());
        if (dto.getEmail() != null)       entity.setEmail(dto.getEmail());
        if (dto.getPhoneNumber() != null) entity.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAddress() != null)     entity.setAddress(dto.getAddress());
        if (dto.getGender() != null)      entity.setGender(dto.getGender());
        if (dto.getEmergencyContactNumber() != null)
            entity.setEmergencyContactNumber(dto.getEmergencyContactNumber());
    }

    private void applyUserUpdates(User user, MemberDto dto) {
        if (hasValue(dto.getFirstName())) user.setFirstName(dto.getFirstName());
        if (hasValue(dto.getLastName()))  user.setLastName(dto.getLastName());
        if (dto.getEmail() != null)       user.setEmail(dto.getEmail());
    }

    private void syncMemberLogin(long userId, MemberDto dto) {
        MemberLoginEntity login = memberLoginRepository.findByUserId(userId);
        if (login == null) return;
        if (hasValue(dto.getFirstName())) login.setFirstName(dto.getFirstName());
        if (hasValue(dto.getLastName()))  login.setLastName(dto.getLastName());
        memberLoginRepository.save(login);
    }

    private boolean hasValue(String value) {
        return value != null && !value.isBlank();
    }

    @Override
    public List<MemberDto> getMemberByMember(String firstName) {
        List<MemberEntity> memberEntityList = memberRepository.findByFirstName(firstName);
        return memberMapper.toMemberDto(memberEntityList);
    }

    @Override
    public long getMemberCount() {
        //calling repository to get member count
        long memberCount = memberRepository.count();
        return memberCount;
    }

    @Override
    public Integer newMembersInThisMonth() {
        //Calling repository to get data
        int newMembers = memberRepository.countNewMembersInCurrentMonth();
        return newMembers;
    }
}
