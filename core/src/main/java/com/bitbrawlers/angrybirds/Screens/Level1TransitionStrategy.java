package com.bitbrawlers.angrybirds.Screens;

import com.bitbrawlers.angrybirds.AngryBirds;

public class Level1TransitionStrategy implements LevelTransitionStrategy {
    //Strategy design pattern
    @Override
    public void transitionToLevel(AngryBirds game) {
        game.setScreen(new Level1(game));
    }
}
