package driftthespire.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static driftthespire.DriftTheSpire.makeID;
import static driftthespire.character.CharacterUtils.doSpeedLoss;
import static driftthespire.character.CharacterVariables.BASE_SPEED_INCREMENT;

public class ReverseGearPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("ReverseGearPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static final int REVERSE_GEAR_AMOUNT = 3;

    public ReverseGearPower(AbstractCreature owner) {
        super(POWER_ID, TYPE, TURN_BASED, owner, -1);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + REVERSE_GEAR_AMOUNT + DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw() {
        doSpeedLoss(REVERSE_GEAR_AMOUNT * BASE_SPEED_INCREMENT);
    }

    //Optional, for CloneablePowerInterface.
    @Override
    public AbstractPower makeCopy() {
        return new ReverseGearPower(owner);
    }
}
