package com.redis.student.mapper;

import com.redis.student.dto.StudentDto;
import com.redis.student.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getDateOfBirth(),
                student.getAge(),
                student.getGender(),
                student.getCourse()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getDateOfBirth(),
                studentDto.getAge(),
                studentDto.getGender(),
                studentDto.getCourse()
        );
    }

}
