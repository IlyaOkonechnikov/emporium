package com.emporium.ad.controller;

import com.emporium.lib.ad.AdDTO;
import com.emporium.ad.model.jpa.Ad;
import com.emporium.ad.service.AdService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdController {

  //TODO: добавить ControllerAdvice для перехвата валидационных сообщений

  private final AdService service;

  @DefaultResponses
  @GetMapping
  public List<Ad> findAll() {
    return service.findAll();
  }

  @DefaultResponses
  @GetMapping("/{id}")
  public Ad findById(@Parameter(description = "id of ad to be searched") @PathVariable long id) {
    return service.findById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @DefaultResponses
  @ApiResponse(responseCode = "201", description = "Successful creation", content = {
      @Content(mediaType = "application/json", schema = @Schema(implementation = Ad.class))
  })
  @PostMapping
  public Long create(@RequestBody @Valid AdDTO dto) {
    return service.create(dto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  @PutMapping("/{id}")
  public void update(
      @Parameter(description = "id of category to be updated")
      @PathVariable long id,
      @RequestBody @Valid AdDTO dto) {
    service.update(id, dto);
  }

  @ResponseStatus(HttpStatus.OK)
  @DefaultResponses
  @DeleteMapping("/{id}")
  public void delete(@Parameter(description = "id of category to be deleted") @PathVariable int id) {
    service.delete(id);
  }
}