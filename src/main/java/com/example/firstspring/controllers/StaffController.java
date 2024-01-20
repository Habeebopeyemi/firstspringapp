package com.example.firstspring.controllers;

import com.example.firstspring.entity.Staff;
import com.example.firstspring.services.StaffServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/Staff")
public class StaffController {
    @Autowired
    private StaffServices staffServicesVar;
    /**
     * Controller method for handling HTTP GET requests to the root path ("/") in a Spring Boot application.
     * Renders the "staff/index" view with a welcome message.
     *
     * <p>This method creates a {@code ModelAndView} object, sets the view name as "staff/index", and adds
     * a welcome message to the model with the attribute name "infos". The generated {@code ModelAndView}
     * is then returned to the client.
     * </p>
     *
     * <p>The method is annotated with {@code @RequestMapping} to specify that it handles GET requests to
     * the root path. The HTTP method is explicitly set to {@code RequestMethod.GET}.
     * </p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     *     @Controller
     *     public class StaffController {
     *
     *         @RequestMapping(value = "", method = RequestMethod.GET)
     *         public ModelAndView home(){
     *             // Implementation details as described in this JavaDoc
     *         }
     *     }
     * }</pre>
     *
     * @return A ModelAndView object representing the "staff/index" view with a welcome message.
     * @see org.springframework.web.bind.annotation.RequestMapping
     * @see org.springframework.web.bind.annotation.RequestMethod
     * @see org.springframework.web.servlet.ModelAndView
     */

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView indexModelAndView = new ModelAndView();
        String info = "Welcome to SpringBoot";
        indexModelAndView.setViewName("staff/index");
        indexModelAndView.addObject("infos", info);
        return indexModelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(){
     ModelAndView createModelAndView = new ModelAndView();
     createModelAndView.setViewName("staff/create");
     return createModelAndView;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(){
        ModelAndView createModelAndView = new ModelAndView();
        createModelAndView.setViewName("staff/list");
        List<Staff> staffList = staffServicesVar.getAllStaff();
        createModelAndView.addObject("staffs", staffList);
        return createModelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age){
        ModelAndView listModelAndView = new ModelAndView();
        listModelAndView.setViewName("staff/list");
        staffServicesVar.createStaff(name, age);
        List<Staff> updatedList = staffServicesVar.getAllStaff();
        listModelAndView.addObject("staffs", updatedList);
        return listModelAndView;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "id") long id){
        ModelAndView viewModelAndView = new ModelAndView();
        Staff curStaff = staffServicesVar.getStaff(id);
        viewModelAndView.setViewName("staff/view");
        viewModelAndView.addObject("staff",curStaff);
        return viewModelAndView;
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") long id){
        ModelAndView editModelAndView = new ModelAndView();
        Staff curStaff = staffServicesVar.getStaff(id);
        editModelAndView.setViewName("staff/edit");
        editModelAndView.addObject("staff",curStaff);
        return editModelAndView;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView update(Staff staff, @PathVariable(value = "id") long id){
        staff.setId(id);
        staffServicesVar.updateStaff(staff);
        return renderUpdatedUI();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ModelAndView delete(Staff staff, @PathVariable(value = "id") long id){
        staff.setId(id);
        staffServicesVar.deleteStaff(staff);
        return renderUpdatedUI();
    }

//utility method to help and update the UI after any mutation
    protected ModelAndView renderUpdatedUI(){
        ModelAndView listModelAndView = new ModelAndView();
        List<Staff> editedList = staffServicesVar.getAllStaff();
        listModelAndView.setViewName("staff/list");
        listModelAndView.addObject("staffs", editedList);
        return listModelAndView;
    }
}
