package uz.pdp.springbootexample.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springbootexample.dto.PositionDto;
import uz.pdp.springbootexample.entity.Position;
import uz.pdp.springbootexample.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @ModelAttribute(name = "position")
    public Position getPosition() {
        return new Position();
    }

    @ModelAttribute(name = "positionList")
    public List<Position> getPositionList() {
        return positionService.getAllPositions();
    }

    @GetMapping
    public String getAllPositions() {
        return "employee.html";
    }

    @PostMapping
    public String savePosition(@Valid PositionDto positionDto) {
        System.out.println(positionDto);
        positionService.savePosition(positionDto);
        return "redirect:/employees";
    }

    @GetMapping("/position-delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        System.out.println(id);
        positionService.delete(id);
        return "redirect:/positions";
    }
}
