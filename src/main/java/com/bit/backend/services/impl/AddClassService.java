package com.bit.backend.services.impl;

import com.bit.backend.dtos.AddClassDto;
import com.bit.backend.entities.AddClassEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AddClassMapper;
import com.bit.backend.repositories.AddClassRepository;
import com.bit.backend.services.AddClassServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddClassService implements AddClassServiceI {

    private final AddClassRepository addClassRepository;
    private final AddClassMapper addClassMapper;

    public AddClassService(AddClassRepository addClassRepository, AddClassMapper addClassMapper) {
        this.addClassRepository = addClassRepository;
        this.addClassMapper = addClassMapper;
    }

    @Override
    public AddClassDto addAddClassEntity(AddClassDto addClassDto) {
        try {
            AddClassEntity entity = addClassMapper.toAddClassEntity(addClassDto);
            AddClassEntity saved  = addClassRepository.save(entity);
            return addClassMapper.toAddClassDto(saved);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to schedule the class. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AddClassDto> getAddClass() {
        try {
            List<AddClassEntity> entities = addClassRepository.findAllByIsDeletedFalse();
            return addClassMapper.toAddClassDto(entities);
        } catch (Exception e) {
            throw new AppException("Failed to load the class schedule. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AddClassDto updateAddClass(long id, AddClassDto addClassDto) {
        AddClassEntity existing = addClassRepository.findById(id)
                .orElseThrow(() -> new AppException("Class not found. It may have been removed.", HttpStatus.NOT_FOUND));
        try {
            int oldTotal    = existing.getTotalSlots();
            int newTotal    = addClassDto.getTotalSlots();
            int bookedCount = oldTotal - existing.getRemainingSlots();

            if (newTotal < bookedCount) {
                throw new AppException(
                    "Cannot reduce total slots below the number of members already booked (" + bookedCount + ").",
                    HttpStatus.BAD_REQUEST
                );
            }

            int newRemaining = existing.getRemainingSlots() + (newTotal - oldTotal);

            existing.setClassTitle(addClassDto.getClassTitle());
            existing.setClassType(addClassDto.getClassType());
            existing.setDescription(addClassDto.getDescription());
            existing.setDate(addClassDto.getDate());
            existing.setStartTime(addClassDto.getStartTime());
            existing.setEndTime(addClassDto.getEndTime());
            existing.setConductorName(addClassDto.getConductorName());
            existing.setTrainerEmployeeId(addClassDto.getTrainerEmployeeId());
            existing.setTotalSlots(newTotal);
            existing.setRemainingSlots(newRemaining);
            existing.setStatus(addClassDto.getStatus());

            AddClassEntity saved = addClassRepository.save(existing);
            return addClassMapper.toAddClassDto(saved);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to update class details. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AddClassDto getClassById(long id) {
        AddClassEntity entity = addClassRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException("Class not found.", HttpStatus.NOT_FOUND));
        return addClassMapper.toAddClassDto(entity);
    }

    @Override
    public AddClassDto deleteAddClass(long id) {
        AddClassEntity existing = addClassRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new AppException("Class not found. It may have already been deleted.", HttpStatus.NOT_FOUND));
        try {
            existing.setDeleted(true);
            addClassRepository.save(existing);
            return addClassMapper.toAddClassDto(existing);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException("Failed to delete the class. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
