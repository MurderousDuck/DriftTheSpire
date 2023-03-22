package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static driftthespire.DriftTheSpire.makeID;

public class FORMulaOnePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("FORMulaOnePower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public FORMulaOnePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (amount <= 0) {
            removeThisPower();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount * 100 + DESCRIPTIONS[1];
    }

    public int modifySpeed(int speed) {
        return (1 + amount) * speed;
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new FORMulaOnePower(owner, amount);
    }
}
