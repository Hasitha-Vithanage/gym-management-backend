package com.bit.backend.repositories;

import com.bit.backend.entities.AssignTrainerEntity;
import com.bit.backend.entities.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT DATE_FORMAT(attendance_date, '%Y-%m') AS month, sum(CASE WHEN attendance_status = 'present' THEN 1 ELSE 0 END) as present_days, SUM(CASE WHEN attendance_status = 'absent' THEN 1 ELSE 0 END) AS absent_days , COUNT(*) AS total_days FROM attendance WHERE attendance_type = 'employee' group by DATE_FORMAT(attendance_date, '%Y-%m') order by  month")
    List<Map<String, Object>> getMonthlyEmployeeAttendance();

    @Query(nativeQuery = true, value = "SELECT DATE_FORMAT(attendance_date, '%Y-%m') AS month, sum(CASE WHEN attendance_status = 'present' THEN 1 ELSE 0 END) as present_days, SUM(CASE WHEN attendance_status = 'absent' THEN 1 ELSE 0 END) AS absent_days , COUNT(*) AS total_days FROM attendance WHERE attendance_type = 'member' group by DATE_FORMAT(attendance_date, '%Y-%m') order by  month")
    List<Map<String, Object>> getMonthlyMemberAttendance();
}
