package com.emporium.ad.controller;

import com.emporium.ad.model.jpa.Category;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successful operation", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))}),
    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)}
)
public @interface DefaultResponses {
}