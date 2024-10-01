package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            int money = Integer.parseInt(Console.readLine());

            LottoGame lottoGame = new LottoGame(money);

            System.out.println("당첨 번호를 입력해 주세요.");
            List<Integer> winningNumbers = Arrays.stream(Console.readLine().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            System.out.println("보너스 번호를 입력해 주세요.");
            int bonusNumber = Integer.parseInt(Console.readLine());

            WinningNumbers winningLotto = new WinningNumbers(winningNumbers, bonusNumber);
            lottoGame.printStatistics(winningLotto);

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 잘못된 입력입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

