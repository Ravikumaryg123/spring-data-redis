package com.redis.student.controller;

import com.redis.student.dto.StudentDto;
import com.redis.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/student/")
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.addStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") UUID studentId){
        StudentDto studentDetail = studentService.getStudentById(studentId);
        return ResponseEntity.ok(studentDetail);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> allStudentDetails = studentService.getAllStudent();
        return ResponseEntity.ok(allStudentDetails);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") UUID id, @RequestBody StudentDto studentDto){
        StudentDto updatedStudentDetail = studentService.updateStudent(id,studentDto);
        return ResponseEntity.ok(updatedStudentDetail);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") UUID id){
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("Student Deleted Successfully");
    }

}
