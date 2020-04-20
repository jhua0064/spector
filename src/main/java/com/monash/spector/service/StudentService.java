package com.monash.spector.service;

import com.monash.spector.model.Student;
import com.monash.spector.service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public List<Student> listAll(){
        return studentRepo.findAll();
    }

    public Student get(Integer id){
        return studentRepo.getOne(id);
    }

    public void save(Student student){
        studentRepo.save(student);
    }


}
