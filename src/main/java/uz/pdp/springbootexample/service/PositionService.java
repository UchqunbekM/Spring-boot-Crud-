package uz.pdp.springbootexample.service;

import org.springframework.stereotype.Service;
import uz.pdp.springbootexample.dto.EmployeeDto;
import uz.pdp.springbootexample.dto.PositionDto;
import uz.pdp.springbootexample.entity.Employee;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

   final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public void savePosition(PositionDto positionDto) {
       Position position = Position
                .builder()
                .name(positionDto.getName())
                .description(positionDto.getDescription())
                .build();
        positionRepository.save(position);
    }
    public void delete(int id) {
        positionRepository.deleteById(id);
    }

}
