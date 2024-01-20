package com.example.firstspring.services;

import com.example.firstspring.entity.Staff;
import com.example.firstspring.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServicesImp implements StaffServices {
    @Autowired
    public StaffRepository staffRepositoryVar;
    @Override
    public void createStaff(String name, int age) {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setAge(age);
        staffRepositoryVar.save(staff);
    }

    @Override
    public List<Staff> getAllStaff(){
        List<Staff> staffList = staffRepositoryVar.findAll();
        return staffList;
    }

    @Override
    public Staff getStaff(long id) {
        Optional<Staff> currentStaff = staffRepositoryVar.findById(id);
        if(currentStaff.isPresent()){
            Staff curStaff = currentStaff.get();
            return curStaff;
        }
        return null;
    }

    @Override
    public void updateStaff(Staff staff) {
        staffRepositoryVar.save(staff);
    }
}
