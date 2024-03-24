package com.marwanmakm.boto.service;

import com.marwanmakm.boto.dto.OperationResponseDto;
import java.io.IOException;
import java.text.ParseException;
import org.springframework.web.multipart.MultipartFile;

public interface FileFormatterService {

  // Receives a certain file and format it to standard structure.
  OperationResponseDto formatFile(MultipartFile file, String sourceType)
      throws IOException, ParseException;
}
