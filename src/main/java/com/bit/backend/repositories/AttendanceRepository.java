package com.bit.backend.repositories;

import com.bit.backend.entities.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    boolean existsByMemberAndAttendanceDateAndAttendanceType(String member, LocalDate attendanceDate, String attendanceType);

    List<AttendanceEntity> findByAttendanceDateAndAttendanceTypeOrderByCheckInTimeDesc(LocalDate attendanceDate, String attendanceType);

    List<AttendanceEntity> findByMemberAndAttendanceDateBetweenAndAttendanceType(
            String member, LocalDate startDate, LocalDate endDate, String attendanceType);

    @Query(nativeQuery = true, value = "SELECT DATE_FORMAT(attendance_date, '%Y-%m') AS month, sum(CASE WHEN attendance_status = 'present' THEN 1 ELSE 0 END) as present_days, SUM(CASE WHEN attendance_status = 'absent' THEN 1 ELSE 0 END) AS absent_days , COUNT(*) AS total_days FROM attendance WHERE attendance_type = 'employee' group by DATE_FORMAT(attendance_date, '%Y-%m') order by  month")
    List<Map<String, Object>> getMonthlyEmployeeAttendance();

    @Query(nativeQuery = true, value = "SELECT DATE_FORMAT(attendance_date, '%Y-%m') AS month, sum(CASE WHEN attendance_status = 'present' THEN 1 ELSE 0 END) as present_days, SUM(CASE WHEN attendance_status = 'absent' THEN 1 ELSE 0 END) AS absent_days , COUNT(*) AS total_days FROM attendance WHERE attendance_type = 'member' group by DATE_FORMAT(attendance_date, '%Y-%m') order by  month")
    List<Map<String, Object>> getMonthlyMemberAttendance();

    @Query(nativeQuery = true, value =
        "SELECT DAY(attendance_date) AS day, COUNT(*) AS visits " +
        "FROM attendance " +
        "WHERE attendance_type = 'member' " +
        "AND YEAR(attendance_date) = :year " +
        "AND MONTH(attendance_date) = :month " +
        "GROUP BY DAY(attendance_date) ORDER BY day")
    List<Map<String, Object>> getDailyMemberCheckIns(@Param("year") int year, @Param("month") int month);

    @Query(nativeQuery = true, value =
        "SELECT HOUR(check_in_time) AS hour, COUNT(*) AS visits " +
        "FROM attendance " +
        "WHERE attendance_type = 'member' " +
        "GROUP BY HOUR(check_in_time) ORDER BY hour")
    List<Map<String, Object>> getMemberPeakHours();

    @Query(nativeQuery = true, value =
        "SELECT a.member AS member_no, " +
        "MAX(a.attendance_date) AS last_visit, " +
        "CONCAT(m.first_name, ' ', m.last_name) AS member_name " +
        "FROM attendance a " +
        "LEFT JOIN member m ON m.member_no = a.member " +
        "WHERE a.attendance_type = 'member' " +
        "AND (m.is_deleted IS NULL OR m.is_deleted = false) " +
        "GROUP BY a.member, m.first_name, m.last_name " +
        "HAVING MAX(a.attendance_date) < DATE_SUB(CURDATE(), INTERVAL :days DAY) " +
        "ORDER BY MAX(a.attendance_date) ASC")
    List<Map<String, Object>> getAtRiskMembers(@Param("days") int days);
}
