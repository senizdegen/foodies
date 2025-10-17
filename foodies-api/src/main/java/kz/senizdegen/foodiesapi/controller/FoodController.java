package kz.senizdegen.foodiesapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.senizdegen.foodiesapi.io.FoodRequest;
import kz.senizdegen.foodiesapi.io.FoodResponse;
import kz.senizdegen.foodiesapi.service.FoodService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
@CrossOrigin("*")
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<FoodResponse> addFood(@RequestPart("food") String foodString,
                                         @RequestPart("file")MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        FoodRequest request = null;
        try {
            request = objectMapper.readValue(foodString, FoodRequest.class);
        }catch (JsonProcessingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format");
        }

        FoodResponse response = foodService.addFood(request, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<FoodResponse> readFoods() {
        return foodService.readFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id) {
        return foodService.readFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id) {
        foodService.deleteFood(id);
    }
}
