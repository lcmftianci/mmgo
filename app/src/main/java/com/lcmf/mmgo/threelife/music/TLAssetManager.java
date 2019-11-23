package com.lcmf.mmgo.threelife.music;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class TLAssetManager {
    public Sound hitSound;

    public void InitAsset(){
        hitSound = Gdx.audio.newSound(Gdx.files.internal("audio/jump.wav"));
    }

    public void PlayHitMusic(){
        hitSound.play(1);
    }
}
