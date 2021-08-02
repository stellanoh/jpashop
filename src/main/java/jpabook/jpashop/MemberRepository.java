package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save (StudyMember member){
        em.persist(member);
        return member.getId();
    }

    public StudyMember find(Long id){
        return em.find(StudyMember.class, id);
    }
}
