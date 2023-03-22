package driftthespire.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import driftthespire.cards.BaseCard;

import static driftthespire.DriftTheSpire.makeID;

public class SpeedLoss extends DynamicVariable {

    @Override
    public String key() {
        return makeID("SL");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).isSpeedLossModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).speedLoss;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof BaseCard) {
            ((BaseCard) card).isSpeedLossModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).baseSpeedLoss;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).upgradeSpeedLoss;
        }
        return false;
    }
}