package lk.ijse.aad.controller;

import lk.ijse.aad.constant.CommonResponse;
import lk.ijse.aad.dto.DepartmentDTO;
import lk.ijse.aad.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.aad.constant.ResponseMassage.SUCCESS_MASSAGE;
import static lk.ijse.aad.constant.ResponseStatusCode.OPERATION_SUCCESS;

@RestController
@RequestMapping(value = "v1/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE) //JSON response turns to JSON String
    public CommonResponse saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.saveDepartment(departmentDTO);
       // return "Department saved successfully";
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return new CommonResponse(OPERATION_SUCCESS, departments, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchDepartment(@PathVariable Long id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return new CommonResponse(OPERATION_SUCCESS, department, SUCCESS_MASSAGE);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(departmentDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MASSAGE);
    }

    @GetMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse searchDepartmentByFilter(@RequestParam (value = "depName" ,required = false)String depName,
                                                        @RequestParam (value = "depLocation" ,required = false)String depLocation){
        List<DepartmentDTO> departments = departmentService.searchDepartmentByFilter(depName,depLocation);
        return new CommonResponse(OPERATION_SUCCESS, departments,SUCCESS_MASSAGE);
    }
}
