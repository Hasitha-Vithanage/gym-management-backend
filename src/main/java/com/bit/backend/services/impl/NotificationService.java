package com.bit.backend.services.impl;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.entities.NotificationEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.NotificationMapper;
import com.bit.backend.repositories.NotificationRepository;
import com.bit.backend.services.NotificationServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService implements NotificationServiceI {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDto addNotification(NotificationDto notificationDto) {
        NotificationEntity notificationEntity = notificationMapper.toNotificationEntity(notificationDto);
        notificationEntity.setTimeStamp(new Date());
        NotificationEntity savedNotification = notificationRepository.save(notificationEntity);
        NotificationDto savedDto = notificationMapper.toNotificationDto(savedNotification);
        return savedDto;
    }

    @Override
    public List<NotificationDto> getUserNotifications(long id) {
        String idString = Long.toString(id);
        List<NotificationEntity> notificationEntityList = notificationRepository.getUserNotification(idString);
        List<NotificationDto> notificationDtoList = notificationMapper.toNotificationDtoList(notificationEntityList);
        return notificationDtoList;
    }

    @Override
    public boolean changeNotificationStatus(String id) {
        Long notificationId = Long.parseLong(id);

        try {
            Optional<NotificationEntity> optionalNotificationEntity = notificationRepository.findById(notificationId);

            if (!optionalNotificationEntity.isPresent()) {
                throw new AppException("Can't update notification status", HttpStatus.BAD_REQUEST);
            }

            NotificationEntity oldNotificationEntity = optionalNotificationEntity.get();
            oldNotificationEntity.setReadStatus(true);
            NotificationEntity updatedNotificationEntity = notificationRepository.save(oldNotificationEntity);
            if (updatedNotificationEntity.isReadStatus() == true) {
                return true;
            } else return false;
        } catch (Exception e) {
            throw new AppException("Request failed with error: " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
