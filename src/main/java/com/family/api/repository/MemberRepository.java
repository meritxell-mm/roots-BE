package com.family.api.repository;

import com.family.api.model.Link;
import com.family.api.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Find members with same father (and not me)
    List<Member> findByFatherIdAndIdNot(Long fatherId, Long id);

    // Find members with same mother (and not me)
    List<Member> findByMotherIdAndIdNot(Long motherId, Long id);

    // Buscar fills per pare
    List<Member> findByFatherId(Long fatherId);

    // Buscar fills per mare
    List<Member> findByMotherId(Long motherId);
}