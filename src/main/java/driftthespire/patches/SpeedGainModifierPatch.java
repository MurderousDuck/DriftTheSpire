package driftthespire.patches;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import driftthespire.cards.BaseCard;
import driftthespire.powers.FORMulaOnePower;

public class SpeedGainModifierPatch {
    @SpirePatch(
            clz= AbstractCard.class,
            method="applyPowers"
    )
    public static class ApplyPowers
    {
        public static void Prefix(AbstractCard __instance)
        {
            if (__instance instanceof BaseCard) {
                BaseCard bcard = (BaseCard) __instance;
                bcard.isSpeedGainModified = false;
                int tmp = bcard.baseSpeedGain;
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    if (p instanceof FORMulaOnePower) {
                        tmp = ((FORMulaOnePower) p).modifySpeed(tmp);
                        if (bcard.baseSpeedGain != MathUtils.floor(tmp)) {
                            bcard.isSpeedGainModified = true;
                        }
                    }
                }
                if (tmp < 0) {
                    tmp = 0;
                }
                bcard.speedGain = MathUtils.floor(tmp);
            }
        }
    }
}

