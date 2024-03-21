package com.marwanmakm.boto.controller;

import com.marwanmakm.boto.service.DataLoaderService;
import com.marwanmakm.boto.service.FileFormatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class DataLoaderController {

  private final DataLoaderService dataLoaderService;
  private final FileFormatterService fileFormatterService;

  @Autowired
  public DataLoaderController(
      DataLoaderService dataLoaderService, FileFormatterService fileFormatterService) {
    this.dataLoaderService = dataLoaderService;
    this.fileFormatterService = fileFormatterService;
  }

  /**
   * Receives a certain file of supported formats to converted into a CSV File The supported formats
   * are Banco de Chile Cuenta Corriente Banco de Chile Tarjeta de Crédito
   *
   * @param body
   * @return
   */
  @PostMapping("/format-file/{source_type}")
  public ResponseEntity<?> formatFile(
      @RequestParam("file") MultipartFile file, @PathVariable("source_type") String sourceType) {
    return new ResponseEntity<>("hola", HttpStatus.CREATED);
  }

  /**
   * Receives a previous formatted file, validate it and load it to the data bas
   *
   * @param body
   * @return
   */
  @PostMapping("/load-file")
  public ResponseEntity<?> loadFile(@RequestBody String body) {
    return new ResponseEntity<>("hola", HttpStatus.CREATED);
  }
}
