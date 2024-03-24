package com.marwanmakm.boto.service.impl;

import com.marwanmakm.boto.dto.OperationResponseDto;
import com.marwanmakm.boto.mapper.TransactionsMapperFactory;
import com.marwanmakm.boto.service.FileFormatterService;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileFormatterServiceImpl implements FileFormatterService {

  private final TransactionsMapperFactory transactionsMapperFactory;

  @Autowired
  public FileFormatterServiceImpl(TransactionsMapperFactory transactionsMapperFactory) {
    this.transactionsMapperFactory = transactionsMapperFactory;
  }

  @Override
  public OperationResponseDto formatFile(MultipartFile file, String sourceType)
      throws IOException, ParseException {

    final var mapper = transactionsMapperFactory.create(sourceType);
    final var formattedFile = mapper.map(file.getInputStream());

    // TODO: Hacer un try catch aquí
    formattedFile.createFile();

    return new OperationResponseDto(
        new Timestamp(System.currentTimeMillis()),
        "Creación exitosa",
        "El archivo tal fue creado exitosamente");
  }
}
