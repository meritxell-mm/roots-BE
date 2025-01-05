package com.family.api.service;

import com.family.api.commons.UploadUtils;
import com.family.api.model.File;
import com.family.api.model.Member;
import com.family.api.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void saveFiles(List<MultipartFile> files, List<File> memberFiles, Member savedMember) {
        if (files != null && !files.isEmpty()) {
            for (int i=0; i<files.size();i++) {
                try {
                    String filePath = UploadUtils.saveFileToDisk(files.get(i), "files");
                    File fileEntity = new File();
                    fileEntity.setPath(filePath);
                    fileEntity.setDescription(memberFiles.get(i).getDescription());
                    fileEntity.setMember(savedMember);
                    fileRepository.save(fileEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
