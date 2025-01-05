package com.family.api.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.family.api.model.Member;
import com.family.api.repository.MemberRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(Long id) {
        // Usamos Optional para evitar un NullPointerException si no se encuentra el miembro
        Optional<Member> member = memberRepository.findById(id);

        // Si el miembro no existe, podemos lanzar una excepción o devolver un valor por defecto
        return member.orElseThrow(() -> new RuntimeException("Member not found: " + id));
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Set<Member> getMemberSiblings(Long id) {
        // Obtenir el membre per ID
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre no trobat"));

        // Obtenir germans pel pare
        // Utilitzar un Set per evitar duplicats (un membre pot tenir germans per la mateixa mare i pare)
        Set<Member> siblings = new HashSet<>();
        if(member.getFather()!=null){
            List<Member> siblingsFromFather = memberRepository.findByFatherIdAndIdNot(member.getFather().getId(), id);
            siblings.addAll(siblingsFromFather);
        }

        // Obtenir germans per la mare
        if(member.getMother()!=null){
            List<Member> siblingsFromMother = memberRepository.findByMotherIdAndIdNot(member.getMother().getId(), id);
            siblings.addAll(siblingsFromMother);
        }

        return siblings;
    }

    // Obtenir els fills d'un membre per ID
    public Set<Member> getMemberChildren(Long id) {
        // Obtenir el membre per ID
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membre no trobat"));

        // Obtenir fills pel pare
        List<Member> childrenFromFather = memberRepository.findByFatherId(member.getId());

        // Obtenir fills per la mare
        List<Member> childrenFromMother = memberRepository.findByMotherId(member.getId());

        // Utilitzar un Set per evitar duplicats
        Set<Member> children = new HashSet<>();
        children.addAll(childrenFromFather);
        children.addAll(childrenFromMother);

        return children;
    }
}
