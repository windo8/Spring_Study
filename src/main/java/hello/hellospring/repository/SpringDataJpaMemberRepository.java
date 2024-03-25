package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Member save(Member member);

    @Override
    Optional<Member> findByName(String name);

    @Override
    Optional<Member> findById(Long Long);

    @Override
    @Query("SELECT m FROM Member m")
    ArrayList<Member> findByAll();

}
