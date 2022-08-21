package uz.pdp.springbootexample.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.springbootexample.Pager;
import uz.pdp.springbootexample.dto.EmployeeDto;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.repository.EmployeeRepository;
import uz.pdp.springbootexample.service.EmployeeService;
import uz.pdp.springbootexample.service.PositionService;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {
    private final PositionService positionService;
    private final EmployeeService employeeService;
    private int id;
    private EmployeeRepository employeeRepository;

    public EmployeeController(
            PositionService positionService,
            EmployeeService employeeService
    ) {
        this.positionService = positionService;
        this.employeeService = employeeService;
    }


    @ModelAttribute(name = "employee")
    public Employee getEmployee() {
        return new Employee();
    }

    @ModelAttribute(name = "employeeList")
    public List<Employee> getEmployeeList() {
        return employeeService.getAllEmployees();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {

        return positionService.getAllPositions();
    }
    @GetMapping("/employees")
    public String getAllEmployees(@PageableDefault (size = 4) Pageable pageable, Model model) {
        Page<Employee> page=employeeService.findAll(pageable);
        model.addAttribute("page",page);
        return "employee";
    }
    @PostMapping("/employees")
    public String saveEmployee(@Valid EmployeeDto employeeDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        employeeService.saveEmployee(employeeDto);
        return "redirect:/employees";
    }
   @GetMapping("/employees-update/{id}")
   public String updateForm(@PathVariable(name = "id") int id, Model model){
        Employee employee=EmployeeService.get(id);
        model.addAttribute("employee", employee);
        return "employee-update";
   }

   @PostMapping("/employees-update/{id}")
   public String update(@PathVariable  (name="id") int id, EmployeeDto employeeDto ){
       EmployeeService.update(id, employeeDto);
       return "redirect:/employees";
   }

    @GetMapping("/employees-delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        System.out.println(id);
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
