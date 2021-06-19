package com.emporium.ad.controller;

import com.emporium.ad.service.AdService;
import com.emporium.lib.ad.AdDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdController {

  private final AdService service;

  @DefaultResponses
  @GetMapping
  public List<AdDTO> findAll() {
    return service.findAll();
  }

  @DefaultResponses
  @GetMapping("/{id}")
  public AdDTO findById(
      @Parameter(description = "Id of ad to be searched", required = true) @PathVariable long id) {
    return service.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @DefaultResponses
  @ApiResponse(responseCode = "201", description = "Successful creation")
  @PostMapping
  public Long create(@RequestBody @Valid AdDTO dto) {
    return service.create(dto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  @PutMapping("/{id}")
  public void update(
      @Parameter(description = "Id of ad to be updated", required = true) @PathVariable long id,
      @RequestBody @Valid AdDTO dto) {
    service.update(id, dto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  @DeleteMapping("/{id}")
  public void delete(
      @Parameter(description = "Id of ad to be deleted", required = true) @PathVariable int id) {
    service.delete(id);
  }
}
