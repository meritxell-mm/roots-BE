package com.family.api.controller;

import com.family.api.model.File;
import com.family.api.model.Link;
import com.family.api.model.Member;
import com.family.api.model.Picture;
import com.family.api.repository.MemberRepository;
import com.family.api.service.FileService;
import com.family.api.service.LinkService;
import com.family.api.service.MemberService;
import com.family.api.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PictureService pictureService;

    // Endpoint GET para obtener todos los miembros
    @GetMapping
    public Iterable<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Member> createMember(
            @RequestBody Member member,
            @RequestParam(value = "pictures", required = false) List<MultipartFile> pictures,
            @RequestParam(value = "files", required = false) List<MultipartFile> files) {

        try {
            // Desar el membre a la base de dades
            Member savedMember = memberRepository.save(member);

            // Gestionar les fotos (pictures)
            pictureService.savePictures(pictures, member.getPictures(), savedMember);

            // Gestionar els fitxers (files)
            fileService.saveFiles(files, member.getFiles(), savedMember);

            // Gestionar els enllaços (links)
            linkService.saveLinks(member.getLinks(), savedMember);

            // Desar el membre amb les fotos, fitxers i enllaços associats
            memberRepository.save(savedMember);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/{id}/siblings")
    public Set<Member> getMemberSiblings(@PathVariable Long id) {
        return memberService.getMemberSiblings(id);
    }

    @GetMapping("/{id}/children")
    public Set<Member> getMemberChildren(@PathVariable Long id) {
        return memberService.getMemberChildren(id);
    }
}
