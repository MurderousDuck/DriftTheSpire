package driftthespire.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import driftthespire.cards.BaseCard;

import static driftthespire.DriftTheSpire.makeID;

public class SecondMagicNumber extends DynamicVariable {

    @Override
    public String key() {
        return makeID("M2");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).isSecondMagicModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).secondMagic;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof BaseCard) {
            ((BaseCard) card).isSecondMagicModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).baseSecondMagic;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof BaseCard) {
            return ((BaseCard) card).upgradeSecondMagic;
        }
        return false;
    }
}