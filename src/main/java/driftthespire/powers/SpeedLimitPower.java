package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static driftthespire.DriftTheSpire.makeID;

public class SpeedLimitPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("TuningPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public SpeedLimitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + (int)(amount / 4f) + DESCRIPTIONS[2] + (int)(amount / 20f) + DESCRIPTIONS[3];
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new SpeedLimitPower(owner, amount);
    }
}
