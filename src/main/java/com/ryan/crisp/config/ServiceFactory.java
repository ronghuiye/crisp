/**
  * Copyright (c) 2018 by Sovos Compliance
 */
package com.ryan.crisp.config;

import com.ryan.crisp.service.FileProcessor;

public interface ServiceFactory {

	FileProcessor getFileProcessor(String fileType);

}
