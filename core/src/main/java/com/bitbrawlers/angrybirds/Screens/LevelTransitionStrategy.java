package com.bitbrawlers.angrybirds.Screens;

import com.bitbrawlers.angrybirds.AngryBirds;

public interface LevelTransitionStrategy {
    //Strategy design pattern
    void transitionToLevel(AngryBirds game);
}
