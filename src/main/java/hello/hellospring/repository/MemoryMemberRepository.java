package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{

    private  static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //ex) auto increment

    @Override
    public Member save(Member member) { //member 받음
        member.setId(++sequence);
        store.put(member.getId(), member); // map에 (1, kojun)식으로 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable 널값이어도 가져올 수 있게
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findByAll() {
        return new ArrayList<>(store.values());
    }

//    public void clearStore() {
//        store.clear();
//    }
}
