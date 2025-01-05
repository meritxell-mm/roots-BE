package com.family.api.service;

import com.family.api.commons.UploadUtils;
import com.family.api.model.Member;
import com.family.api.model.Picture;
import com.family.api.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public void savePictures(List<MultipartFile> pictures, List<Picture> memberPictures, Member member) {
        if (pictures != null && !pictures.isEmpty()) {
            for (int i=0;i<pictures.size();i++) {
                try {
                    String picturePath = UploadUtils.saveFileToDisk(pictures.get(i), "pictures");
                    Picture pictureEntity = new Picture();
                    pictureEntity.setPath(picturePath);
                    pictureEntity.setFooter(memberPictures.get(i).getFooter());
                    pictureEntity = pictureRepository.save(pictureEntity);
                    member.getPictures().add(pictureEntity);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
