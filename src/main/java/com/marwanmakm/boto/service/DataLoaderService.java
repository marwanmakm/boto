package com.marwanmakm.boto.service;

import com.marwanmakm.boto.dto.OperationResponseDto;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface DataLoaderService {

  // Load the database with the data of the given CSV File
  OperationResponseDto load(MultipartFile file) throws IOException;
}
