package org.kpn.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Template method")
public class PatternTemplateMethod {

    private enum GameCode{
        CHESS,
        MONOPOLY
    }

    private static abstract class Game {

        private int playersAmount;

        protected abstract void initializeGame();
        protected abstract void playGame();
        protected abstract void endGame();
        protected abstract void printWinner();

        public final void playOneGame(int playersAmount){
            setPlayersAmount(playersAmount);

            initializeGame();
            playGame();
            endGame();

            printWinner();
        }

        public void setPlayersAmount(int playersAmount){
            this.playersAmount = playersAmount;
        }
    }

    private static class Chess extends Game{
        @Override
        protected void initializeGame() {
            System.out.println("Init chess");
        }

        @Override
        protected void playGame() {
            System.out.println("Play chess");
        }

        @Override
        protected void endGame() {
            System.out.println("End chess");
        }

        @Override
        protected void printWinner() {
            System.out.println("Print chess");
        }
    }

    private static class Monopoly extends Game{
        @Override
        protected void initializeGame() {
            System.out.println("Init monopoly");
        }

        @Override
        protected void playGame() {
            System.out.println("Play monopoly");
        }

        @Override
        protected void endGame() {
            System.out.println("End monopoly");
        }

        @Override
        protected void printWinner() {
            System.out.println("Print monopoly");
        }
    }

    @Test
    void testChess(){
        Chess chess = new Chess();
        chess.playOneGame(2);
    }

    @Test
    void testMonopoly(){
        Monopoly monopoly = new Monopoly();
        monopoly.playOneGame(2);
    }
}
