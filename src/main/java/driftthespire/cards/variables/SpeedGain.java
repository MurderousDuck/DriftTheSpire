package driftthespire.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import driftthespire.cards.BaseCard;

import static driftthespire.DriftTheSpire.makeID;

public class SpeedGain extends DynamicVariable {

    @Override
    public String key() {
        return makeID("SG");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).isSpeedGainModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).speedGain;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof BaseCard) {
            ((BaseCard) card).isSpeedGainModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).baseSpeedGain;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).upgradeSpeedGain;
        }
        return false;
    }
}