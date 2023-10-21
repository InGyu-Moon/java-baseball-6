package baseball.controller;

import baseball.Compare;
import baseball.Computer;
import baseball.GameStatus;
import baseball.input.NewGameInput;
import baseball.input.UserInput;

import java.util.List;

public class NumberBaseBallGame {

    Computer computer = new Computer();
    Compare compare = new Compare();
    UserInput user = new UserInput();
    NewGameInput newGame = new NewGameInput();
    private int ball = 0;
    private int strike = 0;

    public void start(){
        int gameStatus = GameStatus.PLAY.status;
        while(gameStatus == GameStatus.PLAY.status){
            gameStatus = playGame();
        }
    }

    public int playGame(){
        //정답 생성
        List<Integer> answer = computer.makeNewNumber();
        //System.out.println("answer = " + answer);

        //user가 정답을 맞출때까지 user 입력, 볼,스트라이크 출력 반복
        while(strike != 3){
            List<Integer> userInput = user.getUserInput();

            //사용자가 입력한 숫자와 컴퓨터가 저장한 숫자를 비교하여 볼과 스트라이크를 구한다.
            ball = getBall(answer, userInput);
            strike = getStrike(answer, userInput);

            //볼과 스트라이크 값을 출력한다.
            printBallAndStrike(ball, strike);
        }

        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");

        //볼, 스트라이크 초기화
        resetBallAndStrike();

        return newGame.newGame();
    }

    private int getStrike(List<Integer> answer, List<Integer> userInput) {
        return compare.getBallAndStrikeData(userInput,answer).getStrike();
    }

    private int getBall(List<Integer> answer, List<Integer> userInput) {
        return compare.getBallAndStrikeData(userInput,answer).getBall();
    }

    private static void printBallAndStrike(int ball, int strike) {
        if(ball != 0){
            System.out.print(ball + "볼 ");
        }
        if(strike != 0){
            System.out.print(strike + "스트라이크");
        }
        if(ball==0 && strike ==0){
            System.out.print("낫싱");
        }
        System.out.println();
    }

    private void resetBallAndStrike() {
        ball=0;
        strike=0;
    }

}
