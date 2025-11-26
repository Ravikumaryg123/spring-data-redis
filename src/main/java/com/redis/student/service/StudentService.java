package com.redis.student.service;

import com.redis.student.dto.StudentDto;
import com.redis.student.entity.Student;
import com.redis.student.exception.ResourceNotFound;
import com.redis.student.mapper.StudentMapper;
import com.redis.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;

    public StudentDto addStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student createdStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(createdStudent);
    }

    @Cacheable(value = "student", key = "#id")
    public StudentDto getStudentById(UUID id) {
        System.out.println("Before Repo");
        Student studetDetail = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Student with given id " + id + " does not exist."));
        System.out.println("After Repo");
        return StudentMapper.mapToStudentDto(studetDetail);
    }

    public List<StudentDto> getAllStudent() {
        List<Student> allStudent = studentRepository.findAll();
        List<StudentDto> allStudentDetails = allStudent.stream().map((student) -> StudentMapper.mapToStudentDto(student)).collect(Collectors.toList());
        return allStudentDetails;
    }

    @CachePut(value = "student", key = "#id")
    public StudentDto updateStudent(UUID id, StudentDto updatedStudent) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Student does not exist with the given Id "+id));
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        student.setGender(updatedStudent.getGender());
        student.setDateOfBirth(updatedStudent.getDateOfBirth());
        student.setCourse(updatedStudent.getCourse());
        Student updatedStudentDetail = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentDetail);
    }

    @CacheEvict(value = "student", key = "#id")
    public void deleteStudentById(UUID id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Student does not exist with the given Id "+id));
        studentRepository.deleteById(id);
    }

}
