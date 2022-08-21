package uz.pdp.springbootexample.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springbootexample.dto.EmployeeDto;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.repository.EmployeeRepository;
import uz.pdp.springbootexample.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {



    private static EmployeeRepository employeeRepository;
private static  PositionRepository positionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, PositionRepository positionRepository) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
    }

    public static void update(int id, EmployeeDto employeeDto) {
        Integer positionId = employeeDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {
            throw new IllegalStateException("Position not found!!");
        }
        Optional<Employee> byId = employeeRepository.findById(id);
Employee employee=byId.get();
        employee = Employee.builder().id(id).fullName(employeeDto.
                        getFullName())
                .position(optionalPosition.get())
                .salary(employeeDto.getSalary())
                .build();
        employeeRepository.save(employee);
    }

    public void saveEmployee(EmployeeDto employeeDto) {

        Integer positionId = employeeDto.getPositionId();
        Optional<Position> optionalPosition = positionRepository.findById(positionId);
        if (optionalPosition.isEmpty()) {
            throw new IllegalStateException("Position not found!!");
        }
        Employee employee = Employee
                .builder()
                .fullName(employeeDto.getFullName())
                .position(optionalPosition.get())
                .salary(employeeDto.getSalary())
                .build();

        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public static Employee get(int id) {
        return employeeRepository.findById(id).get();
    }
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }
}
