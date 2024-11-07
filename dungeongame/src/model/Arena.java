package dungeongame.src.model;

import java.util.Random;

public class Arena {
    Player myPlayer;
    AbstractMonster myMonster;

    public Arena(Player thePlayer, AbstractMonster theMonster) {
        myPlayer = thePlayer;
        myMonster = theMonster;

    }

    public void combat(){
        Random rand = new Random();
        boolean playerTurn = ((AbstractDungeonCharacter)myPlayer).canAttack(myMonster.getSpeed());
        while(((AbstractDungeonCharacter)myPlayer).getHealth() != 0 || myMonster.getHealth() != 0){
            int playerMove = 0;
            if(playerTurn){
                //needs a listener to set playerMove
                if(playerMove == 0){
                    myMonster.setHealth(-((AbstractDungeonCharacter)myPlayer).getAttack());
                } else if(playerMove == 1){
                    //Player inventory contains potions increase health
                } else if(playerMove == 2){
                    //special attack
                } else if(playerMove == 3){
                    myMonster.setHealth(-myMonster.getHealth());
                }

            } else {
                if(myMonster.canHeal()){
                    myMonster.setHealth(myMonster.getMaxHealth()/10);
                }

                ((AbstractDungeonCharacter)myPlayer).setHealth(-myMonster.getAttack());

            }

            playerTurn = true ? false : true;
        }
    }






















}
