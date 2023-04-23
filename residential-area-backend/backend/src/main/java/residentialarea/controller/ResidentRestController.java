package residentialarea.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import residentialarea.model.ResidentCreateRequestModel;
import residentialarea.model.CommonResponseModel;
import residentialarea.model.ResidentialResponseModel;
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
    public ResponseEntity<List<ResidentialResponseModel>> readResident() {
        List<ResidentialResponseModel> residentialResponseModels = new ArrayList<>();
        try {
            residentialResponseModels = residentService.readResident();
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(residentialResponseModels, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(residentialResponseModels, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    @SuppressWarnings("unused")
    public ResponseEntity<CommonResponseModel> deleteResident(@RequestParam Integer id) {
        List<ResidentialResponseModel> residentialResponseModels = new ArrayList<>();
        try {
            residentService.deleteResident(id);
        } catch (Exception e) {
            log.error("error: ", e);
            return new ResponseEntity<>(new CommonResponseModel("Fail"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new CommonResponseModel("Success"), HttpStatus.OK);
    }
}
