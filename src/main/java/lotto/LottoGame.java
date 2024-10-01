package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private final List<Lotto> purchasedLottos = new ArrayList<>();

    public LottoGame(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
        int lottoCount = money / LOTTO_PRICE;
        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            purchasedLottos.add(new Lotto(numbers));
        }
        System.out.printf("%d개를 구매했습니다.%n", lottoCount);
        purchasedLottos.forEach(lotto -> System.out.println(lotto.getNumbers()));
    }

    public List<Lotto> getPurchasedLottos() {
        return purchasedLottos;
    }

    public void printStatistics(WinningNumbers winningNumbers) {
        int[] rankCounts = new int[Rank.values().length];
        int totalPrize = 0;

        for (Lotto lotto : purchasedLottos) {
            int matchCount = winningNumbers.matchCount(lotto);
            boolean matchBonus = winningNumbers.matchBonus(lotto);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            rankCounts[rank.ordinal()]++;
            totalPrize += rank.getPrize();
        }

        printRankResults(rankCounts);
        printYield(totalPrize);
    }

    private void printRankResults(int[] rankCounts) {
        System.out.printf("3개 일치 (5,000원) - %d개%n", rankCounts[Rank.FIFTH.ordinal()]);
        System.out.printf("4개 일치 (50,000원) - %d개%n", rankCounts[Rank.FOURTH.ordinal()]);
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", rankCounts[Rank.THIRD.ordinal()]);
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", rankCounts[Rank.SECOND.ordinal()]);
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", rankCounts[Rank.FIRST.ordinal()]);
    }

    private void printYield(int totalPrize) {
        double yield = (double) totalPrize / (purchasedLottos.size() * LOTTO_PRICE) * 100;
        System.out.printf("총 수익률은 %.1f%%입니다.%n", yield);
    }
}
