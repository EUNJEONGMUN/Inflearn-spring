package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 클라이언트 코드인 OrderServiceImpl은 DiscountPolicy의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존한다.
     * 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다.
     * DIP 위반 -> 추상에만 의존하도록 변경(인터페이스에만 의존)
     * DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존 관계를 변경하면 된다.
     */
    private final DiscountPolicy discountPolicy; //
    // OrderService 입장에서는 discountpolicy에 fixdiscountpolicy가 들어올지, ratediscountpolicy가 들어올지 전혀 알 수 없음
    // 추상에만 의존하고 있음

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 주문 생성 요청이 오면
        Member member = memberRepository.findById(memberId); // 회원정보를 조회하고
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인정책에 회원을 넘김

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
