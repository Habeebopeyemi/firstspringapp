package com.example.firstspring.services;

import com.example.firstspring.entity.Staff;

import java.util.List;

public interface StaffServices {
    public void createStaff(String name, int age);
    public List<Staff> getAllStaff();
    public Staff getStaff(long id);
    public void updateStaff(Staff staff);
    public void deleteStaff(Staff staff);
}
