package com.betrybe.agrix.controllers.dto;

/**
 * ResponseDTO has been created.
 */
public record ResponseDto<T>(String message, T data) {
}