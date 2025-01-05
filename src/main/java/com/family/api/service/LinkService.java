package com.family.api.service;

import com.family.api.model.Link;
import com.family.api.model.Member;
import com.family.api.repository.LinkRepository;
import com.family.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkService {

    @Autowired
    private final LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public void saveLinks(List<Link> links, Member savedMember) {
        if (links != null && !links.isEmpty()) {
            for (Link link : links) {
                link.setMember(savedMember);
                linkRepository.save(link);
            }
        }
    }
}
