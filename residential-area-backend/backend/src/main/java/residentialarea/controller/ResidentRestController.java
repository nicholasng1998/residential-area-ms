package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.*;
import residentialarea.service.ResidentService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/resident")
@RequiredArgsConstructor
public class ResidentRestController {

    private final ResidentService residentService;

    @PostMapping(value = "/create")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> createResident(@RequestBody ResidentCreateRequestModel requestBody) {
        try {
            residentService.createResident(requestBody);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @GetMapping(value = "/read")
    @SuppressWarnings("unused")
    public ResponseEntity<Page<ResidentResponseModel>> readResident(@RequestParam(defaultValue = "10") Integer pageSize,
                                                                    @RequestParam(defaultValue = "1") Integer pageNumber) {
        Page<ResidentResponseModel> residentResponseModels;
        try {
            residentResponseModels = residentService.readResident(pageNumber, pageSize);
            log.info("residentResponseModels: " + residentResponseModels);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(residentResponseModels, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> deleteResident(@RequestParam Integer id) {
        List<ResidentResponseModel> residentResponseModels = new ArrayList<>();
        try {
            residentService.deleteResident(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> updateResident(@RequestBody ResidentEditRequestModel requestModel) {
        List<ResidentResponseModel> residentResponseModels = new ArrayList<>();
        try {
            residentService.updateResident(requestModel);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }
}
