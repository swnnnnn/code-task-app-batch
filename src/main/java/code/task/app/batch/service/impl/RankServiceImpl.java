package code.task.app.batch.service.impl;

import code.task.app.batch.model.entity.Member;
import code.task.app.batch.repository.MemberRepository;
import code.task.app.batch.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * RankServiceImpl
 *
 * @author 최상원
 * @version 1.0
 * <pre>
 * 2020.09.01 : 최초 작성
 * </pre>
 * @since 2020.09.01
 */
@RequiredArgsConstructor
@Service
public class RankServiceImpl implements RankService {

    private final MemberRepository memberRepository;

    @Transactional
    public void updateRank() {
        List<Object[]> rankDtoList = memberRepository.selectRankList();
        for(Object[] object : rankDtoList) {
            Optional<Member> exist = memberRepository.findById((String)object[0]); // register
            if (exist.isPresent()) {
                Member member = exist.get();
                member.changeRank(Integer.valueOf(object[1].toString())); // rank
                memberRepository.save(member);
            }
        }
    }
}
