package com.bitbrawlers.angrybirds.Screens;

import com.bitbrawlers.angrybirds.AngryBirds;

public class Level3TransitionStrategy implements LevelTransitionStrategy {
    //Strategy design pattern
    @Override
    public void transitionToLevel(AngryBirds game) {
        game.setScreen(new Level3(game));
    }
}
